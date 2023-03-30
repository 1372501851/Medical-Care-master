package com.ruoyi.project.merchant.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.vo.CartVO;
import com.ruoyi.project.merchant.service.MallCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by hujun
 * @date 2022-10-21
 */
@Api(tags = "购物车模块")
@RestController
@RequestMapping("/mall/cart")
public class CartController {

    @Autowired
    private MallCartService cartService;


    @ApiOperation(value = "购物车列表")
    @GetMapping("/list")
    public AjaxResult list(){
        //内部获取用户ID，防止横向越权
        List<CartVO> cartVOLists = cartService.list();
        return AjaxResult.success(cartVOLists);
    }

    //购物车添加商品
    @ApiOperation(value = "将商品添加到购物车")
    @PostMapping("/add")
    public AjaxResult add(@RequestParam("productId") String productId ,@RequestParam("count") Integer count){
        cartService.add( productId, count);
        return AjaxResult.success();

    }

    @ApiOperation(value = "更新商品数量")
    @PostMapping("/update")
    public AjaxResult updateCart(@RequestParam("productId") String productId , @RequestParam("count") Integer count){
        cartService.update(productId, count);
        return AjaxResult.success();
    }


    @ApiOperation(value = "删除购物车商品")
    @DeleteMapping ("/delete")
    public AjaxResult deleteCart(@RequestParam("productId") String productId ){
        cartService.delete(productId);
        return AjaxResult.success();

    }

    @ApiOperation(value = "选中/取消选中/购物车商品")
    @PutMapping("/select")
    public AjaxResult selectCart(@RequestParam("productId") String productId,@RequestParam("selected") Integer selected ){
        cartService.updateSelectedOne( productId,selected);
        return AjaxResult.success();

    }

    @ApiOperation(value = "一键选中/取消选中")
    @PutMapping("/selectAll")
    public AjaxResult selectAllCart(@RequestParam("selected") Integer selected ){
        cartService.updateSelectedAll(selected);
        return AjaxResult.success();

    }

}
