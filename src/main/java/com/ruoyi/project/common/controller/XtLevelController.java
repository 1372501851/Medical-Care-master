package com.ruoyi.project.common.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.XtLevel;
import com.ruoyi.project.common.service.IXtLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 等级Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "护工等级模块")
@RestController
@RequestMapping("/web/carLevel")
public class XtLevelController extends BaseController
{
    @Autowired
    private IXtLevelService xtLevelService;

    /**
     * 查询等级列表
     */
    @ApiOperation(value = "查询等级列表")

    @GetMapping("/list")
    public TableDataInfo list(XtLevel xtLevel)
    {
        startPage();
        List<XtLevel> list = xtLevelService.selectXtLevelList(xtLevel);
        return getDataTable(list);
    }

    /**
     * 导出等级列表
     */
    @ApiOperation(value = "导出等级列表")
    @Log(title = "等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtLevel xtLevel)
    {
        List<XtLevel> list = xtLevelService.selectXtLevelList(xtLevel);
        ExcelUtil<XtLevel> util = new ExcelUtil<XtLevel>(XtLevel.class);
        util.exportExcel(response, list, "等级数据");
    }

    /**
     * 获取等级详细信息
     */
    @ApiOperation(value = "获取等级详细信息")
    @GetMapping(value = "/{ulevelid}")
    public AjaxResult getInfo(@PathVariable("ulevelid") String ulevelid)
    {
        return AjaxResult.success(xtLevelService.selectXtLevelByUlevelid(ulevelid));
    }

    /**
     * 新增等级
     */
    @ApiOperation(value = "新增等级")
    @Log(title = "等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtLevel xtLevel)
    {
        return toAjax(xtLevelService.insertXtLevel(xtLevel));
    }

    /**
     * 修改等级
     */
    @ApiOperation(value = "修改等级")
    @Log(title = "等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtLevel xtLevel)
    {
        return toAjax(xtLevelService.updateXtLevel(xtLevel));
    }

    /**
     * 删除等级
     */
    @ApiOperation(value = "删除等级")
    @Log(title = "等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ulevelids}")
    public AjaxResult remove(@PathVariable String[] ulevelids)
    {
        return toAjax(xtLevelService.deleteXtLevelByUlevelids(ulevelids));
    }
}
