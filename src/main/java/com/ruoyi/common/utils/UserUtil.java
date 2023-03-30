package com.ruoyi.common.utils;

import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/1/12
 */
@Component
public class UserUtil {

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    public String getLoginUserId(){
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        if (appLoginUser != null) {
            return appLoginUser.getUserId();
        } else {
            return SecurityUtils.getWebLoginUser().getUserId();
        }
    }

    public String getLoginCompId(){
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        if (webLoginUser != null) {
            return null;
        } else {
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(SecurityUtils.getAppLoginUser().getUserId());
            return xtEmployeeByUserId.getUcompid();
        }
    }

}
