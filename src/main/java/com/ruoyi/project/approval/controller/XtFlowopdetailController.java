package com.ruoyi.project.approval.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.approval.domain.XtFlowopdetail;
import com.ruoyi.project.approval.service.IXtFlowopdetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 流程操作明细Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Api(tags = "审批-流程操作明细表")
@RestController
@RequestMapping("/approval/flowopdetail")
public class XtFlowopdetailController extends BaseController
{
    @Autowired
    private IXtFlowopdetailService xtFlowopdetailService;

    /**
     * 查询流程操作明细列表
     */

    @ApiOperation(value = "查询流程操作明细列表")
    @GetMapping("/list")
    public TableDataInfo list(XtFlowopdetail xtFlowopdetail)
    {
        startPage();
        List<XtFlowopdetail> list = xtFlowopdetailService.selectXtFlowopdetailList(xtFlowopdetail);
        return getDataTable(list);
    }

    /**
     * 导出流程操作明细列表
     */
    @ApiOperation(value = "导出流程操作明细列表")
    @Log(title = "流程操作明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtFlowopdetail xtFlowopdetail)
    {
        List<XtFlowopdetail> list = xtFlowopdetailService.selectXtFlowopdetailList(xtFlowopdetail);
        ExcelUtil<XtFlowopdetail> util = new ExcelUtil<XtFlowopdetail>(XtFlowopdetail.class);
        util.exportExcel(response, list, "流程操作明细数据");
    }

    /**
     * 获取流程操作明细详细信息
     */
    @ApiOperation(value = "获取流程操作明细详细信息")
    @GetMapping(value = "/info/{uflowopdetailid}")
    public AjaxResult getInfo(@PathVariable("uflowopdetailid") String uflowopdetailid)
    {
        return AjaxResult.success(xtFlowopdetailService.selectXtFlowopdetailByUflowopdetailid(uflowopdetailid));
    }

    /**
     * 新增流程操作明细
     */
    @ApiOperation(value = "新增流程操作明细")
    @Log(title = "流程操作明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtFlowopdetail xtFlowopdetail)
    {
        return toAjax(xtFlowopdetailService.insertXtFlowopdetail(xtFlowopdetail));
    }

    /**
     * 修改流程操作明细
     */
    @ApiOperation(value = "修改流程操作明细")
    @Log(title = "流程操作明细", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtFlowopdetail xtFlowopdetail)
    {
        return toAjax(xtFlowopdetailService.updateXtFlowopdetail(xtFlowopdetail));
    }

    /**
     * 删除流程操作明细
     */
    @ApiOperation(value = "删除流程操作明细")
    @Log(title = "流程操作明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{uflowopdetailids}")
    public AjaxResult remove(@PathVariable String[] uflowopdetailids)
    {
        return toAjax(xtFlowopdetailService.deleteXtFlowopdetailByUflowopdetailids(uflowopdetailids));
    }
}
