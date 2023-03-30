package com.ruoyi.project.approval.service;

import com.ruoyi.project.approval.domain.XtUserApp;

import java.util.List;

/**
 * 用户管理员申请Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtUserAppService
{
    /**
     * 查询用户管理员申请
     *
     * @param userAppid 用户管理员申请主键
     * @return 用户管理员申请
     */
    public XtUserApp selectXtUserAppByUserAppid(String userAppid);

    /**
     * 查询用户管理员申请列表
     *
     * @param xtUserApp 用户管理员申请
     * @return 用户管理员申请集合
     */
    public List<XtUserApp> selectXtUserAppList(XtUserApp xtUserApp);

    /**
     * 新增用户管理员申请
     *
     * @param xtUserApp 用户管理员申请
     * @return 结果
     */
    public int insertXtUserApp(XtUserApp xtUserApp);

    /**
     * 修改用户管理员申请
     *
     * @param xtUserApp 用户管理员申请
     * @return 结果
     */
    public int updateXtUserApp(XtUserApp xtUserApp);

    /**
     * 批量删除用户管理员申请
     *
     * @param userAppids 需要删除的用户管理员申请主键集合
     * @return 结果
     */
    public int deleteXtUserAppByUserAppids(String[] userAppids);

    /**
     * 删除用户管理员申请信息
     *
     * @param userAppid 用户管理员申请主键
     * @return 结果
     */
    public int deleteXtUserAppByUserAppid(String userAppid);
}
