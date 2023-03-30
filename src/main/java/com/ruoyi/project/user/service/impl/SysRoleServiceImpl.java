package com.ruoyi.project.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.user.domain.XtOperUser;
import com.ruoyi.project.user.domain.vo.UserSelect;
import com.ruoyi.project.user.mapper.XtOperUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.user.domain.SysRole;
import com.ruoyi.project.system.domain.SysRoleDept;
import com.ruoyi.project.user.domain.SysRoleMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.user.domain.SysUserRole;
import com.ruoyi.project.system.mapper.SysRoleDeptMapper;
import com.ruoyi.project.user.mapper.SysRoleMapper;
import com.ruoyi.project.user.mapper.SysRoleMenuMapper;
import com.ruoyi.project.user.mapper.SysUserRoleMapper;
import com.ruoyi.project.user.service.ISysRoleService;

/**
 * 角色 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService
{
    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    @Autowired
    private IXtEmployeeService iXtEmployeeService;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private XtOperUserMapper xtOperUserMapper;

    /**
     * 根据条件分页查询角色数据
     *只有平台管理员可以管理角色
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
//    @DataScope(deptAlias = "d")
    public List<SysRole> selectRoleList(SysRole role)
    {
//        String uoperUserid = SecurityUtils.getWebLoginUser().getUser().getUoperUserid();
//        role.setCreaterId(uoperUserid);
        //如果没有选择机构,则是默认查找全局角色,如果选择了机构,则是查询该机构的角色;

//        if (role.getCreaterId() == null){
//            role.setCreaterId(UserConstants.ADMIN_COMPID);
//        }
        //判断是否是管理员
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        if (appLoginUser != null) {
            //是商家
            String userId = appLoginUser.getUserId();
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(userId);
            role.setUcompid(xtEmployeeByUserId.getUcompid());
        }else {
            //是管理员
            role.setUcompid(null);
        }
        return roleMapper.selectRoleList(role);
    }



    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRolesByUserId(String userId)
    {
        List<SysRole> userRoles = roleMapper.selectRolePermissionByUserId(userId);
        for (SysRole userRole : userRoles){
            userRole.setFlag(true);
        }
//
//        List<SysRole> roles = selectRoleAll();
//
//        for (SysRole role : roles)
//        {
//            for (SysRole userRole : userRoles)
//            {
//                if (role.getRoleId().longValue() == userRole.getRoleId().longValue())
//                {
//                    role.setFlag(true);
//                    break;
//                }
//            }
//        }

        return userRoles;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(String userId)
    {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                //role_key:角色权限
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRoleAll()
    {
        return SpringUtils.getAopProxy(this).selectRoleList(new SysRole());
    }

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<Long> selectRoleListByUserId(Long userId)
    {
        return roleMapper.selectRoleListByUserId(userId);
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public SysRole selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        //如果有返回,说明角色名称重复
        SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(SysRole role)
    {

        //管理员角色为1(这里写死了,不够灵活,需要修改)
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin())
        {
            throw new ServiceException("不允许操作超级管理员角色");
        }
    }

    /**
     * 校验角色是否有数据权限
     *
     * @param roleId 角色id
     */
    @Override
    public void checkRoleDataScope(Long roleId)
    {
        if (!SysUser.isAdmin(SecurityUtils.getUserId()))
        {
            SysRole role = new SysRole();
            role.setRoleId(roleId);
            List<SysRole> roles = SpringUtils.getAopProxy(this).selectRoleList(role);
            if (StringUtils.isEmpty(roles))
            {
                throw new ServiceException("没有权限访问角色数据！");
            }
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId)
    {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRole(SysRole role)
    {

        //验证用户
        checkRoleInsert(role);
        // 新增角色信息
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(SysRole role)
    {
        /**
         * 修改角色信息
         * 删除以前角色的菜单信息
         * 写入新的菜单信息(即使不更新菜单也要写,更加通用所有的修改只需一个接口,缺点:删改操作太频繁了)
         * */

        checkRoleUpdate(role);

        // 修改角色信息
        roleMapper.updateRole(role);

        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());

        return insertRoleMenu(role);
    }

    /**
     * 修改角色状态
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRoleStatus(SysRole role)
    {
        checkRoleUpdate(role);
        return roleMapper.updateRole(role);
    }

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int authDataScope(SysRole role)
    {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(SysRole role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(SysRole role)
    {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDept> list = new ArrayList<SysRoleDept>();
        for (Long deptId : role.getDeptIds())
        {
            SysRoleDept rd = new SysRoleDept();
            rd.setRoleId(role.getRoleId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0)
        {
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteRoleById(Long roleId)
    {
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(roleId);
        return roleMapper.deleteRoleById(roleId);
    }

    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleByIds(Long[] roleIds)
    {
        for (Long roleId : roleIds)
        {
            checkRoleAllowed(new SysRole(roleId));

            SysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenu(roleIds);

        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 取消授权用户角色
     *
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public int deleteAuthUser(SysUserRole userRole)
    {
        return userRoleMapper.deleteUserRoleInfo(userRole);
    }

    /**
     * 批量取消授权用户角色
     *
     * @param roleId 角色ID
     * @param userIds 需要取消授权的用户数据ID
     * @return 结果
     */
    @Override
    public int deleteAuthUsers(Long roleId, Long[] userIds)
    {
        return userRoleMapper.deleteUserRoleInfos(roleId, userIds);
    }

    /**
     * 批量选择授权用户角色
     *
     * @param roleId 角色ID
     * @param userIds 需要授权的用户数据ID
     * @return 结果
     */
    @Override
    public int insertAuthUsers(Long roleId, Long[] userIds)
    {
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (Long userId : userIds)
        {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return userRoleMapper.batchUserRole(list);
    }

    @Override
    public List<UserSelect> getSelectList() {
        SysRole query = new SysRole();
        //判断是否是管理员
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        if (appLoginUser != null) {
            //是商家
            String userId = appLoginUser.getUserId();
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(userId);
            query.setCreaterId(xtEmployeeByUserId.getUcompid());
        }else {
            //是管理员
            WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
            String userId = webLoginUser.getUserId();
            XtOperUser xtOperUser = xtOperUserMapper.selectXtOperUserByUoperUserid(userId);
            query.setCreaterId(xtOperUser.getUoperCompid());
        }
        List<SysRole> sysRoles = roleMapper.selectRoleList(query);
        List<UserSelect> selectList = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            UserSelect select = new UserSelect();
            select.setValue(sysRole.getRoleId().toString());
            select.setLabel(sysRole.getRoleName());
            selectList.add(select);
        }
        return selectList;
    }


    /**
     * 判断是管理员操作还是商家操作
     *
     * */

    private void checkRoleInsert(SysRole role){
        //获取当前登陆者(需要判断是平台管理员,还是机构管理员)
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        if (webLoginUser == null){
            //是商家
            AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
            //这里需要获取app用户的信息
            XtEmployee xtEmployee = iXtEmployeeService.queryUserInfo(appLoginUser.getUser().getUserid());
            String ucompid = xtEmployee.getUcompid();
            role.setCreateBy(xtEmployee.getUname());
            role.setCreaterId(ucompid);
            role.setUcompid(ucompid);
        }else{
            //获取当前登陆者姓名
            role.setCreateBy(webLoginUser.getUser().getUname());
            role.setCreaterId(webLoginUser.getUser().getUoperCompid());
            role.setUcompid(null);
        }
    }


    private void checkRoleUpdate(SysRole role){
        //获取当前登陆者(需要判断是平台管理员,还是机构管理员)
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        if (webLoginUser == null){
            AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
            //这里需要获取app用户的信息
            XtEmployee xtEmployee = iXtEmployeeService.queryUserInfo(appLoginUser.getUser().getUserid());
            role.setUpdateBy(xtEmployee.getUname());
        }else{
            //获取当前登陆者姓名
            role.setUpdateBy(webLoginUser.getUser().getUname());
        }
    }
}
