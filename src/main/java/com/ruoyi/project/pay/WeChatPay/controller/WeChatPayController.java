package com.ruoyi.project.pay.WeChatPay.controller;

import com.google.gson.Gson;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.pay.WeChatPay.config.WeChatPayConfig;
import com.ruoyi.project.pay.WeChatPay.controller.service.WeChatPayService;
import com.ruoyi.project.pay.WeChatPay.utils.ReadRequestUtil;
import com.ruoyi.project.pay.WeChatPay.utils.ValidateRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 微信支付接口
 * @Author: 小老鼠呀丶
 * @Date: 2022/11/28
 */
@Api(tags = "微信支付")
@RestController
@RequestMapping("/wechatPayApi")
public class WeChatPayController {

    private final WeChatPayService weChatPayService;

    public WeChatPayController(WeChatPayService weChatPayService) {
        this.weChatPayService = weChatPayService;
    }

    @Resource
    private WeChatPayConfig wechatPayConfig;

    @ApiOperation(value = "app支付下单")
    @PostMapping("/appPay/{orderNo}")
    public AjaxResult appPay(
            @PathVariable @ApiParam(value = "商品订单id", required = true) String orderNo
    ) {
        HashMap<String, String> payParam = weChatPayService.appPay(orderNo);
        return AjaxResult.success(payParam);
    }




    @ApiOperation(value = "支付结果通知（微信服务器调用）")
    @PostMapping("/payNotify")
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        Map<String,String> resultMap = new HashMap<>();//应答对象
        try {
            //处理通知参数
            String body = ReadRequestUtil.readData(request);
            HashMap<String, Object> bodyMap = gson.fromJson(body, HashMap.class);
            String requestId = (String)bodyMap.get("id");
            //签名验证
            ValidateRequestUtil validateRequest = new ValidateRequestUtil(wechatPayConfig.getVerifier(), requestId, body);
            if (!validateRequest.validate(request)){
                //验证失败
                response.setStatus(500);
                resultMap.put("code","FAIL");
                resultMap.put("message","通知验签失败");
                return gson.toJson(resultMap);
            }
            //参数解析并处理数据库订单
            weChatPayService.processOrder(bodyMap);
            //成功应答
            response.setStatus(200);
            resultMap.put("code", "SUCCESS");
            resultMap.put("message", "成功");
            return gson.toJson(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            //失败应答
            response.setStatus(500);
            resultMap.put("code", "ERROR");
            resultMap.put("message", "失败");
            return gson.toJson(resultMap);
        }
    }

    @ApiOperation(value = "用户取消订单")
    @PostMapping("/cancel/{orderNo}")
    public AjaxResult cancelOrder(@PathVariable @ApiParam(value = "商户订单号",required = true) String orderNo) throws Exception {
        weChatPayService.cancelOrder(orderNo);
        return AjaxResult.success();
    }

    @ApiOperation(value = "申请退款")
    @PostMapping("/refund/{orderNo}")
    public AjaxResult refundOrder(@PathVariable @ApiParam(value = "商户订单号",required = true) String orderNo) throws Exception {
        weChatPayService.refundOrder(orderNo);
        return AjaxResult.success();
    }

    @ApiOperation(value = "退款结果通知（微信服务器调用）")
    @PostMapping("/refundNotify")
    public String refundNotify(HttpServletRequest request, HttpServletResponse response){
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();//应答对象
        try {
            //处理通知参数
            String body = ReadRequestUtil.readData(request);
            HashMap<String, Object> bodyMap = gson.fromJson(body, HashMap.class);
            String requestId = (String)bodyMap.get("id");//通知ID
            //签名验证
            ValidateRequestUtil validateRequest = new ValidateRequestUtil(wechatPayConfig.getVerifier(), requestId, body);
            if (!validateRequest.validate(request)){
                //验证失败
                response.setStatus(500);
                map.put("code","FAIL");
                map.put("message","通知验签失败");
                return gson.toJson(map);
            }
            //验证成功
            //处理数据库订单
            weChatPayService.processRefund(bodyMap);
            //成功应答
            response.setStatus(200);
            map.put("code", "SUCCESS");
            map.put("message", "成功");
            return gson.toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            //失败应答
            response.setStatus(500);
            map.put("code", "ERROR");
            map.put("message", "失败");
            return gson.toJson(map);
        }
    }


}
