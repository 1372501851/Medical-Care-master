package com.ruoyi.project.approval.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.approval.domain.XtFlowopmaster;
import com.ruoyi.project.approval.service.IXtFlowopmasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 管理员流程处理主Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "审批-管理员流程处理主表")
@RestController
@RequestMapping("/approval/flowopmaster")
public class XtFlowopmasterController extends BaseController
{
    @Autowired
    private IXtFlowopmasterService xtFlowopmasterService;

    /**
     * 查询管理员流程处理主列表
     */

    @ApiOperation(value = "查询管理员流程处理主列表")
    @GetMapping("/list")
    public TableDataInfo list(XtFlowopmaster xtFlowopmaster)
    {
        startPage();
        List<XtFlowopmaster> list = xtFlowopmasterService.selectXtFlowopmasterList(xtFlowopmaster);
        return getDataTable(list);
    }

    /**
     * 导出管理员流程处理主列表
     */
    @ApiOperation(value = "导出管理员流程处理主列表")
    @Log(title = "管理员流程处理主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtFlowopmaster xtFlowopmaster)
    {
        List<XtFlowopmaster> list = xtFlowopmasterService.selectXtFlowopmasterList(xtFlowopmaster);
        ExcelUtil<XtFlowopmaster> util = new ExcelUtil<XtFlowopmaster>(XtFlowopmaster.class);
        util.exportExcel(response, list, "管理员流程处理主数据");
    }

    /**
     * 获取管理员流程处理主详细信息
     */
    @ApiOperation(value = "获取管理员流程处理主详细信息")
    @GetMapping(value = "/info/{uflowopmasterid}")
    public AjaxResult getInfo(@PathVariable("uflowopmasterid") String uflowopmasterid)
    {
        return AjaxResult.success(xtFlowopmasterService.selectXtFlowopmasterByUflowopmasterid(uflowopmasterid));
    }

    /**
     * 新增管理员流程处理主
     */
    @ApiOperation(value = "新增管理员流程处理")
    @Log(title = "管理员流程处理主", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtFlowopmaster xtFlowopmaster)
    {
        return toAjax(xtFlowopmasterService.insertXtFlowopmaster(xtFlowopmaster));
    }

    /**
     * 修改管理员流程处理主
     */
    @ApiOperation(value = "修改管理员流程处理")
    @Log(title = "管理员流程处理主", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtFlowopmaster xtFlowopmaster)
    {
        return toAjax(xtFlowopmasterService.updateXtFlowopmaster(xtFlowopmaster));
    }

    /**
     * 删除管理员流程处理主
     */
    @ApiOperation(value = "删除管理员流程处理")
    @Log(title = "管理员流程处理主", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{uflowopmasterids}")
    public AjaxResult remove(@PathVariable String[] uflowopmasterids)
    {
        return toAjax(xtFlowopmasterService.deleteXtFlowopmasterByUflowopmasterids(uflowopmasterids));
    }
}
