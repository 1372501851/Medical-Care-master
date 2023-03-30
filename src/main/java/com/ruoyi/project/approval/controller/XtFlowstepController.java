package com.ruoyi.project.approval.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.approval.domain.XtFlowstep;
import com.ruoyi.project.approval.service.IXtFlowstepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 流程步骤Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Api(tags = "审批-流程步骤表")
@RestController
@RequestMapping("/approval/flowstep")
public class XtFlowstepController extends BaseController
{
    @Autowired
    private IXtFlowstepService xtFlowstepService;

    /**
     * 查询流程步骤列表
     */
    @ApiOperation(value = "查询流程步骤列表")

    @GetMapping("/list")
    public TableDataInfo list(XtFlowstep xtFlowstep)
    {
        startPage();
        List<XtFlowstep> list = xtFlowstepService.selectXtFlowstepList(xtFlowstep);
        return getDataTable(list);
    }

    /**
     * 导出流程步骤列表
     */
    @ApiOperation(value = "导出流程步骤列表")
    @Log(title = "流程步骤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtFlowstep xtFlowstep)
    {
        List<XtFlowstep> list = xtFlowstepService.selectXtFlowstepList(xtFlowstep);
        ExcelUtil<XtFlowstep> util = new ExcelUtil<XtFlowstep>(XtFlowstep.class);
        util.exportExcel(response, list, "流程步骤数据");
    }

    /**
     * 获取流程步骤详细信息
     */
    @ApiOperation(value = "获取流程步骤详细信息")
    @GetMapping(value = "/info/{ustepid}")
    public AjaxResult getInfo(@PathVariable("ustepid") String ustepid)
    {
        return AjaxResult.success(xtFlowstepService.selectXtFlowstepByUstepid(ustepid));
    }

    /**
     * 新增流程步骤
     */
    @ApiOperation(value = "新增流程步骤")
    @Log(title = "流程步骤", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtFlowstep xtFlowstep)
    {
        return toAjax(xtFlowstepService.insertXtFlowstep(xtFlowstep));
    }

    /**
     * 修改流程步骤
     */
    @ApiOperation(value = "修改流程步骤")
    @Log(title = "流程步骤", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtFlowstep xtFlowstep)
    {
        return toAjax(xtFlowstepService.updateXtFlowstep(xtFlowstep));
    }

    /**
     * 删除流程步骤
     */
    @ApiOperation(value = "删除流程步骤")
    @Log(title = "流程步骤", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{ustepids}")
    public AjaxResult remove(@PathVariable String[] ustepids)
    {
        return toAjax(xtFlowstepService.deleteXtFlowstepByUstepids(ustepids));
    }
}
