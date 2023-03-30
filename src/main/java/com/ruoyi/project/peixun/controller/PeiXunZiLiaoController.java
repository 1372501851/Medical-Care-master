package com.ruoyi.project.peixun.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.peixun.domain.XtEmployPeiXun;
import com.ruoyi.project.peixun.mapper.XtEmployPeiXunMapper;
import com.ruoyi.project.peixun.mapper.XtPeiXunZiLiaoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-21
 */
@Api(tags = "培训模块")
@RestController
@RequestMapping("/peixun")
public class PeiXunZiLiaoController {

    @Autowired
    private XtPeiXunZiLiaoMapper xtPeiXunZiLiaoMapper;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private XtEmployPeiXunMapper xtEmployPeiXunMapper;

    @ApiOperation(value = "查看所有培训资料")
    @GetMapping("/selectAllPeiXunZiLiao")
    public AjaxResult selectAllPeiXunZiLiao(Integer type)
    {
        return AjaxResult.success(xtPeiXunZiLiaoMapper.selectAllPeiXunZiLiao(type));
    }

    @ApiOperation(value = "添加员工培训打卡")
    @PostMapping("/addXtEmployPeixun")
    public AjaxResult addXtEmployPeixun(@RequestBody XtEmployPeiXun xtEmployPeiXun)
    {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUserId2(userId);

        xtEmployPeiXun.setUtime(new Date());
        xtEmployPeiXun.setId(IdUtil.getSnowflakeNextIdStr());
        xtEmployPeiXun.setEmployid(xtEmployee.getUemployeeid());
        xtEmployPeiXun.setXuexistatus(0);

        return AjaxResult.success(xtEmployPeiXunMapper.addXtEmployPeiXun(xtEmployPeiXun));
    }


    @ApiOperation(value = "查看员工培训学习记录")
    @GetMapping("/selectEmployJiLu")
    public AjaxResult selectEmployJiLu(String employId)
    {
        return AjaxResult.success(xtEmployPeiXunMapper.selectEmployJiLu(employId));
    }

    @ApiOperation(value = "审核员工培训学习记录")
    @GetMapping("/updateEmployJiLu")
    public AjaxResult updateEmployJiLu(String xuexistatus , String id)
    {
        return AjaxResult.success(xtEmployPeiXunMapper.updateEmployJiLu(xuexistatus , id));
    }

//    @ApiOperation(value = "查看资料的名字")
//    @PostMapping("/selectZiLiaoName/{ziliao}")
//    public AjaxResult selectZiLiaoName(@PathVariable String[] ziLiaoId)
//    {
//        List<String> list = new ArrayList<>();
//        for (String x:
//                ziLiaoId) {
//            String s = xtPeiXunZiLiaoMapper.selectZiLiaoName(x);
//            list.add(s);
//        }
//        return AjaxResult.success(list);
//    }

    @ApiOperation(value = "查询资料名字")
    @PostMapping("/selectZiLiaoName/{ziliaoids}")
    public AjaxResult selectCompnmae(@PathVariable String[] ziliaoids) {
        List<String> ob = new ArrayList<>();
        for (String x :
                ziliaoids) {
            String s = xtPeiXunZiLiaoMapper.selectZiLiaoName(x);
            ob.add(s);
        }
        return AjaxResult.success(ob);
    }

}
