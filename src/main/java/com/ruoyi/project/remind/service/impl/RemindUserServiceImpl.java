package com.ruoyi.project.remind.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.remind.domain.Dto.RemindUserDTO;
import com.ruoyi.project.remind.domain.Remind;
import com.ruoyi.project.remind.domain.RemindUser;
import com.ruoyi.project.remind.mapper.RemindMapper;
import com.ruoyi.project.remind.mapper.RemindUserMapper;
import com.ruoyi.project.remind.service.IRemindUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-13
 */
@Service
public class RemindUserServiceImpl implements IRemindUserService
{
    @Autowired
    private RemindUserMapper remindUserMapper;

    @Autowired
    private RemindMapper remindMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public RemindUser selectRemindUserById(String id)
    {
        return remindUserMapper.selectRemindUserById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param remindUser 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<RemindUser> selectRemindUserList(RemindUser remindUser)
    {
        return remindUserMapper.selectRemindUserList(remindUser);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param remindUserDTO 【请填写功能名称】
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRemindUser(RemindUserDTO remindUserDTO) {

        if(remindUserDTO.getReminderIds().length != 0 || remindUserDTO.getReminderIds() != null){
            Remind remind = remindMapper.selectRemindById(remindUserDTO.getRemindId());
            for (int i = 0; i < remindUserDTO.getReminderIds().length; i++) {
                RemindUser remindUser = new RemindUser();
                remindUser.setId(IdUtil.getSnowflakeNextIdStr());
                BeanUtils.copyProperties(remindUserDTO,remindUser);
                remindUser.setReminderId(remindUserDTO.getReminderIds()[i]);
                remindUser.setUserId(SecurityUtils.getAppLoginUser().getUserId());
                remindUser.setCount(remind.getDays());
                remindUserMapper.insertRemindUser(remindUser);
                //需要建立一个新的方法实现定时任务

            }

        }else {
            throw new ServiceException("未选择提醒人");
        }

    }

    /**
     * 修改【请填写功能名称】
     *
     * @param remindUser 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateRemindUser(RemindUser remindUser)
    {
        return remindUserMapper.updateRemindUser(remindUser);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindUserByIds(String[] ids)
    {
        return remindUserMapper.deleteRemindUserByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindUserById(String id)
    {
        return remindUserMapper.deleteRemindUserById(id);
    }
}
