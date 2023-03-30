package com.ruoyi.project.remind.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.remind.domain.Dto.RemindUserDTO;
import com.ruoyi.project.remind.domain.RemindUser;
import com.ruoyi.project.remind.service.IRemindUserService;
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
 * @date 2022-12-13
 */
@RestController
@RequestMapping("/remind/user")
@Api(tags = "提醒-用户所拥有的提醒计划")
public class RemindUserController extends BaseController
{
    @Autowired
    private IRemindUserService remindUserService;

    /**
     * 查询【请填写功能名称】列表
     */

    @GetMapping("/list")
    public AjaxResult list(RemindUser remindUser)
    {
        List<RemindUser> list = remindUserService.selectRemindUserList(remindUser);
        return AjaxResult.success(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, RemindUser remindUser)
    {
        List<RemindUser> list = remindUserService.selectRemindUserList(remindUser);
        ExcelUtil<RemindUser> util = new ExcelUtil<RemindUser>(RemindUser.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(remindUserService.selectRemindUserById(id));
    }

    /**
     * 新增【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody RemindUserDTO remindUserDTO)
    {
        remindUserService.insertRemindUser(remindUserDTO);
        return AjaxResult.success();
    }

    /**
     * 修改【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody RemindUser remindUser)
    {
        return toAjax(remindUserService.updateRemindUser(remindUser));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(remindUserService.deleteRemindUserByIds(ids));
    }
}
