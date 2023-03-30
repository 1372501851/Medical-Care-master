//package com.ruoyi.project.unipush.controller;
//
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.project.unipush.util.GeTuiUtils;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author by hujun
// * @date 2022-12-12
// */
//
//@RestController
//@RequestMapping("/push")
//@Api(tags = "消息推送模块")
//public class PushController {
//
//    @Autowired
//    GeTuiUtils geTuiUtils;
//
//    @Autowired
//    public RedisTemplate redisTemplate;
//
//    @ApiOperation(value = "消息推送测试")
//    @GetMapping("/pushMess")
//    public AjaxResult pushMessage() {
//        /**用户的唯一标识*/
//        String cid = "5ffa487f2f12ebb87c795cd9155bbed4";
//        geTuiUtils.pushToSingleByCid(cid,"离线消息测试","您有一条新消息");
//        return AjaxResult.success("成功");
//    }
//
//}
