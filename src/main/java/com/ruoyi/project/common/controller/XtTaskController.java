//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.task.domain.XtTask;
//import com.ruoyi.project.task.service.IXtTaskService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 任务（图文问诊、护理、护工等）Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//@Api(tags = "任务模块")
//@RestController
//@RequestMapping("/web/task")
//public class XtTaskController extends BaseController
//{
//    @Autowired
//    private IXtTaskService xtTaskService;
//
//    /**
//     * 查询任务（图文问诊、护理、护工等）列表
//     */
//
//    @ApiOperation(value = "查询任务")
//    @GetMapping("/list")
//    public TableDataInfo list(XtTask xtTask)
//    {
//        startPage();
//        List<XtTask> list = xtTaskService.selectXtTaskList(xtTask);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出任务（图文问诊、护理、护工等）列表
//     */
//    @ApiOperation(value = "导出任务")
//    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtTask xtTask)
//    {
//        List<XtTask> list = xtTaskService.selectXtTaskList(xtTask);
//        ExcelUtil<XtTask> util = new ExcelUtil<XtTask>(XtTask.class);
//        util.exportExcel(response, list, "任务（图文问诊、护理、护工等）数据");
//    }
//
//    /**
//     * 获取任务（图文问诊、护理、护工等）详细信息
//     */
//    @ApiOperation(value = "获取任务")
//    @GetMapping(value = "/{utaskid}")
//    public AjaxResult getInfo(@PathVariable("utaskid") String utaskid)
//    {
//        return AjaxResult.success(xtTaskService.selectXtTaskByUtaskid(utaskid));
//    }
//
//    /**
//     * 新增任务（图文问诊、护理、护工等）
//     */
//    @ApiOperation(value = "新增任务")
//    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtTask xtTask)
//    {
//        return toAjax(xtTaskService.insertXtTask(xtTask));
//    }
//
//    /**
//     * 修改任务（图文问诊、护理、护工等）
//     */
//    @ApiOperation(value = "修改任务")
//    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtTask xtTask)
//    {
//        return toAjax(xtTaskService.updateXtTask(xtTask));
//    }
//
//    /**
//     * 删除任务（图文问诊、护理、护工等）
//     */
//    @ApiOperation(value = "删除任务")
//    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{utaskids}")
//    public AjaxResult remove(@PathVariable String[] utaskids)
//    {
//        return toAjax(xtTaskService.deleteXtTaskByUtaskids(utaskids));
//    }
//}
