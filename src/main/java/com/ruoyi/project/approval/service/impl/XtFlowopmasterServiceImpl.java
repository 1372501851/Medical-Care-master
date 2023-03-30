package com.ruoyi.project.approval.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.approval.domain.XtFlowopmaster;
import com.ruoyi.project.approval.mapper.XtFlowopmasterMapper;
import com.ruoyi.project.approval.service.IXtFlowopmasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员流程处理主Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtFlowopmasterServiceImpl implements IXtFlowopmasterService
{
    @Autowired
    private XtFlowopmasterMapper xtFlowopmasterMapper;

    /**
     * 查询管理员流程处理主
     *
     * @param uflowopmasterid 管理员流程处理主主键
     * @return 管理员流程处理主
     */
    @Override
    public XtFlowopmaster selectXtFlowopmasterByUflowopmasterid(String uflowopmasterid)
    {
        return xtFlowopmasterMapper.selectXtFlowopmasterByUflowopmasterid(uflowopmasterid);
    }

    /**
     * 查询管理员流程处理主列表
     *
     * @param xtFlowopmaster 管理员流程处理主
     * @return 管理员流程处理主
     */
    @Override
    public List<XtFlowopmaster> selectXtFlowopmasterList(XtFlowopmaster xtFlowopmaster)
    {
        return xtFlowopmasterMapper.selectXtFlowopmasterList(xtFlowopmaster);
    }

    /**
     * 新增管理员流程处理主
     *
     * @param xtFlowopmaster 管理员流程处理主
     * @return 结果
     */
    @Override
    public int insertXtFlowopmaster(XtFlowopmaster xtFlowopmaster)
    {
        xtFlowopmaster.setUflowopmasterid(IdUtil.getSnowflakeNextIdStr());
        return xtFlowopmasterMapper.insertXtFlowopmaster(xtFlowopmaster);
    }

    /**
     * 修改管理员流程处理主
     *
     * @param xtFlowopmaster 管理员流程处理主
     * @return 结果
     */
    @Override
    public int updateXtFlowopmaster(XtFlowopmaster xtFlowopmaster)
    {
        return xtFlowopmasterMapper.updateXtFlowopmaster(xtFlowopmaster);
    }

    /**
     * 批量删除管理员流程处理主
     *
     * @param uflowopmasterids 需要删除的管理员流程处理主主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowopmasterByUflowopmasterids(String[] uflowopmasterids)
    {
        return xtFlowopmasterMapper.deleteXtFlowopmasterByUflowopmasterids(uflowopmasterids);
    }

    /**
     * 删除管理员流程处理主信息
     *
     * @param uflowopmasterid 管理员流程处理主主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowopmasterByUflowopmasterid(String uflowopmasterid)
    {
        return xtFlowopmasterMapper.deleteXtFlowopmasterByUflowopmasterid(uflowopmasterid);
    }
}
