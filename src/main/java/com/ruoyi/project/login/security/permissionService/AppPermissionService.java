package com.ruoyi.project.login.security.permissionService;

import com.ruoyi.project.user.domain.XtUser;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author by hujun
 * @date 2022-10-28
 */
@Component
public class AppPermissionService {
    public Set<String> getMenuPermission(XtUser user)
    {
        Set<String> perms = new HashSet<String>();
        //全部权限
        perms.add("*:*:*");
        return perms;
    }
}
