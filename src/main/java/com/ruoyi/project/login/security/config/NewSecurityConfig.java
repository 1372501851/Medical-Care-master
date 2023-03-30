package com.ruoyi.project.login.security.config;

import com.ruoyi.project.login.security.filter.NewJwtAuthenticationTokenFilter;
import com.ruoyi.project.login.security.filter.WebAuthenticationProcessingFilter;
import com.ruoyi.project.login.security.handle.AppAuthenticationFailureHandler;
import com.ruoyi.project.login.security.handle.AppAuthenticationSuccessHandler;
import com.ruoyi.framework.config.properties.PermitAllUrlProperties;
import com.ruoyi.framework.security.handle.AuthenticationEntryPointImpl;
import com.ruoyi.framework.security.handle.LogoutSuccessHandlerImpl;
import com.ruoyi.project.login.security.filter.AppAuthenticationProcessingFilter;
import com.ruoyi.project.login.security.handle.WebAuthenticationFailureHandler;
import com.ruoyi.project.login.security.handle.WebAuthenticationSuccessHandler;
import com.ruoyi.project.login.security.provider.AppAuthenticationProvider;
import com.ruoyi.project.login.security.provider.WebAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author by hujun
 * @date 2022-10-28
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class NewSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 认证失败处理类
     */
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    /**
     * 退出处理类
     */

    @Autowired
    private NewJwtAuthenticationTokenFilter newJwtAuthenticationTokenFilter;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    @Autowired
    private PermitAllUrlProperties permitAllUrl;
    @Autowired
    private AppAuthenticationProvider appAuthenticationProvider;

    @Autowired
    private WebAuthenticationProvider webAuthenticationProvider;

    @Autowired
    private AppAuthenticationFailureHandler appAuthenticationFailureHandler;

    @Autowired
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;


    @Autowired
    private WebAuthenticationSuccessHandler webAuthenticationSuccessHandler;
    @Autowired
    private WebAuthenticationFailureHandler webAuthenticationFailureHandler;

    @Autowired
    private CorsFilter corsFilter;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        auth.authenticationProvider(appAuthenticationProvider).authenticationProvider(webAuthenticationProvider);
    }

    @Bean
    AppAuthenticationProcessingFilter appAuthenticationProcessingFilter() throws Exception {
        AppAuthenticationProcessingFilter appAuthenticationProcessingFilter = new AppAuthenticationProcessingFilter();

        //成功后如何处理(应该是在这里进行处理)
        appAuthenticationProcessingFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
        //失败后如何处理
        appAuthenticationProcessingFilter.setAuthenticationFailureHandler(appAuthenticationFailureHandler);

        appAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        //需要过滤的路劲
        appAuthenticationProcessingFilter.setFilterProcessesUrl("/login/app");
        appAuthenticationProcessingFilter.setFilterProcessesUrl("/login_connect/*");
        appAuthenticationProcessingFilter.setFilterProcessesUrl("/webSocket");
        return appAuthenticationProcessingFilter;
    }

    @Bean
    WebAuthenticationProcessingFilter webAuthenticationProcessingFilter() throws Exception {
        WebAuthenticationProcessingFilter webAuthenticationProcessingFilter = new WebAuthenticationProcessingFilter();
        webAuthenticationProcessingFilter.setAuthenticationSuccessHandler(webAuthenticationSuccessHandler);
        webAuthenticationProcessingFilter.setAuthenticationFailureHandler(webAuthenticationFailureHandler);
        webAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        webAuthenticationProcessingFilter.setFilterProcessesUrl("/login/web");
        return webAuthenticationProcessingFilter;
    }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        // 注解标记允许匿名访问的url
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        permitAllUrl.getUrls().forEach(url -> registry.antMatchers(url).permitAll());

        httpSecurity
                // CSRF禁用，因为不使用session
                .csrf().disable()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                .antMatchers("/merchant/app/list","/app/user/register","/app/login","/merchant/**","/web/mall/product/**","/mall/product/**","/advertising/list","/employee/app/list","/equipment/type/equipmentTypes","/push/**","/pushMessage/**").permitAll()
                // 对于登录login 注册register 验证码captchaImage 允许匿名访问
                .antMatchers("/login", "/register", "/captchaImage").permitAll()
                // 静态资源，可匿名访问
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        // 添加Logout filter
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        //这行一定要写,否则会出问题
        httpSecurity.addFilterBefore(newJwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(appAuthenticationProcessingFilter(),UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(webAuthenticationProcessingFilter(),UsernamePasswordAuthenticationFilter.class);
        // 添加JWT filter(先Jwt再UserPF)
        // 添加CORS filter
        httpSecurity.addFilterBefore(corsFilter, NewJwtAuthenticationTokenFilter.class);
        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
    }




}
