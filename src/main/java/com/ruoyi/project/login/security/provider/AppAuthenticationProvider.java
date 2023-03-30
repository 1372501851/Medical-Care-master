package com.ruoyi.project.login.security.provider;

import com.ruoyi.project.login.security.AuthenticationToken.AppAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author by hujun
 * @date 2022-10-28
 */

@Slf4j
@Component
public class AppAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    @Qualifier("appUserDetailsService")
    private UserDetailsService appUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            AppAuthenticationToken tokenReq = (AppAuthenticationToken) authentication;

            //通过账号密码查询登录人的信息
            UserDetails userDetails = appUserDetailsService.loadUserByUsername(tokenReq.getUsername());
            //在这里效验密码,并且记录错误数(引入passwordService)

            //todo 密码加密后比较是否匹配
            if(!passwordEncoder.matches(tokenReq.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("账号或者密码错误");
            }
            //这一步把登录后处理完成的用户信息存储到上下文中
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadCredentialsException("登录异常");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AppAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
