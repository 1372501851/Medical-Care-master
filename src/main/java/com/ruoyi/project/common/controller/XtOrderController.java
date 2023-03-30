//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.mall.domain.XtOrder;
//import com.ruoyi.project.mall.service.IXtOrderService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 订单Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//@Api(tags = "订单模块")
//@RestController
//@RequestMapping("/web/order")
//public class XtOrderController extends BaseController
//{
//    @Autowired
//    private IXtOrderService xtOrderService;
//
//    /**
//     * 查询订单列表
//     */
//    @ApiOperation(value = "查询订单列表")
//    @GetMapping("/list")
//    public TableDataInfo list(XtOrder xtOrder)
//    {
//        startPage();
//        List<XtOrder> list = xtOrderService.selectXtOrderList(xtOrder);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出订单列表
//     */
//    @ApiOperation(value = "导出订单列表")
//    @Log(title = "订单", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtOrder xtOrder)
//    {
//        List<XtOrder> list = xtOrderService.selectXtOrderList(xtOrder);
//        ExcelUtil<XtOrder> util = new ExcelUtil<XtOrder>(XtOrder.class);
//        util.exportExcel(response, list, "订单数据");
//    }
//
//    /**
//     * 获取订单详细信息
//     */
//    @ApiOperation(value = "获取订单详细信息")
//    @GetMapping(value = "/{uorderid}")
//    public AjaxResult getInfo(@PathVariable("uorderid") String uorderid)
//    {
//        return AjaxResult.success(xtOrderService.selectXtOrderByUorderid(uorderid));
//    }
//
//    /**
//     * 新增订单
//     */
//    @ApiOperation(value = "新增订单")
//    @Log(title = "订单", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtOrder xtOrder)
//    {
//        return toAjax(xtOrderService.insertXtOrder(xtOrder));
//    }
//
//    /**
//     * 修改订单
//     */
//    @ApiOperation(value = "修改订单")
//    @Log(title = "订单", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtOrder xtOrder)
//    {
//        return toAjax(xtOrderService.updateXtOrder(xtOrder));
//    }
//
//    /**
//     * 删除订单
//     */
//    @ApiOperation(value = "删除订单")
//    @Log(title = "订单", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{uorderids}")
//    public AjaxResult remove(@PathVariable String[] uorderids)
//    {
//        return toAjax(xtOrderService.deleteXtOrderByUorderids(uorderids));
//    }
//}
