package com.ruoyi.project.approval.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.approval.domain.XtUserApp;
import com.ruoyi.project.approval.service.IXtUserAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理员申请Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "审批-申请表")
@RestController
@RequestMapping("/approval/application")
public class XtUserAppController extends BaseController
{
    @Autowired
    private IXtUserAppService xtUserAppService;

    /**
     * 查询用户管理员申请列表
     */

    @ApiOperation(value = "查询用户管理员申请列表")
    @GetMapping("/list")
    public TableDataInfo list(XtUserApp xtUserApp)
    {
        startPage();
        List<XtUserApp> list = xtUserAppService.selectXtUserAppList(xtUserApp);
        return getDataTable(list);
    }

    /**
     * 导出用户管理员申请列表
     */
    @ApiOperation(value = "导出用户管理员申请列表")
    @Log(title = "用户管理员申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtUserApp xtUserApp)
    {
        List<XtUserApp> list = xtUserAppService.selectXtUserAppList(xtUserApp);
        ExcelUtil<XtUserApp> util = new ExcelUtil<XtUserApp>(XtUserApp.class);
        util.exportExcel(response, list, "用户管理员申请数据");
    }

    /**
     * 获取用户管理员申请详细信息
     */
    @ApiOperation(value = "获取用户管理员申请详细信息")
    @GetMapping(value = "/info/{userAppid}")
    public AjaxResult getInfo(@PathVariable("userAppid") String userAppid)
    {
        return AjaxResult.success(xtUserAppService.selectXtUserAppByUserAppid(userAppid));
    }

    /**
     * 新增用户管理员申请
     */
    @ApiOperation(value = "新增用户管理员申请")
    @Log(title = "用户管理员申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtUserApp xtUserApp)
    {
        return toAjax(xtUserAppService.insertXtUserApp(xtUserApp));
    }

    /**
     * 修改用户管理员申请
     */
    @ApiOperation(value = "修改用户管理员申请")
    @Log(title = "用户管理员申请", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtUserApp xtUserApp)
    {
        return toAjax(xtUserAppService.updateXtUserApp(xtUserApp));
    }

    /**
     * 删除用户管理员申请
     */
    @ApiOperation(value = "删除用户管理员申请")
    @Log(title = "用户管理员申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{userAppids}")
    public AjaxResult remove(@PathVariable String[] userAppids)
    {
        return toAjax(xtUserAppService.deleteXtUserAppByUserAppids(userAppids));
    }
}
