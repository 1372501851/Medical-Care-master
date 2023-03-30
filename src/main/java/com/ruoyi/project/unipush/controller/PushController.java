//package com.ruoyi.project.unipush.controller;
//
//import com.gexin.rp.sdk.base.IPushResult;
//import com.gexin.rp.sdk.base.impl.AppMessage;
//import com.gexin.rp.sdk.http.IGtPush;
//import com.gexin.rp.sdk.template.NotificationTemplate;
//import com.gexin.rp.sdk.template.style.Style0;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.project.remind.domain.Remind;
//import com.ruoyi.project.remind.service.IRemindService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author by hujun
// * @date 2022-12-26
// */
//
//@Api(tags = "消息推送模块")
//@RestController
//@RequestMapping("/pushMessage")
//public class PushController {
//    @Value("${push.appId}")
//    private String appId;
//
//    @Value("${push.appKey}")
//    private String appKey;
//
//    @Value("${push.masterSecret}")
//    private String masterSecret;
//
//    @Autowired
//    private IRemindService remindService;
//
//    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
//
//    @ApiOperation(value = "推送消息")
//    @GetMapping("/push")
//    public AjaxResult push(Remind remind){
//
//        IGtPush push = new IGtPush(url, appKey, masterSecret);
//        Style0 style = new Style0();
//        //step2:设置推送标题,推送内容
//        List<Remind> reminds = remindService.selectRemindList(remind);
//        style.setLogo("push.png");  // 设置推送图标
//        // STEP3：设置响铃、震动等推送效果
//        style.setRing(true);  // 设置响铃
//        style.setVibrate(true);  // 设置震动
//
//        //STEP4：选择通知模板
//        NotificationTemplate template = new NotificationTemplate();
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//        template.setStyle(style);
//
//        // STEP5：定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
//        List<String> appIds = new ArrayList<String>();
//        appIds.add(appId);
//        AppMessage message = new AppMessage();
//        message.setData(template);
//        message.setAppIdList(appIds);
//        message.setOffline(true);
//        message.setOfflineExpireTime(1000 * 600);  // 时间单位为毫秒
//
//        // STEP6：执行推送
//        IPushResult ret = push.pushMessageToApp(message);
//        System.out.println(ret.getResponse().toString());
//        return AjaxResult.success(ret.getResponse().toString());
//    }
//
//
//}
