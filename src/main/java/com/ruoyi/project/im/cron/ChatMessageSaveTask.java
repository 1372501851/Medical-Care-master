package com.ruoyi.project.im.cron;

import com.ruoyi.project.im.domain.XtChatMessage;
import com.ruoyi.project.im.service.XtChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author by hujun
 * @date 2022-10-18
 *
 * 三更半夜将Redis聊天消息持久化到mysql
 *
 */

@Configuration
@EnableScheduling
@Slf4j
public class ChatMessageSaveTask {
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    private XtChatMessageService xtChatMessageService;

    //每天3:00执行
    @Scheduled(cron = "0 0 3 * * ?")
//    @Scheduled(cron = "0 0/2 * * * ?")
    public void chatMessageSave() {
        log.info("【定时任务】 开始！");

        // 获取缓存中的群聊天缓存
        String patternGroup = "*-group_all_message";
        //获取所有的群聊key
        Set<String> keys = redisTemplate.keys(patternGroup);
        if (keys != null){
            for (String key : keys) {
                //获取所有的群消息
                List<XtChatMessage> groupMessage = xtChatMessageService.getGroupMessage(key);
                //存入数据库
                if (!CollectionUtils.isEmpty(groupMessage)){
                    try{
                        //TODO只持久化七天前的历史消息,其他信息保存在redis中
                        xtChatMessageService.saveGroupMessageToMySQL(groupMessage);
                        //清除缓存
                        redisTemplate.opsForZSet().removeRange(key,0,-1);
                        //记录持计划信息
                        log.info("[定时任务]{}群,{}时间,{}条消息持计划到MySQL",groupMessage.get(0).getToId(),new Date(),groupMessage.size());
                    }catch (Exception e){
                        log.error("定时任务出错!");
                    }

                }else {
                    log.info("暂无信息需要持久化");
                }

            }
        }


        // 获取单聊聊天缓存
        String patternPrivate = "*-private_all_message";
        Set<String> keysPrivate = redisTemplate.keys(patternPrivate);
        if (keysPrivate != null){
            for (String key : keysPrivate) {
                //获取所有的私聊消息
                List<XtChatMessage> privateMessage = xtChatMessageService.getPrivateMessage(key);
                //存入数据库
                if (privateMessage != null){
                    try{
                        //TODO只持久化七天前的历史消息,其他信息保存在redis中
                        xtChatMessageService.savePrivateMessageToMySQL(privateMessage);
                        //清除缓存
                        redisTemplate.opsForZSet().removeRange(key,0,-1);
                        //记录持计划信息
                        log.info("[定时任务]{}时间,{}条私聊消息持计划到MySQL",new Date(),privateMessage.size());
                    }catch (Exception e){
                        log.error("定时任务出错!");
                    }

                }else {
                    log.info("暂无信息需要持久化");
                }

            }
        }


    }

}
