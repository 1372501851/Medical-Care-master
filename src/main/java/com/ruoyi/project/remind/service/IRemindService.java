package com.ruoyi.project.remind.service;

import com.ruoyi.project.remind.domain.Dto.RemindDTO;
import com.ruoyi.project.remind.domain.Remind;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-12-10
 */
public interface IRemindService
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
     * @param remindDTO 【请填写功能名称】
     * @return 结果
     */
    public void insertRemind(RemindDTO remindDTO);

    /**
     * 修改【请填写功能名称】
     *
     * @param remind 【请填写功能名称】
     * @return 结果
     */
    public int updateRemind(Remind remind);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteRemindByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteRemindById(String id);
}
