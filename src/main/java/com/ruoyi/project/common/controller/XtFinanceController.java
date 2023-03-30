package com.ruoyi.project.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
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
import com.ruoyi.project.common.domain.XtFinance;
import com.ruoyi.project.common.service.IXtFinanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 财务明细Controller
 * 
 * @author ruoyi
 * @date 2023-02-01
 */
@Api(tags = "财务管理")
@RestController
@RequestMapping("/finance")
public class XtFinanceController extends BaseController
{
    @Autowired
    private IXtFinanceService xtFinanceService;

    /**
     * 查询财务明细列表
     */
    @ApiOperation(value = "查询财务明细列表")
//    @PreAuthorize("@ss.hasPermi('system:finance:list')")
    @GetMapping("/list")
    public TableDataInfo list(XtFinance xtFinance)
    {
        startPage();
        List<XtFinance> list = xtFinanceService.selectXtFinanceList(xtFinance);
        return getDataTable(list);
    }

    /**
     * 导出财务明细列表
     */
    @ApiOperation(value = "导出财务明细列表")
//    @PreAuthorize("@ss.hasPermi('system:finance:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtFinance xtFinance)
    {
        List<XtFinance> list = xtFinanceService.selectXtFinanceList(xtFinance);
        ExcelUtil<XtFinance> util = new ExcelUtil<XtFinance>(XtFinance.class);
        util.exportExcel(response, list, "财务明细数据");
    }

    /**
     * 获取财务明细详细信息
     */
    @ApiOperation(value = "获取财务明细详细信息")
//    @PreAuthorize("@ss.hasPermi('system:finance:query')")
    @GetMapping(value = "/{financeId}")
    public AjaxResult getInfo(@PathVariable("financeId") String financeId)
    {
        return AjaxResult.success(xtFinanceService.selectXtFinanceByFinanceId(financeId));
    }

    /**
     * 新增财务明细
     */
    @ApiOperation(value = "新增财务明细")
//    @PreAuthorize("@ss.hasPermi('system:finance:add')")
    @PostMapping
    public AjaxResult add(@RequestBody XtFinance xtFinance)
    {
        return toAjax(xtFinanceService.insertXtFinance(xtFinance));
    }

    /**
     * 修改财务明细
     */
    @ApiOperation(value = "修改财务明细")
//    @PreAuthorize("@ss.hasPermi('system:finance:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody XtFinance xtFinance)
    {
        return toAjax(xtFinanceService.updateXtFinance(xtFinance));
    }

    /**
     * 删除财务明细
     */
    @ApiOperation(value = "删除财务明细")
//    @PreAuthorize("@ss.hasPermi('system:finance:remove')")
	@DeleteMapping("/{financeIds}")
    public AjaxResult remove(@PathVariable String[] financeIds)
    {
        return toAjax(xtFinanceService.deleteXtFinanceByFinanceIds(financeIds));
    }

}
