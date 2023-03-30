//package com.ruoyi.framework.security.filter;
//
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ruoyi.project.security.MyLoginUser;
//import com.ruoyi.project.security.MyTokenService;
//import com.ruoyi.project.security.back.SysLoginUser;
//import com.ruoyi.project.security.back.SysUserTokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import com.ruoyi.common.utils.SecurityUtils;
//import com.ruoyi.common.utils.StringUtils;
//import com.ruoyi.framework.security.LoginUser;
//import com.ruoyi.framework.security.service.TokenService;
//
///**
// * token过滤器 验证token有效性
// *
// * @author ruoyi
// */
//@Component
//public class NewJwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private MyTokenService myTokenService;
//
//    @Autowired
//    private SysUserTokenService sysUserTokenService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//
//        String range = request.getHeader("range");
//        System.out.println(range);
//        //在这里进行判断登录的平台
//        if (request.getHeader("range").equals("web")) {
//            SysLoginUser loginUser = sysUserTokenService.getLoginMyUser(request);
//            if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
//
//                sysUserTokenService.verifyToken(loginUser);
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        } else {
//            MyLoginUser loginUser = myTokenService.getLoginMyUser(request);
//            if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
//                myTokenService.verifyToken(loginUser);
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//
//        //第一个过滤器(有token,则会拿到token,并将用户信息放入上下文)
//        chain.doFilter(request, response);
//    }
//}
