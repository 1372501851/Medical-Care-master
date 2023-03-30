package com.ruoyi.project.im.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.OO;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.im.domain.XtUserToGroup;
import com.ruoyi.project.im.service.IXtGroupService;
import com.ruoyi.project.im.service.IXtUserToGroupService;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.service.IXtUserService;
import com.ruoyi.project.im.tio.Constant.IMConstant;
import com.ruoyi.project.im.domain.Chat;
import com.ruoyi.project.im.domain.XtChatMessage;
import com.ruoyi.project.im.mapper.XtChatMessageMapper;
import com.ruoyi.project.im.service.ChatService;
import com.ruoyi.project.im.service.XtChatMessageService;
import com.ruoyi.project.im.Dto.QueryHistoryMessageDto;
import com.ruoyi.project.im.util.ChatUtils;

import com.ruoyi.project.login.Dto.AppLoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Slf4j
public class XtChatMessageServiceImpl implements XtChatMessageService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private XtChatMessageMapper xtChatMessageMapper;

    @Autowired
    private IXtUserService iXtUserService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private IXtUserToGroupService xtUserToGroupService;

    @Autowired
    private IXtGroupService xtGroupService;





    /**
     * 记录私聊产生的全部消息
     *key={min}-{max}-l-private_all_message
     * */
    @Override
    public void saveAllPrivateMessage(XtChatMessage message) throws Exception {
        String key = getAllKey(message.getFromId(), message.getToId(), message.getMessageType());
        redisTemplate.opsForZSet().add(key, new ObjectMapper().writeValueAsString(message), message.getCreateTime());
    }





    /**
     *记录产生的离线消息
     * 包括私聊消息
     *离线消息的key:{}-offline_all_message
     * */

    @Override
    public void saveAllOfflineMessage(XtChatMessage message) throws Exception {
        String offLineKey = getOffLineKey(message.getToId());
        redisTemplate.opsForZSet().add(offLineKey, new ObjectMapper().writeValueAsString(message), message.getCreateTime());
    }


    /**
     *
     * 记录所有的群消息
     *
     * */
    @Override
    public void saveAllGroupMessage(XtChatMessage message) throws Exception {
        String groupChatKey = getGroupChatKey(message.getToId());
        redisTemplate.opsForZSet().add(groupChatKey, new ObjectMapper().writeValueAsString(message), message.getCreateTime());
    }

    @Override
    public List<XtChatMessage> getGroupMessage(String key) {
        Set<String> set = redisTemplate.opsForZSet().rangeByScore(key,0,System.currentTimeMillis());
        if (set != null){
            return set.stream().map(this::toMessage).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<XtChatMessage> getPrivateMessage(String key) {
        Set<String> set = redisTemplate.opsForZSet().rangeByScore(key,0,System.currentTimeMillis());
        if (set != null){
            return set.stream().map(this::toMessage).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public void savePrivateMessageToMySQL(List<XtChatMessage> privateMessage) {
        xtChatMessageMapper.savePrivateMessageToMySQL(privateMessage);
    }

    @Override
    public void savePrivateMessageToMySQL2(XtChatMessage privateMessage) {
        xtChatMessageMapper.savePrivateMessageToMySQL2(privateMessage);
    }

    @Override
    public void saveChat(Chat chat) throws JsonProcessingException {


        /**
         * 对发送者自己来说
         *
         * */
        String fromId = chat.getFromId();
        String fromChatTimeListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_TIME, fromId);
        String fromChatMessageListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_LIST, fromId);

        //判断即将要发的对象,是否有过聊天历史
        Object fromLastChatTime = redisTemplate.opsForHash().get(fromChatTimeListKey, chat.getChatId());


        /**
         *
         * 对于自己这些已读状态的设置和用户基本信息需要理解一下
         *
         * 和前端的效果联系紧密,比较绕
         * */
        //管理好自己的最后一条聊天信息
        Chat fromChat = new Chat();
        BeanUtils.copyProperties(chat,fromChat);
        fromChat.setLoaded(true);
        fromChat.setUnreadCount(0);

        //聊天好友的信息
        XtUser xtUser = iXtUserService.selectXtUserByUserid(chat.getChatId());
        fromChat.setName(xtUser.getUsername());


        //改变发送者,接收者
        fromChat.setChatId(chat.getFromId());
        fromChat.setFromId(chat.getChatId());


        if (fromLastChatTime == null){
            //不存在聊天历史
            redisTemplate.opsForHash().put(fromChatTimeListKey,chat.getChatId(),chat.getLastSendTime());
            redisTemplate.opsForZSet().add(fromChatMessageListKey,new ObjectMapper().writeValueAsString(fromChat),chat.getLastSendTime());

        }else {
            //有过聊天历史
            String lastTime = String.valueOf(fromLastChatTime);
            Long timestamp = Long.valueOf(lastTime);
            redisTemplate.opsForZSet().removeRangeByScore(fromChatMessageListKey,timestamp,timestamp);
            //插入最新的聊天时间
            redisTemplate.opsForHash().put(fromChatTimeListKey,chat.getChatId(),chat.getLastSendTime());
            //插入最新的数据
            redisTemplate.opsForZSet().add(fromChatMessageListKey,JSON.toJSONString(fromChat),chat.getLastSendTime());
        }


        /**
         *
         * 对接收者来说
         *
         * */

        String toId = chat.getChatId();
        String toChatTimeListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_TIME, toId);
        String toChatMessageListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_LIST, toId);

        //判断次消息的接收者是否与发送者曾经有过chat
        Object lastChatTime = redisTemplate.opsForHash().get(toChatTimeListKey, fromId);

        if (lastChatTime == null){
            //没有聊天历史

            //加入聊天记录
            redisTemplate.opsForHash().put(toChatTimeListKey,fromId,chat.getLastSendTime());

            //记录最后一条消息
            redisTemplate.opsForZSet().add(toChatMessageListKey, JSON.toJSONString(chat),chat.getLastSendTime());

        }else {
            //有过聊天历史
            String lastTime = String.valueOf(lastChatTime);
            Long timestamp = Long.valueOf(lastTime);
            //先临时保存最后一条消息,再删除
            Set<String> preChat = redisTemplate.opsForZSet().rangeByScore(toChatMessageListKey, timestamp, timestamp);
            redisTemplate.opsForZSet().removeRangeByScore(toChatMessageListKey,timestamp,timestamp);

            //插入最新的聊天时间
            redisTemplate.opsForHash().put(toChatTimeListKey,fromId,chat.getLastSendTime());

            if (preChat != null){
                String preChatString = preChat.iterator().next();
                Chat previousChat = JSON.parseObject(preChatString, Chat.class);

                //是否已读
                Boolean loaded = previousChat.getLoaded();
                if (!loaded){
                    //未读
                    chat.setUnreadCount(previousChat.getUnreadCount()+1);
                }

                //记录最后聊天的信息
                redisTemplate.opsForZSet().add(toChatMessageListKey,JSON.toJSONString(chat),chat.getLastSendTime());

            }

        }

    }

    @Override
    public void saveGroupChat(Chat chat) {

        //记录他们对于群的聊天记录
        //找到该群所有的用户
        List<XtUserToGroup> xtUserToGroups = xtUserToGroupService.selectUserByGroupId(chat.getChatId());
        //改变群中用户对于该群所记录的最后一条消息记录
        //遍历群聊天好友
        for (int i = 0; i < xtUserToGroups.size(); i++) {

            //先改变自己的状态;
            String fromId = chat.getFromId();
            String fromChatTimeListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_TIME, fromId);
            String fromChatMessageListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_LIST, fromId);

            //判断即将要发的对象,是否有过聊天历史
            Object fromLastChatTime = redisTemplate.opsForHash().get(fromChatTimeListKey, chat.getChatId());


            /**
             *
             * 对于自己这些已读状态的设置和用户基本信息需要理解一下
             *
             * 和前端的效果联系紧密,比较绕
             * */

            //管理好自己的最后一条聊天信息
            Chat fromChat = new Chat();
            BeanUtils.copyProperties(chat,fromChat);
            fromChat.setLoaded(true);
            fromChat.setUnreadCount(0);



            //聊天好友的信息
            XtUserToGroup xtUserToGroup = xtUserToGroups.get(i);
            XtUser xtUser = iXtUserService.selectXtUserByUserid(xtUserToGroup.getUserid());
            fromChat.setName(xtUser.getUsername());

            //改变发送者,接收者
            fromChat.setChatId(chat.getFromId());
            fromChat.setFromId(chat.getChatId());


            if (fromLastChatTime == null){
                //不存在聊天历史
                redisTemplate.opsForHash().put(fromChatTimeListKey,chat.getChatId(),chat.getLastSendTime());
                redisTemplate.opsForZSet().add(fromChatMessageListKey,JSON.toJSONString(fromChat),chat.getLastSendTime());

            }else {
                //有过聊天历史
                String lastTime = String.valueOf(fromLastChatTime);
                Long timestamp = Long.valueOf(lastTime);
                redisTemplate.opsForZSet().removeRangeByScore(fromChatMessageListKey,timestamp,timestamp);
                //插入最新的聊天时间
                redisTemplate.opsForHash().put(fromChatTimeListKey,chat.getChatId(),chat.getLastSendTime());
                //插入最新的数据
                redisTemplate.opsForZSet().add(fromChatMessageListKey,JSON.toJSONString(fromChat),chat.getLastSendTime());
            }


            /**
             *
             * 对接收者来说
             *
             * */

            String toId = xtUserToGroup.getUserid();
            String toChatTimeListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_TIME, toId);
            String toChatMessageListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_LIST, toId);

            //判断次消息的接收者是否与发送者曾经有过chat
            Object lastChatTime = redisTemplate.opsForHash().get(toChatTimeListKey, chat.getChatId());

            if (lastChatTime == null){
                //没有聊天历史

                //加入聊天记录
                redisTemplate.opsForHash().put(toChatTimeListKey,chat.getChatId(),chat.getLastSendTime());

                //记录最后一条消息
                redisTemplate.opsForZSet().add(toChatMessageListKey, JSON.toJSONString(chat),chat.getLastSendTime());

            }else {
                //有过聊天历史
                String lastTime = String.valueOf(lastChatTime);
                Long timestamp = Long.valueOf(lastTime);
                //先临时保存最后一条消息,再删除
                Set<String> preChat = redisTemplate.opsForZSet().rangeByScore(toChatMessageListKey, timestamp, timestamp);
                redisTemplate.opsForZSet().removeRangeByScore(toChatMessageListKey,timestamp,timestamp);

                //插入最新的聊天时间
                redisTemplate.opsForHash().put(toChatTimeListKey,chat.getChatId(),chat.getLastSendTime());

                if (preChat != null){
                    String preChatString = preChat.iterator().next();
                    Chat previousChat = JSON.parseObject(preChatString, Chat.class);

                    //是否已读
                    Boolean loaded = previousChat.getLoaded();
                    if (!loaded){
                        //未读
                        chat.setUnreadCount(previousChat.getUnreadCount()+1);
                    }

                    //记录最后聊天的信息
                    redisTemplate.opsForZSet().add(toChatMessageListKey,JSON.toJSONString(chat),chat.getLastSendTime());

                }
            }
        }
    }

    @Override
    public void saveDefaultGroupMessageChat(Chat chat, List<String> groupIds) {


        //记录他们对于群的聊天记录
        //找到该群所有的用户
        //改变群中用户对于该群所记录的最后一条消息记录
        //遍历群聊天好友
        for (int i = 0; i < groupIds.size(); i++) {

            //先改变自己的状态;
            String fromId = chat.getFromId();
            String fromChatTimeListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_TIME, fromId);
            String fromChatMessageListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_LIST, fromId);

            //判断即将要发的对象,是否有过聊天历史
            Object fromLastChatTime = redisTemplate.opsForHash().get(fromChatTimeListKey, chat.getChatId());


            /**
             *
             * 对于自己这些已读状态的设置和用户基本信息需要理解一下
             *
             * 和前端的效果联系紧密,比较绕
             * */

            //管理好自己的最后一条聊天信息
            Chat fromChat = new Chat();
            BeanUtils.copyProperties(chat,fromChat);
            fromChat.setLoaded(true);
            fromChat.setUnreadCount(0);



            //聊天好友的信息
            XtUser xtUser = iXtUserService.selectXtUserByUserid(groupIds.get(i));
