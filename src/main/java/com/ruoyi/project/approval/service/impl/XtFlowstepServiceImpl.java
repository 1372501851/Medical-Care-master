package com.ruoyi.project.approval.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.approval.domain.XtFlowstep;
import com.ruoyi.project.approval.mapper.XtFlowstepMapper;
import com.ruoyi.project.approval.service.IXtFlowstepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程步骤Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtFlowstepServiceImpl implements IXtFlowstepService
{
    @Autowired
    private XtFlowstepMapper xtFlowstepMapper;

    /**
     * 查询流程步骤
     *
     * @param ustepid 流程步骤主键
     * @return 流程步骤
     */
    @Override
    public XtFlowstep selectXtFlowstepByUstepid(String ustepid)
    {
        return xtFlowstepMapper.selectXtFlowstepByUstepid(ustepid);
    }

    /**
     * 查询流程步骤列表
     *
     * @param xtFlowstep 流程步骤
     * @return 流程步骤
     */
    @Override
    public List<XtFlowstep> selectXtFlowstepList(XtFlowstep xtFlowstep)
    {
        return xtFlowstepMapper.selectXtFlowstepList(xtFlowstep);
    }

    /**
     * 新增流程步骤
     *
     * @param xtFlowstep 流程步骤
     * @return 结果
     */
    @Override
    public int insertXtFlowstep(XtFlowstep xtFlowstep)
    {
        xtFlowstep.setUstepid(IdUtil.getSnowflakeNextIdStr());
        return xtFlowstepMapper.insertXtFlowstep(xtFlowstep);
    }

    /**
     * 修改流程步骤
     *
     * @param xtFlowstep 流程步骤
     * @return 结果
     */
    @Override
    public int updateXtFlowstep(XtFlowstep xtFlowstep)
    {
        return xtFlowstepMapper.updateXtFlowstep(xtFlowstep);
    }

    /**
     * 批量删除流程步骤
     *
     * @param ustepids 需要删除的流程步骤主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowstepByUstepids(String[] ustepids)
    {
        return xtFlowstepMapper.deleteXtFlowstepByUstepids(ustepids);
    }

    /**
     * 删除流程步骤信息
     *
     * @param ustepid 流程步骤主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowstepByUstepid(String ustepid)
    {
        return xtFlowstepMapper.deleteXtFlowstepByUstepid(ustepid);
    }
}
