package com.ruoyi.project.remind.mapper;

import com.ruoyi.project.remind.domain.Remind;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-10
 */
public interface RemindMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Remind selectRemindById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param remind 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Remind> selectRemindList(Remind remind);

    /**
     * 新增【请填写功能名称】
     *
     * @param remind 【请填写功能名称】
     * @return 结果
     */
    public int insertRemind(Remind remind);

    /**
     * 修改【请填写功能名称】
     *
     * @param remind 【请填写功能名称】
     * @return 结果
     */
    public int updateRemind(Remind remind);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteRemindById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRemindByIds(String[] ids);
}
