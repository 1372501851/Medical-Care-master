package com.ruoyi.project.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.project.im.domain.XtUserToGroup;
import com.ruoyi.project.im.service.IXtUserToGroupService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户对应群Controller
 *
 * @author ruoyi
 * @date 2022-10-14
 */
@RestController
@RequestMapping("/web/userToGroup")
public class XtUserToGroupController extends BaseController
{
    @Autowired
    private IXtUserToGroupService xtUserToGroupService;

    /**
     * 查询用户对应群列表
     */

    @GetMapping("/list")
    public TableDataInfo list(XtUserToGroup xtUserToGroup)
    {
        startPage();
        List<XtUserToGroup> list = xtUserToGroupService.selectXtUserToGroupList(xtUserToGroup);
        return getDataTable(list);
    }

    /**
     * 导出用户对应群列表
     */

    @Log(title = "用户对应群", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtUserToGroup xtUserToGroup)
    {
        List<XtUserToGroup> list = xtUserToGroupService.selectXtUserToGroupList(xtUserToGroup);
        ExcelUtil<XtUserToGroup> util = new ExcelUtil<XtUserToGroup>(XtUserToGroup.class);
        util.exportExcel(response, list, "用户对应群数据");
    }

    /**
     * 获取用户对应群详细信息
     */

    @GetMapping(value = "/{usergroupid}")
    public AjaxResult getInfo(@PathVariable("usergroupid") String usergroupid)
    {
        return AjaxResult.success(xtUserToGroupService.selectXtUserToGroupByUsergroupid(usergroupid));
    }

    /**
     * 新增用户对应群
     */

    @Log(title = "用户对应群", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtUserToGroup xtUserToGroup)
    {
        return toAjax(xtUserToGroupService.insertXtUserToGroup(xtUserToGroup));
    }

    /**
     * 修改用户对应群
     */

    @Log(title = "用户对应群", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtUserToGroup xtUserToGroup)
    {
        return toAjax(xtUserToGroupService.updateXtUserToGroup(xtUserToGroup));
    }

    /**
     * 删除用户对应群
     */

    @Log(title = "用户对应群", businessType = BusinessType.DELETE)
	@DeleteMapping("/{usergroupids}")
    public AjaxResult remove(@PathVariable String[] usergroupids)
    {
        return toAjax(xtUserToGroupService.deleteXtUserToGroupByUsergroupids(usergroupids));
    }
}
