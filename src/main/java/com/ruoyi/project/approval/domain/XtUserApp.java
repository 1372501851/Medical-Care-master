package com.ruoyi.project.approval.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 用户管理员申请对象 xt_user_app
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Data
public class XtUserApp
{
    private static final long serialVersionUID = 1L;

    /** 用户管理员申请表ID */
    private String userAppid;

    /** 部门（科室）ID */
    @Excel(name = "部门", readConverterExp = "科=室")
    private String udepartmentid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /** 申请人ID */
    @Excel(name = "申请人ID")
    private String uemployeeid;

    /** 申请类型（00.采购表,01.申购单表,02.入库单,03.出库单,04.申领单,05.请款单,06.请款收据,07.借款单,08.借款收据,09.报销单,10.请假单,11.其他单据） */
    @Excel(name = "申请类型", readConverterExp = "0=0.采购表,01.申购单表,02.入库单,03.出库单,04.申领单,05.请款单,06.请款收据,07.借款单,08.借款收据,09.报销单,10.请假单,11.其他单据")
    private String uappType;

    /** 申请标题（可以自动生成） */
    @Excel(name = "申请标题", readConverterExp = "可=以自动生成")
    private String utitle;

    /** 申请金额 */
    @Excel(name = "申请金额")
    private BigDecimal uappMoney;

    /** 申请事由（一般根据不同申请类型，提供不同的选择） */
    @Excel(name = "申请事由", readConverterExp = "一=般根据不同申请类型，提供不同的选择")
    private String usubjectMatter;

    /** 收款人 */
    @Excel(name = "收款人")
    private String upayee;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ustartDate;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uendDate;

    /** 状态（0，草稿，1申请中，2处理完成） */
    @Excel(name = "状态", readConverterExp = "0=，草稿，1申请中，2处理完成")
    private String ustatus;

    /** 内容（具体内容，比如请假的，申请其他的可以自动产生） */
    @Excel(name = "内容", readConverterExp = "具=体内容，比如请假的，申请其他的可以自动产生")
    private String ucontent;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Long ucreatedate;

}
