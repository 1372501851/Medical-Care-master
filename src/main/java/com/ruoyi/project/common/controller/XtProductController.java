//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.mall.domain.XtProduct;
//import com.ruoyi.project.mall.service.IXtProductService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 商品Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
////@Api(tags = "商品模块")
//@RestController
//@RequestMapping("/web/product")
//public class XtProductController extends BaseController
//{
////    @Autowired
////    private IXtProductService xtProductService;
////
////    /**
////     * 查询商品列表
////     */
////    @ApiOperation(value = "查询商品列表")
////
////    @GetMapping("/list")
////    public TableDataInfo list(XtProduct xtProduct)
////    {
////        startPage();
////        List<XtProduct> list = xtProductService.selectXtProductList(xtProduct);
////        return getDataTable(list);
////    }
////
////    /**
////     * 导出商品列表
////     */
////    @ApiOperation(value = "导出商品列表")
////    @Log(title = "商品", businessType = BusinessType.EXPORT)
////    @PostMapping("/export")
////    public void export(HttpServletResponse response, XtProduct xtProduct)
////    {
////        List<XtProduct> list = xtProductService.selectXtProductList(xtProduct);
////        ExcelUtil<XtProduct> util = new ExcelUtil<XtProduct>(XtProduct.class);
////        util.exportExcel(response, list, "商品数据");
////    }
////
////    /**
////     * 获取商品详细信息
////     */
////    @ApiOperation(value = "获取商品详细信息")
////    @GetMapping(value = "/{uproductid}")
////    public AjaxResult getInfo(@PathVariable("uproductid") String uproductid)
////    {
////        return AjaxResult.success(xtProductService.selectXtProductByUproductid(uproductid));
////    }
////
////    /**
////     * 新增商品
////     */
////    @ApiOperation(value = "新增商品")
////    @Log(title = "商品", businessType = BusinessType.INSERT)
////    @PostMapping
////    public AjaxResult add(@RequestBody XtProduct xtProduct)
////    {
////        return toAjax(xtProductService.insertXtProduct(xtProduct));
////    }
////
////    /**
////     * 修改商品
////     */
////    @ApiOperation(value = "修改商品")
////    @Log(title = "商品", businessType = BusinessType.UPDATE)
////    @PutMapping
////    public AjaxResult edit(@RequestBody XtProduct xtProduct)
////    {
////        return toAjax(xtProductService.updateXtProduct(xtProduct));
////    }
////
////    /**
////     * 删除商品
////     */
////    @ApiOperation(value = "删除商品")
////    @Log(title = "商品", businessType = BusinessType.DELETE)
////	@DeleteMapping("/{uproductids}")
////    public AjaxResult remove(@PathVariable String[] uproductids)
////    {
////        return toAjax(xtProductService.deleteXtProductByUproductids(uproductids));
////    }
//}
