package com.ruoyi.project.login.security.UserDetailService;

import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.service.IXtUserService;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.security.passwordService.MyPasswordService;
import com.ruoyi.project.login.security.permissionService.AppPermissionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author by hujun
 * @date 2022-10-28
 */
@Service(value = "appUserDetailsService")
@Slf4j
public class AppUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IXtUserService iXtUserService;

    @Autowired
    private MyPasswordService passwordService;

    @Autowired
    private AppPermissionService appPermissionService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        XtUser xtUser = iXtUserService.selectXtUserByUserLoginName(username);

        if (StringUtils.isNull(xtUser)){
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(xtUser.getUeflag()))
        {
            //2是删除
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }


        //不能在这里效验密码,因为provider还没有处理,上下文中并没有记录下相关信息
//        passwordService.validate(xtUser);
        return createAppLoginUser(xtUser);
    }

    public UserDetails createAppLoginUser(XtUser xtUser)
    {
        //loginUser需要新建一个
        //权限获取需要新建一个
        return new AppLoginUser(xtUser.getUserid(), xtUser,appPermissionService.getMenuPermission(xtUser));
    }
}
