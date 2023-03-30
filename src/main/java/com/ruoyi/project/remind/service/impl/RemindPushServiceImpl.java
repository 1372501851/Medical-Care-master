package com.ruoyi.project.remind.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.remind.domain.RemindPush;
import com.ruoyi.project.remind.mapper.RemindPushMapper;
import com.ruoyi.project.remind.service.IRemindPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-13
 */
@Service
public class RemindPushServiceImpl implements IRemindPushService
{
    @Autowired
    private RemindPushMapper remindPushMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public RemindPush selectRemindPushById(String id)
    {
        return remindPushMapper.selectRemindPushById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param remindPush 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<RemindPush> selectRemindPushList(RemindPush remindPush)
    {
        return remindPushMapper.selectRemindPushList(remindPush);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param remindPush 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertRemindPush(RemindPush remindPush)
    {
        return remindPushMapper.insertRemindPush(remindPush);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param remindPush 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateRemindPush(RemindPush remindPush)
    {
        return remindPushMapper.updateRemindPush(remindPush);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindPushByIds(String[] ids)
    {
        return remindPushMapper.deleteRemindPushByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindPushById(String id)
    {
        return remindPushMapper.deleteRemindPushById(id);
    }
}
