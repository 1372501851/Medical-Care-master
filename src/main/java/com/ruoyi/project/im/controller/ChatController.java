package com.ruoyi.project.im.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.im.domain.Chat;
import com.ruoyi.project.im.domain.Qun;
import com.ruoyi.project.im.domain.Vo.UserVo;
import com.ruoyi.project.im.domain.XtMember;
import com.ruoyi.project.im.domain.create.AddGroupMember;
import com.ruoyi.project.im.domain.create.CreateGroup;
import com.ruoyi.project.im.mapper.XtChatMessageMapper;
import com.ruoyi.project.im.service.ChatService;
import com.ruoyi.project.im.service.IXtGroupService;
import com.ruoyi.project.im.service.IXtUserToGroupService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.user.domain.XtUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-10-24
 */


@RestController
@RequestMapping("/chat")
@Api(tags = "聊天会话模块")

public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private IXtGroupService groupService;

    @Autowired
    private IXtUserToGroupService userToGroupService;

    @Autowired
    private XtChatMessageMapper xtChatMessageMapper;


    /**
     *
     * 获取聊过天的好友列表
     *
     * */
    @ApiOperation(value = "获取聊过天的用户列表")
    @GetMapping(value = "/userList")
    public AjaxResult queryUserList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer pageSize){
        List<Chat> chats = chatService.queryUserList(pageNum, pageSize);
        return AjaxResult.success(chats);
    }

    /**
     * 获取群列表
     * (这样的话,记录最后一条消息信息,就需要分开来处理了.放在私聊中的话,无法判断是私聊还是群聊)
     * */

    @ApiOperation(value = "获取聊过天的群列表")
    @GetMapping(value = "/groupList")
    public AjaxResult queryGroupList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer pageSize){
        List<Chat> chats = chatService.queryGroupList(pageNum, pageSize);
        return AjaxResult.success(chats);
    }


    /**
     * 删除对话框
     *
     * */

