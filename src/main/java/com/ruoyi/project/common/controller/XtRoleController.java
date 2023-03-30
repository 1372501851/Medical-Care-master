//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.common.domain.XtRole;
//import com.ruoyi.project.common.service.IXtRoleService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 商家（医院、药店）角色Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//@Api(tags = "商家模块")
//@RestController
//@RequestMapping("/web/role")
//public class XtRoleController extends BaseController
//{
//    @Autowired
//    private IXtRoleService xtRoleService;
//
//    /**
//     * 查询商家（医院、药店）角色列表
//     */
//
//    @ApiOperation(value = "查询商家（医院、药店）角色列表")
//    @GetMapping("/list")
//    public TableDataInfo list(XtRole xtRole)
//    {
//        startPage();
//        List<XtRole> list = xtRoleService.selectXtRoleList(xtRole);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出商家（医院、药店）角色列表
//     */
//
//    @ApiOperation(value = "导出商家（医院、药店）角色列表")
//    @Log(title = "商家（医院、药店）角色", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtRole xtRole)
//    {
//        List<XtRole> list = xtRoleService.selectXtRoleList(xtRole);
//        ExcelUtil<XtRole> util = new ExcelUtil<XtRole>(XtRole.class);
//        util.exportExcel(response, list, "商家（医院、药店）角色数据");
//    }
//
//    /**
//     * 获取商家（医院、药店）角色详细信息
//     */
//    @ApiOperation(value = "获取商家（医院、药店）角色详细信息")
//    @GetMapping(value = "/{uroleid}")
//    public AjaxResult getInfo(@PathVariable("uroleid") String uroleid)
//    {
//        return AjaxResult.success(xtRoleService.selectXtRoleByUroleid(uroleid));
//    }
//
//    /**
//     * 新增商家（医院、药店）角色
//     */
//    @ApiOperation(value = " 新增商家（医院、药店）角色")
//    @Log(title = "商家（医院、药店）角色", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtRole xtRole)
//    {
//        return toAjax(xtRoleService.insertXtRole(xtRole));
//    }
//
//    /**
//     * 修改商家（医院、药店）角色
//     */
//    @ApiOperation(value = "修改商家（医院、药店）角色")
//    @Log(title = "商家（医院、药店）角色", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtRole xtRole)
//    {
//        return toAjax(xtRoleService.updateXtRole(xtRole));
//    }
//
//    /**
//     * 删除商家（医院、药店）角色
//     */
//    @ApiOperation(value = "删除商家（医院、药店）角色")
//    @Log(title = "商家（医院、药店）角色", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{uroleids}")
//    public AjaxResult remove(@PathVariable String[] uroleids)
//    {
//        return toAjax(xtRoleService.deleteXtRoleByUroleids(uroleids));
//    }
//}
