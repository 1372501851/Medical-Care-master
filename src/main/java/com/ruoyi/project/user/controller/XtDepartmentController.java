package com.ruoyi.project.user.controller;

import com.ruoyi.common.utils.CustomPageUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.vo.DeptVo;
import com.ruoyi.project.user.mapper.XtDepartmentMapper;
import com.ruoyi.project.user.service.IXtDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门(科室)信息Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Api(tags = "部门信息模块")
@RestController
@RequestMapping("/web/department")
public class XtDepartmentController extends BaseController
{
    @Autowired
    private IXtDepartmentService xtDepartmentService;

    @Autowired
    private CustomPageUtil<DeptVo> customPageUtil;

    @Autowired
    private XtDepartmentMapper xtDepartmentMapper;

    /**
     * 查询部门(科室)信息列表
     */
    @ApiOperation(value = "查询部门(科室)信息列表")
    @GetMapping("/list")
    public TableDataInfo list(XtDepartment xtDepartment)
    {
        startPage();
        List<XtDepartment> list = xtDepartmentService.selectXtDepartmentList(xtDepartment);

        return getDataTable(list);
    }


    @ApiOperation(value = "查询部门(科室)信息树")
    @GetMapping("/tree")
    public TableDataInfo getXtDepartmentTree(XtDepartment xtDepartment,
                                             @RequestParam Integer pageNum,
                                             @RequestParam Integer pageSize)
    {

        List<DeptVo> list = xtDepartmentService.getXtDepartmentTree(xtDepartment);
        startPage();
        TableDataInfo dataTable = getDataTable(list);
        dataTable.setRows(customPageUtil.getPageList(list,pageNum,pageSize));
        return dataTable;
    }

    /**
     * 导出部门(科室)信息列表
     */
    @ApiOperation(value = " 导出部门(科室)信息列表")
    @Log(title = "部门(科室)信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtDepartment xtDepartment)
    {
        List<XtDepartment> list = xtDepartmentService.selectXtDepartmentList(xtDepartment);
        ExcelUtil<XtDepartment> util = new ExcelUtil<XtDepartment>(XtDepartment.class);
        util.exportExcel(response, list, "部门(科室)信息数据");
    }

    /**
     * 获取部门(科室)信息详细信息
     */
    @ApiOperation(value = "获取部门(科室)信息详细信息")
    @GetMapping(value = "/info/{udepartmentid}")
    public AjaxResult getInfo(@PathVariable("udepartmentid") String udepartmentid)
    {
        return AjaxResult.success(xtDepartmentService.selectXtDepartmentByUdepartmentid(udepartmentid));
    }

    /**
     * 新增部门(科室)信息
     */
    @ApiOperation(value = "新增部门(科室)信息")
    @Log(title = "部门(科室)信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated  @RequestBody XtDepartment xtDepartment)
    {
        return toAjax(xtDepartmentService.insertXtDepartment(xtDepartment));
    }

    /**
     * 修改部门(科室)信息
     */
    @ApiOperation(value = "修改部门(科室)信息")
    @Log(title = "部门(科室)信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit( @RequestBody XtDepartment xtDepartment)
    {
        return toAjax(xtDepartmentService.updateXtDepartment(xtDepartment));
    }

    /**
     * 删除部门(科室)信息
     */
    @ApiOperation(value = "删除部门(科室)信息")
    @Log(title = "部门(科室)信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/del/{udepartmentids}")
    public AjaxResult remove(@PathVariable String[] udepartmentids)
    {
        return toAjax(xtDepartmentService.deleteXtDepartmentByUdepartmentids(udepartmentids));
    }



    /**
     *
     * 构建前端所需要树结构
     *
     * */
    @ApiOperation(value = "部门下拉树")
    @GetMapping("/deptTree")
    public AjaxResult deptTree(XtDepartment dept)
    {
        return AjaxResult.success(xtDepartmentService.selectDeptTreeList(dept));
    }

    @ApiOperation(value = "通过部门id查询部门名字")
    @Log(title = "查询部门名字", businessType = BusinessType.DELETE)
    @PostMapping("/sele/{udepartmentids}")
    public AjaxResult selectudepartmentidNameById(@PathVariable String[] udepartmentids) {
        List<String> ob = new ArrayList<>();
        for (String x :
                udepartmentids) {
            String s = xtDepartmentMapper.selectudepartmentidNameById(x);
            ob.add(s);
        }
        return AjaxResult.success(ob);
    }



}
