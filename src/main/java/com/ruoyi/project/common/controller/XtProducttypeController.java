//package com.ruoyi.project.common.controller;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.framework.aspectj.lang.annotation.Log;
//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
//import com.ruoyi.framework.web.controller.BaseController;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.framework.web.page.TableDataInfo;
//import com.ruoyi.project.mall.domain.XtProducttype;
//import com.ruoyi.project.mall.service.IXtProducttypeService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * 产品类型（用在医疗护理系统，药店系统，其他系统）Controller
// *
// * @author ruoyi
// * @date 2022-10-13
// */
//@Api(tags = "产品类型模块")
//@RestController
//@RequestMapping("/web/productType")
//public class XtProducttypeController extends BaseController
//{
//    @Autowired
//    private IXtProducttypeService xtProducttypeService;
//
//    /**
//     * 查询产品类型（用在医疗护理系统，药店系统，其他系统）列表
//     */
//
//    @ApiOperation(value = "查询产品类型（用在医疗护理系统，药店系统，其他系统）列表")
//    @GetMapping("/list")
//    public TableDataInfo list(XtProducttype xtProducttype)
//    {
//        startPage();
//        List<XtProducttype> list = xtProducttypeService.selectXtProducttypeList(xtProducttype);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出产品类型（用在医疗护理系统，药店系统，其他系统）列表
//     */
//    @ApiOperation(value = "导出产品类型（用在医疗护理系统，药店系统，其他系统）列表")
//    @Log(title = "产品类型（用在医疗护理系统，药店系统，其他系统）", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtProducttype xtProducttype)
//    {
//        List<XtProducttype> list = xtProducttypeService.selectXtProducttypeList(xtProducttype);
//        ExcelUtil<XtProducttype> util = new ExcelUtil<XtProducttype>(XtProducttype.class);
//        util.exportExcel(response, list, "产品类型（用在医疗护理系统，药店系统，其他系统）数据");
//    }
//
//    /**
//     * 获取产品类型（用在医疗护理系统，药店系统，其他系统）详细信息
//     */
//    @ApiOperation(value = " 获取产品类型（用在医疗护理系统，药店系统，其他系统）详细信息")
//    @GetMapping(value = "/{uproducttypeid}")
//    public AjaxResult getInfo(@PathVariable("uproducttypeid") String uproducttypeid)
//    {
//        return AjaxResult.success(xtProducttypeService.selectXtProducttypeByUproducttypeid(uproducttypeid));
//    }
//
//    /**
//     * 新增产品类型（用在医疗护理系统，药店系统，其他系统）
//     */
//    @ApiOperation(value = "新增产品类型（用在医疗护理系统，药店系统，其他系统）")
//    @Log(title = "产品类型（用在医疗护理系统，药店系统，其他系统）", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody XtProducttype xtProducttype)
//    {
//        return toAjax(xtProducttypeService.insertXtProducttype(xtProducttype));
//    }
//
//    /**
//     * 修改产品类型（用在医疗护理系统，药店系统，其他系统）
//     */
//    @ApiOperation(value = "修改产品类型（用在医疗护理系统，药店系统，其他系统）")
//    @Log(title = "产品类型（用在医疗护理系统，药店系统，其他系统）", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody XtProducttype xtProducttype)
//    {
//        return toAjax(xtProducttypeService.updateXtProducttype(xtProducttype));
//    }
//
//    /**
//     * 删除产品类型（用在医疗护理系统，药店系统，其他系统）
//     */
//    @ApiOperation(value = "删除产品类型（用在医疗护理系统，药店系统，其他系统）")
//    @Log(title = "产品类型（用在医疗护理系统，药店系统，其他系统）", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{uproducttypeids}")
//    public AjaxResult remove(@PathVariable String[] uproducttypeids)
//    {
//        return toAjax(xtProducttypeService.deleteXtProducttypeByUproducttypeids(uproducttypeids));
//    }
//}
