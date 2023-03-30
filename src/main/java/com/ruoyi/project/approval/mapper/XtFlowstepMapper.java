package com.ruoyi.project.approval.mapper;

import com.ruoyi.project.approval.domain.XtFlowstep;

import java.util.List;

/**
 * 流程步骤Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtFlowstepMapper
{
    /**
     * 查询流程步骤
     *
     * @param ustepid 流程步骤主键
     * @return 流程步骤
     */
    public XtFlowstep selectXtFlowstepByUstepid(String ustepid);

    /**
     * 查询流程步骤列表
     *
     * @param xtFlowstep 流程步骤
     * @return 流程步骤集合
     */
    public List<XtFlowstep> selectXtFlowstepList(XtFlowstep xtFlowstep);

    /**
     * 新增流程步骤
     *
     * @param xtFlowstep 流程步骤
     * @return 结果
     */
    public int insertXtFlowstep(XtFlowstep xtFlowstep);

    /**
     * 修改流程步骤
     *
     * @param xtFlowstep 流程步骤
     * @return 结果
     */
    public int updateXtFlowstep(XtFlowstep xtFlowstep);

    /**
     * 删除流程步骤
     *
     * @param ustepid 流程步骤主键
     * @return 结果
     */
    public int deleteXtFlowstepByUstepid(String ustepid);

    /**
     * 批量删除流程步骤
     *
     * @param ustepids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtFlowstepByUstepids(String[] ustepids);
}
