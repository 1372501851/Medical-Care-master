package com.ruoyi.project.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 支付记录对象 xt_payment_record
 * 
 * @author ruoyi
 * @date 2023-01-28
 */
public class XtPaymentRecord {

    /** 支付记录id */
    private String paymentRecordId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userid;

    /** 商家id */
    @Excel(name = "商家id")
    private String ucompid;

    /** 代理商id */
    @Excel(name = "代理商id")
    private String uoperCompid;

    /** 交易编号 */
    @Excel(name = "交易编号")
    private String tradeNo;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String paymentMethod;

    /** 状态（1已成功，0已退回...） */
    @Excel(name = "状态", readConverterExp = "1=已成功，0已退回...")
    private String status;

    private Date createTime;

    public void setPaymentRecordId(String paymentRecordId) 
    {
        this.paymentRecordId = paymentRecordId;
    }

    public String getPaymentRecordId() 
    {
        return paymentRecordId;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setUcompid(String ucompid) 
    {
        this.ucompid = ucompid;
    }

    public String getUcompid() 
    {
        return ucompid;
    }
    public void setUoperCompid(String uoperCompid) 
    {
        this.uoperCompid = uoperCompid;
    }

    public String getUoperCompid() 
    {
        return uoperCompid;
    }
    public void setTradeNo(String tradeNo) 
    {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo() 
    {
        return tradeNo;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paymentRecordId", getPaymentRecordId())
            .append("userid", getUserid())
            .append("ucompid", getUcompid())
            .append("uoperCompid", getUoperCompid())
            .append("tradeNo", getTradeNo())
            .append("description", getDescription())
            .append("amount", getAmount())
            .append("paymentMethod", getPaymentMethod())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
