package com.ruoyi.project.merchant.controller;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtRoom;
import com.ruoyi.project.merchant.service.IXtRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "病房管理")
@RestController
@RequestMapping("/ward")
public class WardController extends BaseController {

    /**
     *
     * 新曾病房
     * 修改病房
     * 删除病房
     * 查找病房
     * 不能重复的逻辑判断
     *
     * */

    @Autowired
    private IXtRoomService xtRoomService;

    /**
     * 查询病房列表
     */

    @ApiOperation(value = "查询病房列表")
    @GetMapping("/list")
    public TableDataInfo list(XtRoom xtRoom)
    {
        startPage();
        List<XtRoom> list = xtRoomService.selectXtRoomList(xtRoom);
        return getDataTable(list);
    }

    /**
     * 导出病房列表
     */
    @ApiOperation(value = "导出病房列表")
    @Log(title = "病房", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtRoom xtRoom)
    {
        List<XtRoom> list = xtRoomService.selectXtRoomList(xtRoom);
        ExcelUtil<XtRoom> util = new ExcelUtil<XtRoom>(XtRoom.class);
        util.exportExcel(response, list, "病房数据");
    }

    /**
     * 获取病房详细信息
     */
    @ApiOperation(value = "获取病房详细信息")
    @GetMapping(value = "/info/{uroomid}")
    public AjaxResult getInfo(@PathVariable("uroomid") String uroomid)
    {
        return AjaxResult.success(xtRoomService.selectXtRoomByUroomid(uroomid));
    }

    /**
     * 新增病房
     */
    @ApiOperation(value = "新增病房")
    @Log(title = "病房", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated  @RequestBody XtRoom xtRoom)
    {
        return toAjax(xtRoomService.insertXtRoom(xtRoom));
    }

    /**
     * 修改病房
     */
    @ApiOperation(value = "修改病房")
    @Log(title = "病房", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtRoom xtRoom)
    {
        return toAjax(xtRoomService.updateXtRoom(xtRoom));
    }

    /**
     * 删除病房
     */
    @ApiOperation(value = "删除病房")
    @Log(title = "病房", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{uroomids}")
    public AjaxResult remove(@PathVariable String[] uroomids)
    {
        return toAjax(xtRoomService.deleteXtRoomByUroomids(uroomids));
    }


    /**病房与床号也需要构建一个下拉树列表*/


}
