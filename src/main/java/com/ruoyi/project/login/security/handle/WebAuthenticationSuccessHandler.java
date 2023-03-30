package com.ruoyi.project.login.security.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.login.TokenService.WebLoginTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author by hujun
 * @date 2022-10-28
 */
@Component
public class WebAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private WebLoginTokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        WebLoginUser appUser = (WebLoginUser)authenticationToken.getPrincipal();
        String token = tokenService.createToken(appUser);
        PrintWriter out = response.getWriter();
        float initialCapacity = 3 / 0.75F + 1.0F;
        HashMap<String, Object> respMap = new HashMap<>((int) initialCapacity);
        respMap.put("code", 200);
        respMap.put("msg", "success");
        respMap.put("token",token);
        String s = new ObjectMapper().writeValueAsString(respMap);
        out.write(s);
        out.flush();
        out.close();
    }
}
