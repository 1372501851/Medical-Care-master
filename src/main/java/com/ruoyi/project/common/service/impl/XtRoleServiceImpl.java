package com.ruoyi.project.common.service.impl;

import com.ruoyi.project.common.domain.XtRole;
import com.ruoyi.project.common.mapper.XtRoleMapper;
import com.ruoyi.project.common.service.IXtRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家（医院、药店）角色Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtRoleServiceImpl implements IXtRoleService
{
    @Autowired
    private XtRoleMapper xtRoleMapper;

    /**
     * 查询商家（医院、药店）角色
     *
     * @param uroleid 商家（医院、药店）角色主键
     * @return 商家（医院、药店）角色
     */
    @Override
    public XtRole selectXtRoleByUroleid(String uroleid)
    {
        return xtRoleMapper.selectXtRoleByUroleid(uroleid);
    }

    /**
     * 查询商家（医院、药店）角色列表
     *
     * @param xtRole 商家（医院、药店）角色
     * @return 商家（医院、药店）角色
     */
    @Override
    public List<XtRole> selectXtRoleList(XtRole xtRole)
    {
        return xtRoleMapper.selectXtRoleList(xtRole);
    }

    /**
     * 新增商家（医院、药店）角色
     *
     * @param xtRole 商家（医院、药店）角色
     * @return 结果
     */
    @Override
    public int insertXtRole(XtRole xtRole)
    {
        return xtRoleMapper.insertXtRole(xtRole);
    }

    /**
     * 修改商家（医院、药店）角色
     *
     * @param xtRole 商家（医院、药店）角色
     * @return 结果
     */
    @Override
    public int updateXtRole(XtRole xtRole)
    {
        return xtRoleMapper.updateXtRole(xtRole);
    }

    /**
     * 批量删除商家（医院、药店）角色
     *
     * @param uroleids 需要删除的商家（医院、药店）角色主键
     * @return 结果
     */
    @Override
    public int deleteXtRoleByUroleids(String[] uroleids)
    {
        return xtRoleMapper.deleteXtRoleByUroleids(uroleids);
    }

    /**
     * 删除商家（医院、药店）角色信息
     *
     * @param uroleid 商家（医院、药店）角色主键
     * @return 结果
     */
    @Override
    public int deleteXtRoleByUroleid(String uroleid)
    {
        return xtRoleMapper.deleteXtRoleByUroleid(uroleid);
    }
}
