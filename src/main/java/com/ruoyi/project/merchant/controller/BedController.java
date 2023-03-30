package com.ruoyi.project.merchant.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtBedNo;
import com.ruoyi.project.merchant.service.IXtBedNoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(tags = "床位管理")
@RestController
@RequestMapping("/bed")
public class BedController extends BaseController {
    @Autowired
    private IXtBedNoService xtBedNoService;

    /**
     * 查询床位号列表
     */
    @ApiOperation(value = "查询床位号列表")

    @GetMapping("/list")
    public TableDataInfo list(XtBedNo xtBedNo)
    {
        startPage();
        List<XtBedNo> list = xtBedNoService.selectXtBedNoList(xtBedNo);
        return getDataTable(list);
    }

    /**
     * 导出床位号列表
     */
    @ApiOperation(value = "导出床位号列表")
    @Log(title = "床位号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtBedNo xtBedNo)
    {
        List<XtBedNo> list = xtBedNoService.selectXtBedNoList(xtBedNo);
        ExcelUtil<XtBedNo> util = new ExcelUtil<XtBedNo>(XtBedNo.class);
        util.exportExcel(response, list, "床位号数据");
    }

    /**
     * 获取床位号详细信息
     */
    @ApiOperation(value = "获取床位号详细信息")
    @GetMapping(value = "/info/{ubednoid}")
    public AjaxResult getInfo(@PathVariable("ubednoid") String ubednoid)
    {
        return AjaxResult.success(xtBedNoService.selectXtBedNoByUbednoid(ubednoid));
    }

    /**
     * 新增床位号
     */
    @ApiOperation(value = "新增床位号")
    @Log(title = "床位号", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody XtBedNo xtBedNo)
    {
        return toAjax(xtBedNoService.insertXtBedNo(xtBedNo));
    }

    /**
     * 修改床位号
     */
    @ApiOperation(value = "修改床位号")
    @Log(title = "床位号", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtBedNo xtBedNo)
    {
        return toAjax(xtBedNoService.updateXtBedNo(xtBedNo));
    }

    /**
     * 删除床位号
     */
    @ApiOperation(value = "删除床位号")
    @Log(title = "床位号", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{ubednoids}")
    public AjaxResult remove(@PathVariable String[] ubednoids)
    {
        return toAjax(xtBedNoService.deleteXtBedNoByUbednoids(ubednoids));
    }


}
