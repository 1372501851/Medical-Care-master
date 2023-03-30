package com.ruoyi.project.pay.WeChatPay.controller.service;


import com.google.gson.Gson;
import com.ruoyi.project.merchant.domain.XtOrder;
import com.ruoyi.project.merchant.mapper.XtOrderMapper;
import com.ruoyi.project.merchant.service.IXtOrderService;
//import com.ruoyi.project.pay.PaymentStatusEnum;
import com.ruoyi.project.pay.Enum.PaymentStatusEnum;
import com.ruoyi.project.pay.WeChatPay.config.WeChatPayConfig;
import com.ruoyi.project.pay.WeChatPay.enums.NotifyType;
import com.ruoyi.project.pay.WeChatPay.utils.OrderNoUtil;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2022/11/29
 */
@Slf4j
@Service
public class WeChatPayService {

    private final ReentrantLock lock = new ReentrantLock();

    @Resource
    private WeChatPayConfig wechatPayConfig;

    @Resource
    private CloseableHttpClient wxPayClient;

    @Autowired
    private IXtOrderService orderService;

    @Autowired
    private XtOrderMapper orderMapper;

    /**
     * app支付下单
     */
    public HashMap<String, String> appPay(String productOrderId) {
        //查询订单
        XtOrder order = orderService.selectXtOrderByUorderNo(productOrderId);
        //调用统一下单api
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/app");//请求URL
        //请求body参数
        //订单金额
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("total", order.getUtotalprice().multiply(new BigDecimal(100)).intValue());//总金额(单位分)
        amountMap.put("currency", "CNY");//货币类型
        //所有请求参数
        Map<Object, Object> map = new HashMap<>();
        map.put("appid", wechatPayConfig.appId);//应用ID
        map.put("mchid", wechatPayConfig.mchId);//直连商户号
        map.put("description", order.getUproductName());//商品描述
        map.put("out_trade_no",order.getOrderNo());//商户订单号
        map.put("notify_url", wechatPayConfig.notifyUrl.concat(NotifyType.PAY_NOTIFY.getType()));//通知地址
        map.put("amount", amountMap);//订单金额
        //将参数转换为json字符串
        Gson gson = new Gson();
        String requestData = gson.toJson(map);
        StringEntity entity = new StringEntity(requestData, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        //完成签名并执行请求
        String body;//返回结果
        try (CloseableHttpResponse response = wxPayClient.execute(httpPost)) {
            body = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();//返回状态码
            if (statusCode == 200) {
                log.info("下单成功，返回结果：" + body);
            } else if (statusCode == 204) {
                log.info("下单成功");
            } else {
                log.info("下单失败，响应码：" + statusCode + "，返回结果：" + body);
                throw new IOException("微信app支付下单请求失败");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //返回前端参数
        HashMap<String, String> resultMap = gson.fromJson(body, HashMap.class);
        resultMap.put("appid", wechatPayConfig.appId);//应用ID
        resultMap.put("partnerid", wechatPayConfig.mchId);//商户号
        resultMap.put("prepayid", resultMap.get("prepay_id"));//预支付交易会话ID
        resultMap.put("package", "Sign=WXPay");//订单详情扩展字符串
        resultMap.put("noncestr", UUID.randomUUID().toString().replace("-", ""));//随机字符串
        resultMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));//时间戳
        //构造签名串
        List<String> param = new ArrayList<>();
        param.add(wechatPayConfig.appId);
        param.add(resultMap.get("timestamp"));
        param.add(resultMap.get("noncestr"));
        param.add(resultMap.get("prepayid"));
        String sign = this.createPaySign(param);
        resultMap.put("sign", sign);//签名
        return resultMap;
    }

    /**
     * 构造签名串
     */
    public String createPaySign(List<String> param) {
        //构造签名串
        StringBuilder signString = new StringBuilder();
        for (String str : param) {
            signString.append(str).append("\n");
        }
        //对结果进行base64编码
        Signature sign;
        byte[] sign1 = null;
        try {
            sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(wechatPayConfig.getPRIVATE_KEY());
            sign.update(signString.toString().getBytes(StandardCharsets.UTF_8));
            sign1 = sign.sign();
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(sign1);
    }

    /**
     * 处理数据库订单
     */
    public void processOrder(HashMap<String, Object> bodyMap) throws GeneralSecurityException {
        //通知密文解密
        String plainText = decryptFromResource(bodyMap);
        //将明文转为map
        Gson gson = new Gson();
        HashMap plainTextMap = gson.fromJson(plainText, HashMap.class);
        String orderNo = (String) plainTextMap.get("out_trade_no");
        //加锁，解决并发
        if (lock.tryLock()){
            try {
                //查询订单支付状态
                //查询订单
                XtOrder order = orderService.selectXtOrderByUorderNo(orderNo);
                String paymentStatus = order.getUstatus();

                if(!PaymentStatusEnum.NotPay.getCode().equals(paymentStatus)){
                    return;
                }
                //支付完成修改订单状态
//                orderMapper.updateOrderStatusAndPaymentStatusByOrderNo(
//                        orderNo,PaymentStatusEnum.SuccessPay.getCode(),
//                );
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 对称解密
     */
    private String decryptFromResource(HashMap<String, Object> bodyMap) throws GeneralSecurityException {
        //通知数据
        Map<String, String> resourceMap = (Map) bodyMap.get("resource");
        //数据密文
        String ciphertext = resourceMap.get("ciphertext");
        //随机串
        String nonce = resourceMap.get("nonce");
        //附加数据
        String associatedData = resourceMap.get("associated_data");
        //解析并返回明文
        AesUtil aesUtil = new AesUtil(wechatPayConfig.apiV3Key.getBytes(StandardCharsets.UTF_8));
        return aesUtil.decryptToString(associatedData.getBytes(StandardCharsets.UTF_8), nonce.getBytes(StandardCharsets.UTF_8), ciphertext);
    }

    /**
     * 用户取消订单
     * @param orderNo 商户订单编号
     */
    public void cancelOrder(String orderNo) throws Exception {
        log.info("用户取消订单,订单编号: {}",orderNo);
        //调用微信支付的关单接口
        this.closeOrder(orderNo);
        //更新数据库的订单状态
//        xtProductOrderMapper.updateOrderStatusByOrderNo(orderNo,OrderStatusEnum.Cancelled.getStatus());
    }

    /**
     * 关闭订单
     * @param orderNo 商户订单编号
     */
    private void closeOrder(String orderNo) throws Exception {
        //创建远程请求对象
        String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + orderNo + "/close";
        HttpPost httpPost = new HttpPost(url);
        //组装json请求体
        Gson gson = new Gson();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("mchid", wechatPayConfig.mchId);
        String jsonParams = gson.toJson(paramsMap);
        //将请求参数设置到请求对象中
        StringEntity entity = new StringEntity(jsonParams,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        //完成签名并执行请求
        try (CloseableHttpResponse response = wxPayClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();//响应状态码
            if (statusCode == 200) { //处理成功
                log.info("成功200");
            } else if (statusCode == 204) { //处理成功，无返回Body
                log.info("成功204");
            } else {
                log.info("关单失败,响应码 = " + statusCode);
                throw new IOException("关单请求失败");
            }
        }
    }

    /**
     * 申请退款
     * @param orderNo 商户订单编号
     */
    public void refundOrder(String orderNo) throws Exception {
        log.info("申请退款,商户订单编号:{}",orderNo);
        //生成退款单号
        String refundNo = OrderNoUtil.getRefundNo();
        XtOrder order = orderMapper.selectXtOrderByUorderNo(orderNo);
        //调用统一下单API
        String url = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";
        HttpPost httpPost = new HttpPost(url);
        //请求body参数
        //金额信息
        Map<String,Object> amountMap = new HashMap<>();
        amountMap.put("refund", order.getUtotalprice().multiply(new BigDecimal(100)).intValue());//退款金额
        amountMap.put("total", order.getUtotalprice().multiply(new BigDecimal(100)).intValue());//原订单金额
        amountMap.put("currency", "CNY");//退款币种
        //所有参数
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("out_trade_no", orderNo);//商户订单号
        paramsMap.put("out_refund_no", refundNo);//商户退款单号
        paramsMap.put("notify_url", wechatPayConfig.notifyUrl.concat(NotifyType.REFUND_NOTIFY.getType()));//退款结果回调url
        paramsMap.put("amount", amountMap);//金额信息
        //将参数转换成json字符串
        Gson gson = new Gson();
        String jsonParams = gson.toJson(paramsMap);
        StringEntity entity = new StringEntity(jsonParams,"utf-8");
        entity.setContentType("application/json");//设置请求报文格式
        httpPost.setEntity(entity);//将请求报文放入请求对象
        httpPost.setHeader("Accept", "application/json");//设置响应报文格式
        //完成签名并执行请求，并完成验签
        try (CloseableHttpResponse response = wxPayClient.execute(httpPost)) {
            //解析响应结果
            String bodyAsString = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                log.info("成功, 退款返回结果 = " + bodyAsString);
            } else if (statusCode == 204) {
                log.info("成功");
            } else {
                throw new RuntimeException("退款异常, 响应码 = " + statusCode + ", 退款返回结果 = " + bodyAsString);
            }
            //申请退款成功（申请成功，不是退款成功）
            //更新数据库订单状态为退款中，等查询微信服务器退款信息后修改为已退款
//            xtProductOrderMapper.updateOrderStatusByOrderNo(orderNo, OrderStatusEnum.Refunding.getStatus());
        }
    }

    /**
     * 处理退款
     */
    public void processRefund(HashMap<String, Object> bodyMap) throws Exception {
        //解密报文
        String plainText = decryptFromResource(bodyMap);
        //将明文转换成map
        Gson gson = new Gson();
        HashMap plainTextMap = gson.fromJson(plainText, HashMap.class);
        String orderNo = (String)plainTextMap.get("out_trade_no");
        if(lock.tryLock()){

            //这里需要给订单加一个字段,记录订单的状态,(不是支付状态)
//            try {
//                //获取订单状态，若状态为退款成功则表示已经接收到退款通知并处理，（幂等性：处理重复通知）
//                String orderStatus = xtProductOrderMapper.getOrderStatusByOrderNo(orderNo);
//                if (!OrderStatusEnum.Refunding.getStatus().equals(orderStatus)) {
//                    return;
//                }
//                //更新订单状态
//                xtProductOrderMapper.updateOrderStatusByOrderNo(orderNo, OrderStatusEnum.RefundSuccess.getStatus());
//            } finally {
//                //要主动释放锁
//                lock.unlock();
//            }
        }
    }

}
