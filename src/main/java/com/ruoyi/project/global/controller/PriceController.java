package com.ruoyi.project.global.controller;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.global.domain.XtPrice;
import com.ruoyi.project.global.service.IXtPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "价格管理")
@RestController
@RequestMapping("/price")
public class PriceController extends BaseController {
    @Autowired
    private IXtPriceService xtPriceService;

    /**
     * 查询价格（全局）针对护工列表
     */

    @ApiOperation(value = "查询价格（全局）针对护工列表")
    @GetMapping("/list")
    public TableDataInfo list(XtPrice xtPrice)
    {
        startPage();
        List<XtPrice> list = xtPriceService.selectXtPriceList(xtPrice);
        return getDataTable(list);
    }

    /**
     * 导出价格（全局）针对护工列表
     */
    @ApiOperation(value = "导出价格（全局）针对护工列表")
    @Log(title = "价格（全局）针对护工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtPrice xtPrice)
    {
        List<XtPrice> list = xtPriceService.selectXtPriceList(xtPrice);
        ExcelUtil<XtPrice> util = new ExcelUtil<XtPrice>(XtPrice.class);
        util.exportExcel(response, list, "价格（全局）针对护工数据");
    }

    /**
     * 获取价格（全局）针对护工详细信息
     */
    @ApiOperation(value = "获取价格（全局）针对护工详细信息")
    @GetMapping(value = "/info/{upriceid}")
    public AjaxResult getInfo(@PathVariable("upriceid") String upriceid)
    {
        return AjaxResult.success(xtPriceService.selectXtPriceByUpriceid(upriceid));
    }

    /**
     * 新增价格（全局）针对护工
     */
    @ApiOperation(value = "新增价格（全局）针对护工")
    @Log(title = "价格（全局）针对护工", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtPrice xtPrice)
    {
        return toAjax(xtPriceService.insertXtPrice(xtPrice));
    }

    /**
     * 修改价格（全局）针对护工
     */
    @ApiOperation(value = "修改价格（全局）针对护工")
    @Log(title = "价格（全局）针对护工", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtPrice xtPrice)
    {
        return toAjax(xtPriceService.updateXtPrice(xtPrice));
    }

    /**
     * 删除价格（全局）针对护工
     */
    @ApiOperation(value = "删除价格（全局）针对护工")
    @Log(title = "价格（全局）针对护工", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{upriceids}")
    public AjaxResult remove(@PathVariable String[] upriceids)
    {
        return toAjax(xtPriceService.deleteXtPriceByUpriceids(upriceids));
    }
}
