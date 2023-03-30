package com.ruoyi.project.common.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.XtAppProduct;
import com.ruoyi.project.common.service.IXtAppProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 申请产品列(也可以是报销项目)Controller
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@RestController
@RequestMapping("/web/application/product")
@Api(tags = "申请产品模块")
public class XtAppProductController extends BaseController
{
    @Autowired
    private IXtAppProductService xtAppProductService;

    /**
     * 查询申请产品列(也可以是报销项目)列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询申请产品")
    public TableDataInfo list(XtAppProduct xtAppProduct)
    {
        startPage();
        List<XtAppProduct> list = xtAppProductService.selectXtAppProductList(xtAppProduct);
        return getDataTable(list);
    }

    /**
     * 导出申请产品列(也可以是报销项目)列表
     */

    @Log(title = "申请产品列(也可以是报销项目)", businessType = BusinessType.EXPORT)
    @ApiOperation(value = "导出申请产品")
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtAppProduct xtAppProduct)
    {
        List<XtAppProduct> list = xtAppProductService.selectXtAppProductList(xtAppProduct);
        ExcelUtil<XtAppProduct> util = new ExcelUtil<XtAppProduct>(XtAppProduct.class);
        util.exportExcel(response, list, "申请产品列(也可以是报销项目)数据");
    }

    /**
     * 获取申请产品列(也可以是报销项目)详细信息
     */
    @ApiOperation(value = "获取申请产品")
    @GetMapping(value = "/{uappProductid}")
    public AjaxResult getInfo(@PathVariable("uappProductid") String uappProductid)
    {
        return AjaxResult.success(xtAppProductService.selectXtAppProductByUappProductid(uappProductid));
    }

    /**
     * 新增申请产品列(也可以是报销项目)
     */

    @Log(title = "申请产品列(也可以是报销项目)", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增申请产品")
    @PostMapping
    public AjaxResult add(@RequestBody XtAppProduct xtAppProduct)
    {
        return toAjax(xtAppProductService.insertXtAppProduct(xtAppProduct));
    }

    /**
     * 修改申请产品列(也可以是报销项目)
     */
    @ApiOperation(value = "修改申请产品")
    @Log(title = "申请产品列(也可以是报销项目)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtAppProduct xtAppProduct)
    {
        return toAjax(xtAppProductService.updateXtAppProduct(xtAppProduct));
    }

    /**
     * 删除申请产品列(也可以是报销项目)
     */
    @ApiOperation(value = "删除申请产品")
    @Log(title = "申请产品列(也可以是报销项目)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uappProductids}")
    public AjaxResult remove(@PathVariable String[] uappProductids)
    {
        return toAjax(xtAppProductService.deleteXtAppProductByUappProductids(uappProductids));
    }
}
