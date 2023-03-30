package com.ruoyi.project.im.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.im.domain.XtChatMessage;
import com.ruoyi.project.im.mapper.XtChatMessageMapper;
import com.ruoyi.project.im.service.XtChatMessageService;
import com.ruoyi.project.im.Dto.QueryHistoryMessageDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-10-18
 */


@RestController
@RequestMapping("/msg")
@Api(tags = "聊天信息模块")
public class ChatMessageController {


    @Autowired
    private XtChatMessageService xtChatMessageService;


    /**
     *
     * 获取历史消息
     *
     * */

    @ApiOperation(value = "获取历史消息")
    @PostMapping("/query/history")
    public AjaxResult queryHistoryMesssage(@RequestBody QueryHistoryMessageDto queryHistoryMessageDto , String toId){

        if (queryHistoryMessageDto.getPageNum() == null || queryHistoryMessageDto.getPageSize() == null){
            queryHistoryMessageDto.setPageNum(1);
            queryHistoryMessageDto.setPageSize(10);
        }
        queryHistoryMessageDto.setTimeStamp(System.currentTimeMillis());
        List<XtChatMessage> historyMessagList = xtChatMessageService.queryHistoryMessage(queryHistoryMessageDto , toId);
        return AjaxResult.success(historyMessagList);
    }

    @Autowired
    private XtChatMessageMapper xtChatMessageMapper;
    @ApiOperation(value = "获取历史消息(群聊)")
    @PostMapping("/query/historyOfQun")
    public AjaxResult queryHistoryMesssageOfQun(String qunId){
        List<XtChatMessage> xtChatMessages1 = xtChatMessageMapper.queryHistoryMesssageOfQun(qunId);
        return AjaxResult.success(xtChatMessages1);
    }

    @ApiOperation(value = "后端查看消息记录")
    @GetMapping("/messageList")
    public AjaxResult messageList(@RequestParam("num") Integer num,@RequestParam("size") Integer size){
        PageInfo messageList = xtChatMessageService.getMessageList(num, size);
        return AjaxResult.success(messageList);
    }

    @ApiOperation(value = "删除消息记录")
    @GetMapping("/delMessageList")
    public AjaxResult delMessageList( String[] ids){
        xtChatMessageService.delMessageList(ids);
        return AjaxResult.success();
    }






}
