package com.ruoyi.project.login.security.permissionService;

import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.project.user.domain.SysRole;
import com.ruoyi.project.user.domain.XtOperUser;
import com.ruoyi.project.user.service.ISysMenuService;
import com.ruoyi.project.user.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author by hujun
 * @date 2022-10-28
 */
@Component
public class WebPermissionService {

    /**
     * 获取用户的角色,权限,菜单等信息
     *
     * */


    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(XtOperUser user)
    {
        Set<String> roles = new HashSet<String>();

        if (user.getUsertype().equals(UserConstants.WEB_USER_TYPE_ADMIN))
        {
            // 管理员拥有所有权限
            roles.add("admin");
        }
        else
        {
            //获取用户角色
            roles.addAll(iSysRoleService.selectRolePermissionByUserId(user.getUoperUserid()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(XtOperUser user)
    {
        Set<String> perms = new HashSet<String>();
        // 平台管理员拥有所有权限
        if (user.getUsertype().equals(UserConstants.WEB_USER_TYPE_ADMIN))
        {
            perms.add("*:*:*");
        }
        else
        {
            //获取该用户所有的角色
//            List<SysRole> roles = user.getRoles();
            List<SysRole> roles = iSysRoleService.selectRolesByUserId(user.getUoperUserid());
            if (!roles.isEmpty() && roles.size() > 1)
            {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (SysRole role : roles)
                {
                    Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
                    role.setPermissions(rolePerms);
                    perms.addAll(rolePerms);
                }
            }
            else
            {
                perms.addAll(menuService.selectMenuPermsByUserId(user.getUoperUserid()));
            }
        }
        return perms;
    }

}
