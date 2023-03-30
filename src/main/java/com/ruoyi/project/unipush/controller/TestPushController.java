package com.ruoyi.project.unipush.controller;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-12-13
 */

@Api(tags = "测试消息推送")
@RestController
@RequestMapping("/pushMessage")
public class TestPushController {

    @Value("${push.appId}")
    private String appId;

    @Value("${push.appKey}")
    private String appKey;

    @Value("${push.masterSecret}")
    private String masterSecret;
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";


    @ApiOperation(value = "测试推消息")
    @GetMapping("/push")
    public AjaxResult  push(String Title,String Text){

        IGtPush push = new IGtPush(url, appKey, masterSecret);
        Style0 style = new Style0();
        // STEP2：设置推送标题、推送内容

        style.setTitle(Title);
        style.setText(Text);
        style.setLogo("push.png");  // 设置推送图标
        // STEP3：设置响铃、震动等推送效果
        style.setRing(true);  // 设置响铃
        style.setVibrate(true);  // 设置震动


        // STEP4：选择通知模板
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);


        // STEP5：定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);  // 时间单位为毫秒

        // STEP6：执行推送
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
        System.out.println(ret.getMessageId());

        return AjaxResult.success("成功",ret.getResponse().toString());

    }
}
