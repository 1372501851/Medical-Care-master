package com.ruoyi.project.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.user.domain.UserGuardianship;
import com.ruoyi.project.user.domain.vo.UserGuardianshipVo;
import com.ruoyi.project.user.service.IUserGuardianshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-11-26
 */
@Api(tags = "监护人模块")
@RestController
@RequestMapping("/user/guardianship")
public class UserGuardianshipController extends BaseController
{
    @Autowired
    private IUserGuardianshipService userGuardianshipService;

    /**
     * 查询【请填写功能名称】列表
     */
//
//    @GetMapping("/list")
//    public TableDataInfo list(UserGuardianship userGuardianship)
//    {
//        startPage();
//        List<UserGuardianship> list = userGuardianshipService.selectUserGuardianshipList(userGuardianship);
//        return getDataTable(list);
//    }

    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, UserGuardianship userGuardianship )
    {
        List<UserGuardianshipVo> list = userGuardianshipService.selectUserGuardianshipList(userGuardianship );
        ExcelUtil<UserGuardianshipVo> util = new ExcelUtil<UserGuardianshipVo>(UserGuardianshipVo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(userGuardianshipService.selectUserGuardianshipById(id));
    }

    /**
     * 新增【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增监护人信息")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody UserGuardianship userGuardianship)
    {
        return toAjax(userGuardianshipService.insertUserGuardianship(userGuardianship));
    }


    /**获取监护人信息列表*/

    @ApiOperation(value = "获取监护人信息列表")
    @GetMapping("/list")
    public AjaxResult list(UserGuardianship userGuardianship){
        List<UserGuardianshipVo> userGuardianshipVos = userGuardianshipService.selectUserGuardianshipList(userGuardianship);
        return AjaxResult.success(userGuardianshipVos);
    }

    /**
     * 修改【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserGuardianship userGuardianship)
    {
        return toAjax(userGuardianshipService.updateUserGuardianship(userGuardianship));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(userGuardianshipService.deleteUserGuardianshipByIds(ids));
    }
}
