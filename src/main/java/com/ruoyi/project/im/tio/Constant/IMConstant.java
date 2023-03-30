package com.ruoyi.project.im.tio.Constant;

/**
 * @author by hujun
 * @date 2022-10-24
 */

public class IMConstant {
    /**
     * 记录私聊产生的全部消息
     *key={min}-{max}-l-private_all_message
     * */
    public static final String PRIVATE_ALL_MESSAGE = "{}-{}-private_all_message";

    /**
     *记录产生的离线消息
     * 包括私聊消息
     *离线消息的key:{}-offline_all_message
     * */
    public static final String OFFLINE_ALL_MESSAGE = "{}-offline_all_message";

    /**
     * 记录群聊产生的全部消息
     *key={min}-{max}-l-group_all_message
     * */
    public static final String GROUP_ALL_MESSAGE = "{}-group_all_message";


    /**
     * 记录自己与别人聊天的最后一条Chat信息
     *
     * */
    public static final String USER_LAST_CHAT_LIST = "{}-user_last_chat_list";

    /**
     * 记录自己与别人聊天的最后一次聊天的时间
     *
     * */

    public static final String USER_LAST_CHAT_TIME = "{}-user_lsat_chat_time";


    /**
     *
     * 记录自己与群聊天的最后一条消息(群列表)
     *
     * */

    public  static  final String GROUP_LAST_CHAT_LIST = "{}-group_last_chat_list";

    /**
     * 记录自己与群聊天的最后一次时间(群消息时间列表)
     * (查看消息后会重置状态与时间)
     *
     * */

    //这个字母写错了,改的话,以前的消息查不到;清空历史消息再改;
    public static final String GROUP_LAST_CHAT_TIME = "{}-group_lsat_chat_time";


    /**
     * 群消息已读回执的key
     * {userId}-{groupId}
     *
     * */

    public static final String GROUP_READ_RECEIPT = "{}-{}-group_read_receipt";




}
