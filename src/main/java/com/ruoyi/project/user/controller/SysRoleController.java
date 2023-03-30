package com.ruoyi.project.user.controller;

import java.util.List;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.login.TokenService.WebLoginTokenService;
import com.ruoyi.project.login.security.permissionService.WebPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.user.domain.SysRole;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.user.domain.SysUserRole;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.user.service.ISysRoleService;
import com.ruoyi.project.system.service.ISysUserService;

import javax.servlet.http.HttpServletResponse;

/**
 * 角色信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/web/role")
@Api(tags = "Web角色管理模块")
public class SysRoleController extends BaseController
{
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private WebLoginTokenService webLoginTokenService;

    @Autowired
    private WebPermissionService permissionService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;





    /**
     *
     *
     * 角色列表
     *
     * */
    @GetMapping("/list")
    @ApiOperation(value = "获取角色列表")
    public TableDataInfo list( SysRole role)
    {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @ApiOperation(value = "获取角色下拉列表")
    @GetMapping("/selectList")
    public AjaxResult getSelectList() {
        return AjaxResult.success(roleService.getSelectList());
    }


    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, SysRole role)
    {
        List<SysRole> list = roleService.selectRoleList(role);
        ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
        util.exportExcel(response, list, "角色数据");
    }


    /**
     * 根据角色编号获取详细信息
     */
    @ApiOperation(value = "根据角色编号获取详细信息")
    @GetMapping(value = "/info/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId)
    {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }


    /**
     * 新增角色
     */
    @ApiOperation(value = "新增角色")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody SysRole role)
    {
        //效验角色名称
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        //效验角色roleKey()
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }


         //这样操作的话,创建者的商户号,通过前端来传更加方便;
        return toAjax(roleService.insertRole(role));

    }



    /**
     * 修改保存角色
     */
    @ApiOperation(value = "修改角色")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody SysRole role)
    {
        //这里需要变动,修改为动态的,而不是固定为1
        roleService.checkRoleAllowed(role);
        //如果只有管理员可以操作角色的话,不需要检查数据权限

        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }

        if (roleService.updateRole(role) > 0)
        {
            //管理员更新后,不会有刷新用户信息的操作,其他用户需要重新登录后再使用
            return AjaxResult.success();
        }
        return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }


    /**
     * 修改保存数据权限
     * 这个不需要,需要的时候再搞
     */
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @PutMapping("/dataScope")
//    public AjaxResult dataScope(@RequestBody SysRole role)
//    {
//        roleService.checkRoleAllowed(role);
//        roleService.checkRoleDataScope(role.getRoleId());
//        return toAjax(roleService.authDataScope(role));
//    }


    /**
     * 状态修改
     */
    @ApiOperation(value = "修改角色状态")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);

        if (roleService.updateRoleStatus(role) > 0)
        {
            return AjaxResult.success();
        }
        return AjaxResult.error("修改角色'" + role.getRoleName() + "'状态失败，请重试");
    }

    /**
     * 删除角色
     *
     * 这个涉及到多表删除,因为角色关联了菜单表和部门表
     */
    @ApiOperation(value = "删除角色(可批量)")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }


    /**
     * 获取角色选择框列表
     */
    @ApiOperation(value = "获取角色选择框列表" )
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        return AjaxResult.success(
                roleService.selectRoleAll());
    }


    /**
     * 查询已分配用户角色列表
     */
    @GetMapping("/authUser/allocatedList")
    @ApiOperation(value = "查询已分配用户角色列表")
    public TableDataInfo allocatedList(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }


    /**
     * 查询未分配用户角色列表
     */
    @GetMapping("/authUser/unallocatedList")
    @ApiOperation(value = "查询未分配用户角色列表")
    public TableDataInfo unallocatedList(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }


    /**
     * 取消授权用户
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancel")
    @ApiOperation(value = "取消授权用户")
    public AjaxResult cancelAuthUser(@RequestBody SysUserRole userRole)
    {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权用户
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/cancelAll")
    @ApiOperation(value = "批量取消授权用户")
    public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds)
    {
        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
    }


    /**
     * 批量选择用户授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser/selectAll")
    @ApiOperation(value = "批量选择用户授权")
    public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds)
    {
        roleService.checkRoleDataScope(roleId);
        return toAjax(roleService.insertAuthUsers(roleId, userIds));
    }


    /**
     * 获取对应角色部门树列表
     */
    @GetMapping(value = "/deptTree/{roleId}")
    public AjaxResult deptTree(@PathVariable("roleId") Long roleId)
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", deptService.selectDeptTreeList(new SysDept()));
        return ajax;
    }



}
