package com.ruoyi.project.find.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.find.domain.query.QueryModel;
import com.ruoyi.project.find.domain.query.QueryWork;
import com.ruoyi.project.find.domain.vo.OrderStatus;
import com.ruoyi.project.find.domain.vo.RobOrderVo;
import com.ruoyi.project.find.domain.vo.WorkVo;
import com.ruoyi.project.find.service.IFindModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author by hujun
 * @date 2022-12-05
 */

@Api(tags = "寻找护工")
@RestController
@RequestMapping("/find/work")
public class FindWorkController {
    @Autowired
    private IFindModelService findModelService;

    /**护工列表*/

    @ApiOperation(value = "护工列表")
    @GetMapping("/workList")
    public AjaxResult workList(QueryWork queryWork){
        List<WorkVo> workVos = findModelService.queryWorkList(queryWork);
        return AjaxResult.success(workVos);
    }


    @ApiOperation(value = "获取护工抢单列表")
    @GetMapping("/rob/list")
    public AjaxResult robList(QueryModel queryModel)
    {
        //这里需要额外处理抢单列表的展示数据
        List<RobOrderVo> list = findModelService.selectFindModelRobList(queryModel);
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "状态查询列表")
    @GetMapping("/status/list")
    public AjaxResult status()
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        List<OrderStatus> robOrderVos = findModelService.status(userid);
        return AjaxResult.success(robOrderVos);
    }


    /**共享护工,查询所服务的患者列表*/
    @ApiOperation(value = "护工所服务的患者列表")
    @GetMapping("/patientsList")
    public AjaxResult workList(){
        List<WorkVo> workVos = findModelService.patientsList();
        return AjaxResult.success(workVos);
    }

}
