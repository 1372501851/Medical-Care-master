package com.ruoyi.project.agent.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.agent.domain.Agent;
import com.ruoyi.project.agent.mapper.AgentMapper;
import com.ruoyi.project.agent.service.AgentService;
import com.ruoyi.project.approval.domain.XtFlowmaster;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author by hujun
 * @date 2023-02-15
 */
@Api(tags = "渠道管理（代理模块）")
@RestController
@RequestMapping("/Agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentMapper agentMapper;

    @ApiOperation(value = "增加代理")
    @PostMapping("/addAgent")
    public AjaxResult  addAgent(@RequestBody Agent agent)
    {
        return AjaxResult.success(agentService.addAgent(agent));
    }

    @ApiOperation(value = "查看代理列表")
    @GetMapping("/addAgent")
    public AjaxResult  seleListAgent(String agtype)
    {
        return AjaxResult.success(agentService.seleListAgent(agtype));
    }

    @ApiOperation(value = "查看代理详细信息")
    @GetMapping("/seleAgentById")
    public AjaxResult  seleAgentById(String id)
    {
        return AjaxResult.success(agentService.seleAgentById(id));
    }

    @ApiOperation(value = "模糊查找代理")
    @GetMapping("/seleLikeAgentByName")
    public AjaxResult  seleLikeAgentByName(String name , String agtype)
    {
        return AjaxResult.success(agentMapper.seleLikeAgent("%"+name+"%" , agtype));
    }


}
