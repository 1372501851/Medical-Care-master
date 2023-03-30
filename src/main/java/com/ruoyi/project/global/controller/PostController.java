package com.ruoyi.project.global.controller;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.global.domain.XtPosition;
import com.ruoyi.project.global.service.IXtPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "职位管理模块")
@RestController
@RequestMapping("/post")
public class PostController extends BaseController {
    @Autowired
    private IXtPositionService xtPositionService;

    /**
     * 查询职位 提供选择（传职位名称给用户信息）列表
     */

    @ApiOperation(value = "查询职位 提供选择（传职位名称给用户信息）列表")
    @GetMapping("/list")
    public TableDataInfo list(XtPosition xtPosition)
    {
        startPage();
        List<XtPosition> list = xtPositionService.selectXtPositionList(xtPosition);
        return getDataTable(list);
    }

    /**
     * 导出职位 提供选择（传职位名称给用户信息）列表
     */
    @ApiOperation(value = "导出职位 提供选择（传职位名称给用户信息）列表")
    @Log(title = "职位 提供选择（传职位名称给用户信息）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtPosition xtPosition)
    {
        List<XtPosition> list = xtPositionService.selectXtPositionList(xtPosition);
        ExcelUtil<XtPosition> util = new ExcelUtil<XtPosition>(XtPosition.class);
        util.exportExcel(response, list, "职位 提供选择（传职位名称给用户信息）数据");
    }

    /**
     * 获取职位 提供选择（传职位名称给用户信息）详细信息
     */
    @ApiOperation(value = "获取职位 提供选择（传职位名称给用户信息）详细信息")
    @GetMapping(value = "/info/{upositionid}")
    public AjaxResult getInfo(@PathVariable("upositionid") String upositionid)
    {
        return AjaxResult.success(xtPositionService.selectXtPositionByUpositionId(upositionid));
    }

    /**
     * 新增职位 提供选择（传职位名称给用户信息）
     */
    @ApiOperation(value = "新增职位 提供选择（传职位名称给用户信息）")
    @Log(title = "职位 提供选择（传职位名称给用户信息）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtPosition xtPosition)
    {
        return toAjax(xtPositionService.insertXtPosition(xtPosition));
    }

    /**
     * 修改职位 提供选择（传职位名称给用户信息）
     */
    @ApiOperation(value = "修改职位 提供选择（传职位名称给用户信息）")
    @Log(title = "职位 提供选择（传职位名称给用户信息）", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtPosition xtPosition)
    {
        return toAjax(xtPositionService.updateXtPosition(xtPosition));
    }

    /**
     * 删除职位 提供选择（传职位名称给用户信息）
     */
    @ApiOperation(value = "删除职位 提供选择（传职位名称给用户信息）")
    @Log(title = "职位 提供选择（传职位名称给用户信息）", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{upositionids}")
    public AjaxResult remove(@PathVariable String[] upositionids)
    {
        return toAjax(xtPositionService.deleteXtPositionByUpositionIds(upositionids));
    }

    /**
     *
     * 获取职位下拉列表;
     */
    @ApiOperation(value = "获取职位下拉列表")
    @GetMapping("/selectList")
    public AjaxResult getSelectList() {
        return AjaxResult.success(xtPositionService.getSelectList());
    }

}
