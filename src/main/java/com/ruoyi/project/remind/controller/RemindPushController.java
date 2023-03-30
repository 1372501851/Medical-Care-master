package com.ruoyi.project.remind.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.remind.domain.RemindPush;
import com.ruoyi.project.remind.service.IRemindPushService;
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
@RequestMapping("/remind/push")
@Api(tags = "提醒-推送过的消息模块")
public class RemindPushController extends BaseController
{
    @Autowired
    private IRemindPushService remindPushService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public AjaxResult list(RemindPush remindPush)
    {
        List<RemindPush> list = remindPushService.selectRemindPushList(remindPush);
        return AjaxResult.success(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excal")
    public void export(HttpServletResponse response, RemindPush remindPush)
    {
        List<RemindPush> list = remindPushService.selectRemindPushList(remindPush);
        ExcelUtil<RemindPush> util = new ExcelUtil<RemindPush>(RemindPush.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(remindPushService.selectRemindPushById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody RemindPush remindPush)
    {
        return toAjax(remindPushService.insertRemindPush(remindPush));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody RemindPush remindPush)
    {
        return toAjax(remindPushService.updateRemindPush(remindPush));
    }

    /**
     * 删除【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(remindPushService.deleteRemindPushByIds(ids));
    }
}
