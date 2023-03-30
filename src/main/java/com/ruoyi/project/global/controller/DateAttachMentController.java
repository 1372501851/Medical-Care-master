package com.ruoyi.project.global.controller;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.global.domain.XtHoliday;
import com.ruoyi.project.global.service.IXtHolidayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "节假日日期附件管理模块")
@RestController
@RequestMapping("/date")
public class DateAttachMentController extends BaseController {

    @Autowired
    private IXtHolidayService xtHolidayService;


    /**
     * 查询假期（每年的假期都录入进来）平台管理录入列表
     */
    @ApiOperation(value = "查询日期附件")
    @GetMapping("/list")
    public TableDataInfo list(XtHoliday xtHoliday)
    {
        startPage();
        List<XtHoliday> list = xtHolidayService.selectXtHolidayList(xtHoliday);
        return getDataTable(list);
    }

    /**
     * 导出假期（每年的假期都录入进来）平台管理录入列表
     */
    @ApiOperation(value = "导出假期（每年的假期都录入进来）平台管理录入列表")
    @Log(title = "假期（每年的假期都录入进来）平台管理录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtHoliday xtHoliday)
    {
        List<XtHoliday> list = xtHolidayService.selectXtHolidayList(xtHoliday);
        ExcelUtil<XtHoliday> util = new ExcelUtil<XtHoliday>(XtHoliday.class);
        util.exportExcel(response, list, "假期（每年的假期都录入进来）平台管理录入数据");
    }

    /**
     * 获取假期（每年的假期都录入进来）平台管理录入详细信息
     */
    @ApiOperation(value = "根据id获取假期")
    @GetMapping(value = "/info/{uholidayid}")
    public AjaxResult getInfo(@PathVariable("uholidayid") String uholidayid)
    {
        return AjaxResult.success(xtHolidayService.selectXtHolidayByUholidayid(uholidayid));
    }

    /**
     * 新增假期（每年的假期都录入进来）平台管理录入
     */
    @ApiOperation(value = "新增假期")
    @Log(title = "假期（每年的假期都录入进来）平台管理录入", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtHoliday xtHoliday)
    {
        return toAjax(xtHolidayService.insertXtHoliday(xtHoliday));
    }

    /**
     * 修改假期（每年的假期都录入进来）平台管理录入
     */
    @ApiOperation(value = "修改假期")
    @Log(title = "假期（每年的假期都录入进来）平台管理录入", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtHoliday xtHoliday)
    {
        return toAjax(xtHolidayService.updateXtHoliday(xtHoliday));
    }

    /**
     * 删除假期（每年的假期都录入进来）平台管理录入
     */
    @ApiOperation(value = "删除假期")
    @Log(title = "假期（每年的假期都录入进来）平台管理录入", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{uholidayids}")
    public AjaxResult remove(@PathVariable String[] uholidayids)
    {
        return toAjax(xtHolidayService.deleteXtHolidayByUholidayids(uholidayids));
    }

}
