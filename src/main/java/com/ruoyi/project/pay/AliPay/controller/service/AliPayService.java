package com.ruoyi.project.pay.AliPay.controller.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.merchant.domain.XtOrder;
import com.ruoyi.project.merchant.mapper.XtOrderMapper;
import com.ruoyi.project.merchant.service.IXtOrderService;
import com.ruoyi.project.pay.AliPay.config.AliPayConfig;
//import com.ruoyi.project.pay.OrderStatusEnum;
//import com.ruoyi.project.pay.PaymentStatusEnum;
import com.ruoyi.project.pay.Enum.OrderStatusEnum;
import com.ruoyi.project.pay.Enum.PaymentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2022/12/5
 */
@Service
public class AliPayService {


    //定义锁
    private final ReentrantLock lock = new ReentrantLock();

    @Resource
    private AliPayConfig aliPayConfig;

    @Resource
    private AlipayClient alipayClient;

    @Autowired
    private IXtOrderService orderService;

    @Autowired
    private XtOrderMapper orderMapper;


    /**
     * app支付下单
     */
    public String appPay(String orderNo) {
        try {
            //查询订单
            XtOrder order = orderService.selectXtOrderByUorderNo(orderNo);
            //调用支付宝接口
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //设置请求参数
            //回调通知地址
            request.setNotifyUrl(aliPayConfig.notifyUrl);
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", order.getOrderNo());
            //total_amount:为订单总金额
            bizContent.put("total_amount", order.getUtotalprice().toString());
            bizContent.put("subject", order.getUproductName());
            //bizContent.put("time_expire", "2022-08-01 22:00:00");

            //// 商品明细信息，按需传入
            //JSONArray goodsDetail = new JSONArray();
            //JSONObject goods1 = new JSONObject();
            //goods1.put("goods_id", "goodsNo1");
            //goods1.put("goods_name", "子商品1");
            //goods1.put("quantity", 1);
            //goods1.put("price", 0.01);
            //goodsDetail.add(goods1);
            //bizContent.put("goods_detail", goodsDetail);

            //// 扩展信息，按需传入
            //JSONObject extendParams = new JSONObject();
            //extendParams.put("sys_service_provider_id", "2088511833207846");
            //bizContent.put("extend_params", extendParams);

            request.setBizContent(bizContent.toString());
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if(response.isSuccess()){
                //调用成功
                return response.getBody();
            } else {
                //调用失败
                throw new RuntimeException("支付宝创建支付交易失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("支付宝创建支付交易失败");
        }
    }

    /**
     * 处理商户订单
     */
    public void processOrder(Map<String, String> params) {
        //获取商户订单号
        String outTradeNo = params.get("out_trade_no");
        //加锁，解决并发
        if (lock.tryLock()) {
            try {
                //查询订单支付状态
                XtOrder order = orderService.selectXtOrderByUorderNo(outTradeNo);
                String paymentStatus = order.getUstatus();

                if(!PaymentStatusEnum.NotPay.getCode().equals(paymentStatus)){
                    return;
                }
                //修改订单状态
                orderService.updateOrderStatusAndPaymentStatusByOrderNo(outTradeNo, OrderStatusEnum.WaitForDelivery.getCode(), PaymentStatusEnum.SuccessPay.getCode());
            } finally {
                //lock必须手动释放锁,避免死锁的发生
                lock.unlock();
            }
        }
    }

    /**
     * 取消订单
     */
    public void cancelOrder(String orderNo) {
        //调用支付宝支付的关单接口
        this.closeOrder(orderNo);
        //更新商户订单状态
        orderService.updateOrderStatusByOrderNo(orderNo,OrderStatusEnum.Cancelled.getCode());
    }

    /**
     * 关闭订单
     * @param orderNo 商户订单编号
     */
    private void closeOrder(String orderNo) {
        try {
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            request.setBizContent(bizContent.toString());
            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if(!response.isSuccess()){
                throw new ServiceException("支付宝订单关闭失败");
            }
        } catch (AlipayApiException e) {
            throw new ServiceException("支付宝订单关闭失败");
        }
    }

    /**
     * 申请退款
     * @param orderNo 商户订单号
     */
    public void refundOrder(String orderNo) {
        //查询订单
        XtOrder order = orderMapper.selectXtOrderByUorderNo(orderNo);
        try {
            //调用退款接口
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            bizContent.put("refund_amount",order.getProductNum().toString());
            request.setBizContent(bizContent.toString());
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                //退款成功
                orderService.updateOrderStatusByOrderNo(orderNo,OrderStatusEnum.RefundSuccess.getCode());
            }
        } catch (AlipayApiException e) {
            throw new ServiceException("支付宝退款异常");
        }
    }


}
