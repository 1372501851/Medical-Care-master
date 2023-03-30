package com.ruoyi.project.login.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.user.service.IXtOperUserService;
import com.ruoyi.project.user.service.IXtUserService;
import com.ruoyi.project.login.Dto.MyLoginBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by hujun
 * @date 2022-10-28
 */

@Api(tags = "登录模块")
@RestController
public class LoginController {

    @Autowired
    private IXtUserService iXtUserService;
    @Autowired
    private IXtOperUserService iXtOperUserService;

    /**
     *
     * app登录
     * */

    @ApiOperation(value = "登录")
    @PostMapping("/app/login")
    public AjaxResult login(@RequestBody MyLoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = iXtUserService.appLogin(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }



    /**
     *
     *
     * web登录
     *
     * */

//        @ApiOperation(value = "登录")
//    @PostMapping("/web")
//    public AjaxResult login(@RequestBody MyLoginBody loginBody)
//    {
//        AjaxResult ajax = AjaxResult.success();
//        // 生成令牌
//        String token = ixtUserService.login(loginBody.getUsername(), loginBody.getPassword());
//        ajax.put(Constants.TOKEN, token);
//        return ajax;
//    }

}
