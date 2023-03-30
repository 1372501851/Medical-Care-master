package com.ruoyi.project.remind.service;

import com.ruoyi.project.remind.domain.RemindPush;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-12-13
 */
public interface IRemindPushService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public RemindPush selectRemindPushById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param remindPush 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<RemindPush> selectRemindPushList(RemindPush remindPush);

    /**
     * 新增【请填写功能名称】
     *
     * @param remindPush 【请填写功能名称】
     * @return 结果
     */
    public int insertRemindPush(RemindPush remindPush);

    /**
     * 修改【请填写功能名称】
     *
     * @param remindPush 【请填写功能名称】
     * @return 结果
     */
    public int updateRemindPush(RemindPush remindPush);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteRemindPushByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteRemindPushById(String id);
}
