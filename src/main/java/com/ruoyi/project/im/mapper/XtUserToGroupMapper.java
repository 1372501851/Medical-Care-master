package com.ruoyi.project.im.mapper;

import java.util.List;
import com.ruoyi.project.im.domain.XtUserToGroup;
import com.ruoyi.project.im.domain.create.AddGroupMember;
import org.springframework.stereotype.Repository;

/**
 * 用户对应群Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-14
 */
@Repository
public interface XtUserToGroupMapper
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
     * 删除用户对应群
     *
     * @param usergroupid 用户对应群主键
     * @return 结果
     */
    public int deleteXtUserToGroupByUsergroupid(String usergroupid);

    /**
     * 批量删除用户对应群
     *
     * @param usergroupids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtUserToGroupByUsergroupids(String[] usergroupids);

    List<XtUserToGroup> selectUserListByGroupId(String chatId);

    List<XtUserToGroup> selectUserListByUserId(String userId);

    int deleteXtUserToGroupByUsergroupidAndUserId(AddGroupMember addGroupMember);
}
