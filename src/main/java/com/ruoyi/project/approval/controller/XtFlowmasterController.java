package com.ruoyi.project.approval.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.approval.domain.XtFlowmaster;
import com.ruoyi.project.approval.service.IXtFlowmasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 流程主Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Api(tags = "审批-流程主表")
@RestController
@RequestMapping("/approval/flowmaster")
public class XtFlowmasterController extends BaseController
{
    @Autowired
    private IXtFlowmasterService xtFlowmasterService;

    /**
     * 查询流程主列表
     */

    @ApiOperation(value = "查询流程主列表")
    @GetMapping("/list")
    public TableDataInfo list(XtFlowmaster xtFlowmaster)
    {
        startPage();
        List<XtFlowmaster> list = xtFlowmasterService.selectXtFlowmasterList(xtFlowmaster);
        return getDataTable(list);
    }

    /**
     * 导出流程主列表
     */
    @ApiOperation(value = "导出流程主列表")
    @Log(title = "流程主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtFlowmaster xtFlowmaster)
    {
        List<XtFlowmaster> list = xtFlowmasterService.selectXtFlowmasterList(xtFlowmaster);
        ExcelUtil<XtFlowmaster> util = new ExcelUtil<XtFlowmaster>(XtFlowmaster.class);
        util.exportExcel(response, list, "流程主数据");
    }

    /**
     * 获取流程主详细信息
     */
    @ApiOperation(value = "获取流程主详细信息")
    @GetMapping(value = "/info/{uflowid}")
    public AjaxResult getInfo(@PathVariable("uflowid") String uflowid)
    {
        return AjaxResult.success(xtFlowmasterService.selectXtFlowmasterByUflowid(uflowid));
    }

    /**
     * 新增流程主
     */
    @ApiOperation(value = "新增流程")
    @Log(title = "流程主", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtFlowmaster xtFlowmaster)
    {
        return toAjax(xtFlowmasterService.insertXtFlowmaster(xtFlowmaster));
    }

    /**
     * 修改流程主
     */
    @ApiOperation(value = "修改流程")
    @Log(title = "流程主", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtFlowmaster xtFlowmaster)
    {
        return toAjax(xtFlowmasterService.updateXtFlowmaster(xtFlowmaster));
    }

    /**
     * 删除流程主
     */
    @ApiOperation(value = "删除流程")
    @Log(title = "流程主", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{uflowids}")
    public AjaxResult remove(@PathVariable String[] uflowids)
    {
        return toAjax(xtFlowmasterService.deleteXtFlowmasterByUflowids(uflowids));
    }

    /**
     *
     * 获取所有流程下拉列表;
     */
    @ApiOperation(value = "获取所有流程下拉列表")
    @GetMapping("/selectList")
    public AjaxResult getSelectList() {
        return AjaxResult.success(xtFlowmasterService.getAllFlowSelectList());
    }

}
