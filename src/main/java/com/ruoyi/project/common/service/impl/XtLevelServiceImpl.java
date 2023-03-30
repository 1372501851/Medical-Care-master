package com.ruoyi.project.common.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.common.domain.XtLevel;
import com.ruoyi.project.common.mapper.XtLevelMapper;
import com.ruoyi.project.common.service.IXtLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 等级Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtLevelServiceImpl implements IXtLevelService
{
    @Autowired
    private XtLevelMapper xtLevelMapper;

    /**
     * 查询等级
     *
     * @param ulevelid 等级主键
     * @return 等级
     */
    @Override
    public XtLevel selectXtLevelByUlevelid(String ulevelid)
    {
        return xtLevelMapper.selectXtLevelByUlevelid(ulevelid);
    }

    /**
     * 查询等级列表
     *
     * @param xtLevel 等级
     * @return 等级
     */
    @Override
    public List<XtLevel> selectXtLevelList(XtLevel xtLevel)
    {
        return xtLevelMapper.selectXtLevelList(xtLevel);
    }

    /**
     * 新增等级
     *
     * @param xtLevel 等级
     * @return 结果
     */
    @Override
    public int insertXtLevel(XtLevel xtLevel)
    {

        xtLevel.setUlevelid(IdUtil.getSnowflakeNextIdStr());
        return xtLevelMapper.insertXtLevel(xtLevel);
    }

    /**
     * 修改等级
     *
     * @param xtLevel 等级
     * @return 结果
     */
    @Override
    public int updateXtLevel(XtLevel xtLevel)
    {
        return xtLevelMapper.updateXtLevel(xtLevel);
    }

    /**
     * 批量删除等级
     *
     * @param ulevelids 需要删除的等级主键
     * @return 结果
     */
    @Override
    public int deleteXtLevelByUlevelids(String[] ulevelids)
    {
        return xtLevelMapper.deleteXtLevelByUlevelids(ulevelids);
    }

    /**
     * 删除等级信息
     *
     * @param ulevelid 等级主键
     * @return 结果
     */
    @Override
    public int deleteXtLevelByUlevelid(String ulevelid)
    {
        return xtLevelMapper.deleteXtLevelByUlevelid(ulevelid);
    }
}
