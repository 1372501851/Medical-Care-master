package com.ruoyi.project.common.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.common.mapper.XtPaymentRecordMapper;
import com.ruoyi.project.common.domain.XtPaymentRecord;
import com.ruoyi.project.common.service.IXtPaymentRecordService;

/**
 * 支付记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-28
 */
@Service
public class XtPaymentRecordServiceImpl implements IXtPaymentRecordService 
{
    @Autowired
    private XtPaymentRecordMapper xtPaymentRecordMapper;

    /**
     * 查询支付记录
     * 
     * @param paymentRecordId 支付记录主键
     * @return 支付记录
     */
    @Override
    public XtPaymentRecord selectXtPaymentRecordByPaymentRecordId(String paymentRecordId)
    {
        return xtPaymentRecordMapper.selectXtPaymentRecordByPaymentRecordId(paymentRecordId);
    }

    /**
     * 查询支付记录列表
     * 
     * @param xtPaymentRecord 支付记录
     * @return 支付记录
     */
    @Override
    public List<XtPaymentRecord> selectXtPaymentRecordList(XtPaymentRecord xtPaymentRecord)
    {
        return xtPaymentRecordMapper.selectXtPaymentRecordList(xtPaymentRecord);
    }

    /**
     * 新增支付记录
     * 
     * @param xtPaymentRecord 支付记录
     * @return 结果
     */
    @Override
    public int insertXtPaymentRecord(XtPaymentRecord xtPaymentRecord)
    {
        xtPaymentRecord.setCreateTime(DateUtils.getNowDate());
        return xtPaymentRecordMapper.insertXtPaymentRecord(xtPaymentRecord);
    }

    /**
     * 修改支付记录
     * 
     * @param xtPaymentRecord 支付记录
     * @return 结果
     */
    @Override
    public int updateXtPaymentRecord(XtPaymentRecord xtPaymentRecord)
    {
        return xtPaymentRecordMapper.updateXtPaymentRecord(xtPaymentRecord);
    }

    /**
     * 批量删除支付记录
     * 
     * @param paymentRecordIds 需要删除的支付记录主键
     * @return 结果
     */
    @Override
    public int deleteXtPaymentRecordByPaymentRecordIds(String[] paymentRecordIds)
    {
        return xtPaymentRecordMapper.deleteXtPaymentRecordByPaymentRecordIds(paymentRecordIds);
    }

    /**
     * 删除支付记录信息
     * 
     * @param paymentRecordId 支付记录主键
     * @return 结果
     */
    @Override
    public int deleteXtPaymentRecordByPaymentRecordId(String paymentRecordId)
    {
        return xtPaymentRecordMapper.deleteXtPaymentRecordByPaymentRecordId(paymentRecordId);
    }
}
