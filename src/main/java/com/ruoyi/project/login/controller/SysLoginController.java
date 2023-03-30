//package com.ruoyi.project.login.controller;
//
//import com.ruoyi.common.constant.Constants;
//import com.ruoyi.common.utils.SecurityUtils;
//import com.ruoyi.common.utils.StringUtils;
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.project.user.domain.XtOperUser;
//
//import com.ruoyi.project.user.service.IXtOperUserService;
//import com.ruoyi.project.login.Dto.MyLoginBody;
//import com.ruoyi.project.login.security.permissionService.WebPermissionService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Set;
//
//import static com.ruoyi.framework.web.domain.AjaxResult.error;
//import static com.ruoyi.framework.web.domain.AjaxResult.success;
//
///**
// * @author by hujun
// * @date 2022-10-27
// */
//
//@Api(tags = "后台端登录模块")
//@RestController
//@RequestMapping("/back")
//public class SysLoginController {
//
//    @Autowired
//    private IXtOperUserService iXtOperUserService;
//
//    @Autowired
//    private WebPermissionService webPermissionService;
//
//    /***
//     * 仿注册
//     *
//     * */
//    @PostMapping("/register")
//    @ApiOperation(value = "用户注册")
//    public AjaxResult appRegister(@RequestParam("username") String username, @RequestParam("password")String password){
//        String registerInfo = iXtOperUserService.register(username, password);
//        return StringUtils.isEmpty(registerInfo) ? success() : error(registerInfo);
//    }
//
//
//    /**
//     * 登录方法
//     *
//     * @param loginBody 登录信息
//     * @return 结果
//     */
//    @ApiOperation(value = "登录")
//    @PostMapping("/login")
//    public AjaxResult login(@RequestBody MyLoginBody loginBody)
//    {
//        AjaxResult ajax = AjaxResult.success();
//        // 生成令牌
//        String token = iXtOperUserService.login(loginBody.getUsername(), loginBody.getPassword());
//        ajax.put(Constants.TOKEN, token);
//        return ajax;
//    }
//
//    /**
//     * 获取用户信息
//     *
//     * @return 用户信息
//     */
//    @GetMapping("/getInfo")
//    public AjaxResult getInfo()
//    {
//        XtOperUser user = SecurityUtils.getWebLoginUser().getUser();
////        Set<String> roles = permissionService.getRolePermission(user);
//        // 权限集合
//        Set<String> permissions = webPermissionService.getMenuPermission(user);
//        AjaxResult ajax = AjaxResult.success();
//        ajax.put("user", user);
////        ajax.put("roles", roles);
//        ajax.put("permissions", permissions);
//        return ajax;
//    }
//
//
//
//
//
//}
