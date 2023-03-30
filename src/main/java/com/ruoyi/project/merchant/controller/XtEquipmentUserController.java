package com.ruoyi.project.merchant.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtEquipment;
import com.ruoyi.project.merchant.domain.XtEquipmentUser;
import com.ruoyi.project.merchant.domain.query.EquipmentUserQuery;
import com.ruoyi.project.merchant.domain.vo.EquipmentAppVo;
import com.ruoyi.project.merchant.mapper.XtEquipmentUserMapper;
import com.ruoyi.project.merchant.service.IXtEquipmentUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备与用户关系Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Api(tags = "设备与用户关系模块")
@RestController
@RequestMapping("/equipment/user")
public class XtEquipmentUserController extends BaseController
{
    @Autowired
    private IXtEquipmentUserService xtEquipmentUserService;

    @Autowired
    private XtEquipmentUserMapper xtEquipmentUserMapper;

    /**
     * 查询设备与用户关系列表
     */
    @ApiOperation(value = "查询设备与用户关系列表")

    @GetMapping("/list")
    public TableDataInfo list(XtEquipmentUser xtEquipmentUser)
    {
        startPage();
        List<XtEquipmentUser> list = xtEquipmentUserService.selectXtEquipmentUserList(xtEquipmentUser);
        return getDataTable(list);
    }

    /**
     * 导出设备与用户关系列表
     */
    @ApiOperation(value = "导出设备与用户关系列表")
    @Log(title = "设备与用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtEquipmentUser xtEquipmentUser)
    {
        List<XtEquipmentUser> list = xtEquipmentUserService.selectXtEquipmentUserList(xtEquipmentUser);
        ExcelUtil<XtEquipmentUser> util = new ExcelUtil<XtEquipmentUser>(XtEquipmentUser.class);
        util.exportExcel(response, list, "设备与用户关系数据");
    }

    /**
     * 获取设备与用户关系详细信息
     */
    @ApiOperation(value = "获取设备与用户关系详细信息")
    @GetMapping(value = "/info/{uequipmentUserid}")
    public AjaxResult getInfo(@PathVariable("uequipmentUserid") String uequipmentUserid)
    {
        return AjaxResult.success(xtEquipmentUserService.selectXtEquipmentUserByUequipmentUserid(uequipmentUserid));
    }

    /**
     * 新增设备与用户关系
     */
    @ApiOperation(value = "新增设备与用户关系")
    @Log(title = "设备与用户关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtEquipmentUser xtEquipmentUser)
    {
        return toAjax(xtEquipmentUserService.insertXtEquipmentUser(xtEquipmentUser));
    }

    /**
     * 修改设备与用户关系
     */
    @ApiOperation(value = "修改设备与用户关系")
    @Log(title = "设备与用户关系", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtEquipmentUser xtEquipmentUser)
    {
        return toAjax(xtEquipmentUserService.updateXtEquipmentUser(xtEquipmentUser));
    }


    @ApiOperation(value = "修改设备状态（增加设备）")
    @PutMapping("updateEquipmentStatus")
    public AjaxResult updateEquipmentStatus(String status , String typeId)
    {
        return toAjax(xtEquipmentUserMapper.updateEquipmentStatus(status,typeId));
    }


    /**
     * 删除设备与用户关系
     */
    @ApiOperation(value = "删除设备与用户关系")
    @Log(title = "设备与用户关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{uequipmentUserids}")
    public AjaxResult remove(@PathVariable String[] uequipmentUserids)
    {
        return toAjax(xtEquipmentUserService.deleteXtEquipmentUserByUequipmentUserids(uequipmentUserids));
    }



    /**用户所拥有的设备*/
    @ApiOperation(value = "用户所拥有的设备")
    @GetMapping("/userEquipmentList")
    public AjaxResult userEquipmentList(String uequipmentTypeid){
        List<EquipmentAppVo> equipmentAppVos = xtEquipmentUserService.selectEquipments(uequipmentTypeid);
        return AjaxResult.success(equipmentAppVos);
    }
}
