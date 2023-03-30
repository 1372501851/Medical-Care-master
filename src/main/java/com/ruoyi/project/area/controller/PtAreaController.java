package com.ruoyi.project.area.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.CustomPageUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.area.domain.PtArea;
import com.ruoyi.project.area.domain.vo.PtAreaVo;
import com.ruoyi.project.area.service.IPtAreaService;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.vo.DeptVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 地区Controller
 * 
 * @author ruoyi
 * @date 2023-01-11
 */
@Api(tags = "地区管理")
@RestController
@RequestMapping("/areaApi/area")
public class PtAreaController extends BaseController
{
    @Autowired
    private IPtAreaService ptAreaService;

    @Autowired
    private CustomPageUtil<PtAreaVo> customPageUtil;

    /**
     * 查询地区列表
     */
    @ApiOperation(value = "查询地区列表")
//    @PreAuthorize("@ss.hasPermi('system:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(PtArea ptArea)
    {
        startPage();
        List<PtArea> list = ptAreaService.selectPtAreaList(ptArea);
        return getDataTable(list);
    }

    @ApiOperation(value = "查询地区树")
    @GetMapping("/tree")
    public TableDataInfo getPtAreaTree(PtArea ptArea,
                                       @RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize) {
        List<PtAreaVo> list = ptAreaService.getPtAreaTree(ptArea);
        startPage();
        TableDataInfo dataTable = getDataTable(list);
        dataTable.setRows(customPageUtil.getPageList(list,pageNum,pageSize));
        return dataTable;
    }

    /**
     * 导出地区列表
     */
    @ApiOperation(value = "导出地区列表")
    @PreAuthorize("@ss.hasPermi('system:area:export')")
    @Log(title = "地区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PtArea ptArea)
    {
        List<PtArea> list = ptAreaService.selectPtAreaList(ptArea);
        ExcelUtil<PtArea> util = new ExcelUtil<PtArea>(PtArea.class);
        util.exportExcel(response, list, "地区数据");
    }

    /**
     * 获取地区详细信息
     */
    @ApiOperation(value = "获取地区详细信息")
//    @PreAuthorize("@ss.hasPermi('system:area:query')")
    @GetMapping(value = "/{uareaid}")
    public AjaxResult getInfo(@PathVariable("uareaid") String uareaid)
    {
        return AjaxResult.success(ptAreaService.selectPtAreaByUareaid(uareaid));
    }

    /**
     * 新增地区
     */
    @ApiOperation(value = "新增地区")
//    @PreAuthorize("@ss.hasPermi('system:area:add')")
    @Log(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PtArea ptArea)
    {
        return toAjax(ptAreaService.insertPtArea(ptArea));
    }

    /**
     * 修改地区
     */
    @ApiOperation(value = "修改地区")
//    @PreAuthorize("@ss.hasPermi('system:area:edit')")
    @Log(title = "地区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PtArea ptArea)
    {
        return toAjax(ptAreaService.updatePtArea(ptArea));
    }

    /**
     * 删除地区
     */
    @ApiOperation(value = "删除地区")
//    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @Log(title = "地区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uareaids}")
    public AjaxResult remove(@PathVariable String[] uareaids)
    {
        return toAjax(ptAreaService.deletePtAreaByUareaids(uareaids));
    }

    @ApiOperation(value = "地区下拉树")
    @GetMapping("/areaSelectTree")
    public AjaxResult getAreaSelectTree(PtArea ptArea)
    {
        return AjaxResult.success(ptAreaService.getAreaSelectTree(ptArea));
    }

}
