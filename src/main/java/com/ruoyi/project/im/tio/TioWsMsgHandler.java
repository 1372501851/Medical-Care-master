package com.ruoyi.project.im.tio;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.im.domain.*;
import com.ruoyi.project.im.mapper.XtChatMessageMapper;
import com.ruoyi.project.im.service.IXtGroupService;
import com.ruoyi.project.im.service.IXtUserToGroupService;
import com.ruoyi.project.im.service.XtChatMessageService;
import com.ruoyi.project.im.util.ChatUtils;

import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.service.IXtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.common.HttpResponseStatus;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * websocket 处理函数
 *消息处理类
 * @author 乐天
 * @since 2018-10-08
 */
@Component
@Slf4j
public class TioWsMsgHandler implements IWsMsgHandler {

    public static TioConfig tioConfig;



    @Autowired
    private AppLoginTokenService appLoginTokenService;

    @Autowired
    private IXtGroupService iXtGroupService;


    @Autowired
    private XtChatMessageService xtChatMessageService;

    @Autowired
    private IXtGroupService xtGroupService;

    @Autowired
    private IXtUserToGroupService xtUserToGroupService;

    @Autowired
    private IXtUserService userService;


    /**
     * 握手时走这个方法，业务可以在这里获取cookie，request参数等
     *
     * @param request        request
     * @param httpResponse   httpResponse
     * @param channelContext channelContext
     * @return HttpResponse
     */
    @Override
    public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) {
        try {
            tioConfig = channelContext.tioConfig;
            //利用request传入自己的用户id,然后直接接收;来通信;
            //通过request中的平台标识,进行判断登录用户,然后分别获取他们的登录信息;
            String userId = request.getParam("id");
//            AppLoginUser loginUser = appLoginTokenService.getLoginMyUser(request);
            //利用userId
//            String userId = loginUser.getUser().getUserid();
            //先关闭原先的连接
            Tio.closeUser(tioConfig, userId, null);
            //绑定用户
            Tio.bindUser(channelContext, userId);
            // 在线用户绑定到上下文 用于发送在线消息
            WsOnlineContext.bindUser(userId, channelContext);

            // 绑定用户所在的群组
//            List<XtUserToGroup> xtUserToGroups = xtUserToGroupService.selectUserByUserId(userId);
//            List<XtGroup> groups = new ArrayList<>();
//            for (int i = 0; i < xtUserToGroups.size(); i++) {
//                XtGroup xtGroup = xtGroupService.selectXtGroupByUgroupid(xtUserToGroups.get(i).getUgroupid());
//                groups.add(xtGroup);
//            }
//
//            for (XtGroup group : groups) {
//                Tio.bindGroup(channelContext, String.valueOf(group.getUgroupid()));
//            }
//
//            System.out.println("777");System.out.println("777");System.out.println("777");System.out.println("777");System.out.println("777");System.out.println("777");System.out.println("777");System.out.println("777");System.out.println("777");
//            System.out.println(Tio.getAll(channelContext.tioConfig).getObj());
        } catch (Exception e) {
            log.error("websocket 连接失败：{}", e.getMessage());
            httpResponse.setStatus(HttpResponseStatus.getHttpStatus(401));
        }
        return httpResponse;
    }

    /**
     * @param httpRequest    httpRequest
     * @param httpResponse   httpResponse
     * @param channelContext channelContext
     * @author tanyaowu tanyaowu
     */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext){
        String id = httpRequest.getParam("id");
        // 绑定用户
//        Tio.bindUser(channelContext, id);
        // 给用户发送消息
        WsResponse wsResponse = WsResponse.fromText("如果您看到此消息，表示您已成功连接 WebSocket 服务器", "UTF-8");
        Tio.sendToUser(channelContext.tioConfig, id, wsResponse);

    }

    /**
     * 字节消息（binaryType = arraybuffer）过来后会走这个方法
     */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext)  {
        return null;
    }

    /**
     * 当客户端发close flag时，会走这个方法
     */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext)  {
        Tio.remove(channelContext, "receive close flag");
        return null;
    }

    /**
     * 字符消息（binaryType = blob）过来后会走这个方法
     *
     * @param wsRequest      wsRequest
     * @param text           text
     * @param channelContext channelContext
     * @return obj
     */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SendInfo sendInfo = objectMapper.readValue(text, SendInfo.class);
            log.info("消息内容:{}",text);

            if (ChatUtils.MSG_PING.equals(sendInfo.getCode())) {
                //心跳检测包
                WsResponse wsResponse = WsResponse.fromText(text, TioServerConfig.CHARSET);
                log.info("心跳监测");
                //这个语句是向用户端发送心跳结果
                Tio.send(channelContext, wsResponse);
            } else if (ChatUtils.MSG_MESSAGE.equals(sendInfo.getCode())) {
                //发送消息(私聊和群聊消息发送)
                System.out.println(text);
                System.out.println(text);
                System.out.println(text);
                sendMessage(channelContext, objectMapper, sendInfo);
            }
            else if (ChatUtils.MSG_READY.equals(sendInfo.getCode())) {
                //拉取离线消息(私聊和群聊,这个用不上了,直接从接口中获取历史消息)
                pullOffLineMessage(channelContext, objectMapper);
            } else if (ChatUtils.MSG_READ.equals(sendInfo.getCode())) {
                //群聊消息已读回执
                readReceipt(sendInfo);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        //返回值是要发送给客户端的内容，一般都是返回null
        return null;
    }


    /**
     *
     *主动拉取离线消息
     *
     * */

    private void pullOffLineMessage(ChannelContext channelContext, ObjectMapper objectMapper) throws Exception {
        String userId = channelContext.userid;
        TioConfig tioConfig = channelContext.tioConfig;
        List<XtChatMessage> messageList = xtChatMessageService.queryAllOffLineMessage(userId);
        if (messageList != null) {
            for (XtChatMessage message : messageList) {
                SendInfo sendInfo = new SendInfo();
                sendInfo.setCode(ChatUtils.MSG_MESSAGE);
                sendInfo.setMessage(BeanUtil.beanToMap(message));
                WsResponse wsResponse = WsResponse.fromText(objectMapper.writeValueAsString(sendInfo), TioServerConfig.CHARSET);
                Tio.sendToUser(tioConfig, userId, wsResponse);
            }
        }
        //发送完后,需要将离线消息清空;
        xtChatMessageService.deleteAllOffLineMessage(userId);

        //获取用户所有的群
        List<XtGroup> groups = iXtGroupService.getGroups(userId);
        for (XtGroup group : groups) {
            List<XtChatMessage> unReadGroupMessageList = xtChatMessageService.querryUnReadGroupMessage(userId, group.getUgroupid());
            if (unReadGroupMessageList != null) {
                for (XtChatMessage message : unReadGroupMessageList) {
                    SendInfo sendInfo = new SendInfo();
                    sendInfo.setCode(ChatUtils.MSG_MESSAGE);
                    sendInfo.setMessage(BeanUtil.beanToMap(message));
                    WsResponse wsResponse = WsResponse.fromText(objectMapper.writeValueAsString(sendInfo), TioServerConfig.CHARSET);
                    Tio.sendToUser(tioConfig, userId, wsResponse);
                }
            }
        }
    }


    @Autowired
    private XtChatMessageMapper xtChatMessageMapper;
    /**
     *
     * 发送消息
     *
     * */

    private void sendMessage(ChannelContext channelContext, ObjectMapper objectMapper, SendInfo sendInfo) throws Exception {


        //获取消息内容
        Map<String, Object> map = sendInfo.getMessage();
        map.put("messageId",IdUtil.getSnowflakeNextIdStr());
        XtChatMessage message = BeanUtil.fillBeanWithMap(map, new XtChatMessage(), false);
        message.setCreateTime(System.currentTimeMillis());
        WsResponse wsResponse = WsResponse.fromText(objectMapper.writeValueAsString(sendInfo), TioServerConfig.CHARSET);
        //获取聊天对象的id
        String chatId = message.getToId();
        if (ChatUtils.MESSAGE_TYPE_FRIEND.equals(message.getMessageType())) {
            //单聊
//            xtChatMessageService.saveAllPrivateMessage(message);
            XtChatMessage xtChatMessage = new XtChatMessage();

            xtChatMessage.setMessageId(message.getMessageId());
            xtChatMessage.setMessageType(message.getMessageType());
            xtChatMessage.setContent(message.getContent());
            xtChatMessage.setToId(message.getToId());
            xtChatMessage.setAvatar(message.getAvatar());
            xtChatMessage.setName(message.getName());
            xtChatMessage.setCreateTime(message.getCreateTime());
            xtChatMessage.setSecond(message.getSecond());
            xtChatMessage.setFromId(message.getFromId());
            xtChatMessage.setTaskId(message.getTaskId());
            xtChatMessage.setType(message.getType());
            xtChatMessage.setWeizhi(message.getWeizhi());

            xtChatMessageService.savePrivateMessageToMySQL2(xtChatMessage);

            Chat chat = new Chat();
            chat.setType(message.getMessageType());
            chat.setUnreadCount(1);
            chat.setLoaded(false);
            chat.setChatId(chatId);
            chat.setFromId(message.getFromId());
            //搜索该用户的信息
//            XtUser user = userService.selectXtUserByUserid(chatId);
            XtUser user = new XtUser();
            user.setAvatar("1");
            user.setUsername("1");
            user.setUpassword("1");
            chat.setName(user.getUsername());
            chat.setAvatar(user.getAvatar());
            chat.setLastMessage(message);

            message.setAvatar(user.getAvatar());
            //默认不置顶
            chat.setTop(0);
            //默认是待结单
            chat.setStatus(0);
            chat.setLastSendTime(message.getCreateTime());
//            xtChatMessageService.saveChat(chat);

            //发给自己
            Tio.sendToUser(channelContext.tioConfig, message.getFromId(), wsResponse);

            SetWithLock<ChannelContext> channelContextSetWithLock = Tio.getByUserid(channelContext.tioConfig, chatId);
            if (channelContextSetWithLock == null || channelContextSetWithLock.size() == 0)  {
                //用户没有登录,啥也不做
                wsResponse =  WsResponse.fromText("聊天对象没有登录，待会儿再试试吧！！", "UTF-8");
                Tio.sendToUser(channelContext.tioConfig, message.getFromId(), wsResponse);
            } else {
                //发给自己
//                Tio.sendToUser(channelContext.tioConfig, message.getFromId(), wsResponse);
                //用户登陆了,直接发送给对方
                Tio.sendToUser(channelContext.tioConfig, chatId, wsResponse);
            }

        } else {

            XtChatMessage xtChatMessage = new XtChatMessage();

            xtChatMessage.setMessageId(message.getMessageId());
            xtChatMessage.setMessageType(message.getMessageType());
            xtChatMessage.setContent(message.getContent());
            xtChatMessage.setToId(message.getToId());
            xtChatMessage.setAvatar(message.getAvatar());
            xtChatMessage.setName(message.getName());
            xtChatMessage.setCreateTime(message.getCreateTime());
            xtChatMessage.setSecond(message.getSecond());
            xtChatMessage.setFromId(message.getFromId());
            xtChatMessage.setTaskId(message.getTaskId());
            xtChatMessage.setType(message.getType());

            xtChatMessageService.savePrivateMessageToMySQL2(xtChatMessage);

            List<String> chengyaunids = new ArrayList<>();
                Tio.sendToUser(channelContext.tioConfig, message.getFromId(), wsResponse);
                try {
                    chengyaunids = xtChatMessageMapper.seleChengYuanId(chatId);
                }catch (Exception r){
                    System.out.println(r.getMessage());
                }
            for (String id:
                 chengyaunids) {
                    Tio.sendToUser(channelContext.tioConfig, id, wsResponse);
            }
        }
    }

