//package com.ruoyi.project.im.domain;
//
//import java.io.Serializable;
//
///**
// * websocket 通讯的消息类型
// *
// * @author 乐天
// * @since 2018-10-07
// */
//public class Message implements Serializable {
//
//    /**
//     * 消息id
//     */
//    private String id;
//
//    /**
//     * 消息来源用户名
//     */
//    private String name;
//
//    /**
//     * 发送者头像
//     */
//    private String avatar;
//
//    /**
//     * 消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
//     */
//    private String chatId;
//
//    /**
//     * 消息类型 friend
//     */
//    private String type;
//
//    /**
//     * 消息内容
//     */
//    private String content;
//
//    /**
//     * 是否本人发送
//     */
//    private boolean mine;
//
//    /**
//     * 消息的发送者id
//     */
//    private String fromId;
//
//    /**
//     * 服务端时间戳毫秒数
//     */
//    private Long timestamp;
//
//
//    /**
//     * 是否是离线的消息
//     */
//    private boolean offline = false;
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public String getChatId() {
//        return chatId;
//    }
//
//    public void setChatId(String chatId) {
//        this.chatId = chatId;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public boolean isMine() {
//        return mine;
//    }
//
//    public void setMine(boolean mine) {
//        this.mine = mine;
//    }
//
//    public String getFromId() {
//        return fromId;
//    }
//
//    public void setFromId(String fromId) {
//        this.fromId = fromId;
//    }
//
//    public Long getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Long timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public boolean isOffline() {
//        return offline;
//    }
//
//    public void setOffline(boolean offline) {
//        this.offline = offline;
//    }
//}
