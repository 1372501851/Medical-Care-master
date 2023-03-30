package com.ruoyi.project.common.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.common.mapper.XtFinanceMapper;
import com.ruoyi.project.common.domain.XtFinance;
import com.ruoyi.project.common.service.IXtFinanceService;

/**
 * 财务明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-02-01
 */
@Service
public class XtFinanceServiceImpl implements IXtFinanceService 
{
    @Autowired
    private XtFinanceMapper xtFinanceMapper;

    /**
     * 查询财务明细
     * 
     * @param financeId 财务明细主键
     * @return 财务明细
     */
    @Override
    public XtFinance selectXtFinanceByFinanceId(String financeId)
    {
        return xtFinanceMapper.selectXtFinanceByFinanceId(financeId);
    }

    /**
     * 查询财务明细列表
     * 
     * @param xtFinance 财务明细
     * @return 财务明细
     */
    @Override
    public List<XtFinance> selectXtFinanceList(XtFinance xtFinance)
    {
        return xtFinanceMapper.selectXtFinanceList(xtFinance);
    }

    /**
     * 新增财务明细
     * 
     * @param xtFinance 财务明细
     * @return 结果
     */
    @Override
    public int insertXtFinance(XtFinance xtFinance)
    {
        xtFinance.setCreateTime(DateUtils.getNowDate());
        return xtFinanceMapper.insertXtFinance(xtFinance);
    }

    /**
     * 修改财务明细
     * 
     * @param xtFinance 财务明细
     * @return 结果
     */
    @Override
    public int updateXtFinance(XtFinance xtFinance)
    {
        return xtFinanceMapper.updateXtFinance(xtFinance);
    }

    /**
     * 批量删除财务明细
     * 
     * @param financeIds 需要删除的财务明细主键
     * @return 结果
     */
    @Override
    public int deleteXtFinanceByFinanceIds(String[] financeIds)
    {
        return xtFinanceMapper.deleteXtFinanceByFinanceIds(financeIds);
    }

    /**
     * 删除财务明细信息
     * 
     * @param financeId 财务明细主键
     * @return 结果
     */
    @Override
    public int deleteXtFinanceByFinanceId(String financeId)
    {
        return xtFinanceMapper.deleteXtFinanceByFinanceId(financeId);
    }
}
