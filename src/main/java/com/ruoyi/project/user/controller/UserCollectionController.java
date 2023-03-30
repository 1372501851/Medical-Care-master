//package com.ruoyi.project.user.controller;
//
//import com.ruoyi.framework.web.domain.AjaxResult;
////import com.ruoyi.project.user.service.UserCollectionService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author by hujun
// * @date 2022-12-26
// */
//
//@Api(tags = "用户收藏管理模块")
//@RestController
//@RequestMapping("/user/collection")
//public class UserCollectionController {
//
//    @Autowired
//    private UserCollectionService userCollectionService;
//
//    /**
//    * 收藏商品
//     * 向数据库表中添加一条数据
//    * */
//    //购物车添加商品
//    @ApiOperation(value = "将商品添加到收藏")
//    @PostMapping("/add")
//    public AjaxResult add(@RequestParam("productId") String productId , @RequestParam("userId") String userId){
////        cartService.add( productId, count);
//        userCollectionService.add(productId, userId);
//        return AjaxResult.success();
//
//    }
//
//
//
//
//}
