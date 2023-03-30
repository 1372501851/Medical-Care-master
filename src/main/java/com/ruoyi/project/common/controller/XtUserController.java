//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.user.domain.XtUser;
//import com.ruoyi.project.user.service.IXtUserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 所有用户登录（所有用户，医生、护士、患者、家属）Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//
//@Api(tags = "用户模块")
//@RestController
//@RequestMapping("/app/user")
//public class XtUserController extends BaseController
//{
//    @Autowired
//    private IXtUserService xtUserService;
//
//    /**
//     * 查询所有用户登录（所有用户，医生、护士、患者、家属）列表
//     */
//
//    @ApiOperation(value = "查询用户")
//    @GetMapping("/list")
//    public TableDataInfo list(XtUser xtUser)
//    {
//        startPage();
//        List<XtUser> list = xtUserService.selectXtUserList(xtUser);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出所有用户登录（所有用户，医生、护士、患者、家属）列表
//     */
//    @ApiOperation(value = "导出用户")
//    @Log(title = "所有用户登录（所有用户，医生、护士、患者、家属）", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtUser xtUser)
//    {
//        List<XtUser> list = xtUserService.selectXtUserList(xtUser);
//        ExcelUtil<XtUser> util = new ExcelUtil<XtUser>(XtUser.class);
//        util.exportExcel(response, list, "所有用户登录（所有用户，医生、护士、患者、家属）数据");
//    }
//
//    /**
//     * 获取所有用户登录（所有用户，医生、护士、患者、家属）详细信息
//     */
//    @ApiOperation(value = "获取用户信息")
//    @GetMapping(value = "/{userid}")
//    public AjaxResult getInfo(@PathVariable("userid") String userid)
//    {
//        return AjaxResult.success(xtUserService.selectXtUserByUserid(userid));
//    }
//
//    /**
//     * 新增所有用户登录（所有用户，医生、护士、患者、家属）
//     */
//    @ApiOperation(value = "新增用户")
//    @Log(title = "所有用户登录（所有用户，医生、护士、患者、家属）", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtUser xtUser)
//    {
//        return toAjax(xtUserService.insertXtUser(xtUser));
//    }
//
//    /**
//     * 修改所有用户登录（所有用户，医生、护士、患者、家属）
//     */
//    @ApiOperation(value = "修改用户信息")
//    @Log(title = "所有用户登录（所有用户，医生、护士、患者、家属）", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtUser xtUser)
//    {
//        return toAjax(xtUserService.updateXtUser(xtUser));
//    }
//
//    /**
//     * 删除所有用户登录（所有用户，医生、护士、患者、家属）
//     */
//    @ApiOperation(value = "删除用户信息")
//    @Log(title = "所有用户登录（所有用户，医生、护士、患者、家属）", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{userids}")
//    public AjaxResult remove(@PathVariable String[] userids)
//    {
//        return toAjax(xtUserService.deleteXtUserByUserids(userids));
//    }
//}
