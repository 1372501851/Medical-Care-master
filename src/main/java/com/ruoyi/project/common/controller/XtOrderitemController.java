package com.ruoyi.project.common.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtOrderitem;
import com.ruoyi.project.merchant.service.IXtOrderitemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单详情Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "订单详情模块")
@RestController
@RequestMapping("/web/orderitem")
public class XtOrderitemController extends BaseController
{
    @Autowired
    private IXtOrderitemService xtOrderitemService;

    /**
     * 查询订单详情列表
     */

    @ApiOperation(value = "查询订单详情列表")
    @GetMapping("/list")
    public TableDataInfo list(XtOrderitem xtOrderitem)
    {
        startPage();
        List<XtOrderitem> list = xtOrderitemService.selectXtOrderitemList(xtOrderitem);
        return getDataTable(list);
    }

    /**
     * 导出订单详情列表
     */
    @ApiOperation(value = "导出订单详情列表")
    @Log(title = "订单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtOrderitem xtOrderitem)
    {
        List<XtOrderitem> list = xtOrderitemService.selectXtOrderitemList(xtOrderitem);
        ExcelUtil<XtOrderitem> util = new ExcelUtil<XtOrderitem>(XtOrderitem.class);
        util.exportExcel(response, list, "订单详情数据");
    }

    /**
     * 获取订单详情详细信息
     */
    @ApiOperation(value = "获取订单详情详细信息")
    @GetMapping(value = "/{uorderitemid}")
    public AjaxResult getInfo(@PathVariable("uorderitemid") String uorderitemid)
    {
        return AjaxResult.success(xtOrderitemService.selectXtOrderitemByUorderitemid(uorderitemid));
    }

    /**
     * 新增订单详情
     */
    @ApiOperation(value = "新增订单详情")
    @Log(title = "订单详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtOrderitem xtOrderitem)
    {
        return toAjax(xtOrderitemService.insertXtOrderitem(xtOrderitem));
    }

    /**
     * 修改订单详情
     */
    @ApiOperation(value = "修改订单详情")
    @Log(title = "订单详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtOrderitem xtOrderitem)
    {
        return toAjax(xtOrderitemService.updateXtOrderitem(xtOrderitem));
    }

    /**
     * 删除订单详情
     */
    @ApiOperation(value = "删除订单详情")
    @Log(title = "订单详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uorderitemids}")
    public AjaxResult remove(@PathVariable String[] uorderitemids)
    {
        return toAjax(xtOrderitemService.deleteXtOrderitemByUorderitemids(uorderitemids));
    }
}
