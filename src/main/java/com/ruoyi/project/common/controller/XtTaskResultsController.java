//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.task.domain.XtTaskResults;
//import com.ruoyi.project.task.service.IXtTaskResultsService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 任务结果 主要是看病医生填写的结果Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//
//@Api(tags = "任务结果模块")
//@RestController
//@RequestMapping("/web/taskResults")
//public class XtTaskResultsController extends BaseController
//{
//    @Autowired
//    private IXtTaskResultsService xtTaskResultsService;
//
//    /**
//     * 查询任务结果 主要是看病医生填写的结果列表
//     */
//
//    @ApiOperation(value = "查询任务结果")
//    @GetMapping("/list")
//    public TableDataInfo list(XtTaskResults xtTaskResults)
//    {
//        startPage();
//        List<XtTaskResults> list = xtTaskResultsService.selectXtTaskResultsList(xtTaskResults);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出任务结果 主要是看病医生填写的结果列表
//     */
//    @ApiOperation(value = "导出任务结果")
//    @Log(title = "任务结果 主要是看病医生填写的结果", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtTaskResults xtTaskResults)
//    {
//        List<XtTaskResults> list = xtTaskResultsService.selectXtTaskResultsList(xtTaskResults);
//        ExcelUtil<XtTaskResults> util = new ExcelUtil<XtTaskResults>(XtTaskResults.class);
//        util.exportExcel(response, list, "任务结果 主要是看病医生填写的结果数据");
//    }
//
//    /**
//     * 获取任务结果 主要是看病医生填写的结果详细信息
//     */
//    @ApiOperation(value = "获取任务结果")
//    @GetMapping(value = "/{utaskResultsid}")
//    public AjaxResult getInfo(@PathVariable("utaskResultsid") String utaskResultsid)
//    {
//        return AjaxResult.success(xtTaskResultsService.selectXtTaskResultsByUtaskResultsid(utaskResultsid));
//    }
//
//    /**
//     * 新增任务结果 主要是看病医生填写的结果
//     */
//    @ApiOperation(value = "新增任务结果")
//    @Log(title = "任务结果 主要是看病医生填写的结果", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtTaskResults xtTaskResults)
//    {
//        return toAjax(xtTaskResultsService.insertXtTaskResults(xtTaskResults));
//    }
//
//    /**
//     * 修改任务结果 主要是看病医生填写的结果
//     */
//    @ApiOperation(value = "修改任务结果")
//    @Log(title = "任务结果 主要是看病医生填写的结果", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtTaskResults xtTaskResults)
//    {
//        return toAjax(xtTaskResultsService.updateXtTaskResults(xtTaskResults));
//    }
//
//    /**
//     * 删除任务结果 主要是看病医生填写的结果
//     */
//    @ApiOperation(value = "删除任务结果")
//    @Log(title = "任务结果 主要是看病医生填写的结果", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{utaskResultsids}")
//    public AjaxResult remove(@PathVariable String[] utaskResultsids)
//    {
//        return toAjax(xtTaskResultsService.deleteXtTaskResultsByUtaskResultsids(utaskResultsids));
//    }
//}
