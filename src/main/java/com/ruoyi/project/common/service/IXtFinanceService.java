package com.ruoyi.project.common.service;

import java.util.List;
import com.ruoyi.project.common.domain.XtFinance;

/**
 * 财务明细Service接口
 * 
 * @author ruoyi
 * @date 2023-02-01
 */
public interface IXtFinanceService 
{
    /**
     * 查询财务明细
     * 
     * @param financeId 财务明细主键
     * @return 财务明细
     */
    public XtFinance selectXtFinanceByFinanceId(String financeId);

    /**
     * 查询财务明细列表
     * 
     * @param xtFinance 财务明细
     * @return 财务明细集合
     */
    public List<XtFinance> selectXtFinanceList(XtFinance xtFinance);

    /**
     * 新增财务明细
     * 
     * @param xtFinance 财务明细
     * @return 结果
     */
    public int insertXtFinance(XtFinance xtFinance);

    /**
     * 修改财务明细
     * 
     * @param xtFinance 财务明细
     * @return 结果
     */
    public int updateXtFinance(XtFinance xtFinance);

    /**
     * 批量删除财务明细
     * 
     * @param financeIds 需要删除的财务明细主键集合
     * @return 结果
     */
    public int deleteXtFinanceByFinanceIds(String[] financeIds);

    /**
     * 删除财务明细信息
     * 
     * @param financeId 财务明细主键
     * @return 结果
     */
    public int deleteXtFinanceByFinanceId(String financeId);
}
