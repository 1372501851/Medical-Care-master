package com.ruoyi.project.remind.mapper;

import com.ruoyi.project.remind.domain.RemindType;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-10
 */
public interface RemindTypeMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public RemindType selectRemindTypeById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param remindType 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<RemindType> selectRemindTypeList(RemindType remindType);

    /**
     * 新增【请填写功能名称】
     *
     * @param remindType 【请填写功能名称】
     * @return 结果
     */
    public int insertRemindType(RemindType remindType);

    /**
     * 修改【请填写功能名称】
     *
     * @param remindType 【请填写功能名称】
     * @return 结果
     */
    public int updateRemindType(RemindType remindType);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteRemindTypeById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRemindTypeByIds(String[] ids);
}
