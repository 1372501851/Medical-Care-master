package com.ruoyi.project.remind.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.remind.domain.RemindType;
import com.ruoyi.project.remind.service.IRemindTypeService;
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
 * @date 2022-12-10
 */
@RestController
@RequestMapping("/remind/type")
@Api(tags = "提醒类型模块")
public class RemindTypeController extends BaseController
{
    @Autowired
    private IRemindTypeService remindTypeService;

    /**
     * 查询【请填写功能名称】列表
     */

    @GetMapping("/list")
    @ApiOperation("提醒类型列表")
    public AjaxResult list(RemindType remindType)
    {
        List<RemindType> list = remindTypeService.selectRemindTypeList(remindType);
        return AjaxResult.success(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, RemindType remindType)
    {
        List<RemindType> list = remindTypeService.selectRemindTypeList(remindType);
        ExcelUtil<RemindType> util = new ExcelUtil<RemindType>(RemindType.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(remindTypeService.selectRemindTypeById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ApiOperation("增加提醒类型")
    public AjaxResult add(@RequestBody RemindType remindType)
    {
        return toAjax(remindTypeService.insertRemindType(remindType));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    @ApiOperation("修改提醒类型")
    public AjaxResult edit(@RequestBody RemindType remindType)
    {
        return toAjax(remindTypeService.updateRemindType(remindType));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除提醒类型列")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(remindTypeService.deleteRemindTypeByIds(ids));
    }
}
