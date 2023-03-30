package com.ruoyi.project.common.mapper;

import java.util.List;
import com.ruoyi.project.common.domain.XtFinance;

/**
 * 财务明细Mapper接口
 * 
 * @author ruoyi
 * @date 2023-02-01
 */
public interface XtFinanceMapper 
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
     * 删除财务明细
     * 
     * @param financeId 财务明细主键
     * @return 结果
     */
    public int deleteXtFinanceByFinanceId(String financeId);

    /**
     * 批量删除财务明细
     * 
     * @param financeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtFinanceByFinanceIds(String[] financeIds);
}
