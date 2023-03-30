package com.ruoyi.project.approval.service;

import com.ruoyi.project.approval.domain.XtFlowopdetail;

import java.util.List;

/**
 * 流程操作明细Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtFlowopdetailService
{
    /**
     * 查询流程操作明细
     *
     * @param uflowopdetailid 流程操作明细主键
     * @return 流程操作明细
     */
    public XtFlowopdetail selectXtFlowopdetailByUflowopdetailid(String uflowopdetailid);

    /**
     * 查询流程操作明细列表
     *
     * @param xtFlowopdetail 流程操作明细
     * @return 流程操作明细集合
     */
    public List<XtFlowopdetail> selectXtFlowopdetailList(XtFlowopdetail xtFlowopdetail);

    /**
     * 新增流程操作明细
     *
     * @param xtFlowopdetail 流程操作明细
     * @return 结果
     */
    public int insertXtFlowopdetail(XtFlowopdetail xtFlowopdetail);

    /**
     * 修改流程操作明细
     *
     * @param xtFlowopdetail 流程操作明细
     * @return 结果
     */
    public int updateXtFlowopdetail(XtFlowopdetail xtFlowopdetail);

    /**
     * 批量删除流程操作明细
     *
     * @param uflowopdetailids 需要删除的流程操作明细主键集合
     * @return 结果
     */
    public int deleteXtFlowopdetailByUflowopdetailids(String[] uflowopdetailids);

    /**
     * 删除流程操作明细信息
     *
     * @param uflowopdetailid 流程操作明细主键
     * @return 结果
     */
    public int deleteXtFlowopdetailByUflowopdetailid(String uflowopdetailid);
}
