//package com.ruoyi.project.common.controller;
//
//import java.util.List;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ruoyi.project.user.domain.XtOperUser;
//import com.ruoyi.project.user.service.IXtOperUserService;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.web.page.TableDataInfo;
//
///**
// * 系统管理员Controller
// *
// * @author ruoyi
// * @date 2022-10-27
// */
//@RestController
//@RequestMapping("/system/user")
//public class XtOperUserController extends BaseController
//{
//    @Autowired
//    private IXtOperUserService xtOperUserService;
//
//    /**
//     * 查询系统管理员列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(XtOperUser xtOperUser)
//    {
//        startPage();
//        List<XtOperUser> list = xtOperUserService.selectXtOperUserList(xtOperUser);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出系统管理员列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:export')")
//    @Log(title = "系统管理员", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtOperUser xtOperUser)
//    {
//        List<XtOperUser> list = xtOperUserService.selectXtOperUserList(xtOperUser);
//        ExcelUtil<XtOperUser> util = new ExcelUtil<XtOperUser>(XtOperUser.class);
//        util.exportExcel(response, list, "系统管理员数据");
//    }
//
//    /**
//     * 获取系统管理员详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:query')")
//    @GetMapping(value = "/{uoperUserid}")
//    public AjaxResult getInfo(@PathVariable("uoperUserid") String uoperUserid)
//    {
//        return AjaxResult.success(xtOperUserService.selectXtOperUserByUoperUserid(uoperUserid));
//    }
//
//    /**
//     * 新增系统管理员
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:add')")
//    @Log(title = "系统管理员", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtOperUser xtOperUser)
//    {
//        return toAjax(xtOperUserService.insertXtOperUser(xtOperUser));
//    }
//
//    /**
//     * 修改系统管理员
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:edit')")
//    @Log(title = "系统管理员", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtOperUser xtOperUser)
//    {
//        return toAjax(xtOperUserService.updateXtOperUser(xtOperUser));
//    }
//
//    /**
//     * 删除系统管理员
//     */
//    @PreAuthorize("@ss.hasPermi('system:user:remove')")
//    @Log(title = "系统管理员", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{uoperUserids}")
//    public AjaxResult remove(@PathVariable String[] uoperUserids)
//    {
//        return toAjax(xtOperUserService.deleteXtOperUserByUoperUserids(uoperUserids));
//    }
//}
