package com.ruoyi.project.merchant.service.impl;


import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.merchant.domain.MallCart;
import com.ruoyi.project.merchant.domain.XtProduct;
import com.ruoyi.project.merchant.domain.vo.CartVO;
import com.ruoyi.project.merchant.mapper.MallCartMapper;
import com.ruoyi.project.merchant.mapper.XtProductMapper;
import com.ruoyi.project.merchant.service.MallCartService;
import com.ruoyi.project.merchant.util.MallConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class MallCartServiceImpl implements MallCartService {

    @Autowired
    private MallCartMapper cartMapper;

    @Autowired
    private XtProductMapper productMapper;

    @Override
    public List<CartVO> list() {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        List<MallCart> mallCarts = cartMapper.selectList(userId);

        List<CartVO>  cartVOList = new ArrayList<>();
        for (int i = 0; i < mallCarts.size(); i++) {
            CartVO cartVO = new CartVO();
            BeanUtils.copyProperties(mallCarts.get(i),cartVO);

            BigDecimal quantity = new BigDecimal(cartVO.getQuantity());

//                BigDecimal price = cartVO.getPrice();
//                BigDecimal multiply = price.multiply(quantity);

//                cartVO.setTotalPrice(BigDecimal.valueOf(999));



             String productId = mallCarts.get(i).getProductId();
            XtProduct xtProduct = productMapper.selectXtProductByUproductid(productId);

            cartVO.setProductName(xtProduct.getUtradeName());
            cartVO.setProductImage(xtProduct.getUproductPic());
            BigDecimal uoutsourcingPrice = xtProduct.getUoutsourcingPrice();
            BigDecimal multiply = uoutsourcingPrice.multiply(quantity);
            cartVO.setTotalPrice(multiply);
            cartVOList.add(cartVO);
        }

        return cartVOList;
    }


    @Override
    public void add(String productId, Integer count) {

        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        validProduct(productId,count);
        //如果商品不在购物车中
        MallCart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null){
            //这个商品之前不在购物车里，需要新增一个记录
            cart = new MallCart();
            cart.setId(IdUtil.getSnowflakeNextIdStr());
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setQuantity(count );
            cart.setSelected(MallConstant.NOTCHECKED);
            cart.setCreateTime(new Date());
            cartMapper.insertSelective(cart);
        }else{
            //这个商品已经在购物车里了，则数量相加
            count = cart.getQuantity() + count;
            MallCart cartNew = new MallCart();
            cartNew.setQuantity(count);
            cartNew.setId(cart.getId());
            cartNew.setProductId(cart.getProductId());
            cartNew.setUserId(cart.getUserId());
            cartNew.setSelected(MallConstant.NOTCHECKED);
            cart.setUpdateTime(new Date());
            cartMapper.updateByPrimaryKeySelective(cartNew);
        }

    }


    /**验证这次添加是否合法*/

    private void validProduct(String productId, Integer count){
        XtProduct product = productMapper.selectXtProductByUproductid(productId);
        //判断商品是否存在，商品是否上架
        if (product == null || product.getStatus().equals(MallConstant.NOTSALE)){
            throw new ServiceException("该商品不可售卖.");
        }

        //判断商品库存
        if (count > product.getUinventoryQuantity()){
            throw  new ServiceException("商品库存不足");
        }

    }

    @Override
    public void update(String productId, Integer count) {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        //如果商品不在购物车中
        MallCart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null){
            //这个商品之前不在购物车里,更新失败
            throw new ServiceException("操作失败");
        }else{
            //这个商品已经在购物车里了，则数量相加
            MallCart cartNew = new MallCart();
            cartNew.setQuantity(count);
            cartNew.setId(cart.getId());
            cartNew.setProductId(cart.getProductId());
            cartNew.setUserId(cart.getUserId());
            cartNew.setSelected(MallConstant.NOTCHECKED);
            cartMapper.updateByPrimaryKeySelective(cartNew);
        }

    }

    @Override
    public void delete(String productId) {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        //如果商品不在购物车中
        MallCart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null){
            //这个商品之前不在购物车里,删除失败
            throw new ServiceException("操作失败");
        }else{
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
    }

    @Override
    public void updateSelectedOne(String productId, Integer selected) {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        MallCart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (cart == null){
            //这个商品之前不在购物车里,删除失败
            throw new ServiceException("操作失败");
        }else{
            int i = cartMapper.updateSelectedOne(userId, productId, selected);
            if (i == 0){
                throw new ServiceException("操作失败");
            }
        }
    }

    @Override
    public void updateSelectedAll(Integer selected) {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        cartMapper.updateSelectedAll(userId, selected);
    }
}




