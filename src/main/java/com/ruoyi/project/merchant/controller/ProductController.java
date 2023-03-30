package com.ruoyi.project.merchant.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtProduct;
import com.ruoyi.project.merchant.domain.query.MallQuery;
import com.ruoyi.project.merchant.domain.query.ProductQuery;
import com.ruoyi.project.merchant.domain.vo.MallProductVo;
import com.ruoyi.project.merchant.domain.vo.MallVo;
import com.ruoyi.project.merchant.domain.vo.QueryPriductVo;
import com.ruoyi.project.merchant.mapper.XtProductMapper;
import com.ruoyi.project.merchant.service.IXtOrderService;
import com.ruoyi.project.merchant.service.IXtProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @author by hujun
 * @date 2022-10-21
 */


@RestController
@RequestMapping("/web/mall/product")
@Api(tags = "Web商品模块")
public class ProductController extends BaseController {

    @Autowired
    private IXtProductService xtProductService;

    @Autowired
    private IXtOrderService orderService;

    @Autowired
    private XtProductMapper xtProductMapper;



    /**热销商品查询*/
    @ApiOperation(value = "热销商品列表")
    @GetMapping("/hot/list")
    public AjaxResult hotList(MallQuery mallQuery)
    {
        List<MallProductVo> list = xtProductService.selectHotProductList(mallQuery);
        return AjaxResult.success(list);
    }


    @ApiOperation(value = "app商品列表")
    @GetMapping("/app/list")
    public AjaxResult appList(ProductQuery  query)
    {
        List<MallProductVo> list = xtProductService.selectXtProductListByQuery(query);
        return AjaxResult.success(list);
    }



//    @ApiOperation(value = "app商品列表树")
//    @GetMapping("/app/list")
//    public AjaxResult appList(MallQuery mallQuery)
//    {
//
//        List<MallVo> list = xtProductService.selectXtProductListVo(mallQuery);
//        return AjaxResult.success(list);
//    }
    /**
     * 查询商品列表
     */
    @ApiOperation(value = "查询所有商品列表")
    @GetMapping("/list")
    public TableDataInfo list(XtProduct xtProduct) {
        startPage();
        List<XtProduct> list = xtProductService.selectXtProductList(xtProduct);
        return getDataTable(list);
    }

    @ApiOperation(value = "查询所有商品列表")
    @GetMapping("/list2")
    public AjaxResult list2(XtProduct xtProduct) {

        List<XtProduct> list = xtProductService.selectXtProductList(xtProduct);
        return AjaxResult.success(list);
    }

    /**
     * 获取商品详细信息
     */
    @ApiOperation(value = "根据商家Id查询所有商品列表")
    @GetMapping(value = "/comp/{compId}")
    public AjaxResult getProList(@PathVariable("compId") String compId)
    {
        startPage();
        List<XtProduct> xtProducts = xtProductService.selectXtProductByCompId(compId);
        return AjaxResult.success(xtProducts);
    }

