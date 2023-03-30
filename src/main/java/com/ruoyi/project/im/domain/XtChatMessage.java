package com.ruoyi.project.im.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @TableName xt_chat_message
 */

@Data
public class XtChatMessage implements Serializable {
    /**
     * id
     */

    private String messageId;

    /**
     * 消息发送者id
     */
    private String fromId;

    /**
     * 消息接收者id(群id)
     */
    private String toId;

    /**
     * 内容
     */
    private String content;

    /**
     * 单聊:0;群聊:1
     */
    private String messageType;

    /**
     * 发送时间
     */
    private Long createTime;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 消息来源用户名(群名)
     */
    private String name;

    /**
     * 发送者头像(群头像)
     */
    private String avatar;


    /**消息类型,图片还是音频*/
    private String type;

    /**语音消息的时长*/
    private Integer second;

    /**聊天消息位置*/
    private String weizhi;



    private static final long serialVersionUID = 1L;
}
