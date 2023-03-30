package com.ruoyi.project.login.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.project.login.security.AuthenticationToken.AppAuthenticationToken;
import com.ruoyi.project.login.security.AuthenticationToken.WebAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by hujun
 * @date 2022-10-28
 */
@Slf4j
public class WebAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private boolean postOnly = true;

    public WebAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/login/web", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //在这里设置支持json格式传输数据
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
//
//        Map<String, String> loginData = new HashMap<>();
//        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
//
//            try {
//                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
//            } catch (IOException e) {
//                log.info("登录异常信息:{}",e);
//            }
//        }
//        String loginAcct = loginData.get(usernameParameter);
//        String password = loginData.get(passwordParameter);
//        if (loginAcct == null) {
//            loginAcct = "";
//            log.info("{}账号开始登录",loginAcct);
//        }
//        if (password == null) {
//            password = "";
//        }
//        loginAcct = loginAcct.trim();
//       // 过滤完后进行认证
//        return this.getAuthenticationManager().authenticate(new WebAuthenticationToken(loginAcct, password));

//            String code = request.getParameter("code");
//            String kaptchaCode = (String) request.getSession().getAttribute("verificationCode");
//            if(StringUtils.isEmpty(code)) {
//                response.setContentType("application/json;charset=UTF-8");
//                response.setStatus(500);
//                PrintWriter printWriter = response.getWriter();
//                //这个是他自己定义的封装返回的类型
//                R<Object> httpResponse = R.fail(500, "请填写验证码！");
//                printWriter.write(new ObjectMapper().writeValueAsString(httpResponse));
//                printWriter.flush();
//                printWriter.close();
//                throw new AuthenticationServiceException("请填写验证码");
//            }else if(!kaptchaCode.toLowerCase().equals(code.toLowerCase())) {
//                logger.info("goto===>验证码错误");
//                response.setContentType("application/json;charset=UTF-8");
//                response.setStatus(500);
//                PrintWriter printWriter = response.getWriter();
//                R<Object> httpResponse = R.fail(500, "验证码错误！");
//                printWriter.write(new ObjectMapper().writeValueAsString(httpResponse));
//                printWriter.flush();
//                printWriter.close();
//                throw new AuthenticationServiceException("验证码错误");
//            }

         //用户名称
        String loginAcct = obtainUsername(request);
        if (StringUtils.isEmpty(loginAcct)) {
            throw new AuthenticationServiceException("登录账号不能为空");
        }
        // 密码
        String password = obtainPassword(request);
        if (StringUtils.isEmpty(password)) {
            throw new AuthenticationServiceException("密码不能为空");
        }

        return this.getAuthenticationManager().authenticate(new com.ruoyi.project.login.security.AuthenticationToken.WebAuthenticationToken(loginAcct, password));
//        //这一步就是登录里面的一步

    }


    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

}
