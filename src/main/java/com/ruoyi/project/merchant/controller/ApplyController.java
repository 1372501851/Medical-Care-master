package com.ruoyi.project.merchant.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.dto.merchant.ApplyMemberDto;
import com.ruoyi.project.merchant.domain.dto.merchant.MerchantsApplyDto;
import com.ruoyi.project.merchant.service.ApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by hujun
 * @date 2022-11-08
 */
@Api(tags = "商家/员工:申请/审核模块")
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;


    /**
     * 填写申请信息
     *用户信息
     *填写商家信息
     * */

    @ApiOperation(value = "商家申请接口")
    @PostMapping("/addMerchant")
    public AjaxResult addMerchant(@RequestBody MerchantsApplyDto applyDto){
        //需要加一个表,记录申请信息,用户查询申请状态,管理员进行审核

    applyService.applyMerchant(applyDto);
        //审核完成后,需要同时插入这两张表

        return AjaxResult.success("审核提交成功");

    }

    /**
     *
     *员工申请接口
     *
     * */

    @ApiOperation(value = "员工申请接口")
    @PostMapping("/addMember")
    public AjaxResult addMember(@Validated @RequestBody ApplyMemberDto applyMemberDto){
        //需要加一个表,记录申请信息,用户查询申请状态,管理员进行审核
        applyService.applyMember(applyMemberDto);
        //审核完成后,需要同时插入这两张表
        return AjaxResult.success("审核提交成功");
    }


    /**
     *
     *商家审核
     * */
    @ApiOperation(value = "商家审核接口")
    @GetMapping("/auditMerchant")
    public AjaxResult auditMerchant(String compnyId,String employeeId,String status){
        //需要加一个表,记录申请信息,用户查询申请状态,管理员进行审核
        applyService.auditMerchant(compnyId,employeeId,status);
        //审核完成后,需要同时插入这两张表
        return AjaxResult.success();
    }


    /**
     * 员工审核
     *
     * */

    @ApiOperation(value = "员工审核接口")
    @GetMapping("/auditMember")
    public AjaxResult auditMember(String employeeId,String status){
        //需要加一个表,记录申请信息,用户查询申请状态,管理员进行审核
        applyService.auditMember(employeeId,status);
        //审核完成后,需要同时插入这两张表
        return AjaxResult.success();
    }


    /**
     *   申请商家
     */
//    @ApiOperation(value = "申请商家")
    @Log(title = "申请商家", businessType = BusinessType.INSERT)
    @PostMapping("/listcomp")
    public AjaxResult addcomp(String userid) {
        return  applyService.addcomp(userid);

    }

    /**
     *  商家用户待审核
     */
//    @ApiOperation(value = "商家用户待审核")
    @Log(title = "商家用户待审核", businessType = BusinessType.INSERT)
    @GetMapping("/addcomp")
    public List<XtComp> addcomp() {
        return  applyService.selectcomp();
    }


    /**
     *   商家用户审核
     */
//    @ApiOperation(value = "商家用户审核模块")
    @Log(title = "商家用户审核模块", businessType = BusinessType.INSERT)
    @PutMapping("/auditcomp")
    public AjaxResult auditcomp(String compid,String status) {
          applyService.auditcomp(compid,status);
          return AjaxResult.success();
    }

}
