package com.ruoyi.project.user.service.impl;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.common.domain.XtRole;
import com.ruoyi.project.common.mapper.XtRoleMapper;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.user.domain.*;
import com.ruoyi.project.user.mapper.AllRoleToMenuMapper;
import com.ruoyi.project.user.mapper.XtDepartmentMapper;
import com.ruoyi.project.user.service.IallRoleService;
import com.ruoyi.project.user.util.KeyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-12-30
 */
@Service
public class AllRoleServiceImpl implements IallRoleService {
    @Autowired
    private AllRoleToMenuMapper xtAllRoleToMenuMapper;
    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;
    @Autowired
    private XtRoleMapper xtRoleMapper;

    @Autowired
    private XtDepartmentMapper xtDepartmentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertXtAllRoleToMenu(XtAllRoleToMenus xtAllRoleToMenus) {
        Integer uallroleid = xtAllRoleToMenus.getUallroleid();
        List<Integer> menuIds = xtAllRoleToMenus.getMenuId();

        List<XtAllRoleToMenu> list = new ArrayList<>();
//        if(StringUtils.isNotEmpty(menuIds)){
        // 先删除原来的菜单权限,再重新保存
        xtAllRoleToMenuMapper.deleteXtAllRoleToMenu(uallroleid);
        menuIds.forEach(id -> {
            XtAllRoleToMenu role = new XtAllRoleToMenu();
            role.setUallroleid(uallroleid);
            role.setMenuId(id);
            list.add(role);
        });
//        }
        xtAllRoleToMenuMapper.insertBatch(list);
        return 1;
    }

    @Override
    public AjaxResult addrole(XtAllRole xtAllRole) {
        String id = xtAllRole.getUallroleid();
        String name = xtAllRole.getUrolename();
        String uroledesc = xtAllRole.getUroledesc();
        String type = xtAllRole.getUcompType();
        int i = xtAllRoleToMenuMapper.addrole(id, name, uroledesc, type);
        if (i > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @Override
    public AjaxResult accredit(int id, int menuid) {
        xtAllRoleToMenuMapper.accredit(id, menuid);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult revampaccredit(int id, int menuid) {
        xtAllRoleToMenuMapper.revamp(id);
        xtAllRoleToMenuMapper.accredit(id, menuid);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult revamprole(XtAllRole xtAllRole) {
        xtAllRoleToMenuMapper.updataByid(xtAllRole);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult addemployee(XtEmployee xtEmployee) {
        xtEmployee.setUemployeeid(KeyId.nextId());
        xtEmployee.setUsertype("0");
        long time = System.currentTimeMillis();
        xtEmployee.setUroleid(time);
        xtEmployee.setUcreatedate(time);
        xtEmployeeMapper.insertXtEmployee(xtEmployee);
        return AjaxResult.success();
    }




    @Override
    public AjaxResult adddepartment(XtDepartment xtDepartment) {
        xtDepartmentMapper.insertXtDepartment(xtDepartment);
        return AjaxResult.success();
    }

    @Override
    public XtEmployee quercomp(String userid) {
      return  xtEmployeeMapper.selectXtEmployeeByUserId(userid);
    }

    @Override
    public XtDepartment querydepartment(String userid) {
       return xtDepartmentMapper.selectXtDepartmentByUdepartmentid(userid);
    }

    @Override
    public AjaxResult accreditdepartment(XtRoldDao xtRoleDao, Long menuid) {
        String  uroleid = xtRoleDao.getUroleid();
        xtRoleMapper.insertXtRoldDao(xtRoleDao);
        xtRoleMapper.insertXtRoleMenu(uroleid,menuid);
        return AjaxResult.success();
    }
}
