//package com.ruoyi.framework.config;
//
//import com.ruoyi.project.security.back.filter.SysUserAuthenticationFilter;
//import com.ruoyi.project.security.mult.CustomUserDetailService;
//import com.ruoyi.project.security.mult.MyAuthenticationProvider;
//import com.ruoyi.project.security.mult.SysAdminDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//import org.springframework.web.filter.CorsFilter;
//import com.ruoyi.framework.config.properties.PermitAllUrlProperties;
//import com.ruoyi.framework.security.filter.NewJwtAuthenticationTokenFilter;
//import com.ruoyi.framework.security.handle.AuthenticationEntryPointImpl;
//import com.ruoyi.framework.security.handle.LogoutSuccessHandlerImpl;
//import org.w3c.dom.stylesheets.LinkStyle;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * spring security配置
// *
// * @author ruoyi
// */
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter
//{
//    /**
//     * 自定义用户认证逻辑
//     */
//
//    //指定注入
////    @Autowired
////    @Qualifier("userDetailsServiceImpl")
////    private UserDetailsService userDetailsService;
//
//    @Autowired
//    @Qualifier("myUserDetailsService")
//    private UserDetailsService myUserDetailsService;
//
//    @Autowired
//    @Qualifier("sysAdminDetailService")
//    private CustomUserDetailService sysAdminDetailService;
//
//    /**
//     * 认证失败处理类
//     */
//    @Autowired
//    private AuthenticationEntryPointImpl unauthorizedHandler;
//
//    /**
//     * 退出处理类
//     */
//    @Autowired
//    private LogoutSuccessHandlerImpl logoutSuccessHandler;
//
//    /**
//     * token认证过滤器
//     */
//    @Autowired
//    private NewJwtAuthenticationTokenFilter authenticationTokenFilter;
//
//    /**
//     * 跨域过滤器
//     */
//    @Autowired
//    private CorsFilter corsFilter;
//
//    /**
//     * 允许匿名访问的地址
//     */
//    @Autowired
//    private PermitAllUrlProperties permitAllUrl;
//
//    /**
//     * 解决 无法直接注入 AuthenticationManager
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception
//    {
//        return super.authenticationManagerBean();
//    }
//
//    /**
//     * anyRequest          |   匹配所有请求路径
//     * access              |   SpringEl表达式结果为true时可以访问
//     * anonymous           |   匿名可以访问
//     * denyAll             |   用户不能访问
//     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
//     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
//     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
//     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
//     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
//     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
//     * permitAll           |   用户可以任意访问
//     * rememberMe          |   允许通过remember-me登录的用户访问
//     * authenticated       |   用户登录后可访问
//     */
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception
//    {
//        // 注解标记允许匿名访问的url
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
//        permitAllUrl.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
//
//        httpSecurity
//                // CSRF禁用，因为不使用session
//                .csrf().disable()
//                // 认证失败处理类
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                // 过滤请求
//                .authorizeRequests()
//                // 对于登录login 注册register 验证码captchaImage 允许匿名访问
//                .antMatchers("/login", "/register", "/captchaImage","/back/register","/back/login").permitAll()
//                // 静态资源，可匿名访问
//                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated()
//                .and()
//                .headers().frameOptions().disable();
//        // 添加Logout filter
//        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
////        httpSecurity.addFilterAt(authentication(), UsernamePasswordAuthenticationFilter.class);
//        // 添加JWT filter(先Jwt再UserPF)
//        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        // 添加CORS filter
//        httpSecurity.addFilterBefore(corsFilter, NewJwtAuthenticationTokenFilter.class);
//        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
//    }
//
//    /**
//     * 强散列哈希加密实现
//     */
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 身份认证接口
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
////        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
////        myAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
////        List<CustomUserDetailService> userDetailServices = new ArrayList<>();
////        userDetailServices.add(sysAdminDetailService);
//////        userDetailServices.add(myUserDetailsService);
////        //把所有的userDetailService全部加进来就可以了
////        myAuthenticationProvider.setUserDetailServices(userDetailServices);
////        auth.authenticationProvider(myAuthenticationProvider);
//        //直接写两个
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
//
//        auth.userDetailsService(sysAdminDetailService).passwordEncoder(bCryptPasswordEncoder());
//
//    }
//
//
////    @Bean
////    public SysUserAuthenticationFilter authentication() throws Exception {
////        //此处生成一个自定义的AbstractAuthenticationProcessingFilter对象，并配置登录请求的路径
////        SysUserAuthenticationFilter sysUserAuthenticationFilter = new SysUserAuthenticationFilter();
////        sysUserAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
//////        //登录成功时处理器
//////        sysUserAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//////        //登录失败时的处理器
//////        sysUserAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
////        return sysUserAuthenticationFilter;
////    }
//
//}