//    @ApiOperation(value = "删除聊天框删除缓存历史消息接口")
    @GetMapping("/dele/redis")
    public AjaxResult deleteToRedis(){

        return AjaxResult.success();
    }



    /**
     * 重置未读消息数
     *
     * */
    @ApiOperation(value = "重置私聊消息未读数接口")
    @GetMapping(value = "/resetUser")
    public AjaxResult resetUserUnreadAmount(String chatId){
        //chatId(用户Id)
        chatService.resetUnreadAmount(chatId);
        return AjaxResult.success();
    }


    /**
     *
     * 重置群消息未读数
     * */
    @ApiOperation(value = "重置群聊消息未读数接口")
    @GetMapping(value = "/resetGroup")
    public AjaxResult resetGroupUnreadAmount(String chatId){
        //chatId(群Id)
        chatService.resetGroupUnreadAmount(chatId);
        return AjaxResult.success();
    }


    /**
     *置顶会话
     * */
    @ApiOperation(value = "置顶私聊会话")
    @GetMapping("/top/user")
    public  AjaxResult topChatUser(String chatId,Integer top){
        chatService.topChatUser(chatId,top);
        return AjaxResult.success();
    }


    /**接单*/
    @ApiOperation(value = "接单")
    @GetMapping("/status")
    public AjaxResult status(String chatId,Integer status){
        chatService.resetStatus(chatId,status);
        return AjaxResult.success();
    }



    /**创建群聊*/
    @ApiOperation(value = "创建群聊")
    @PostMapping("/creatGroup")
    public AjaxResult creatGroup(@RequestBody CreateGroup createGroup){
        chatService.createGroup(createGroup.getGuardianIds(),createGroup.getDoctorIds());
        return AjaxResult.success();
    }


    /**查看群成员*/


    @ApiOperation(value = "查看群成员")
    @GetMapping("/GroupMember")
    public AjaxResult groupMember(String groupId){
        List<UserVo> userVos = chatService.groupMemberList(groupId);
        return AjaxResult.success(userVos);
    }


    /**添加群成员*/

    @ApiOperation(value = "添加群成员")
    @PostMapping("/addGroupMember")
    public AjaxResult addGroupMember(@RequestBody AddGroupMember addGroupMember){
        chatService.addGroupMember(addGroupMember);
        return AjaxResult.success();
    }





    /**移除群成员*/
    @ApiOperation(value = "移除群成员")
    @PostMapping("/delGroupMember")
    public AjaxResult delGroupMember(@RequestBody AddGroupMember addGroupMember){
        chatService.delGroupMember(addGroupMember);
        return AjaxResult.success();
    }

    @ApiOperation(value = "创建群聊2")
    @PostMapping("/creatQun")
    public AjaxResult creatQun(@RequestBody String[] userids , String weizhi,String name,String ucompid){
        String s = chatService.creatQun(userids, weizhi, name, ucompid);
        return AjaxResult.success(s);
    }

    @ApiOperation(value = "创建群聊3")
    @PostMapping("/creatQun3")
    public AjaxResult creatQun3(@RequestBody String[] userids , String weizhi,String name,String ucompid){

        XtUser user = SecurityUtils.getAppLoginUser().getUser();
        String userid = user.getUserid();
        String s1 = xtChatMessageMapper.selectMemberIdByUserId(userid);



        if("".equals(s1) || s1 == null){
            XtMember xtMember = new XtMember();
            xtMember.setUserId(userid);
            xtMember.setUmemberId(IdUtil.getSnowflakeNextIdStr());
            Integer integer = xtChatMessageMapper.creatXtMember(xtMember);
        }

        String uloginname = "";
        try {
            uloginname = user.getUloginname();
        }catch (NullPointerException n){
            throw new ServiceException("你还没有登录");
        }
        name = name+uloginname;

        String s = chatService.creatQun(userids, weizhi, name, ucompid);
        return AjaxResult.success(s);
    }

    @ApiOperation(value = "添加群成员2(拉人)")
    @PostMapping("/addchengyuangs")
    public AjaxResult addchengyuangs(@RequestBody String[] chengyuans , String qunid){
        chatService.addchengyuangs(chengyuans , qunid);
        return AjaxResult.success();
    }

    @ApiOperation(value = "移除群成员2（踢人）")
    @PostMapping("/delChengYuangs")
    public AjaxResult delChengYuangs(@RequestBody String[] chengyuans , String qunid){
        chatService.delChengYuangs(chengyuans , qunid);
        return AjaxResult.success();
    }

    @ApiOperation(value = "查看群成员2")
    @GetMapping("/chakangChengYuan/{qunId}")
    public AjaxResult chakangChengYuan(@PathVariable String qunId){
        List<String> strings = xtChatMessageMapper.seleChengYuanId(qunId);
        return AjaxResult.success(strings);
    }

    @ApiOperation(value = "查看群,通过商家，群位置")
    @GetMapping("/selectQunByCompIdAndQunmingzi")
    public AjaxResult selectQunByCompIdAndQunmingziAndWeizhi(String compid,String weizhi){
        List<Qun> qun = xtChatMessageMapper.selectQunByCompIdAndQunmingziAndWeizhi(compid, weizhi);
        HashSet<Qun> quns = new HashSet<>();

        for (Qun q:
                qun) {
            quns.add(q);
        }

        return AjaxResult.success(quns);
    }

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @ApiOperation(value = "拿到员工详细信息")
    @GetMapping ("/getEmployInfoByUserId")
    public AjaxResult getEmployInfoByUserId( String[] userid){
        List<XtEmployee> list = new ArrayList<>();
        for (String id:
                userid) {
            XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUserId2(id);
            list.add(xtEmployee);
        }

        return AjaxResult.success(list);
    }

    @ApiOperation(value = "员工详细信息")
    @GetMapping ("/getcompid")
    public AjaxResult getcompid(){
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUserId(userid);
        return AjaxResult.success(xtEmployee);
    }



}
