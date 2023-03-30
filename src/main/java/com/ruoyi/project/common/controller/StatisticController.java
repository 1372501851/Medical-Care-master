package com.ruoyi.project.common.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.domain.statistic.Statistic;
import com.ruoyi.project.common.service.StatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/2/3
 */
@Api(tags = "统计模块")
@RestController
@RequestMapping("/statisticApi")
public class StatisticController extends BaseController {

    @Autowired
    private StatisticService statisticService;

    @ApiOperation(value = "统计")
    @GetMapping("/statistic")
    public AjaxResult getStatisticTable(){
        return AjaxResult.success(statisticService.getStatisticTable2());
    }

}
