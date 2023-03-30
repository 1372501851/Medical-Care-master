package com.ruoyi.project.im.service;

import java.util.List;

import com.ruoyi.project.im.domain.XtGroup;
import com.ruoyi.project.im.domain.XtUserToGroup;

/**
 * 用户对应群Service接口
 *
 * @author ruoyi
 * @date 2022-10-14
 */
public interface IXtUserToGroupService
{
    /**
     * 查询用户对应群
     *
     * @param usergroupid 用户对应群主键
     * @return 用户对应群
     */
    public XtUserToGroup selectXtUserToGroupByUsergroupid(String usergroupid);

    /**
     * 查询用户对应群列表
     *
     * @param xtUserToGroup 用户对应群
     * @return 用户对应群集合
     */
    public List<XtUserToGroup> selectXtUserToGroupList(XtUserToGroup xtUserToGroup);

    /**
     * 新增用户对应群
     *
     * @param xtUserToGroup 用户对应群
     * @return 结果
     */
    public int insertXtUserToGroup(XtUserToGroup xtUserToGroup);

    /**
     * 修改用户对应群
     *
     * @param xtUserToGroup 用户对应群
     * @return 结果
     */
    public int updateXtUserToGroup(XtUserToGroup xtUserToGroup);

    /**
     * 批量删除用户对应群
     *
     * @param usergroupids 需要删除的用户对应群主键集合
     * @return 结果
     */
    public int deleteXtUserToGroupByUsergroupids(String[] usergroupids);

    /**
     * 删除用户对应群信息
     *
     * @param usergroupid 用户对应群主键
     * @return 结果
     */
    public int deleteXtUserToGroupByUsergroupid(String usergroupid);

    List<XtUserToGroup> selectUserByGroupId(String chatId);

    List<XtUserToGroup> selectUserByUserId(String userId);
}
