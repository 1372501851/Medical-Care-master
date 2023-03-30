package com.ruoyi.project.remind.cron;


import cn.hutool.core.util.IdUtil;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.ruoyi.project.remind.domain.Remind;
import com.ruoyi.project.remind.domain.RemindPush;
import com.ruoyi.project.remind.domain.RemindUser;
import com.ruoyi.project.remind.mapper.RemindMapper;
import com.ruoyi.project.remind.mapper.RemindPushMapper;
import com.ruoyi.project.remind.mapper.RemindUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
@Slf4j
public class RemindMessageCron {
    @Value("${push.appId}")
    private String appId;

    @Value("${push.appKey}")
    private String appKey;

    @Value("${push.masterSecret}")
    private String masterSecret;

    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    @Autowired
    private RemindUserMapper remindUserMapper;

    @Autowired
    private RemindMapper remindMapper;

    @Autowired
    private RemindPushMapper remindPushMapper;

    //每一分钟执行一次
    @Scheduled(cron = "0 */1 * * * ?")
    public void PushRemindMessage(){
        //定时任务开始，去RemindUser表中找出执行次数大于0的数据；执行推送消息；
        List<RemindUser> remindUsers = remindUserMapper.selectRemindUserListForCron();
        //如果有需要推送的记录
        if (!CollectionUtils.isEmpty(remindUsers)){
            log.info("消息提醒开始");
            for (int i = 0; i < remindUsers.size(); i++) {
                //查找提醒项
                Remind remind = remindMapper.selectRemindById(remindUsers.get(i).getRemindId());

                //推送消息
                pushMessage(remind);

                //推送消息完成后，需要将推送的消息记录到mysql
                savePushMessage(remind,remindUsers.get(i).getRemindId());

                //将消息发送数-1(可异步)
                remindUserMapper.reduceCountRemindUserById(remindUsers.get(i).getId());
            }
            log.info("消息提醒结束");

        }

    }


    private void pushMessage(Remind remind){
        //推送消息（还需要有个表记录用户的appid等信息）
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        Style0 style = new Style0();
        // STEP2：设置推送标题、推送内容
        style.setTitle("系统推送通知");
        style.setText(remind.getContent());
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
        log.info("消息推送结果：{}",ret.getResponse().toString());

    }

    private void savePushMessage(Remind remind,String reminderId){
        RemindPush remindPush = new RemindPush();
        remindPush.setId(IdUtil.getSnowflakeNextIdStr());
        remindPush.setTitle("系统消息通知");
        remindPush.setContent(remind.getContent());
        remindPush.setUserId(reminderId);
        remindPushMapper.insertRemindPush(remindPush);
    }


}
