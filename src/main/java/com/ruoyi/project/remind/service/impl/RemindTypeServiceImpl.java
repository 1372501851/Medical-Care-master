package com.ruoyi.project.remind.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.remind.domain.RemindType;
import com.ruoyi.project.remind.mapper.RemindTypeMapper;
import com.ruoyi.project.remind.service.IRemindTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-10
 */
@Service
public class RemindTypeServiceImpl implements IRemindTypeService
{
    @Autowired
    private RemindTypeMapper remindTypeMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public RemindType selectRemindTypeById(String id)
    {
        return remindTypeMapper.selectRemindTypeById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param remindType 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<RemindType> selectRemindTypeList(RemindType remindType)
    {
        return remindTypeMapper.selectRemindTypeList(remindType);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param remindType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertRemindType(RemindType remindType)
    {
        remindType.setId(IdUtil.getSnowflakeNextIdStr());
        return remindTypeMapper.insertRemindType(remindType);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param remindType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateRemindType(RemindType remindType)
    {
        return remindTypeMapper.updateRemindType(remindType);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindTypeByIds(String[] ids)
    {
        return remindTypeMapper.deleteRemindTypeByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindTypeById(String id)
    {
        return remindTypeMapper.deleteRemindTypeById(id);
    }
}
