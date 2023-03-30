package com.ruoyi.project.common.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.XtPaiban;
import com.ruoyi.project.common.domain.dto.PaibanAndEmployee;
import com.ruoyi.project.common.domain.vo.PaibanVo;
import com.ruoyi.project.common.service.IXtPaibanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 排班Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "排班模块")
@RestController
@RequestMapping("/web/scheduling")
public class XtPaibanController extends BaseController
{
    @Autowired
    private IXtPaibanService xtPaibanService;

    /**
     * 查询排班列表
     */
    @ApiOperation(value = "查询排班列表")
    @GetMapping("/list")
    public TableDataInfo list(XtPaiban xtPaiban)
    {
        startPage();
        List<XtPaiban> list = xtPaibanService.selectXtPaibanList(xtPaiban);
        return getDataTable(list);
    }

    /**
     * 查询排班列表(包括员工)
     */
    @ApiOperation(value = "查询排班列表(包含员工)")
    @GetMapping("/employee/list")
    public TableDataInfo listAndEmployee(XtPaiban xtPaiban) {
        startPage();
        List<PaibanVo> list = xtPaibanService.selectPaibanVoList(xtPaiban);
        return getDataTable(list);
    }

    /**
     * 导出排班列表
     */
    @ApiOperation(value = "导出排班列表")
    @Log(title = "排班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtPaiban xtPaiban)
    {
        List<XtPaiban> list = xtPaibanService.selectXtPaibanList(xtPaiban);
        ExcelUtil<XtPaiban> util = new ExcelUtil<XtPaiban>(XtPaiban.class);
        util.exportExcel(response, list, "排班数据");
    }

    /**
     * 获取排班详细信息
     */
    @ApiOperation(value = "获取排班详细信息")
    @GetMapping(value = "/{upaibanid}")
    public AjaxResult getInfo(@PathVariable("upaibanid") String upaibanid)
    {
        return AjaxResult.success(xtPaibanService.selectXtPaibanByUpaibanid(upaibanid));
    }

    @ApiOperation(value = "获取排班和员工信息")
    @GetMapping(value = "/employee/{upaibanid}")
    public AjaxResult getPaibanAndEmployeeInfo(@PathVariable("upaibanid") String upaibanid) {
        return AjaxResult.success(xtPaibanService.selectXtPaibanAndEmployeeByUpaibanid(upaibanid));
    }

    /**
     * 新增排班
     */
    @ApiOperation(value = "新增排班")
    @Log(title = "排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtPaiban xtPaiban)
    {
        return toAjax(xtPaibanService.insertXtPaiban(xtPaiban));
    }

    @ApiOperation(value = "新增排班和员工")
    @PostMapping("/addPaibanAndEmployee")
    public AjaxResult addPaibanAndEmployee(@RequestBody PaibanAndEmployee paibanAndEmployee){
        return toAjax(xtPaibanService.insertXtPaibanAndEmployee(paibanAndEmployee));
    }

    /**
     * 修改排班
     */
    @ApiOperation(value = "修改排班")
    @Log(title = "排班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtPaiban xtPaiban)
    {
        return toAjax(xtPaibanService.updateXtPaiban(xtPaiban));
    }

    @ApiOperation(value = "修改排班和员工")
    @PutMapping("/updatePaibanAndEmployee")
    public AjaxResult updatePaibanAndEmployee(@RequestBody PaibanAndEmployee paibanAndEmployee) {
        return toAjax(xtPaibanService.updatePaibanAndEmployee(paibanAndEmployee));
    }

    /**
     * 删除排班
     */
    @ApiOperation(value = "删除排班")
    @Log(title = "排班", businessType = BusinessType.DELETE)
	@DeleteMapping("/{upaibanids}")
    public AjaxResult remove(@PathVariable String[] upaibanids)
    {
        return toAjax(xtPaibanService.deleteXtPaibanByUpaibanids(upaibanids));
    }

    @ApiOperation(value = "删除排班和员工")
    @DeleteMapping("/employee/{upaibanids}")
    public AjaxResult removePaibanAndEmployee(@PathVariable String[] upaibanids)
    {
        return toAjax(xtPaibanService.deleteXtPaibanAndEmployeeByUpaibanids(upaibanids));
    }


}
