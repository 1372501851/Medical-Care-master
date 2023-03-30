package com.ruoyi.project.merchant.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.domain.XtOrder;
import com.ruoyi.project.merchant.domain.XtProduct;
import com.ruoyi.project.merchant.domain.dto.OrderDto;
import com.ruoyi.project.merchant.domain.vo.OrderVo;
import com.ruoyi.project.merchant.mapper.XtProductMapper;
import com.ruoyi.project.merchant.service.IXtOrderService;
import com.ruoyi.project.merchant.service.IXtProductService;
import com.ruoyi.project.task.upload.until.StringToURL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by hujun
 * @date 2022-10-21
 */

@RestController
@RequestMapping("/order")
@Api(tags = "订单模块")
public class OrderController extends BaseController {

    @Autowired
    private IXtOrderService xtOrderService;

    @Autowired
    private IXtProductService productService;

    /**项目赶,写在这里别见怪*/
    @Autowired
    private XtProductMapper productMapper;

    /**
     * 查询订单列表
     */
    @ApiOperation(value = "查询订单列表")
    @GetMapping("/list")
    public TableDataInfo list(XtOrder xtOrder)
    {
        startPage();
        List<XtOrder> list = xtOrderService.selectXtOrderList(xtOrder);
        return getDataTable(list);
    }

    @ApiOperation(value = "查询订单列表")
    @GetMapping("/app/list")
    public AjaxResult appList(String status,String name)
    {
        List<OrderVo> list = xtOrderService.selectXtOrderListByUserId(status,name);
        return AjaxResult.success(list);
    }

    /**
     * 导出订单列表
     */
//    @ApiOperation(value = "导出订单列表")
//    @Log(title = "订单", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtOrder xtOrder)
//    {
//        List<XtOrder> list = xtOrderService.selectXtOrderList(xtOrder);
//        ExcelUtil<XtOrder> util = new ExcelUtil<XtOrder>(XtOrder.class);
//        util.exportExcel(response, list, "订单数据");
//    }

    /**
     * 获取订单详细信息
     */
    @ApiOperation(value = "获取订单详细信息")
    @GetMapping(value = "/info/{uorderid}")
    public AjaxResult getInfo(@PathVariable("uorderid") String uorderid)
    {
        XtOrder order = xtOrderService.selectXtOrderByUorderid(uorderid);
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order,orderVo);
        //查询该商品
        String productId =order.getProductId();
        XtProduct product = productMapper.selectXtProductByUproductid(productId);
        String path = product.getUproductPic();
        List<String> strings = StringToURL.toURL(path);
        orderVo.setUproductPic(strings.get(0));
        orderVo.setUproductSpecs(product.getUproductSpecs());
        orderVo.setUproductName(product.getUproductName());
        orderVo.setUretailPrice(product.getUretailPrice());
        return AjaxResult.success(orderVo);
    }

    /**
     * 新增订单
     */
    @ApiOperation(value = "新增订单")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtOrder xtOrder)
    {
        //可以返回订单号
        String orderNo = xtOrderService.insertXtOrder(xtOrder);
        return AjaxResult.success(orderNo);
    }

    @ApiOperation(value = "新增订单")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add/order")
    public AjaxResult add(@RequestBody OrderDto order)
    {
        //可以返回订单号No
        String orderNo = xtOrderService.insertXtOrderByDto(order);
        Map data = new HashMap<>();
        data.put("orderNo",orderNo);
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "订单支付成功回调")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @GetMapping("/pay/success")
    public AjaxResult paySuccess(String orderId)
    {
        xtOrderService.paySuccess(orderId);
        return AjaxResult.success();
    }



    /**
     * 修改订单
     */
    @ApiOperation(value = "修改订单")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtOrder xtOrder)
    {
        return toAjax(xtOrderService.updateXtOrder(xtOrder));
    }

    /**
     * 删除订单
     */
    @ApiOperation(value = "删除订单")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{uorderids}")
    public AjaxResult remove(@PathVariable String[] uorderids)
    {
        return toAjax(xtOrderService.deleteXtOrderByUorderids(uorderids));
    }

    /**取消订单*/

    @ApiOperation(value = "取消订单")
    @GetMapping("/cancle")
    public AjaxResult cancle(String orderId){
        xtOrderService.cancleOrder(orderId);
        return AjaxResult.success();
    }


    /**确认收货*/
    @ApiOperation(value = "确认收货")
    @GetMapping("/receive")
    public AjaxResult receive(String orderId){
        xtOrderService.receiveOrder(orderId);
        return AjaxResult.success();
    }


}
