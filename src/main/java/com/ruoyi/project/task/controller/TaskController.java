package com.ruoyi.project.task.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.task.domain.XtTask;
import com.ruoyi.project.task.service.IXtTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-10-21
 */

@RestController
@RequestMapping("/task")
@Api(tags = "任务模块")
public class TaskController extends BaseController {


    /**
     *
     * 创建任务(订单)
     *
     * 填写:
     * 提问者信息:
     *这个信息通过用户userId获取(四张表关联)
     * 用户信息表->部门(科室医生则属于什么科室,患者则隶属于什么科室)->病房->床位
     *
     * 接单医生信息:
     *前端传来医生userId
     *
     *
     * 被服务人信息:
     * 如果该服务人有注册过,通过userId获取被服务人信息
     * 如果没有注册过,需要前端填写这些信息;
     *
     * 订单信息
     *
     *前端填写一部分
     *
     * 后端填写一部分
     *
     *
     * 订单状态:
     * 结束时间,订单结束时再进行更新这个订单操作;
     *
     *
     * */





    @Autowired
    private IXtTaskService xtTaskService;

    /**
     * 查询任务（图文问诊、护理、护工等）列表
     */

    @ApiOperation(value = "获取任务列表")
    @GetMapping("/list")
    public TableDataInfo list(XtTask xtTask)
    {
        startPage();
        List<XtTask> list = xtTaskService.selectXtTaskList(xtTask);
        return getDataTable(list);
    }

    /**
     * 导出任务（图文问诊、护理、护工等）列表
     */
//    @ApiOperation(value = "导出任务")
//    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtTask xtTask)
//    {
//        List<XtTask> list = xtTaskService.selectXtTaskList(xtTask);
//        ExcelUtil<XtTask> util = new ExcelUtil<XtTask>(XtTask.class);
//        util.exportExcel(response, list, "任务（图文问诊、护理、护工等）数据");
//    }


    /**
     * 新增任务（图文问诊、护理、护工等）
     */
    @ApiOperation(value = "发布任务")
    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtTask xtTask)
    {
        return toAjax(xtTaskService.insertXtTask(xtTask));
    }

    /**
     * 获取任务（图文问诊、护理、护工等）详细信息
     */
    @ApiOperation(value = "根据任务id获取任务详细信息")
    @GetMapping(value = "/info/{utaskid}")
    public AjaxResult getInfo(@PathVariable("utaskid") String utaskid)
    {
        return AjaxResult.success(xtTaskService.selectXtTaskByUtaskid(utaskid));
    }



    /**
     * 修改任务（图文问诊、护理、护工等）
     */
    @ApiOperation(value = "修改任务")
    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtTask xtTask)
    {
        return toAjax(xtTaskService.updateXtTask(xtTask));
    }

    /**
     * 删除任务（图文问诊、护理、护工等）
     */
    @ApiOperation(value = "删除任务")
    @Log(title = "任务（图文问诊、护理、护工等）", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{utaskids}")
    public AjaxResult remove(@PathVariable String[] utaskids)
    {
        return toAjax(xtTaskService.deleteXtTaskByUtaskids(utaskids));
    }

}
