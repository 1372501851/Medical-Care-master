package com.ruoyi.project.common.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.XtUsertopaiban;
import com.ruoyi.project.common.domain.dto.PaibanEmployeeDto;
import com.ruoyi.project.common.service.IXtUsertopaibanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户与排班Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Api(tags = "用户排班模块")
@RestController
@RequestMapping("/web/userAndScheduling")
public class XtUsertopaibanController extends BaseController
{
    @Autowired
    private IXtUsertopaibanService xtUsertopaibanService;

    /**
     * 查询用户与排班列表
     */
    @ApiOperation(value = "查询用户与排班列表")
    @GetMapping("/list")
    public TableDataInfo list(XtUsertopaiban xtUsertopaiban)
    {
        startPage();
        List<XtUsertopaiban> list = xtUsertopaibanService.selectXtUsertopaibanList(xtUsertopaiban);
        return getDataTable(list);
    }

    /**
     * 导出用户与排班列表
     */
    @ApiOperation(value = "导出用户与排班列表")
    @Log(title = "用户与排班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtUsertopaiban xtUsertopaiban)
    {
        List<XtUsertopaiban> list = xtUsertopaibanService.selectXtUsertopaibanList(xtUsertopaiban);
        ExcelUtil<XtUsertopaiban> util = new ExcelUtil<XtUsertopaiban>(XtUsertopaiban.class);
        util.exportExcel(response, list, "用户与排班数据");
    }

    /**
     * 获取用户与排班详细信息
     */

    @ApiOperation(value = "获取用户与排班详细信息")
    @GetMapping(value = "/{upaibanid}")
    public AjaxResult getInfo(@PathVariable("upaibanid") String upaibanid)
    {
        return AjaxResult.success(xtUsertopaibanService.selectXtUsertopaibanByUpaibanid(upaibanid));
    }

    /**
     * 新增用户与排班
     */
    @ApiOperation(value = "新增用户与排班")
    @Log(title = "用户与排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtUsertopaiban xtUsertopaiban)
    {
        return toAjax(xtUsertopaibanService.insertXtUsertopaiban(xtUsertopaiban));
    }

    /**
     * 修改用户与排班
     */

    @ApiOperation(value = "修改用户与排班")
    @Log(title = "用户与排班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtUsertopaiban xtUsertopaiban)
    {
        return toAjax(xtUsertopaibanService.updateXtUsertopaiban(xtUsertopaiban));
    }

    /**
     * 删除用户与排班
     */

    @ApiOperation(value = "删除用户与排班")
    @Log(title = "用户与排班", businessType = BusinessType.DELETE)
	@DeleteMapping("/{upaibanids}")
    public AjaxResult remove(@PathVariable String[] upaibanids)
    {
        return toAjax(xtUsertopaibanService.deleteXtUsertopaibanByUpaibanids(upaibanids));
    }

    @ApiOperation(value = "批量添加排班用户")
    @PostMapping("/add/batch")
    public AjaxResult addBatch(@RequestBody PaibanEmployeeDto paibanEmployeeDto){
        return toAjax(xtUsertopaibanService.insertXtUsertopaibanBatch(paibanEmployeeDto.getUpaibanid(),paibanEmployeeDto.getUemployeeids()));
    }


}
