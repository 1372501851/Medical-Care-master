package com.ruoyi.project.login.security.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
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
public class WebAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        float initialCapacity = 2 / 0.75F + 1.0F;
        HashMap<String, Object> respMap = new HashMap<>((int) initialCapacity);
        respMap.put("code", 500);
        if (exception instanceof BadCredentialsException) {
            respMap.put("message", "用户名或者密码输入错误，请重新输入!");
        }
        out.write(new ObjectMapper().writeValueAsString(respMap));
        out.flush();
        out.close();
    }

}
