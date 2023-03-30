package com.ruoyi.project.advertising.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.advertising.domain.Advertising;
import com.ruoyi.project.advertising.domain.GuangGaoOfGuanLi;
import com.ruoyi.project.advertising.mapper.GuangGaoOfGuanLiMapper;
import com.ruoyi.project.advertising.service.AdvertisingService;
import com.ruoyi.project.agent.domain.Agent;
import com.ruoyi.project.agent.mapper.AgentMapper;
import com.ruoyi.project.agent.service.AgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/GuangGaoOfGuanLi")
@Api(tags = "管理端广告模块")
public class GuangGaoOfGuanLiController {

    @Autowired
    private GuangGaoOfGuanLiMapper guangGaoOfGuanLiMapper;


    @ApiOperation(value = "增加广告")
    @PostMapping("/add")
    public AjaxResult  add(@RequestBody GuangGaoOfGuanLi guangGaoOfGuanLi)
    {
        guangGaoOfGuanLi.setId(IdUtil.getSnowflakeNextIdStr());
        System.out.println(guangGaoOfGuanLi.getId());System.out.println(guangGaoOfGuanLi.getId());System.out.println(guangGaoOfGuanLi.getId());System.out.println(guangGaoOfGuanLi.getId());System.out.println(guangGaoOfGuanLi.getId());System.out.println(guangGaoOfGuanLi.getId());System.out.println(guangGaoOfGuanLi.getId());System.out.println(guangGaoOfGuanLi.getId());
        String userId =   SecurityUtils.getAppLoginUser().getUser().getUserid();
        guangGaoOfGuanLi.setUserid(userId);
        guangGaoOfGuanLi.setStatus("0");
        return AjaxResult.success(guangGaoOfGuanLiMapper.add(guangGaoOfGuanLi));
    }

    @ApiOperation(value = "查询广告")
    @GetMapping("/selectGuangGaoOfGuanLi")
    public AjaxResult  selectGuangGaoOfGuanLi(String type)
    {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        List<GuangGaoOfGuanLi> data = guangGaoOfGuanLiMapper.seleGuangGaoOfGuanLiByUserId(userId,type);
        return AjaxResult.success(data);
    }


}
