package com.ruoyi.project.remind.mapper;

import com.ruoyi.project.remind.domain.RemindUser;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-13
 */
public interface RemindUserMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public RemindUser selectRemindUserById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param remindUser 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<RemindUser> selectRemindUserList(RemindUser remindUser);

    /**
     * 新增【请填写功能名称】
     *
     * @param remindUser 【请填写功能名称】
     * @return 结果
     */
    public int insertRemindUser(RemindUser remindUser);

    /**
     * 修改【请填写功能名称】
     *
     * @param remindUser 【请填写功能名称】
     * @return 结果
     */
    public int updateRemindUser(RemindUser remindUser);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteRemindUserById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRemindUserByIds(String[] ids);

    List<RemindUser> selectRemindUserListForCron();

    int reduceCountRemindUserById(String id);
}
