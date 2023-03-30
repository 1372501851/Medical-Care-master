package com.ruoyi.project.pay.AliPay.config;

import com.alipay.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2022/12/5
 */
@Configuration
public class AliPayConfig {

    @Value("${pay.aliPay.app-id}")
    public String appId;

    @Value("${pay.aliPay.seller-id}")
    public String sellerId;

    @Value("${pay.aliPay.mch-private-key}")
    public String mchPrivateKey;

    @Value("${pay.aliPay.ali-public-key}")
    public String aliPublicKey;

    @Value("${pay.aliPay.gateway-url}")
    public String gatewayUrl;

    @Value("${pay.aliPay.notify-url}")
    public String notifyUrl;

    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(gatewayUrl);
        alipayConfig.setAppId(appId);
        alipayConfig.setPrivateKey(mchPrivateKey);
        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
        alipayConfig.setAlipayPublicKey(aliPublicKey);
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
        //构造client
        return new DefaultAlipayClient(alipayConfig);
    }
}