//            fromChat.setName(xtUser.getUsername());

            //改变发送者,接收者
            fromChat.setChatId(chat.getFromId());
            fromChat.setFromId(chat.getChatId());


            if (fromLastChatTime == null){
                //不存在聊天历史
                redisTemplate.opsForHash().put(fromChatTimeListKey,chat.getChatId(),chat.getLastSendTime());
                redisTemplate.opsForZSet().add(fromChatMessageListKey,JSON.toJSONString(fromChat),chat.getLastSendTime());

            }else {
                //有过聊天历史
                String lastTime = String.valueOf(fromLastChatTime);
                Long timestamp = Long.valueOf(lastTime);
                redisTemplate.opsForZSet().removeRangeByScore(fromChatMessageListKey,timestamp,timestamp);
                //插入最新的聊天时间
                redisTemplate.opsForHash().put(fromChatTimeListKey,chat.getChatId(),chat.getLastSendTime());
                //插入最新的数据
                redisTemplate.opsForZSet().add(fromChatMessageListKey,JSON.toJSONString(fromChat),chat.getLastSendTime());
            }


            /**
             *
             * 对接收者来说
             *
             * */

            String toId = groupIds.get(i);
            String toChatTimeListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_TIME, toId);
            String toChatMessageListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_LIST, toId);

            //判断次消息的接收者是否与发送者曾经有过chat
            Object lastChatTime = redisTemplate.opsForHash().get(toChatTimeListKey, chat.getChatId());

            if (lastChatTime == null){
                //没有聊天历史

                //加入聊天记录
                redisTemplate.opsForHash().put(toChatTimeListKey,chat.getChatId(),chat.getLastSendTime());

                //记录最后一条消息
                redisTemplate.opsForZSet().add(toChatMessageListKey, JSON.toJSONString(chat),chat.getLastSendTime());

            }else {
                //有过聊天历史
                String lastTime = String.valueOf(lastChatTime);
                Long timestamp = Long.valueOf(lastTime);
                //先临时保存最后一条消息,再删除
                Set<String> preChat = redisTemplate.opsForZSet().rangeByScore(toChatMessageListKey, timestamp, timestamp);
                redisTemplate.opsForZSet().removeRangeByScore(toChatMessageListKey,timestamp,timestamp);

                //插入最新的聊天时间
                redisTemplate.opsForHash().put(toChatTimeListKey,chat.getChatId(),chat.getLastSendTime());

                if (preChat != null){
                    String preChatString = preChat.iterator().next();
                    Chat previousChat = JSON.parseObject(preChatString, Chat.class);

                    //是否已读
                    Boolean loaded = previousChat.getLoaded();
                    if (!loaded){
                        //未读
                        chat.setUnreadCount(previousChat.getUnreadCount()+1);
                    }

                    //记录最后聊天的信息
                    redisTemplate.opsForZSet().add(toChatMessageListKey,JSON.toJSONString(chat),chat.getLastSendTime());

                }
            }
        }




    }

    @Override
    public PageInfo getMessageList(Integer num, Integer size) {

        PageHelper.startPage(num,size);

        List<XtChatMessage> xtChatMessages = xtChatMessageMapper.selectMessageListAll();

        PageInfo<XtChatMessage> result = new PageInfo<>(xtChatMessages);

        return result;


    }

    @Override
    public void delMessageList(String[] ids) {
        xtChatMessageMapper.delMessageListByIds(ids);
    }

    @Override
    public void saveGroupMessageToMySQL(List<XtChatMessage> groupMessage) {
        xtChatMessageMapper.saveGroupMessageToMySQL(groupMessage);
    }



    @Override
    public List<XtChatMessage> queryAllOffLineMessage(String userId) {
        String key = StrUtil.format(IMConstant.OFFLINE_ALL_MESSAGE, userId);

        //获取全部未读消息
        Set<String> set = redisTemplate.opsForZSet().range(key,0,-1);

        if (set != null){
            return set.stream().map(this::toMessage).collect(Collectors.toList());
        }

        //没有离线消息则返回空集合
        return new ArrayList<>();

    }

    @Override
    public List<XtChatMessage> querryUnReadGroupMessage(String userId, String groupId) {
        //获取最后一条群消息的查看时间
        String readReceiptKey = getReadReceiptKey(groupId, userId);
        String time = redisTemplate.opsForValue().get(readReceiptKey);
        long score  = -1;
        if (time != null && StrUtil.isNotBlank(time)){
            score = Long.parseLong(time);
        }
        String groupChatKey = getGroupChatKey(groupId);
        Set<String> set = redisTemplate.opsForZSet().rangeByScore(groupChatKey,score,System.currentTimeMillis());
        if (set != null){
            return set.stream().map(this::toMessage).collect(Collectors.toList());
        }
        return new ArrayList<>();


    }

    @Override
    public void deleteAllOffLineMessage(String userId) {
        String key = StrUtil.format(IMConstant.OFFLINE_ALL_MESSAGE, userId);
        redisTemplate.opsForZSet().removeRange(key,0,-1);
    }

    @Override
    public List<XtChatMessage> queryHistoryMessage(QueryHistoryMessageDto queryHistoryMessageDto , String toId) {

        AppLoginUser myLoginUser = SecurityUtils.getAppLoginUser();
//        String toId = myLoginUser.getUser().getUserid();
        if (queryHistoryMessageDto.getPageSize() == null || queryHistoryMessageDto.getPageNum() == null || queryHistoryMessageDto.getPageSize() == 0 || queryHistoryMessageDto.getPageNum() == 0){
            queryHistoryMessageDto.setPageSize(1);
            queryHistoryMessageDto.setPageNum(1);
        }
        //要根据类型去分别处理群和私聊消息

        String key = getAllKey(queryHistoryMessageDto.getFromId(), toId, queryHistoryMessageDto.getType());

        List<XtChatMessage> messageList = new ArrayList<>();

        if ((!redisTemplate.hasKey(key)) || (redisTemplate.opsForZSet().size(key)) <= 0) {
            //从mysql获取(分页)
            //开始分页
            if (queryHistoryMessageDto.getType().equals(ChatUtils.MESSAGE_TYPE_FRIEND)){
                //私聊

                ArrayList<XtChatMessage> xtChatMessages1 = (ArrayList<XtChatMessage>) xtChatMessageMapper.queryPrivateHistoryMessageList(queryHistoryMessageDto.getFromId(), toId, queryHistoryMessageDto.getType() , queryHistoryMessageDto.getWeizhi());
               try {
                   List<?> objects = OO.subList(xtChatMessages1, queryHistoryMessageDto.getPageSize(), queryHistoryMessageDto.getPageNum());
                   messageList = (List<XtChatMessage>) objects;
               }catch (IndexOutOfBoundsException e){
                   return new ArrayList<>();
               }

            }else {
                //群聊
                PageInfo<XtChatMessage> source = PageHelper.startPage(queryHistoryMessageDto.getPageNum(), queryHistoryMessageDto.getPageSize()).doSelectPageInfo(()->{
                    //查询语句
                    xtChatMessageMapper.queryGroupHistoryMessageList( toId, queryHistoryMessageDto.getType(), queryHistoryMessageDto.getTimeStamp());
                });
                //对查询的list进行下一步操作
                messageList = source.getList().stream().collect(Collectors.toList());

            }


        } else {
            //从redis中查询(分页)
            int off = (queryHistoryMessageDto.getPageNum()-1) * (queryHistoryMessageDto.getPageSize());
            int count = queryHistoryMessageDto.getPageSize();
            Set<String> set = redisTemplate.opsForZSet().reverseRangeByScore(key, 0, queryHistoryMessageDto.getTimeStamp(), off, count);
            messageList = set.stream().map(this::toMessage).collect(Collectors.toList());

        }

        if (queryHistoryMessageDto.getType().equals(ChatUtils.MESSAGE_TYPE_FRIEND)){
            //重置未读数量
            chatService.resetUnreadAmount(queryHistoryMessageDto.getFromId());
        }else{
            chatService.resetGroupUnreadAmount(queryHistoryMessageDto.getFromId());
        }

        //处理头像问题
//        for (int i = 0; i < messageList.size(); i++) {
//            String avatar = messageList.get(i).getAvatar();
//            List<String> pics = StringToURL.toURL(avatar);
//            messageList.get(i).setAvatar(pics.get(0));
//        }

        //还需要反转一下
        Collections.reverse(messageList);
        return messageList;
    }

    @Override
    public void readReceipt(String chatId, String userId, long currentTimeMillis) {
        String key = getReadReceiptKey(userId,chatId);
        //记录最后一条消息的读取时间
        redisTemplate.opsForValue().set(key,String.valueOf(currentTimeMillis));
    }


    private String getAllKey(String fromId, String chatId, String type){
        if (ChatUtils.MESSAGE_TYPE_FRIEND.equals(type)) {
            if (Long.parseLong(fromId) < Long.parseLong(chatId)) {
                return StrUtil.format(IMConstant.PRIVATE_ALL_MESSAGE, fromId, chatId);
            } else {
                return StrUtil.format(IMConstant.PRIVATE_ALL_MESSAGE, chatId, fromId);
            }
        } else {
            return StrUtil.format(IMConstant.GROUP_ALL_MESSAGE, fromId);
        }
    }


    private String getOffLineKey(String chatId){
        return StrUtil.format(IMConstant.OFFLINE_ALL_MESSAGE, chatId);
    }

    private String getReadReceiptKey(String chatId,String userId){
        String group_read_receipt_key = StrUtil.format(IMConstant.GROUP_READ_RECEIPT, userId, chatId);
        return  group_read_receipt_key;
    }


    private String getGroupChatKey(String groupId){
        String key = StrUtil.format(IMConstant.GROUP_ALL_MESSAGE, groupId);
        return key;

    }

        private XtChatMessage toMessage(String str) {
        try {
            XtChatMessage message = new ObjectMapper().readValue(str, XtChatMessage.class);
            return message;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }




}




