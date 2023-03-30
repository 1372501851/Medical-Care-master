package com.ruoyi.project.merchant.controller;

import com.ruoyi.common.utils.CustomPageUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtProducttype;
import com.ruoyi.project.merchant.domain.query.MallQuery;
import com.ruoyi.project.merchant.domain.tree.ProductTypeTreeSelect;
import com.ruoyi.project.merchant.domain.vo.ProductTypeVo;
import com.ruoyi.project.merchant.mapper.XtProducttypeMapper;
import com.ruoyi.project.merchant.service.IXtProducttypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/mall/product/type")
@Api(tags = "商品类型模块")
public class ProductTypeController extends BaseController {
    @Autowired
    private IXtProducttypeService xtProducttypeService;

    @Autowired
    private CustomPageUtil<ProductTypeVo> customPageUtil;

    @Autowired
    private XtProducttypeMapper xtProducttypeMapper;

    /**根据商家,获取对应的商品类型树*/

    @ApiOperation(value = "根据商家获取对应的商品类型树")
    @GetMapping("/merchant/list")
    public AjaxResult merchantList(MallQuery mallQuery)
    {
        List<ProductTypeTreeSelect> list = xtProducttypeService.buildTypeTreeBycompId(mallQuery);
        return AjaxResult.success(list);
    }


    @ApiOperation(value = "查询商品类型树")
    @GetMapping("/list")
    public TableDataInfo list(XtProducttype xtProducttype,
                              @RequestParam Integer pageNum,
                              @RequestParam Integer pageSize)
    {
        List<ProductTypeVo> list = xtProducttypeService.buildTypeTree(xtProducttype);
        startPage();
        TableDataInfo dataTable = getDataTable(list);
        dataTable.setRows(customPageUtil.getPageList(list,pageNum,pageSize));
        return dataTable;
    }

    /**
     * 导出产品类型（用在医疗护理系统，药店系统，其他系统）列表
     */
    @ApiOperation(value = "导出产品类型（用在医疗护理系统，药店系统，其他系统）列表")
    @Log(title = "产品类型（用在医疗护理系统，药店系统，其他系统）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtProducttype xtProducttype)
    {
        List<XtProducttype> list = xtProducttypeService.selectXtProducttypeList(xtProducttype);
        ExcelUtil<XtProducttype> util = new ExcelUtil<XtProducttype>(XtProducttype.class);
        util.exportExcel(response, list, "产品类型（用在医疗护理系统，药店系统，其他系统）数据");
    }

    /**
     * 获取商品类型详细信息
     */
    @ApiOperation(value = " 获取商品类型详细信息")
    @GetMapping(value = "/info/{uproducttypeid}")
    public AjaxResult getInfo(@PathVariable("uproducttypeid") String uproducttypeid)
    {
        return AjaxResult.success(xtProducttypeService.selectXtProducttypeByUproducttypeid(uproducttypeid));
    }

    /**
     * 新增产品类型（用在医疗护理系统，药店系统，其他系统）
     */
    @ApiOperation(value = "新增产品类型")
    @Log(title = "商品类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtProducttype xtProducttype)
    {
        return toAjax(xtProducttypeService.insertXtProducttype(xtProducttype));
    }

    /**
     * 修改产品类型（用在医疗护理系统，药店系统，其他系统）
     */
    @ApiOperation(value = "修改商品类型")
    @Log(title = "商品类型", businessType = BusinessType.UPDATE)
    @PutMapping ("/xg")
    public AjaxResult xg(String name , String space ,String id)
    {
        return toAjax(xtProducttypeService.updateXtProducttype(name , space ,id));
    }

    /**
     * 删除产品类型（用在医疗护理系统，药店系统，其他系统）
     */
    @ApiOperation(value = "删除商品类型")
    @Log(title = "商品类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{uproducttypeids}")
    public AjaxResult remove(@PathVariable String[] uproducttypeids)
    {
        return toAjax(xtProducttypeService.deleteXtProducttypeByUproducttypeids(uproducttypeids));
    }

    /**
     *
     * 构建构建前端下拉类别树;
     */
    @ApiOperation(value = "获取商品类型下拉树")
    @GetMapping("/typeTree")
    public AjaxResult tree(XtProducttype xtProducttype)
    {
        return AjaxResult.success(xtProducttypeService.selectTypeTreeSelect(xtProducttype));
    }


    @ApiOperation(value = "小刘专用，获取商品类型详细信息")
    @GetMapping("/selectAllXtproductType")
    public AjaxResult selectAllXtproductType()
    {
        return AjaxResult.success(xtProducttypeMapper.selectAllXtproductType());
    }

    @ApiOperation(value = "小刘专用，获取购买过该商品种类的客户数量")
    @GetMapping("/selectProductTypeCusnum")
    public AjaxResult selectProductTypeCusnum(String id)
    {
        return AjaxResult.success(xtProducttypeMapper.selectProductTypeCusnum(id));
    };


}
