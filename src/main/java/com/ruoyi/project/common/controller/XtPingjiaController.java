//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.merchant.domain.XtPingjia;
//import com.ruoyi.project.merchant.service.IXtPingjiaService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 任务评价Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//@Api(tags = "任务评价模块")
//@RestController
//@RequestMapping("/web/evaluation")
//public class XtPingjiaController extends BaseController
//{
//    @Autowired
//    private IXtPingjiaService xtPingjiaService;
//
//    /**
//     * 查询任务评价列表
//     */
//
//    @ApiOperation(value = "查询任务评价列表")
//    @GetMapping("/list")
//    public TableDataInfo list(XtPingjia xtPingjia)
//    {
//        startPage();
//        List<XtPingjia> list = xtPingjiaService.selectXtPingjiaList(xtPingjia);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出任务评价列表
//     */
//    @ApiOperation(value = "导出任务评价列表")
//    @Log(title = "任务评价", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtPingjia xtPingjia)
//    {
//        List<XtPingjia> list = xtPingjiaService.selectXtPingjiaList(xtPingjia);
//        ExcelUtil<XtPingjia> util = new ExcelUtil<XtPingjia>(XtPingjia.class);
//        util.exportExcel(response, list, "任务评价数据");
//    }
//
//    /**
//     * 获取任务评价详细信息
//     */
//    @ApiOperation(value = "获取任务评价详细信息")
//    @GetMapping(value = "/{upingjiaid}")
//    public AjaxResult getInfo(@PathVariable("upingjiaid") String upingjiaid)
//    {
//        return AjaxResult.success(xtPingjiaService.selectXtPingjiaByUpingjiaid(upingjiaid));
//    }
//
//    /**
//     * 新增任务评价
//     */
//    @ApiOperation(value = "新增任务评价")
//    @Log(title = "任务评价", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtPingjia xtPingjia)
//    {
//        return toAjax(xtPingjiaService.insertXtPingjia(xtPingjia));
//    }
//
//    /**
//     * 修改任务评价
//     */
//    @ApiOperation(value = "修改任务评价")
//    @Log(title = "任务评价", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtPingjia xtPingjia)
//    {
//        return toAjax(xtPingjiaService.updateXtPingjia(xtPingjia));
//    }
//
//    /**
//     * 删除任务评价
//     */
//    @ApiOperation(value = "删除任务评价")
//    @Log(title = "任务评价", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{upingjiaids}")
//    public AjaxResult remove(@PathVariable String[] upingjiaids)
//    {
//        return toAjax(xtPingjiaService.deleteXtPingjiaByUpingjiaids(upingjiaids));
//    }
//}
