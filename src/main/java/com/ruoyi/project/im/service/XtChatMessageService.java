package com.ruoyi.project.im.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.ruoyi.project.im.domain.Chat;
import com.ruoyi.project.im.domain.XtChatMessage;
import com.ruoyi.project.im.Dto.QueryHistoryMessageDto;


import java.util.List;

/**
 *
 */
public interface XtChatMessageService  {

    /**
     * ***********************************************************************************************
     * */

    void saveAllPrivateMessage(XtChatMessage message) throws Exception;

    void saveAllOfflineMessage(XtChatMessage message) throws Exception;

    List<XtChatMessage> queryAllOffLineMessage(String userId);

    List<XtChatMessage> querryUnReadGroupMessage(String userId,String groupId);

    void deleteAllOffLineMessage(String userId);

    List<XtChatMessage> queryHistoryMessage(QueryHistoryMessageDto queryHistoryMessageDto , String toId);

    void readReceipt(String chatId, String userId, long currentTimeMillis);

    void saveAllGroupMessage(XtChatMessage message) throws Exception;

    List<XtChatMessage> getGroupMessage(String key);

    void saveGroupMessageToMySQL(List<XtChatMessage> groupMessage);

    List<XtChatMessage> getPrivateMessage(String key);

    void savePrivateMessageToMySQL(List<XtChatMessage> privateMessage);

    void savePrivateMessageToMySQL2(XtChatMessage privateMessage);

    void saveChat(Chat chat) throws JsonProcessingException;

    void saveGroupChat(Chat chat);

    void saveDefaultGroupMessageChat(Chat chat,List<String> groupIds);

    PageInfo getMessageList(Integer num, Integer size);

    void delMessageList(String[] ids);

    /**
     **************************************************************************************************
     * */

}