////群聊操作
//
//    //记录去群聊消息
//            xtChatMessageService.saveAllGroupMessage(message);
//
//    //保存这条会话
//    Chat chat = new Chat();
//            chat.setType(message.getMessageType());
//            chat.setUnreadCount(1);
//            chat.setLoaded(false);
//    //群号
//            chat.setChatId(chatId);
//
//            chat.setFromId(message.getFromId());
//    //群名
//            chat.setName(message.getName());
//    //群头像
//            chat.setAvatar(message.getAvatar());
//            chat.setLastMessage(message);
//    //默认不置顶
//            chat.setTop(0);
//            chat.setStatus(0);
//            chat.setLastSendTime(message.getCreateTime());
//            xtChatMessageService.saveGroupChat(chat);
//
//    //发给群
//            Tio.sendToGroup(channelContext.tioConfig, chatId, wsResponse);
//
//

    /**
     *
     * 记录查看最后一条群消息的时间及相关信息
     *
     * */

    private void readReceipt(SendInfo sendInfo){
        Map<String,Object> map = sendInfo.getMessage();
        Receipt receipt = BeanUtil.fillBeanWithMap(map,new Receipt(),false);
        xtChatMessageService.readReceipt(receipt.getChatId(),receipt.getUserId(),System.currentTimeMillis());
    }

}
