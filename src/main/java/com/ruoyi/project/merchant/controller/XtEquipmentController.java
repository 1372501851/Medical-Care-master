package com.ruoyi.project.merchant.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEquipment;
import com.ruoyi.project.merchant.domain.XtEquipmentType;
import com.ruoyi.project.merchant.domain.vo.EquipmentInfoVo;
import com.ruoyi.project.merchant.enums.EquipmentEnum;
import com.ruoyi.project.merchant.mapper.XtEquipmentMapper;
import com.ruoyi.project.merchant.service.IXtCompService;
import com.ruoyi.project.merchant.service.IXtEquipmentService;
import com.ruoyi.project.merchant.service.IXtEquipmentTypeService;
import com.ruoyi.project.task.upload.until.StringToURL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Api(tags = "设备模块")
@RestController
@RequestMapping("/equipment")
public class XtEquipmentController extends BaseController
{
    @Autowired
    private IXtEquipmentService xtEquipmentService;


    @Autowired
    private IXtCompService compService;

    @Autowired
    private IXtEquipmentTypeService equipmentTypeService;

    /**
     * 查询设备列表
     */
    @ApiOperation(value = "查询设备列表")
    @GetMapping("/list")
    public TableDataInfo list(XtEquipment xtEquipment)
    {
        startPage();
        List<XtEquipment> list = xtEquipmentService.selectXtEquipmentList(xtEquipment);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @ApiOperation(value = "导出设备列表")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtEquipment xtEquipment)
    {
        List<XtEquipment> list = xtEquipmentService.selectXtEquipmentList(xtEquipment);
        ExcelUtil<XtEquipment> util = new ExcelUtil<XtEquipment>(XtEquipment.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @ApiOperation(value = "获取设备详细信息")
    @GetMapping(value = "/info/{uequipmentid}")
    public AjaxResult getInfo(@PathVariable("uequipmentid") String uequipmentid)
    {
        XtEquipment equipment = xtEquipmentService.selectXtEquipmentByUequipmentid(uequipmentid);
        EquipmentInfoVo equipmentInfoVo = new EquipmentInfoVo();
        BeanUtils.copyProperties(equipment,equipmentInfoVo);
        String status = equipment.getUequipmentStatus();
        if (EquipmentEnum.STOP.getCode().equals(status)){
            equipmentInfoVo.setEquipmentStatusName(EquipmentEnum.STOP.getName());
            equipmentInfoVo.setOpen(false);
            equipmentInfoVo.setPlay(false);
        }else if (EquipmentEnum.START.getCode().equals(status)){
            equipmentInfoVo.setEquipmentStatusName(EquipmentEnum.START.getName());
            equipmentInfoVo.setOpen(true);
            equipmentInfoVo.setPlay(true);
        } else if (EquipmentEnum.SUSPENDED.getCode().equals(status)) {
            equipmentInfoVo.setEquipmentStatusName(EquipmentEnum.SUSPENDED.getName());
            equipmentInfoVo.setOpen(true);
            equipmentInfoVo.setPlay(false);
        }

        String unetworkError = equipment.getUnetworkError();
        if (EquipmentEnum.NTEWORK_ERROR.getCode().equals(unetworkError)){
            equipmentInfoVo.setUnetworkErrorName(EquipmentEnum.NTEWORK_ERROR.getName());
        } else if (EquipmentEnum.NTEWORK_NORMAL.getCode().equals(unetworkError)) {
            equipmentInfoVo.setUnetworkErrorName(EquipmentEnum.NTEWORK_NORMAL.getName());
        }

        String ubrownOut = equipment.getUbrownOut();

        if (EquipmentEnum.UNDER_VOLTAGE_ERROR.getCode().equals(ubrownOut)){
            equipmentInfoVo.setUbrownOutName(EquipmentEnum.UNDER_VOLTAGE_ERROR.getName());
        } else if (EquipmentEnum.UNDER_VOLTAGE_NORMAL.getCode().equals(ubrownOut)) {
            equipmentInfoVo.setUbrownOutName(EquipmentEnum.UNDER_VOLTAGE_NORMAL.getName());
        }

        String ucompid = equipment.getUcompid();
        XtComp xtComp = compService.selectXtCompByUcompid(ucompid);
        if (xtComp != null) {
            equipmentInfoVo.setUcompname(xtComp.getUcompname());
        }

        XtEquipmentType xtEquipmentType = equipmentTypeService.selectXtEquipmentTypeByUequipmentTypeid(equipment.getUequipmentTypeid());
        String pic = xtEquipmentType.getPic();
        List<String> url = StringToURL.toURL(pic);
        equipmentInfoVo.setPic(url.get(0));


        return AjaxResult.success(equipmentInfoVo);
    }

    @ApiOperation(value = "获取设备信息")
    @GetMapping(value = "/getInfo/{uequipmentid}")
    public AjaxResult getInfoByEquipmentId(@PathVariable("uequipmentid") String uequipmentid) {
        return AjaxResult.success(xtEquipmentService.selectXtEquipmentByUequipmentid(uequipmentid));
    }

    /**
     * 新增设备
     */
    @ApiOperation(value = "新增设备")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtEquipment xtEquipment)
    {
        return toAjax(xtEquipmentService.insertXtEquipment(xtEquipment));
    }

    /**
     * 修改设备
     */
    @ApiOperation(value = "修改设备")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtEquipment xtEquipment)
    {
        return toAjax(xtEquipmentService.updateXtEquipment(xtEquipment));
    }

    /**
     * 删除设备
     */
    @ApiOperation(value = "删除设备")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{uequipmentids}")
    public AjaxResult remove(@PathVariable String[] uequipmentids)
    {
        return toAjax(xtEquipmentService.deleteXtEquipmentByUequipmentids(uequipmentids));
    }


    /**设备开启*/
    @ApiOperation(value = "设备开启")
    @GetMapping("/start")
    public AjaxResult start(String uequipmentid)
    {
        String status = EquipmentEnum.START.getCode();
        xtEquipmentService.updateEquipmentStatus(uequipmentid,status);
       return AjaxResult.success();
    }

    /**设备暂停*/
    @ApiOperation(value = "设备停止")
    @GetMapping("/stop")
    public AjaxResult stop(String uequipmentid)
    {
        String status = EquipmentEnum.STOP.getCode();
        xtEquipmentService.updateEquipmentStatus(uequipmentid,status);
        return AjaxResult.success();
    }

    /**设备停止*/

    @ApiOperation(value = "设备暂停")
    @GetMapping("/suspended")
    public AjaxResult suspended(String uequipmentid)
    {
        String status = EquipmentEnum.SUSPENDED.getCode();
        xtEquipmentService.updateEquipmentStatus(uequipmentid,status);
        return AjaxResult.success();
    }
    @ApiOperation(value = "设备恢复")
    @GetMapping("/restore")
    public AjaxResult restore(String uequipmentid)
    {
        String status = EquipmentEnum.START.getCode();
        xtEquipmentService.updateEquipmentStatus(uequipmentid,status);
        return AjaxResult.success();
    }

    @Autowired
    private XtEquipmentMapper xtEquipmentMapper;

    @ApiOperation(value = "群体设置输液设备,min:最小滴数，max：最大滴数")
    @PostMapping("/shuyequntishezhi")
    public AjaxResult shuyequntishezhi(Integer min,Integer max)
    {
        Integer shuyequntishezhi = xtEquipmentMapper.shuyequntishezhi(min, max);
        return AjaxResult.success();
    }
}
