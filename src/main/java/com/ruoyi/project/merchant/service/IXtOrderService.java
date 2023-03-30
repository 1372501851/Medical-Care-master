package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtOrder;
import com.ruoyi.project.merchant.domain.dto.OrderDto;
import com.ruoyi.project.merchant.domain.vo.OrderVo;

import java.util.List;

/**
 * 订单Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtOrderService
{
    /**
     * 查询订单
     *
     * @param uorderid 订单主键
     * @return 订单
     */
    public XtOrder selectXtOrderByUorderid(String uorderid);

    /**
     * 查询订单列表
     *
     * @param xtOrder 订单
     * @return 订单集合
     */
    public List<XtOrder> selectXtOrderList(XtOrder xtOrder);

    /**
     * 新增订单
     *
     * @param xtOrder 订单
     * @return 结果
     */
    public String insertXtOrder(XtOrder xtOrder);

    /**
     * 修改订单
     *
     * @param xtOrder 订单
     * @return 结果
     */
    public int updateXtOrder(XtOrder xtOrder);

    /**
     * 批量删除订单
     *
     * @param uorderids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteXtOrderByUorderids(String[] uorderids);

    /**
     * 删除订单信息
     *
     * @param uorderid 订单主键
     * @return 结果
     */
    public int deleteXtOrderByUorderid(String uorderid);

    String  insertXtOrderByDto(OrderDto order);

    void paySuccess(String orderId);

    List<OrderVo> selectXtOrderListByUserId(String status,String name);

    void cancleOrder(String orderId);

    void receiveOrder(String orderId);



    Integer countSale(String productId);

    XtOrder selectXtOrderByUorderNo(String outTradeNo);

    int updateOrderStatusAndPaymentStatusByOrderNo(String outTradeNo, String statusCode, String payCode);

    void updateOrderStatusByOrderNo(String orderNo, String code);
}
