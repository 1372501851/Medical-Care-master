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
import com.ruoyi.project.im.domain.XtGroup;
import com.ruoyi.project.im.service.IXtGroupService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 群 （科室搭建一个群，值班人在群，不值班人不在群了）Controller
 *
 * @author ruoyi
 * @date 2022-10-14
 */
@RestController
@RequestMapping("/web/group")
public class XtGroupController extends BaseController
{
    @Autowired
    private IXtGroupService xtGroupService;

    /**
     * 查询群 （科室搭建一个群，值班人在群，不值班人不在群了）列表
     */

    @GetMapping("/list")
    public TableDataInfo list(XtGroup xtGroup)
    {
        startPage();
        List<XtGroup> list = xtGroupService.selectXtGroupList(xtGroup);
        return getDataTable(list);
    }

    /**
     * 导出群 （科室搭建一个群，值班人在群，不值班人不在群了）列表
     */

    @Log(title = "群 （科室搭建一个群，值班人在群，不值班人不在群了）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtGroup xtGroup)
    {
        List<XtGroup> list = xtGroupService.selectXtGroupList(xtGroup);
        ExcelUtil<XtGroup> util = new ExcelUtil<XtGroup>(XtGroup.class);
        util.exportExcel(response, list, "群 （科室搭建一个群，值班人在群，不值班人不在群了）数据");
    }

    /**
     * 获取群 （科室搭建一个群，值班人在群，不值班人不在群了）详细信息
     */

    @GetMapping(value = "/{ugroupid}")
    public AjaxResult getInfo(@PathVariable("ugroupid") String ugroupid)
    {
        return AjaxResult.success(xtGroupService.selectXtGroupByUgroupid(ugroupid));
    }

    /**
     * 新增群 （科室搭建一个群，值班人在群，不值班人不在群了）
     */

    @Log(title = "群 （科室搭建一个群，值班人在群，不值班人不在群了）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtGroup xtGroup)
    {
        return toAjax(xtGroupService.insertXtGroup(xtGroup));
    }

    /**
     * 修改群 （科室搭建一个群，值班人在群，不值班人不在群了）
     */

    @Log(title = "群 （科室搭建一个群，值班人在群，不值班人不在群了）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtGroup xtGroup)
    {
        return toAjax(xtGroupService.updateXtGroup(xtGroup));
    }

    /**
     * 删除群 （科室搭建一个群，值班人在群，不值班人不在群了）
     */

    @Log(title = "群 （科室搭建一个群，值班人在群，不值班人不在群了）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ugroupids}")
    public AjaxResult remove(@PathVariable String[] ugroupids)
    {
        return toAjax(xtGroupService.deleteXtGroupByUgroupids(ugroupids));
    }
}
