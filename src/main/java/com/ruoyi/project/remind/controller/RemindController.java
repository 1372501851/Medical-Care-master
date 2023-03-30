package com.ruoyi.project.remind.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.remind.domain.Dto.RemindDTO;
import com.ruoyi.project.remind.domain.Remind;
import com.ruoyi.project.remind.service.IRemindService;
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
@RequestMapping("/remind")
@Api(tags = "提醒模块")
public class RemindController extends BaseController
{
    @Autowired
    private IRemindService remindService;

    /**
     * 查询需要消息提醒的列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "提醒列表")
    public AjaxResult list(Remind remind)
    {
        List<Remind> list = remindService.selectRemindList(remind);
        return AjaxResult.success(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excal")
    public void export(HttpServletResponse response, Remind remind)
    {
        List<Remind> list = remindService.selectRemindList(remind);
        ExcelUtil<Remind> util = new ExcelUtil<Remind>(Remind.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "提醒详情")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(remindService.selectRemindById(id));
    }

    /**
     * 新增【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ApiOperation(value = "增加提醒")
    public AjaxResult add(@RequestBody RemindDTO remindDTO)
    {
        remindService.insertRemind(remindDTO);
        return AjaxResult.success();
    }

    /**
     * 修改【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    @ApiOperation(value = "修改提醒")
    public AjaxResult edit(@RequestBody Remind remind)
    {
        return toAjax(remindService.updateRemind(remind));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation(value = "删除提醒")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(remindService.deleteRemindByIds(ids));
    }
}
