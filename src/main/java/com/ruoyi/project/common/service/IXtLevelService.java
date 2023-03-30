package com.ruoyi.project.common.service;

import com.ruoyi.project.common.domain.XtLevel;

import java.util.List;

/**
 * 等级Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtLevelService
{
    /**
     * 查询等级
     *
     * @param ulevelid 等级主键
     * @return 等级
     */
    public XtLevel selectXtLevelByUlevelid(String ulevelid);

    /**
     * 查询等级列表
     *
     * @param xtLevel 等级
     * @return 等级集合
     */
    public List<XtLevel> selectXtLevelList(XtLevel xtLevel);

    /**
     * 新增等级
     *
     * @param xtLevel 等级
     * @return 结果
     */
    public int insertXtLevel(XtLevel xtLevel);

    /**
     * 修改等级
     *
     * @param xtLevel 等级
     * @return 结果
     */
    public int updateXtLevel(XtLevel xtLevel);

    /**
     * 批量删除等级
     *
     * @param ulevelids 需要删除的等级主键集合
     * @return 结果
     */
    public int deleteXtLevelByUlevelids(String[] ulevelids);

    /**
     * 删除等级信息
     *
     * @param ulevelid 等级主键
     * @return 结果
     */
    public int deleteXtLevelByUlevelid(String ulevelid);
}
