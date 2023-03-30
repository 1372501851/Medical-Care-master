package com.ruoyi.project.common.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.XtUserstore;
import com.ruoyi.project.common.service.IXtUserstoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户收藏 收藏医院、收藏医生、收藏语言内容Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "用户收藏模块")
@RestController
@RequestMapping("/web/userStore")
public class XtUserstoreController extends BaseController
{
    @Autowired
    private IXtUserstoreService xtUserstoreService;

    /**
     * 查询用户收藏 收藏医院、收藏医生、收藏语言内容列表
     */
    @ApiOperation(value = "查询用户收藏")

    @GetMapping("/list")
    public TableDataInfo list(XtUserstore xtUserstore)
    {
        startPage();
        List<XtUserstore> list = xtUserstoreService.selectXtUserstoreList(xtUserstore);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏 收藏医院、收藏医生、收藏语言内容列表
     */
    @ApiOperation(value = "导出用户收藏")
    @Log(title = "用户收藏 收藏医院、收藏医生、收藏语言内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtUserstore xtUserstore)
    {
        List<XtUserstore> list = xtUserstoreService.selectXtUserstoreList(xtUserstore);
        ExcelUtil<XtUserstore> util = new ExcelUtil<XtUserstore>(XtUserstore.class);
        util.exportExcel(response, list, "用户收藏 收藏医院、收藏医生、收藏语言内容数据");
    }

    /**
     * 获取用户收藏 收藏医院、收藏医生、收藏语言内容详细信息
     */
    @ApiOperation(value = "获取用户收藏")
    @GetMapping(value = "/{userstoreid}")
    public AjaxResult getInfo(@PathVariable("userstoreid") String userstoreid)
    {
        return AjaxResult.success(xtUserstoreService.selectXtUserstoreByUserstoreid(userstoreid));
    }

    /**
     * 新增用户收藏 收藏医院、收藏医生、收藏语言内容
     */
    @ApiOperation(value = "新增用户收藏")
    @Log(title = "用户收藏 收藏医院、收藏医生、收藏语言内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtUserstore xtUserstore)
    {
        return toAjax(xtUserstoreService.insertXtUserstore(xtUserstore));
    }

    /**
     * 修改用户收藏 收藏医院、收藏医生、收藏语言内容
     */
    @ApiOperation(value = "修改用户收藏")
    @Log(title = "用户收藏 收藏医院、收藏医生、收藏语言内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtUserstore xtUserstore)
    {
        return toAjax(xtUserstoreService.updateXtUserstore(xtUserstore));
    }

    /**
     * 删除用户收藏 收藏医院、收藏医生、收藏语言内容
     */
    @ApiOperation(value = "删除用户收藏")
    @Log(title = "用户收藏 收藏医院、收藏医生、收藏语言内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userstoreids}")
    public AjaxResult remove(@PathVariable String[] userstoreids)
    {
        return toAjax(xtUserstoreService.deleteXtUserstoreByUserstoreids(userstoreids));
    }
}
