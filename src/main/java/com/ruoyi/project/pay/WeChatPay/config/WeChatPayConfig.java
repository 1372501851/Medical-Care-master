package com.ruoyi.project.pay.WeChatPay.config;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2022/11/25
 */
@Configuration
public class WeChatPayConfig {

    @Value("${pay.wechatPay.app-id}")
    public String appId;

    @Value("${pay.wechatPay.api-v3-key}")
    public String apiV3Key;

    @Value("${pay.wechatPay.mch-id}")
    public String mchId;

    @Value("${pay.wechatPay.mch-serial-no}")
    public String mchSerialNo;

    @Value("${pay.wechatPay.mch-private-key}")
    private String mchPrivateKey;

    @Value("${pay.wechatPay.notify-domain}")
    public String notifyUrl;//接收通知地址

    //获取商户私钥
    public PrivateKey getPRIVATE_KEY() {
        PrivateKey privateKey = null;
        try {
            privateKey = PemUtil.loadPrivateKey(new FileInputStream(mchPrivateKey));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    //获取签名验证器
    @Bean
    public Verifier getVerifier() throws GeneralSecurityException, IOException, HttpCodeException, NotFoundException {
        //获取商户私钥
        PrivateKey mchPrivateKey = this.getPRIVATE_KEY();
        //获取私钥签名对象
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(mchSerialNo, mchPrivateKey);
        //身份认证对象
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(mchId, privateKeySigner);
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(mchId, wechatPay2Credentials, apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier签名验证器
        return certificatesManager.getVerifier(mchId);
    }

    //获取带签名的Http请求客户端对象
    @Bean
    public CloseableHttpClient getWxPayClient(Verifier verifier) {
        //获取商户私钥
        PrivateKey mchPrivateKey = this.getPRIVATE_KEY();

        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, mchPrivateKey)
                .withValidator(new WechatPay2Validator(verifier));

        // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签，并进行证书自动更新
        return builder.build();
    }

}
