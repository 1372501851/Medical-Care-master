package com.ruoyi.project.approval.service;

import com.ruoyi.project.approval.domain.XtFlowopmaster;

import java.util.List;

/**
 * 管理员流程处理主Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtFlowopmasterService
{
    /**
     * 查询管理员流程处理主
     *
     * @param uflowopmasterid 管理员流程处理主主键
     * @return 管理员流程处理主
     */
    public XtFlowopmaster selectXtFlowopmasterByUflowopmasterid(String uflowopmasterid);

    /**
     * 查询管理员流程处理主列表
     *
     * @param xtFlowopmaster 管理员流程处理主
     * @return 管理员流程处理主集合
     */
    public List<XtFlowopmaster> selectXtFlowopmasterList(XtFlowopmaster xtFlowopmaster);

    /**
     * 新增管理员流程处理主
     *
     * @param xtFlowopmaster 管理员流程处理主
     * @return 结果
     */
    public int insertXtFlowopmaster(XtFlowopmaster xtFlowopmaster);

    /**
     * 修改管理员流程处理主
     *
     * @param xtFlowopmaster 管理员流程处理主
     * @return 结果
     */
    public int updateXtFlowopmaster(XtFlowopmaster xtFlowopmaster);

    /**
     * 批量删除管理员流程处理主
     *
     * @param uflowopmasterids 需要删除的管理员流程处理主主键集合
     * @return 结果
     */
    public int deleteXtFlowopmasterByUflowopmasterids(String[] uflowopmasterids);

    /**
     * 删除管理员流程处理主信息
     *
     * @param uflowopmasterid 管理员流程处理主主键
     * @return 结果
     */
    public int deleteXtFlowopmasterByUflowopmasterid(String uflowopmasterid);
}
