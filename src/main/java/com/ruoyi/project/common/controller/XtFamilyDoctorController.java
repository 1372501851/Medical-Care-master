//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.common.domain.XtFamilyDoctor;
//import com.ruoyi.project.common.service.IXtFamilyDoctorService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 我的家庭医生,主要是用户与医生签约Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//
////@Api(tags = "家庭医生模块")
//@RestController
//@RequestMapping("/web/homeDoctor")
//public class XtFamilyDoctorController extends BaseController
//{
//    @Autowired
//    private IXtFamilyDoctorService xtFamilyDoctorService;
//
//    /**
//     * 查询我的家庭医生,主要是用户与医生签约列表
//     */
//
//    @ApiOperation(value = "查询我的家庭医生,主要是用户与医生签约列表")
//    @GetMapping("/list")
//    public TableDataInfo list(XtFamilyDoctor xtFamilyDoctor)
//    {
//        startPage();
//        List<XtFamilyDoctor> list = xtFamilyDoctorService.selectXtFamilyDoctorList(xtFamilyDoctor);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出我的家庭医生,主要是用户与医生签约列表
//     */
//    @ApiOperation(value = "导出我的家庭医生,主要是用户与医生签约列表")
//    @Log(title = "我的家庭医生,主要是用户与医生签约", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtFamilyDoctor xtFamilyDoctor)
//    {
//        List<XtFamilyDoctor> list = xtFamilyDoctorService.selectXtFamilyDoctorList(xtFamilyDoctor);
//        ExcelUtil<XtFamilyDoctor> util = new ExcelUtil<XtFamilyDoctor>(XtFamilyDoctor.class);
//        util.exportExcel(response, list, "我的家庭医生,主要是用户与医生签约数据");
//    }
//
//    /**
//     * 获取我的家庭医生,主要是用户与医生签约详细信息
//     */
//    @ApiOperation(value = "获取我的家庭医生,主要是用户与医生签约详细信息")
//    @GetMapping(value = "/{ufamilyDoctorid}")
//    public AjaxResult getInfo(@PathVariable("ufamilyDoctorid") String ufamilyDoctorid)
//    {
//        return AjaxResult.success(xtFamilyDoctorService.selectXtFamilyDoctorByUfamilyDoctorid(ufamilyDoctorid));
//    }
//
//    /**
//     * 新增我的家庭医生,主要是用户与医生签约
//     */
//    @ApiOperation(value = "新增我的家庭医生,主要是用户与医生签约")
//    @Log(title = "我的家庭医生,主要是用户与医生签约", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtFamilyDoctor xtFamilyDoctor)
//    {
//        return toAjax(xtFamilyDoctorService.insertXtFamilyDoctor(xtFamilyDoctor));
//    }
//
//    /**
//     * 修改我的家庭医生,主要是用户与医生签约
//     */
//    @ApiOperation(value = "修改我的家庭医生,主要是用户与医生签约")
//    @Log(title = "我的家庭医生,主要是用户与医生签约", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtFamilyDoctor xtFamilyDoctor)
//    {
//        return toAjax(xtFamilyDoctorService.updateXtFamilyDoctor(xtFamilyDoctor));
//    }
//
//    /**
//     * 删除我的家庭医生,主要是用户与医生签约
//     */
//    @ApiOperation(value = "删除我的家庭医生,主要是用户与医生签约")
//    @Log(title = "我的家庭医生,主要是用户与医生签约", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ufamilyDoctorids}")
//    public AjaxResult remove(@PathVariable String[] ufamilyDoctorids)
//    {
//        return toAjax(xtFamilyDoctorService.deleteXtFamilyDoctorByUfamilyDoctorids(ufamilyDoctorids));
//    }
//}
