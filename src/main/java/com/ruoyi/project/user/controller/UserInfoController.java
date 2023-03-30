package com.ruoyi.project.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.user.domain.UserInfo;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.UserInfoMapper;
import com.ruoyi.project.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-11-25
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户信息模块")
public class UserInfoController extends BaseController
{
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;


    /**
     * 查询【请填写功能名称】列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "你妈的，命名都能命错")
    public TableDataInfo list(UserInfo userInfo)
    {
        startPage();
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, UserInfo userInfo)
    {
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "傻逼吧，不写这个纯给我挖坑呢")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(userInfoService.selectUserInfoById(id));
    }

    /**
     * 新增【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增用户信息")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody UserInfo userInfo)
    {
        return toAjax(userInfoService.insertUserInfo(userInfo));
    }

    /**
     * 修改【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改用户信息")
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody UserInfo userInfo)
    {
        int rows = userInfoService.updateUserInfo(userInfo);
        return toAjax(rows);
    }


    @ApiOperation(value = "查询用户信息")
    @GetMapping("/getInfo")
    public AjaxResult getInfo(){
        UserInfo info = userInfoService.getInfo();
        return AjaxResult.success(info);
    }

    @ApiOperation(value = "通过用户id查询用户（患者）信息")
    @GetMapping("/getInfoById/{userId}")
    public AjaxResult getInfoById(@PathVariable String Id){
        UserInfo info =userInfoMapper.selectUserInfoByUserId(Id);
        return AjaxResult.success(info);
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(userInfoService.deleteUserInfoByIds(ids));
    }
}
