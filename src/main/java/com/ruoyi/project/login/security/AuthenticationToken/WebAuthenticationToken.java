package com.ruoyi.project.login.security.AuthenticationToken;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author by hujun
 * @date 2022-10-28
 */

public class WebAuthenticationToken extends AbstractAuthenticationToken {
    private String username;
    private String password;
//    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getCode() {
//        return code;
//    }

//    public void setCode(String code) {
//        this.code = code;
//    }

    public WebAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
