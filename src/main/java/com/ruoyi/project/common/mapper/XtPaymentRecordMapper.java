package com.ruoyi.project.common.mapper;

import java.util.List;
import com.ruoyi.project.common.domain.XtPaymentRecord;

/**
 * 支付记录Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-28
 */
public interface XtPaymentRecordMapper 
{
    /**
     * 查询支付记录
     * 
     * @param paymentRecordId 支付记录主键
     * @return 支付记录
     */
    public XtPaymentRecord selectXtPaymentRecordByPaymentRecordId(String paymentRecordId);

    /**
     * 查询支付记录列表
     * 
     * @param xtPaymentRecord 支付记录
     * @return 支付记录集合
     */
    public List<XtPaymentRecord> selectXtPaymentRecordList(XtPaymentRecord xtPaymentRecord);

    /**
     * 新增支付记录
     * 
     * @param xtPaymentRecord 支付记录
     * @return 结果
     */
    public int insertXtPaymentRecord(XtPaymentRecord xtPaymentRecord);

    /**
     * 修改支付记录
     * 
     * @param xtPaymentRecord 支付记录
     * @return 结果
     */
    public int updateXtPaymentRecord(XtPaymentRecord xtPaymentRecord);

    /**
     * 删除支付记录
     * 
     * @param paymentRecordId 支付记录主键
     * @return 结果
     */
    public int deleteXtPaymentRecordByPaymentRecordId(String paymentRecordId);

    /**
     * 批量删除支付记录
     * 
     * @param paymentRecordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtPaymentRecordByPaymentRecordIds(String[] paymentRecordIds);
}
