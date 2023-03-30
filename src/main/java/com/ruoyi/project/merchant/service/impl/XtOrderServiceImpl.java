package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtOrder;
import com.ruoyi.project.merchant.domain.XtOrderitem;
import com.ruoyi.project.merchant.domain.XtProduct;
import com.ruoyi.project.merchant.domain.dto.OrderDto;
import com.ruoyi.project.merchant.domain.vo.CartVO;
import com.ruoyi.project.merchant.domain.vo.OrderVo;
import com.ruoyi.project.merchant.mapper.*;
import com.ruoyi.project.merchant.service.IXtOrderService;
import com.ruoyi.project.merchant.service.MallCartService;
import com.ruoyi.project.merchant.util.MallConstant;
import com.ruoyi.project.task.upload.until.StringToURL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtOrderServiceImpl implements IXtOrderService
{
    @Autowired
    private XtOrderMapper xtOrderMapper;


    @Autowired
    private MallCartService cartService;


    @Autowired
    private XtProductMapper productMapper;

    @Autowired
    private XtOrderitemMapper orderItemMapper;

    @Autowired
    private MallCartMapper cartMapper;

    @Autowired
    private XtCompMapper compMapper;
    /**
     * 查询订单
     *
     * @param uorderid 订单主键
     * @return 订单
     */
    @Override
    public XtOrder selectXtOrderByUorderid(String uorderid)
    {
        return xtOrderMapper.selectXtOrderByUorderid(uorderid);
    }

    /**
     * 查询订单列表
     *
     * @param xtOrder 订单
     * @return 订单
     */
    @Override
    public List<XtOrder> selectXtOrderList(XtOrder xtOrder)
    {
        return xtOrderMapper.selectXtOrderList(xtOrder);
    }

    /**
     * 新增订单
     *
     * @param xtOrder 订单
     * @return 结果
     */


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertXtOrder(XtOrder xtOrder)
    {
        //从购物车查找已经勾选的商品
        List<CartVO> cartVOList = cartService.list();
        ArrayList<CartVO> cartVOListTemp = new ArrayList<>();
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            if (cartVO.getSelected().equals(MallConstant.ISCHECKED)){
                cartVOListTemp.add(cartVO);
            }
        }
        cartVOList = cartVOListTemp;

        //如果购物车为空，报错（淘宝提示的是：您还未挑选商品）
        if (CollectionUtils.isEmpty(cartVOList)){
            throw new ServiceException("您还未挑选商品");
        }
        //判断商品是否存在，上下架状态，库存
        validStatusAndStock(cartVOList);
        //把购物车对象转为订单item对象
        List<XtOrderitem> orderItemList = cartVOListToOrderItem(cartVOList);
        //扣库存
        for (int i = 0; i < orderItemList.size(); i++) {
            XtOrderitem orderItem = orderItemList.get(i);
            XtProduct product = productMapper.selectXtProductByUproductid(orderItem.getUproductid());
            int stock = product.getUinventoryQuantity() - orderItem.getUnum();
            if (stock < 0){
                throw new ServiceException("库存不足");
            }
            product.setUinventoryQuantity(stock);
            productMapper.updateXtProduct(product);
        }
        //扣库存成功：把购物车中的已勾选的商品删除
        cleanCart(cartVOList);
        //生成订单
        XtOrder order = new XtOrder();


        order.setUorderid(IdUtil.getSnowflakeNextIdStr());

        BeanUtils.copyProperties(xtOrder,order);
        //生成订单号，有独立的规则
//        String orderNo = OrderCodeFactory.getOrderCode(userId);
        //直接使用雪花算法,不使用自定义的生成规则
        order.setOrderNo(IdUtil.getSnowflakeNextIdStr());
        //插入到Order表
        xtOrderMapper.insertXtOrder(order);
        //循环保存每个商品到order_item表
        for (int i = 0; i <orderItemList.size(); i++) {
            XtOrderitem orderItem = orderItemList.get(i);
            orderItem.setUorderid(order.getUorderid());
            orderItemMapper.insertXtOrderitem(orderItem);
        }

        //把结果返回
        return order.getOrderNo();

    }

    private void validStatusAndStock(List<CartVO> cartVOList){
        for (int i = 0; i <cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            XtProduct product = productMapper.selectXtProductByUproductid(cartVO.getProductId());
            //判断商品是否存在，商品是否上架
            if (product == null || product.getStatus().equals(MallConstant.NOTSALE)){
                throw new ServiceException("该商品不可售卖");
            }
            //判断商品库存
            if (cartVO.getQuantity() > product.getUinventoryQuantity()){
                throw  new ServiceException("该商品库存不足");
            }

        }
    }
    private  void cleanCart(List<CartVO> cartVOList){
        for (int i = 0; i < cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            cartMapper.deleteByPrimaryKey(cartVO.getId());
        }
    }


    /**计算总价*/
    private BigDecimal totalPrice(List<XtOrderitem> orderItemList){
        BigDecimal totalPrice = new BigDecimal(0);
        for (int i = 0; i <orderItemList.size(); i++) {
            XtOrderitem orderItem = orderItemList.get(i);

            BigDecimal quantity = new BigDecimal(orderItem.getUnum());
            totalPrice =  totalPrice.add(orderItem.getUprice().multiply(quantity));
        }
        return totalPrice;
    }

    private List<XtOrderitem>  cartVOListToOrderItem(List<CartVO> cartVOList){
        List<XtOrderitem> oderItemList = new ArrayList<>();
        for (int i = 0; i <cartVOList.size(); i++) {
            CartVO cartVO = cartVOList.get(i);
            XtOrderitem orderItem = new XtOrderitem();
            orderItem.setUproductid(cartVO.getProductId());
            //记录商品快照信息
            orderItem.setUproductPic(cartVO.getProductImage());
            orderItem.setUproductName(cartVO.getProductName());
            orderItem.setUprice(cartVO.getPrice());
            orderItem.setUnum(cartVO.getQuantity());
            orderItem.setTotalPrice(cartVO.getTotalPrice());
            oderItemList.add(orderItem);
        }
        return oderItemList;

    }
    /**
     * 修改订单
     *
     * @param xtOrder 订单
     * @return 结果
     */
    @Override
    public int updateXtOrder(XtOrder xtOrder)
    {
        return xtOrderMapper.updateXtOrder(xtOrder);
    }

    /**
     * 批量删除订单
     *
     * @param uorderids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteXtOrderByUorderids(String[] uorderids)
    {
        return xtOrderMapper.deleteXtOrderByUorderids(uorderids);
    }

    /**
     * 删除订单信息
     *
     * @param uorderid 订单主键
     * @return 结果
     */
    @Override
    public int deleteXtOrderByUorderid(String uorderid)
    {
        return xtOrderMapper.deleteXtOrderByUorderid(uorderid);
    }

    @Override
    public String insertXtOrderByDto(OrderDto order) {

        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();


        //判断该商品存存是否充足
        validStatusAndStock(order.getProductId(),order.getProductNum());




        //将这些信息赋值给order表
        XtOrder xtOrder  = new XtOrder();
        BeanUtils.copyProperties(order,xtOrder);
        xtOrder.setUserid(userid);

        //设置商家信息
        XtComp xtComp = compMapper.selectXtCompByUcompid(order.getUcompid());
        xtOrder.setUcompid(xtComp.getUcompid());
        xtOrder.setUcompname(xtComp.getUcompname());

        //设置订单id
        xtOrder.setUorderid(IdUtil.getSnowflakeNextIdStr());

        //设置支付状态(默认为未支付0,1为已支付)
        xtOrder.setUstatus(MallConstant.ORDER_UNPAID);


        //设置订单创建时间
        xtOrder.setUcreatedate(new Date());

        //计算商品总价
        XtProduct product = productMapper.selectXtProductByUproductid(order.getProductId());
        //单价
        BigDecimal uretailPrice = product.getUretailPrice();
        //总价
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal quantity = new BigDecimal(order.getProductNum());
        totalPrice =  totalPrice.add(uretailPrice.multiply(quantity));
        xtOrder.setUproductName(product.getUproductName());
        xtOrder.setUtotalprice(totalPrice);

        //设置订单号
        xtOrder.setOrderNo(IdUtil.getSnowflakeNextIdStr());

        //插入数据库
        xtOrderMapper.insertXtOrder(xtOrder);

        return xtOrder.getOrderNo();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void paySuccess(String orderId) {

        XtOrder xtOrder = xtOrderMapper.selectXtOrderByUorderid(orderId);
        String productId = xtOrder.getProductId();
        Integer productNum = xtOrder.getProductNum();
        XtProduct product = productMapper.selectXtProductByUproductid(productId);

        if (productNum > product.getUinventoryQuantity()){
            throw  new ServiceException("该商品库存不足");
        }

        if (product == null || product.getStatus().equals(MallConstant.NOTSALE)){
            throw new ServiceException("该商品不可售卖");
        }

        //支付成功扣减库存
        product.setUinventoryQuantity(product.getUinventoryQuantity() - productNum);

        //更新订单信息

        //支付时间
        xtOrder.setUpaymentTime(new Date());
        //支付状态
        xtOrder.setUstatus(MallConstant.ORDER_RECEIVE);

    }

    @Override
    public List<OrderVo> selectXtOrderListByUserId(String status, String name) {

        List<OrderVo> orderVos = new ArrayList<>();
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtOrder order = new XtOrder();
        order.setUserid(userid);
        order.setUstatus(status);
        List<XtOrder> xtOrders = xtOrderMapper.selectOrderListByStatusAndName(userid, status, name);
//        List<XtOrder> xtOrders = xtOrderMapper.selectXtOrderList(order);
        for (int i = 0; i < xtOrders.size(); i++) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(xtOrders.get(i),orderVo);
            //查询该商品
            String productId = xtOrders.get(i).getProductId();
            XtProduct product = productMapper.selectXtProductByUproductid(productId);
            String path = product.getUproductPic();
            List<String> strings = StringToURL.toURL(path);
            orderVo.setUproductPic(strings.get(0));
            orderVo.setUproductName(product.getUproductName());
            orderVo.setUretailPrice(product.getUretailPrice());
            if (xtOrders.get(i).getUstatus().equals(MallConstant.ORDER_UNPAID)){
                orderVo.setStatusName("待付款");
            }else if(xtOrders.get(i).getUstatus().equals(MallConstant.ORDER_RECEIVE)){
                orderVo.setStatusName("待收货");
            }else if(xtOrders.get(i).getUstatus().equals(MallConstant.ORDER_CANCLE)){
                orderVo.setStatusName("已取消");
            } else if (xtOrders.get(i).getUstatus().equals(MallConstant.ORDER_NOT_COMMENT)) {
                orderVo.setStatusName("待评论");
            }
            orderVos.add(orderVo);
        }

        return orderVos;
    }

    @Override
    public void cancleOrder(String orderId) {
        XtOrder order = new XtOrder();
        order.setUorderid(orderId);
        order.setUstatus(MallConstant.ORDER_CANCLE);
        int i = xtOrderMapper.updateXtOrder(order);
        if (i == 0){
            throw new ServiceException("操作失败");
        }
    }

    @Override
    public void receiveOrder(String orderId) {
        XtOrder order = new XtOrder();
        order.setUorderid(orderId);
        order.setUstatus(MallConstant.ORDER_NOT_COMMENT);
        int i = xtOrderMapper.updateXtOrder(order);
        if (i == 0){
            throw new ServiceException("操作失败");
        }
    }

    @Override
    public Integer countSale(String productId) {
        Integer count = 0;
        XtOrder order = new XtOrder();
        order.setProductId(productId);
        List<XtOrder> xtOrders = xtOrderMapper.selectXtOrderList(order);
        for (int i = 0; i < xtOrders.size(); i++) {
            Integer productNum = xtOrders.get(i).getProductNum();
            count = count + productNum;
        }

        return count;

    }

    @Override
    public XtOrder selectXtOrderByUorderNo(String outTradeNo) {
        XtOrder order = xtOrderMapper.selectXtOrderByUorderNo(outTradeNo);
        return order;
    }

    @Override
    public int updateOrderStatusAndPaymentStatusByOrderNo(String outTradeNo, String statusCode, String payCode) {
        int count = xtOrderMapper.updateOrderStatusAndPaymentStatusByOrderNo(outTradeNo, statusCode, payCode);
        return count;
    }

    @Override
    public void updateOrderStatusByOrderNo(String orderNo, String code) {
        xtOrderMapper.updateOrderStatusByOrderNo(orderNo,code);
    }

    private  void validStatusAndStock(String productId,Integer num){
        XtProduct product = productMapper.selectXtProductByUproductid(productId);
        //判断商品是否存在，商品是否上架
        if (product == null || product.getStatus().equals(MallConstant.NOTSALE)){
            throw new ServiceException("该商品不可售卖");
        }
        //判断商品库存
        if (num > product.getUinventoryQuantity()){
            throw  new ServiceException("该商品库存不足");
        }
    }
}
