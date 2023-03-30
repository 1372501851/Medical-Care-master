package com.ruoyi.project.common.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.XtVisitDoc;
import com.ruoyi.project.common.service.IXtVisitDocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 就诊单据Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "就诊单据模块")
@RestController
@RequestMapping("/web/doc")
public class XtVisitDocController extends BaseController
{
    @Autowired
    private IXtVisitDocService xtVisitDocService;

    /**
     * 查询就诊单据列表
     */

    @ApiOperation(value = "查询就诊单据列表")
    @GetMapping("/list")
    public TableDataInfo list(XtVisitDoc xtVisitDoc)
    {
        startPage();
        List<XtVisitDoc> list = xtVisitDocService.selectXtVisitDocList(xtVisitDoc);
        return getDataTable(list);
    }

    /**
     * 导出就诊单据列表
     */

    @ApiOperation(value = "导出就诊单据列表")
    @Log(title = "就诊单据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtVisitDoc xtVisitDoc)
    {
        List<XtVisitDoc> list = xtVisitDocService.selectXtVisitDocList(xtVisitDoc);
        ExcelUtil<XtVisitDoc> util = new ExcelUtil<XtVisitDoc>(XtVisitDoc.class);
        util.exportExcel(response, list, "就诊单据数据");
    }

    /**
     * 获取就诊单据详细信息
     */

    @ApiOperation(value = "获取就诊单据详细信息")
    @GetMapping(value = "/{uvisitDocid}")
    public AjaxResult getInfo(@PathVariable("uvisitDocid") String uvisitDocid)
    {
        return AjaxResult.success(xtVisitDocService.selectXtVisitDocByUvisitDocid(uvisitDocid));
    }

    /**
     * 新增就诊单据
     */

    @ApiOperation(value = "新增就诊单据")
    @Log(title = "就诊单据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtVisitDoc xtVisitDoc)
    {
        return toAjax(xtVisitDocService.insertXtVisitDoc(xtVisitDoc));
    }

    /**
     * 修改就诊单据
     */

    @ApiOperation(value = "修改就诊单据")
    @Log(title = "就诊单据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtVisitDoc xtVisitDoc)
    {
        return toAjax(xtVisitDocService.updateXtVisitDoc(xtVisitDoc));
    }

    /**
     * 删除就诊单据
     */

    @ApiOperation(value = "删除就诊单据")
    @Log(title = "就诊单据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uvisitDocids}")
    public AjaxResult remove(@PathVariable String[] uvisitDocids)
    {
        return toAjax(xtVisitDocService.deleteXtVisitDocByUvisitDocids(uvisitDocids));
    }
}
