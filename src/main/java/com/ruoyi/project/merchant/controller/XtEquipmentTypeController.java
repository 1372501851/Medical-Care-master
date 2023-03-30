package com.ruoyi.project.merchant.controller;

import com.ruoyi.common.utils.CustomPageUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtEquipmentType;
import com.ruoyi.project.merchant.domain.XtProducttype;
import com.ruoyi.project.merchant.domain.vo.EquipmentAppTypeVo;
import com.ruoyi.project.merchant.domain.vo.EquipmentTypeVo;
import com.ruoyi.project.merchant.service.IXtEquipmentTypeService;
import com.ruoyi.project.user.domain.vo.DeptVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备类型Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Api(tags = "设备类型模块")
@RestController
@RequestMapping("/equipment/type")
public class XtEquipmentTypeController extends BaseController
{
    @Autowired
    private IXtEquipmentTypeService xtEquipmentTypeService;

    @Autowired
    private CustomPageUtil<EquipmentTypeVo> customPageUtil;

    /**
     * 查询设备类型列表
     */
    @ApiOperation(value = "查询设备类型树")
    @GetMapping("/list")
    public TableDataInfo list(XtEquipmentType xtEquipmentType,
                              @RequestParam Integer pageNum,
                              @RequestParam Integer pageSize) {
        List<EquipmentTypeVo> list = xtEquipmentTypeService.buildTypeTree(xtEquipmentType);
        startPage();
        TableDataInfo dataTable = getDataTable(list);
        dataTable.setRows(customPageUtil.getPageList(list,pageNum,pageSize));
        return dataTable;
    }

    /**
     * 导出设备类型列表
     */
    @ApiOperation(value = "导出设备类型列表")
    @Log(title = "设备类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtEquipmentType xtEquipmentType)
    {
        List<XtEquipmentType> list = xtEquipmentTypeService.selectXtEquipmentTypeList(xtEquipmentType);
        ExcelUtil<XtEquipmentType> util = new ExcelUtil<XtEquipmentType>(XtEquipmentType.class);
        util.exportExcel(response, list, "设备类型数据");
    }

    /**
     * 获取设备类型详细信息
     */
    @ApiOperation(value = "获取设备类型详细信息")
    @GetMapping(value = "/info/{uequipmentTypeid}")
    public AjaxResult getInfo(@PathVariable("uequipmentTypeid") String uequipmentTypeid)
    {
        return AjaxResult.success(xtEquipmentTypeService.selectXtEquipmentTypeByUequipmentTypeid(uequipmentTypeid));
    }


    /**
     * 新增设备类型
     */
    @ApiOperation(value = "新增设备类型")
    @Log(title = "设备类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated  @RequestBody XtEquipmentType xtEquipmentType)
    {
        return toAjax(xtEquipmentTypeService.insertXtEquipmentType(xtEquipmentType));
    }

    /**
     * 修改设备类型
     */
    @ApiOperation(value = "修改设备类型")
    @Log(title = "设备类型", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtEquipmentType xtEquipmentType)
    {
        return toAjax(xtEquipmentTypeService.updateXtEquipmentType(xtEquipmentType));
    }

    /**
     * 删除设备类型
     */
    @ApiOperation(value = "删除设备类型")
    @Log(title = "设备类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{uequipmentTypeids}")
    public AjaxResult remove(@PathVariable String[] uequipmentTypeids)
    {
        return toAjax(xtEquipmentTypeService.deleteXtEquipmentTypeByUequipmentTypeids(uequipmentTypeids));
    }


    /**
     *
     * 构建构建前端下拉类别树;
     */
    @ApiOperation(value = "获取设备类型下拉列表树")
    @GetMapping("/typeTree")
    public AjaxResult tree(XtEquipmentType xtEquipmentType)
    {
        return AjaxResult.success(xtEquipmentTypeService.selectTypeTreeSelect(xtEquipmentType));
    }


    /**app设备类型展示*/

    @ApiOperation(value = "app设备类型展示")
    @GetMapping("/equipmentTypes")
    public AjaxResult equipmentTypes(){
        List<EquipmentAppTypeVo> equipmentAppTypeVos = xtEquipmentTypeService.selectAppEquipmentTypes();
        return AjaxResult.success(equipmentAppTypeVos);
    }



}
