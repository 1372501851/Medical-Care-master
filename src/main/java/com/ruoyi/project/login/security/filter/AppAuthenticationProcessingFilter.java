package com.ruoyi.project.login.security.filter;

import com.ruoyi.project.login.security.AuthenticationToken.AppAuthenticationToken;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.user.domain.SysMenu;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.SysMenuMapper;
import com.ruoyi.project.user.mapper.SysRoleMenuMapper;
import com.ruoyi.project.user.service.IXtUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-10-28
 */

public class AppAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

        public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
        public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

        private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
        private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
        private boolean postOnly = true;

        @Autowired
        private IXtEmployeeService xtEmployeeService;

        @Autowired
        private IXtUserService iXtUserService;

        @Autowired
        private SysMenuMapper menuMapper;


    public AppAuthenticationProcessingFilter() {
            super(new AntPathRequestMatcher("/login/app", "POST"));
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
            //在这里设置支持json格式传输数据
            if (postOnly && !request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException(
                        "Authentication method not supported: " + request.getMethod());
            }


            //这里加入验证码
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

                // 用户名称

            String loginAcct = obtainUsername(request);

            if (StringUtils.isEmpty(loginAcct)) {
                throw new AuthenticationServiceException("登录账号不能为空");
            }
            // 密码
            String password = obtainPassword(request);

            if (StringUtils.isEmpty(password)) {
                throw new AuthenticationServiceException("密码不能为空");
                }


            String platform = request.getHeader("platform");
            //如果是商家登录web端
            if (platform != null && platform.equals("web")){
                XtUser xtUser = iXtUserService.selectXtUserByUserLoginName(loginAcct);
                //这里的判断规则后面需要改变
                XtEmployee xtEmployee = xtEmployeeService.queryUserInfo(xtUser.getUserid());
                if (xtEmployee == null){
                    //如果用户信息为空,说明没有申请成为员工
                    throw new AuthenticationServiceException("暂无权限登录,请联系管理员.");
                }else {

                    List<SysMenu> sysMenus = menuMapper.selectMenuByUserId(xtEmployee.getUserid());
                    if (sysMenus == null){
                        //该用户没有被分配菜单
                        throw new AuthenticationServiceException("暂无权限登录,请联系管理员.");
                    }

                }
            }

            //这一步就是登录里面的一步
            //过滤完后进行认证
            return this.getAuthenticationManager().authenticate(new AppAuthenticationToken(loginAcct, password));
        }

        protected String obtainUsername(HttpServletRequest request) {
            return request.getParameter(usernameParameter);
        }

        protected String obtainPassword(HttpServletRequest request) {

            return request.getParameter(passwordParameter);
        }




}
