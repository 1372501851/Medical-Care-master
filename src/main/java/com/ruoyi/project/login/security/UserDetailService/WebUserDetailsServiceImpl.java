package com.ruoyi.project.login.security.UserDetailService;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.user.domain.XtOperUser;
import com.ruoyi.project.user.service.IXtOperUserService;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.login.security.permissionService.WebPermissionService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author by hujun
 * @date 2022-10-28
 */
@Service(value = "webUserDetailsService")
@Slf4j
public class WebUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IXtOperUserService iXtOperUserService;



    @Autowired
    private WebPermissionService webPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        XtOperUser xtOperUser = iXtOperUserService.selectXtOperUserByUserLoginName(username);

        if (StringUtils.isNull(xtOperUser)){
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
//        else if (UserStatus.DELETED.getCode().equals(xtOperUser.getUeflag()))
//        {
//            //2是删除
//            log.info("登录用户：{} 已被删除.", username);
//            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
//        }
        //不能在这里效验密码,因为provider还没有处理,上下文中并没有记录下相关信息
//        passwordService.validate(xtUser);
        return createWebLoginUser(xtOperUser);
    }

    public UserDetails createWebLoginUser(XtOperUser xtOperUser)
    {
        //权限获取需要新建一个
        return new WebLoginUser(xtOperUser.getUoperUserid(), xtOperUser,webPermissionService.getMenuPermission(xtOperUser));
    }
}
