package com.ruoyi.project.im.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.im.mapper.XtUserToGroupMapper;
import com.ruoyi.project.im.domain.XtUserToGroup;
import com.ruoyi.project.im.service.IXtUserToGroupService;

/**
 * 用户对应群Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-14
 */
@Service
public class XtUserToGroupServiceImpl implements IXtUserToGroupService
{
    @Autowired
    private XtUserToGroupMapper xtUserToGroupMapper;

    /**
     * 查询用户对应群
     *
     * @param usergroupid 用户对应群主键
     * @return 用户对应群
     */
    @Override
    public XtUserToGroup selectXtUserToGroupByUsergroupid(String usergroupid)
    {
        return xtUserToGroupMapper.selectXtUserToGroupByUsergroupid(usergroupid);
    }

    /**
     * 查询用户对应群列表
     *
     * @param xtUserToGroup 用户对应群
     * @return 用户对应群
     */
    @Override
    public List<XtUserToGroup> selectXtUserToGroupList(XtUserToGroup xtUserToGroup)
    {
        return xtUserToGroupMapper.selectXtUserToGroupList(xtUserToGroup);
    }

    /**
     * 新增用户对应群
     *
     * @param xtUserToGroup 用户对应群
     * @return 结果
     */
    @Override
    public int insertXtUserToGroup(XtUserToGroup xtUserToGroup)
    {
        return xtUserToGroupMapper.insertXtUserToGroup(xtUserToGroup);
    }

    /**
     * 修改用户对应群
     *
     * @param xtUserToGroup 用户对应群
     * @return 结果
     */
    @Override
    public int updateXtUserToGroup(XtUserToGroup xtUserToGroup)
    {
        xtUserToGroup.setUpdateTime(DateUtils.getNowDate());
        return xtUserToGroupMapper.updateXtUserToGroup(xtUserToGroup);
    }

    /**
     * 批量删除用户对应群
     *
     * @param usergroupids 需要删除的用户对应群主键
     * @return 结果
     */
    @Override
    public int deleteXtUserToGroupByUsergroupids(String[] usergroupids)
    {
        return xtUserToGroupMapper.deleteXtUserToGroupByUsergroupids(usergroupids);
    }

    /**
     * 删除用户对应群信息
     *
     * @param usergroupid 用户对应群主键
     * @return 结果
     */
    @Override
    public int deleteXtUserToGroupByUsergroupid(String usergroupid)
    {
        return xtUserToGroupMapper.deleteXtUserToGroupByUsergroupid(usergroupid);
    }

    @Override
    public List<XtUserToGroup> selectUserByGroupId(String chatId) {
        List<XtUserToGroup> xtUserToGroups = xtUserToGroupMapper.selectUserListByGroupId(chatId);
        return xtUserToGroups;
    }

    @Override
    public List<XtUserToGroup> selectUserByUserId(String userId) {
        List<XtUserToGroup> xtUserToGroups = xtUserToGroupMapper.selectUserListByUserId(userId);
        return xtUserToGroups;
    }
}
