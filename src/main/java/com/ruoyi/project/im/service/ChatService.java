package com.ruoyi.project.im.service;

import com.ruoyi.project.im.domain.Chat;
import com.ruoyi.project.im.domain.Qun;
import com.ruoyi.project.im.domain.Vo.UserVo;
import com.ruoyi.project.im.domain.create.AddGroupMember;

import java.util.List;

public interface ChatService {
    void resetUnreadAmount(String chatId);

    List<Chat> queryUserList(Integer pageNum, Integer pageSize);

    List<Chat> queryGroupList(Integer pageNum, Integer pageSize);

    void resetGroupUnreadAmount(String chatId);

    void topChatUser(String chatId, Integer top);

    void resetStatus(String chatId, Integer status);

    void createGroup(String[] guardianIds, String[] doctorIds) ;

    List<UserVo> groupMemberList(String groupId);

    void addGroupMember(AddGroupMember addGroupMember);

    void delGroupMember(AddGroupMember addGroupMember);

    String creatQun(String[] userids , String weizhi , String name , String compid);

    String addchengyuangs(String[] chengyuans , String qunid);


    String delChengYuangs(String[] chengyuans , String qunid);

    Qun selectQunByCompIdAndQunmingzi(String compId , String qunMingZi);



}
