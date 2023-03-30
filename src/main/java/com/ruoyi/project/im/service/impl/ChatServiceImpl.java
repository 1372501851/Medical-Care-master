package com.ruoyi.project.im.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.im.domain.*;
import com.ruoyi.project.im.mapper.XtChatMessageMapper;
import com.ruoyi.project.im.tio.Constant.IMConstant;
import com.ruoyi.project.im.domain.Vo.UserVo;
import com.ruoyi.project.im.domain.create.AddGroupMember;
import com.ruoyi.project.im.mapper.XtGroupMapper;
import com.ruoyi.project.im.mapper.XtUserToGroupMapper;
import com.ruoyi.project.im.service.ChatService;

import com.ruoyi.project.im.service.XtChatMessageService;
import com.ruoyi.project.im.util.ChatUtils;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.XtUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author by hujun
 * @date 2022-10-24
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private XtCompMapper compMapper;

    @Autowired
    private XtEmployeeMapper employeeMapper;

    @Autowired
    private XtUserMapper userMapper;

    @Autowired
    private XtUserToGroupMapper userToGroupMapper;

    @Autowired
    private XtGroupMapper groupMapper;

    @Autowired
    private XtChatMessageService xtChatMessageService;

    private static final String TOUXIANG = "profile/2023/03/20/20230320100226A005.png";


    /**
     *
     * 参数chatId就是信息里的fromId
     * */
    @Override
    public void resetUnreadAmount(String chatId) {

        AppLoginUser myLoginUser = SecurityUtils.getAppLoginUser();
        String fromId = myLoginUser.getUser().getUserid();

        String fromChatTimeListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_TIME, fromId);
        String fromChatMessageListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_LIST, fromId);
        Object fromLastChatTime = redisTemplate.opsForHash().get(fromChatTimeListKey, chatId);
        if (fromLastChatTime != null) {
            //有过聊天历史
            String lastTime = String.valueOf(fromLastChatTime);
            Long timestamp = Long.valueOf(lastTime);
            Set<String> preChat = redisTemplate.opsForZSet().rangeByScore(fromChatMessageListKey, timestamp, timestamp);
            String preChatString = preChat.iterator().next();
            Chat previousChat = JSON.parseObject(preChatString, Chat.class);

            previousChat.setLoaded(true);
            previousChat.setUnreadCount(0);

            //这里出问题了,重新写入redis中格式出问题了;

            redisTemplate.opsForZSet().removeRangeByScore(fromChatMessageListKey,timestamp,timestamp);
            //插入最新的聊天时间
            redisTemplate.opsForHash().put(fromChatTimeListKey,fromId,timestamp);
            //插入最新的数据
            redisTemplate.opsForZSet().add(fromChatMessageListKey,JSON.toJSONString(previousChat),timestamp);
        }


    }

    @Override
    public void resetGroupUnreadAmount(String chatId) {
        //重置群消息的未读数,判断逻辑和单聊的不一样;
        AppLoginUser myLoginUser = SecurityUtils.getAppLoginUser();

        String fromId = myLoginUser.getUser().getUserid();
        String fromChatTimeListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_TIME, fromId);
        String fromChatMessageListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_LIST, fromId);

        Object fromLastChatTime = redisTemplate.opsForHash().get(fromChatTimeListKey, chatId);
        if (fromLastChatTime != null) {
            //有过聊天历史
            String lastTime = String.valueOf(fromLastChatTime);
            Long timestamp = Long.valueOf(lastTime);
            Set<String> preChat = redisTemplate.opsForZSet().rangeByScore(fromChatMessageListKey, timestamp, timestamp);
            String preChatString = preChat.iterator().next();
            Chat previousChat = JSON.parseObject(preChatString, Chat.class);
            previousChat.setLoaded(true);
            previousChat.setUnreadCount(0);

            redisTemplate.opsForZSet().removeRangeByScore(fromChatMessageListKey,timestamp,timestamp);
            //插入最新的聊天时间
            redisTemplate.opsForHash().put(fromChatTimeListKey,fromId,timestamp);
            //插入最新的数据
            redisTemplate.opsForZSet().add(fromChatMessageListKey,JSON.toJSONString(previousChat),timestamp);
        }

    }

    @Override
    public void topChatUser(String chatId, Integer top) {
        //获取该会话
        AppLoginUser myLoginUser = SecurityUtils.getAppLoginUser();
        String myId = myLoginUser.getUser().getUserid();
        String myChatMessageListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_LIST, myId);
        String fromChatTimeListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_TIME, myId);
        Object fromLastChatTime = redisTemplate.opsForHash().get(fromChatTimeListKey, chatId);
        String lastTime = String.valueOf(fromLastChatTime);
        Long timestamp = Long.valueOf(lastTime);
        //根据会话时间拿到这个chat
        Set<String> chat = redisTemplate.opsForZSet().rangeByScore(myChatMessageListKey, timestamp, timestamp);
        String chatString = chat.iterator().next();
        Chat topChat = JSON.parseObject(chatString, Chat.class);
        //设为置顶
        topChat.setTop(1);
        //先删除原来那个
        redisTemplate.opsForZSet().removeRangeByScore(myChatMessageListKey,timestamp,timestamp);

        //再保存进会话列表中
        redisTemplate.opsForZSet().add(myChatMessageListKey,JSON.toJSONString(topChat),timestamp);


    }

    @Override
    public void resetStatus(String chatId, Integer status) {
        //获取该会话
        AppLoginUser myLoginUser = SecurityUtils.getAppLoginUser();
        String myId = myLoginUser.getUser().getUserid();
        String myChatMessageListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_LIST, myId);
        String fromChatTimeListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_TIME, myId);
        Object fromLastChatTime = redisTemplate.opsForHash().get(fromChatTimeListKey, chatId);
        String lastTime = String.valueOf(fromLastChatTime);
        Long timestamp = Long.valueOf(lastTime);
        //根据会话时间拿到这个chat
        Set<String> chat = redisTemplate.opsForZSet().rangeByScore(myChatMessageListKey, timestamp, timestamp);
        String chatString = chat.iterator().next();
        Chat topChat = JSON.parseObject(chatString, Chat.class);
        //设为对应的状态
        topChat.setStatus(1);
        //先删除原来那个
        redisTemplate.opsForZSet().removeRangeByScore(myChatMessageListKey,timestamp,timestamp);

        //再保存进会话列表中
        redisTemplate.opsForZSet().add(myChatMessageListKey,JSON.toJSONString(topChat),timestamp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createGroup(String[] guardianIds, String[] doctorIds) {

        List<String> doctors = Arrays.asList(doctorIds);
        if (!CollectionUtils.isEmpty(doctors)) {
            for (int i = 0; i < doctors.size(); i++) {
                List<String> groupIds = new ArrayList<>();
                XtUser user = userMapper.selectXtUserByUserid(doctors.get(i));
                if (user == null){
//                    throw new ServiceException("该医生不存在");
                }
                //根据医生信息,查找商家信息
                XtEmployee employee = employeeMapper.selectXtEmployeeByUserId(doctors.get(i));
                String compId = employee.getUcompid();
                //医院信息
                XtComp xtComp = compMapper.selectXtCompByUcompid(compId);

                //创建群
                XtGroup group = new XtGroup();
                group.setUgroupid(IdUtil.getSnowflakeNextIdStr());

                group.setUcompid(xtComp.getUcompid());
                group.setUdepartmentid(employee.getUdepartmentid());
                //设置头像(得整一个默认的群头像,要不然都是用户的,群分不开)
                String avatar = user.getAvatar();
                if (avatar != null) {
                    List<String> url = StringToURL.toURL(avatar);
                    group.setUgroupavatar(url.get(0));
                } else {
                    group.setUgroupavatar("");
                }



                group.setCreateTime(new Date());



                groupIds.add(doctors.get(i));

                //把监护人拉入群中
                List<String> guardians = Arrays.asList(guardianIds);
                if (!CollectionUtils.isEmpty(guardians)) {
                    for (int j = 0; j < guardians.size(); j++) {
                        XtUserToGroup userToGroup = new XtUserToGroup();
                        userToGroup.setUsergroupid(IdUtil.getSnowflakeNextIdStr());
                        userToGroup.setCreatTime(new Date());
                        userToGroup.setUserid(guardians.get(j));
                        userToGroup.setUgroupid(group.getUgroupid());
                        userToGroupMapper.insertXtUserToGroup(userToGroup);
                        //
                        groupIds.add(guardians.get(j));
                    }
                }

                //把医生拉入群中
                XtUserToGroup userToGroup = new XtUserToGroup();
                userToGroup.setUsergroupid(IdUtil.getSnowflakeNextIdStr());
                userToGroup.setCreatTime(new Date());
                userToGroup.setUserid(doctors.get(i));
                userToGroup.setUgroupid(group.getUgroupid());
                userToGroupMapper.insertXtUserToGroup(userToGroup);
                //发送默认信息
//                sendDefaultMessage(group.getUgroupid(),doctors.get(i));

                //把自己拉入群中
                XtUserToGroup userToGroup2 = new XtUserToGroup();
                userToGroup2.setUsergroupid(IdUtil.getSnowflakeNextIdStr());
                userToGroup2.setCreatTime(new Date());
                String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
                userToGroup2.setUserid(userid);
                userToGroup2.setUgroupid(group.getUgroupid());
                userToGroupMapper.insertXtUserToGroup(userToGroup2);

                XtUser my = userMapper.selectXtUserByUserid(userid);

                //群聊名称(多人的姓名)
                group.setUgroupname(my.getUsername() +","+user.getUsername());
                groupMapper.insertXtGroup(group);

                //发送默认信息(只有自己才会发出一条默认消息)
                try {
                    sendDefaultMessage(group.getUgroupid(),userid,groupIds,my.getUsername(),user.getUsername());
                } catch (Exception e) {
                    log.info("发送默认消息失败");
                    throw new ServiceException("创建失败,请重试;");
                }
                //创建完成后,在群里发送一条默认消息:(医生您好)

            }
        }else {
            throw new ServiceException("请选择医生");
        }

    }

    @Override
    public List<UserVo> groupMemberList(String groupId) {
        List<UserVo> userVos = new ArrayList<>();

        XtUserToGroup xtUserToGroup = new XtUserToGroup();
        xtUserToGroup.setUgroupid(groupId);
        List<XtUserToGroup> xtUserToGroups = userToGroupMapper.selectXtUserToGroupList(xtUserToGroup);
        if (!CollectionUtils.isEmpty(xtUserToGroups)){
            for (int i = 0; i < xtUserToGroups.size(); i++) {
                UserVo userVo = new UserVo();
                String userid = xtUserToGroups.get(i).getUserid();
                XtUser xtUser = userMapper.selectXtUserByUserid(userid);
                userVo.setUserId(userid);
                userVo.setUserName(xtUser.getUsername());
                List<String> url = StringToURL.toURL(xtUser.getAvatar());
                userVo.setAvatar(url.get(0));
                userVos.add(userVo);
            }
        }
        return userVos;
    }

    @Override
    public void addGroupMember(AddGroupMember addGroupMember) {

        XtUserToGroup userToGroup = new XtUserToGroup();
        userToGroup.setUgroupid(addGroupMember.getGroupId());
        userToGroup.setUserid(addGroupMember.getUserId());
        userToGroup.setCreatTime(new Date());
        int count = userToGroupMapper.insertXtUserToGroup(userToGroup);
        if (count == 0){
            throw  new ServiceException("添加失败");
        }
    }

    @Override
    public void delGroupMember(AddGroupMember addGroupMember) {

        int count = userToGroupMapper.deleteXtUserToGroupByUsergroupidAndUserId(addGroupMember);
        if (count == 0){
            throw new ServiceException("删除失败");
        }


    }



    private void sendDefaultMessage(String groupId,String fromId,List<String> groupIds,String myName,String doctorName) throws Exception {
        XtUser xtUser = userMapper.selectXtUserByUserid(fromId);

        //设置默认信息
        XtChatMessage message  = new XtChatMessage();
        message.setMessageId(IdUtil.getSnowflakeNextIdStr());
        message.setFromId(fromId);
        message.setToId(groupId);
        message.setContent("医生,你好");
        message.setMessageType(ChatUtils.MESSAGE_TYPE_GROUP);
        message.setCreateTime(System.currentTimeMillis());
        message.setName(xtUser.getUsername());

        //设置头像
        String avatar = xtUser.getAvatar();
        if (avatar != null) {
            List<String> url = StringToURL.toURL(avatar);
            message.setAvatar(url.get(0));
        } else {
            message.setAvatar("");
        }


        //记录群聊消息
        xtChatMessageService.saveAllGroupMessage(message);

        //保存这条会话
        Chat chat = new Chat();
        chat.setType(message.getMessageType());
        chat.setUnreadCount(1);
        chat.setLoaded(false);
        //群号
        chat.setChatId(groupId);

        chat.setFromId(message.getFromId());
        //群名
        chat.setName(myName + "," + doctorName);
        //群头像
        chat.setAvatar(message.getAvatar());
        chat.setLastMessage(message);
        //默认不置顶
        chat.setTop(0);
        chat.setStatus(0);
        chat.setLastSendTime(message.getCreateTime());
        //需要改写这个方法(要不然,在事务提交之前查不到相应的群中用户)

        //保存会话这个方法,需要重新实现一下;
        //传的是chat和对方的userid(陪护人ids和医生id)

        xtChatMessageService.saveDefaultGroupMessageChat(chat,groupIds);
//        xtChatMessageService.saveGroupChat(chat);

    }

    @Override
    public List<Chat> queryUserList(Integer pageNum, Integer pageSize) {
        int start = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize - 1;
        AppLoginUser myLoginUser = SecurityUtils.getAppLoginUser();
        String myId = myLoginUser.getUser().getUserid();
        String myChatMessageListKey = StrUtil.format(IMConstant.USER_LAST_CHAT_LIST, myId);
        Set<String> chatListSet = redisTemplate.opsForZSet().reverseRange(myChatMessageListKey, start, end);
        if (CollectionUtils.isEmpty(chatListSet)){
            return new ArrayList<>();
        }
        List<Chat> chats = JSON.parseArray(chatListSet.toString(), Chat.class);

        //置顶处理
        for (int i = 0; i < chats.size(); i++) {
           Chat top  = chats.get(i);
           //转换头像链接
            String avatar = top.getAvatar();
            List<String> strings = StringToURL.toURL(avatar);
            top.setAvatar(strings.get(0));

            if (top.getTop().equals(1)){
                chats.remove(i);
                chats.add(0,top);
            }
        }
        return chats;
    }

    @Override
    public List<Chat> queryGroupList(Integer pageNum, Integer pageSize) {
        int start = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize - 1;
        AppLoginUser myLoginUser = SecurityUtils.getAppLoginUser();
        String myId = myLoginUser.getUser().getUserid();
        String myChatMessageListKey = StrUtil.format(IMConstant.GROUP_LAST_CHAT_LIST, myId);
        Set<String> chatListSet = redisTemplate.opsForZSet().reverseRange(myChatMessageListKey, start, end);
        if (CollectionUtils.isEmpty(chatListSet)){
            return new ArrayList<>();
        }
        List<Chat> chats = JSON.parseArray(chatListSet.toString(), Chat.class);
        return chats;
    }



























    @Autowired
    private XtChatMessageMapper xtChatMessageMapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public String creatQun(String[] userids , String weizhi , String name , String compid) {

        Qun qun = new Qun();

//        qun.setQunzhuid(SecurityUtils.getAppLoginUser().getUser().getUserid());
        String qunId = IdUtil.getSnowflakeNextIdStr();
        qun.setId(qunId);
        qun.setQuntouxiang(TOUXIANG);
        qun.setQunmingzi(name);
        qun.setWeizhi(weizhi);
        qun.setCompid(compid);
        xtChatMessageMapper.creatQun(qun);

        for (String id:
                userids) {
            XtQunConnetMember xtQunConnetMember = new XtQunConnetMember();
            String  qunConnectMemberId = IdUtil.getSnowflakeNextIdStr();

            xtQunConnetMember.setUqunConnectMemberId(qunConnectMemberId);
            xtQunConnetMember.setUqunId(qunId);
            xtQunConnetMember.setUmemberId(xtChatMessageMapper.selectMemberIdByUserId(id));
            xtChatMessageMapper.creatXtQunConnetMember(xtQunConnetMember);
        }

        return qunId;
    }

    @Override
    public String addchengyuangs(String[] chengyuans , String qunid) {

        for (String i:
             chengyuans) {
            XtQunConnetMember xtQunConnetMember = new XtQunConnetMember();
            xtQunConnetMember.setUqunConnectMemberId(IdUtil.getSnowflakeNextIdStr());
            xtQunConnetMember.setUqunId(qunid);
            xtQunConnetMember.setUmemberId(i);
            xtChatMessageMapper.creatXtQunConnetMember(xtQunConnetMember);
        }

        return "成功";
    }

    @Override
    public String delChengYuangs(String[] chengyuans, String qunid) {

        for (String i:
                chengyuans) {
            xtChatMessageMapper.delChengYaungById(qunid,i);
        }

        return "成功";
    }

    @Override
    public Qun selectQunByCompIdAndQunmingzi(String compId, String qunMingZi) {
        List<Qun> quns = xtChatMessageMapper.selectQunByCompIdAndQunmingzi(compId, qunMingZi);
        if(quns.isEmpty()){
            throw new ServiceException("没有查到群");
        }
        return quns.get(0);
    }



}
