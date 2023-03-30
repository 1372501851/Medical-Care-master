package com.ruoyi.project.user.service;


import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.domain.XtRole;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.user.domain.XtAllRole;
import com.ruoyi.project.user.domain.XtAllRoleToMenus;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.XtRoldDao;


public interface IallRoleService {



    int insertXtAllRoleToMenu(XtAllRoleToMenus xtAllRoleToMenus);


    AjaxResult addrole(XtAllRole xtAllRole);

    AjaxResult accredit(int id, int menuid);

    AjaxResult revampaccredit(int id , int menuid);


    AjaxResult revamprole(XtAllRole xtAllRole);

    AjaxResult addemployee(XtEmployee xtEmployee);



    AjaxResult adddepartment(XtDepartment xtDepartment);

    XtEmployee quercomp(String userid);

    XtDepartment querydepartment(String userid);

    AjaxResult accreditdepartment(XtRoldDao xtRoleDao, Long menuid);
}
