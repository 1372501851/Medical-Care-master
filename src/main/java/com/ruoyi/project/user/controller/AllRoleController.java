package com.ruoyi.project.user.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.user.domain.XtAllRole;
import com.ruoyi.project.user.domain.XtAllRoleToMenus;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.XtRoldDao;
import com.ruoyi.project.user.service.IallRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author by hujun
 * @date 2022-12-30
 */
@Api(tags = "商家的全局角色")
@RestController
@RequestMapping("/web/AllRole")
public class AllRoleController extends BaseController {

    @Autowired
    private IallRoleService iallRoleService;

    /**
     * 新增角色菜单权限
     */
    @ApiOperation(value = " 新增角色菜单权限")
    @Log(title = "新增角色菜单权限", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtAllRoleToMenus xtAllRoleToMenus) {
        return toAjax(iallRoleService.insertXtAllRoleToMenu(xtAllRoleToMenus));
    }

    /**
     * 新增全局角色
     */
    @ApiOperation(value = " 新增全局角色")
    @Log(title = "新增全局角色", businessType = BusinessType.INSERT)
    @PostMapping("/addrole")
    public AjaxResult addrole(@Validated @RequestBody XtAllRole xtAllRole) {
        return  iallRoleService.addrole(xtAllRole);
    }

    /**
     * 修改全局角色
     */
    @ApiOperation(value = " 修改全局角色")
    @Log(title = "修改全局角色", businessType = BusinessType.INSERT)
    @PutMapping("/revamprole")
    public AjaxResult revamprole(@RequestBody XtAllRole xtAllRole) {
        return  iallRoleService.revamprole(xtAllRole);
    }


    /**
     *  商家授权
     */
    @ApiOperation(value = "商家授权")
    @Log(title = "商家授权", businessType = BusinessType.INSERT)
    @PostMapping ("/rolemenu")
    public AjaxResult accredit(int id,int munuid) {
        iallRoleService.accredit(id,munuid);
        return  AjaxResult.success();
    }

    /**
     * 修改商家授权
     */
    @ApiOperation(value = "修改商家授权")
    @Log(title = "修改商家授权", businessType = BusinessType.INSERT)
    @PutMapping("/revamprolemenu")
    public AjaxResult revampaccredit(int id,int munuid) {
        iallRoleService.revampaccredit(id,munuid);
        return  AjaxResult.success();
    }

    /**
     * 申请商户用户
     */
    @ApiOperation(value = "申请商户用户")
    @Log(title = "申请商户用户", businessType = BusinessType.INSERT)
    @PostMapping("/employee")
    public AjaxResult addemployee(XtEmployee xtEmployee) {
        iallRoleService.addemployee(xtEmployee);
        return  AjaxResult.success();
    }





    /**
     * 查询商家用户
     */
    @ApiOperation(value = "查询申请商家")
    @Log(title = "查询申请商家", businessType = BusinessType.INSERT)
    @GetMapping("/comp/{userid}")
    public AjaxResult querycomp(@PathVariable("userid") String userid) {
        XtEmployee xtEmployee =  iallRoleService.quercomp(userid);
        return AjaxResult.success(xtEmployee);
    }


    /**
     *  新增商家部门接口
     */
    @ApiOperation(value = "新增商家部门")
    @Log(title = "新增商家部门", businessType = BusinessType.INSERT)
    @PostMapping("/adddepartment")
    public AjaxResult adddepartment(XtDepartment xtDepartment) {
        return iallRoleService.adddepartment(xtDepartment);

    }

    /**
     *  查询商家部门
     */
    @ApiOperation(value = "查询商家部门")
    @Log(title = "查询商家部门", businessType = BusinessType.INSERT)
    @GetMapping ("/adddepartment/{userid}")
    public AjaxResult querydepartment(@PathVariable("userid") String userid) {
        XtDepartment xtDepartment = iallRoleService.querydepartment(userid);
        return AjaxResult.success(xtDepartment);
    }


    /**
     * 新增商家角色并给角色授权
     */
    @ApiOperation(value = "新增商家角色并给角色授权")
    @Log(title = "新增商家角色并给角色授权", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult insertt(long menuid, XtRoldDao xtRoleDao) {
        return  iallRoleService.accreditdepartment(xtRoleDao,menuid);
    }
}
