package com.ruoyi.project.im.domain;

import lombok.Data;

@Data
public class Chat {


    /**
     * 消息接收者
     *
     * */
    private String chatId;

    /**
    *消息的发送者
    *
    * */
    private String fromId;


    /**
     * 发送者姓名,如果是群,则显示群名字
     *
     * */
    private String name;

    /**
     * 发送者头像,如果是群,则显示群头像
     *
     * */
    private String avatar;

    /**
     * 消息类型
     *
     * */
    private String type;

    /**
     * 最后一条发送数据
     */
    private XtChatMessage lastMessage;

    /**
     * 最后发送的时间
     */
    private Long lastSendTime;

    /**
     *
     * 消息未读数量
     *
     * */
    private Integer unreadCount;

    /**
     * 消息是否已读
     *
     * */

    private Boolean loaded;


    /**是否置顶1:是;0:否*/

    private Integer top;

    /**状态:0:待结单;1:已接单*/
    private Integer status;
}
