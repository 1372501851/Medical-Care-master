package com.ruoyi.project.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 财务明细对象 xt_finance
 * 
 * @author ruoyi
 * @date 2023-02-01
 */
@Data
public class XtFinance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 财务明细id */
    private String financeId;

    /** 商家id */
    @Excel(name = "商家id")
    private String ucompid;

    /** 类型（1收入，2支出） */
    @Excel(name = "类型", readConverterExp = "1=收入，2支出")
    private String tradeType;

    /** 交易编号 */
    @Excel(name = "交易编号")
    private String tradeNo;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createTime;

}
