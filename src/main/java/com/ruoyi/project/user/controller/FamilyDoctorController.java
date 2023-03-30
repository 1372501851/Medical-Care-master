package com.ruoyi.project.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.user.domain.FamilyDoctor;
import com.ruoyi.project.user.domain.vo.FamilyDoctorVo;
import com.ruoyi.project.user.service.IFamilyDoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-11-28
 */

@Api(tags = "家庭医生模块")
@RestController
@RequestMapping("/user/familyDoctor")
public class FamilyDoctorController extends BaseController
{
    @Autowired
    private IFamilyDoctorService familyDoctorService;

    /**
     * 查询【请填写功能名称】列表
     */

//    @GetMapping("/list")
//    public TableDataInfo list(FamilyDoctor familyDoctor)
//    {
//        startPage();
//        List<FamilyDoctor> list = familyDoctorService.selectFamilyDoctorList(familyDoctor);
//        return getDataTable(list);
//    }

    @ApiOperation(value = "app：获取家庭医生信息列表")
    @GetMapping("/list")
    public AjaxResult list(FamilyDoctor familyDoctor){
        List<FamilyDoctorVo> userGuardianshipVos = familyDoctorService.selectFamilyDoctorVoList(familyDoctor);
        return AjaxResult.success(userGuardianshipVos);
    }

    @ApiOperation(value = "后台：获取家庭医生列表")
    @GetMapping("/back/list")
    public AjaxResult getListByBack(FamilyDoctor familyDoctor){
        List<FamilyDoctorVo> userGuardianshipVos = familyDoctorService.getListByBack(familyDoctor);
        return AjaxResult.success(userGuardianshipVos);
    }

    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, FamilyDoctor familyDoctor)
    {
        List<FamilyDoctor> list = familyDoctorService.selectFamilyDoctorList(familyDoctor);
        ExcelUtil<FamilyDoctor> util = new ExcelUtil<FamilyDoctor>(FamilyDoctor.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @ApiOperation(value = "获取家庭医生详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(familyDoctorService.selectFamilyDoctorById(id));
    }

    /**
     * 新增【请填写功能名称】
     */

    @ApiOperation(value = "app:新增家庭医生")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FamilyDoctor familyDoctor)
    {
        return toAjax(familyDoctorService.insertFamilyDoctor(familyDoctor));
    }

    @ApiOperation(value = "后台:新增家庭医生")
    @Log(title = "后台:新增家庭医生", businessType = BusinessType.INSERT)
    @PostMapping("/back/add")
    public AjaxResult addFamilyDoctorByBack(@RequestBody FamilyDoctor familyDoctor)
    {
        return toAjax(familyDoctorService.addFamilyDoctorByBack(familyDoctor));
    }

    /**
     * 修改【请填写功能名称】
     */

    @ApiOperation(value = "修改家庭医生")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FamilyDoctor familyDoctor)
    {
        return toAjax(familyDoctorService.updateFamilyDoctor(familyDoctor));
    }

    /**
     * 删除【请填写功能名称】
     */
    @ApiOperation(value = "删除家庭医生")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(familyDoctorService.deleteFamilyDoctorByIds(ids));
    }


    /**签约*/
    @ApiOperation(value = "签约")
    @GetMapping("/sign")
    public AjaxResult sign(String id){
        familyDoctorService.sign(id);
        return AjaxResult.success();
    }

    /**拒签*/
    @ApiOperation(value = "拒签")
    @GetMapping("/deny")
    public AjaxResult deny(String id){
        familyDoctorService.deny(id);
        return AjaxResult.success();
    }

}