    /**
     * 导出商品列表
     */
    @ApiOperation(value = "导出商品列表")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtProduct xtProduct)
    {
        List<XtProduct> list = xtProductService.selectXtProductList(xtProduct);
        ExcelUtil<XtProduct> util = new ExcelUtil<XtProduct>(XtProduct.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @ApiOperation(value = "根据商品Id获取商品详细信息")
    @GetMapping(value = "/info/{uproductid}")
    public AjaxResult getInfo(@PathVariable("uproductid") String uproductid)
    {
        AjaxResult ajaxResult = new AjaxResult();
        //统计销量
        Integer sale = orderService.countSale(uproductid);
        ajaxResult.put("saleQuantity",sale);
        XtProduct product = xtProductService.selectXtProductByUproductid(uproductid);
        ajaxResult.put("data",product);
        return ajaxResult;
    }




    /**
     * 新增商品
     */
    @ApiOperation(value = "小刘专用，新增商品")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtProduct xtProduct , HttpServletRequest request)
    {
        return toAjax(xtProductService.insertXtProduct(xtProduct , request));
    }

    /**
     * 修改商品
     */
    @ApiOperation(value = "修改商品")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtProduct xtProduct ,boolean isAdministrators)
    {
        return toAjax(xtProductService.updateXtProduct(xtProduct , isAdministrators));
    }

    /**
     * 修改商品
     */
    @ApiOperation(value = "小刘专用，修改商品")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping("/rework")
    public AjaxResult rework(@RequestBody XtProduct xtProduct ,boolean isAdministrators)
    {
        return toAjax(xtProductService.reworkXtProduct(xtProduct , isAdministrators));
    }

    /**
     * 删除商品
     */
    @ApiOperation(value = "删除商品")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{uproductids}")
    public AjaxResult remove(@PathVariable String[] uproductids)
    {
        return toAjax(xtProductService.deleteXtProductByUproductids(uproductids));
    }

    /**
     *
     * 商品下架
     *
     * */
    @ApiOperation("商品下架")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @GetMapping("/ban/{uproductids}")
    public AjaxResult ban(@PathVariable String[] uproductids){
        return toAjax(xtProductService.banXtProductByUproductids(uproductids));
    }

    /**
     *
     * 商品上架
     *
     * */
    @ApiOperation("商品上架")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @GetMapping("/release/{uproductids}")
    public AjaxResult release(@PathVariable String[] uproductids){
        return toAjax(xtProductService.releaseXtProductByUproductids(uproductids));

    }

    @ApiOperation("根据商品类型查询商品")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @GetMapping("/select/{productTypeId}")
    public AjaxResult selectXtProductListByProductType(@PathVariable String productTypeId){
        return AjaxResult.success(xtProductService.selectXtProductListByProductType(productTypeId));
    }


    @ApiOperation("查询商品的全部品类")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @GetMapping("/selectAllProductCategoryName")
    public AjaxResult selectAllProductCategoryName(){
        return AjaxResult.success(xtProductService.selectAllProductCategoryName());
    }

    @ApiOperation("根据商品的品类查询商品")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @GetMapping("/selectListByProductCategoryName")
    public AjaxResult selectListByProductCategoryNam(String ProductCategoryName){
        return AjaxResult.success(xtProductService.selectListByProductCategoryName(ProductCategoryName));
    }

    @ApiOperation("根据商品的品类和商品的类别查询商品")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @GetMapping("/selectListByProductCategoryNameAndProductType")
    public AjaxResult selectListByProductCategoryNameAndProductType(String ProductCategoryName, String productTypeId) {
        return AjaxResult.success(xtProductService.selectListByProductCategoryNameAndProductType(ProductCategoryName,productTypeId));
    }

    /**
     * 模糊查询商品
     */
    @ApiOperation(value = "小刘专用，模糊查询商品")
    @GetMapping(value = "/like")
    public AjaxResult like(String compName , String pinleiid , String leixingid)
    {
        if(pinleiid == null && leixingid != null){

        }
        List<XtProduct> xtProducts = xtProductService.likeSelectXtProductByProductName(compName , pinleiid , leixingid);
        return AjaxResult.success(xtProducts);
    }


    @ApiOperation(value = "小刘专用，获取所有商品")
    @GetMapping(value = "/selectAllProduct")
    public AjaxResult selectAllProduct()
    {
        List<XtProduct> xtProducts = xtProductMapper.selectAllProduct();
        return AjaxResult.success(xtProducts);
    }

    @ApiOperation(value = "小刘专用，按品类和种类获取商品(usortName:种类名，ucategoryName：品类名)")
    @PostMapping(value = "/selectProductByPlAndZl")
    public AjaxResult selectProductByPlAndZl(@RequestBody QueryPriductVo queryPriductVo)
    {

        List<XtProduct> xtProducts = xtProductMapper.selectProductByPlAndZl(queryPriductVo.getUsortName(), queryPriductVo.getUcategoryName());
        return AjaxResult.success(xtProducts);
    }

    @ApiOperation(value = "小刘专用，按id获取商品详细信息")
    @GetMapping(value = "/selectProductInfoId")
    public AjaxResult selectProductInfoId(String id)
    {
        XtProduct xtProducts = xtProductMapper. selectProductInfoId(id);
        return AjaxResult.success(xtProducts);
    }


    @ApiOperation(value = "小刘专用，按id删除商品")
    @DeleteMapping(value = "/deleteXtProductByUproductid")
    public AjaxResult deleteXtProductByUproductid(String uproductid)
    {
        return AjaxResult.success(xtProductMapper.deleteXtProductByUproductid(uproductid));
    }

    @ApiOperation(value = "小刘专用，按商家查询商品,compid:商家id")
    @GetMapping(value = "/selectProductByComp")
    public AjaxResult selectProductBycomp(String compid)
    {
        return AjaxResult.success(xtProductMapper.selectProductBycomp(compid));
    }

    @ApiOperation(value = "小刘专用，获取商品排序信息信息 collation填：dailysales（日销排行），weeklysales（周销排行）, monthsales（月销排行）, quartersales（季销排行） annualsales（年销排行）")
    @GetMapping(value = "/selectProductSort/{collation}")
    public AjaxResult selectProductSort(@PathVariable String collation)
    {
        System.out.println(collation);
        return AjaxResult.success(xtProductMapper.selectProductSort(collation));
    }

    @ApiOperation(value = "小刘专用，获取收益排行,seat可以填：dailysales日销，巴拉巴拉巴拉巴拉，和前面填的那个一样（昨日收益和去年收益没写，因为还没想好）")
    @GetMapping(value = "/selectIncome")
    public AjaxResult selectIncome(String seat)
    {
        return AjaxResult.success(xtProductMapper.selectIncome(seat).stream().reduce(0, Integer::sum));
    }

    @ApiOperation("小刘查看多个商品")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @GetMapping("/seleArr/{uproductids}")
    public AjaxResult seleArr(@PathVariable String[] uproductids){
        List<XtProduct> xtProducts = new ArrayList<>();

        for (String x:
                uproductids) {
            XtProduct product = xtProductMapper.selectAll(x);

            xtProducts.add(product);

        }
        return  AjaxResult.success(xtProducts);
    }

}
