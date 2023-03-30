package com.ruoyi.project.pay.AliPay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtOrder;
import com.ruoyi.project.merchant.service.IXtOrderService;
import com.ruoyi.project.pay.AliPay.config.AliPayConfig;
import com.ruoyi.project.pay.AliPay.controller.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.BigDecimal;

import java.util.Map;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2022/12/5
 */
@Slf4j
@Api(tags = "支付宝支付")
@RestController
@RequestMapping("/aliPayApi")
public class AliPayController {

    private final AliPayService aliPayService;

    public AliPayController(AliPayService aliPayService) {
        this.aliPayService = aliPayService;
    }

    @Resource
    private AliPayConfig aliPayConfig;


    @Autowired
    private IXtOrderService orderService;

    @ApiOperation(value = "app支付下单")
    @PostMapping("/appPay/{orderNo}")
    public AjaxResult appPay(@PathVariable @ApiParam(value = "订单No", required = true) String orderNo) {
        log.info("app支付下单,订单编号NO: {}", orderNo);
        String orderStr = aliPayService.appPay(orderNo);
        return AjaxResult.success();
    }

    @ApiOperation(value = "支付结果异步通知（支付宝服务器调用）")
    @PostMapping("/payNotify")
    public String payNotify(@RequestParam Map<String, String> params) {
        String result = "fail";
        //调用SDK验证签名
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPayConfig.aliPublicKey, AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
            if (!signVerified) {
                //验签失败
                return result;
            }
            //验签成功
            //验证参数
            //商户订单号
            String outTradeNo = params.get("out_trade_no");
            XtOrder order = orderService.selectXtOrderByUorderNo(outTradeNo);
            if (order == null) {
                return result;
            }
            //订单总金额
            String totalAmount = params.get("total_amount");
            BigDecimal bigDecimal = new BigDecimal(totalAmount);
            if (!bigDecimal.equals(order.getUtotalprice())) {
                return result;
            }

            //固有参数
            //商户id
            String sellerId = params.get("seller_id");
            if (!sellerId.equals(aliPayConfig.sellerId)) {
                return result;
            }
            String appId = params.get("app_id");
            if (!appId.equals(aliPayConfig.appId)) {
                return result;
            }
            //验证完毕，判断交易状态
            String tradeStatus = params.get("trade_status");
            if (!tradeStatus.equals("TRADE_SUCCESS")) {
                return result;
            }
            //支付成功，处理商户订单
            aliPayService.processOrder(params);
            result = "success";
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "取消订单")
    @PostMapping("/cancel/{orderNo}")
    public AjaxResult cancelOrder(@PathVariable @ApiParam(value = "商户订单号",required = true) String orderNo){
        log.info("取消订单: {}",orderNo);
        aliPayService.cancelOrder(orderNo);
        return AjaxResult.success();
    }

    @ApiOperation(value = "申请退款")
    @PostMapping("/refund/{orderNo}")
    public AjaxResult refundOrder(@PathVariable @ApiParam(value = "商户订单号",required = true) String orderNo) {
        log.info("申请退款: {}",orderNo);
        aliPayService.refundOrder(orderNo);
        return AjaxResult.success();
    }


}
