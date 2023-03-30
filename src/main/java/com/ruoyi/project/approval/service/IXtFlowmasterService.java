package com.ruoyi.project.approval.service;

import com.ruoyi.project.approval.domain.XtFlowmaster;
import com.ruoyi.project.common.domain.select.Select;

import java.util.List;

/**
 * 流程主Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtFlowmasterService
{
    /**
     * 查询流程主
     *
     * @param uflowid 流程主主键
     * @return 流程主
     */
    public XtFlowmaster selectXtFlowmasterByUflowid(String uflowid);

    /**
     * 查询流程主列表
     *
     * @param xtFlowmaster 流程主
     * @return 流程主集合
     */
    public List<XtFlowmaster> selectXtFlowmasterList(XtFlowmaster xtFlowmaster);

    /**
     * 新增流程主
     *
     * @param xtFlowmaster 流程主
     * @return 结果
     */
    public int insertXtFlowmaster(XtFlowmaster xtFlowmaster);

    /**
     * 修改流程主
     *
     * @param xtFlowmaster 流程主
     * @return 结果
     */
    public int updateXtFlowmaster(XtFlowmaster xtFlowmaster);

    /**
     * 批量删除流程主
     *
     * @param uflowids 需要删除的流程主主键集合
     * @return 结果
     */
    public int deleteXtFlowmasterByUflowids(String[] uflowids);

    /**
     * 删除流程主信息
     *
     * @param uflowid 流程主主键
     * @return 结果
     */
    public int deleteXtFlowmasterByUflowid(String uflowid);

    List<Select> getAllFlowSelectList();
}
