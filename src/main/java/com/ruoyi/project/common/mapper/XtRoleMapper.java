package com.ruoyi.project.common.mapper;

import com.ruoyi.project.common.domain.XtRole;
import com.ruoyi.project.user.domain.XtRoldDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商家（医院、药店）角色Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtRoleMapper
{
    /**
     * 查询商家（医院、药店）角色
     *
     * @param uroleid 商家（医院、药店）角色主键
     * @return 商家（医院、药店）角色
     */
    public XtRole selectXtRoleByUroleid(String uroleid);

    /**
     * 查询商家（医院、药店）角色列表
     *
     * @param xtRole 商家（医院、药店）角色
     * @return 商家（医院、药店）角色集合
     */
    public List<XtRole> selectXtRoleList(XtRole xtRole);

    /**
     * 新增商家（医院、药店）角色
     *
     * @param xtRole 商家（医院、药店）角色
     * @return 结果
     */
    public int insertXtRole(XtRole xtRole);


    int insertXtRoldDao(XtRoldDao xtRoleDao);
    /**
     * 修改商家（医院、药店）角色
     *
     * @param xtRole 商家（医院、药店）角色
     * @return 结果
     */
    public int updateXtRole(XtRole xtRole);

    /**
     * 删除商家（医院、药店）角色
     *
     * @param uroleid 商家（医院、药店）角色主键
     * @return 结果
     */
    public int deleteXtRoleByUroleid(String uroleid);

    /**
     * 批量删除商家（医院、药店）角色
     *
     * @param uroleids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtRoleByUroleids(String[] uroleids);

    void insertXtRoleMenu(@Param("uroleid")String uroleid, @Param("menuid") Long menuid);
}
