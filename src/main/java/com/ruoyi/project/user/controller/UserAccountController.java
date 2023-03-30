package com.ruoyi.project.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.user.domain.UserAccount;
import com.ruoyi.project.user.service.IUserAccountService;
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
 * @date 2022-12-01
 */
@RestController

@RequestMapping("/user/account")
public class UserAccountController extends BaseController
{
    @Autowired
    private IUserAccountService userAccountService;

    /**
     * 查询【请填写功能名称】列表
     */

    @GetMapping("/list")
    public TableDataInfo list(UserAccount userAccount)
    {
        startPage();
        List<UserAccount> list = userAccountService.selectUserAccountList(userAccount);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, UserAccount userAccount)
    {
        List<UserAccount> list = userAccountService.selectUserAccountList(userAccount);
        ExcelUtil<UserAccount> util = new ExcelUtil<UserAccount>(UserAccount.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/detail")
    @ApiOperation(value = "获取账户信息")
    public AjaxResult getInfo()
    {
        UserAccount account = userAccountService.selectUserAccountByUserId();
        return AjaxResult.success(account);
    }

    /**
     * 新增【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAccount userAccount)
    {
        return toAjax(userAccountService.insertUserAccount(userAccount));
    }

    /**
     * 修改【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAccount userAccount)
    {
        return toAjax(userAccountService.updateUserAccount(userAccount));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(userAccountService.deleteUserAccountByIds(ids));
    }
}
