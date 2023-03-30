package com.ruoyi.project.login.security.filter;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.login.TokenService.WebLoginTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by hujun
 * @date 2022-10-28
 */

@Component
public class NewJwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * 需要完成对所有的请求进行拦截并且token验证
     * 验证成功的请求,利用redis中存储的token,装换为用户信息,放入scuerity上下文中,方便后面全局调用;
     * websocket正好可以利用这一点,去获取使用者的信息,而不需要前端传递
     *
     *
     * 这里由于两个表登录,需要判断他们是从哪个表获取信息,从而转为相应的信息实体
     * 需要前端在请求头header加入 platform 字段,判断是从哪个端进行操作的;
     * */

    @Autowired
    private AppLoginTokenService appLoginTokenService;

    @Autowired
    private WebLoginTokenService webLoginTokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws ServletException, IOException {

        //登录平台
//        String platform = request.getHeader("platform");
//        if (platform != null){
//            if (platform.equals("app")){
//                AppLoginUser loginMyUser = appLoginTokenService.getLoginMyUser(request);
//                if (StringUtils.isNotNull(loginMyUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
//                    //验证成功,会刷新token的使用时长;(后面两个端的token使用时长分别进行设定)
//                    appLoginTokenService.verifyToken(loginMyUser);
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginMyUser, null, loginMyUser.getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//
//            } else if (platform.equals("web")) {
//
//                WebLoginUser loginMyUser = webLoginTokenService.getLoginMyUser(request);
//                webLoginTokenService.verifyToken(loginMyUser);
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginMyUser, null, loginMyUser.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }


        //先验证app,后验证web
        AppLoginUser loginMyUser = appLoginTokenService.getLoginMyUser(request);
        if (loginMyUser == null){
            WebLoginUser loginUser = webLoginTokenService.getLoginMyUser(request);
            if (loginUser != null){
                webLoginTokenService.verifyToken(loginUser);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }else {
            appLoginTokenService.verifyToken(loginMyUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginMyUser, null, loginMyUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }


}
