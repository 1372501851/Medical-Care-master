package com.ruoyi.project.approval.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 管理员流程处理主对象 xt_flowopmaster
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Data
public class XtFlowopmaster
{
    private static final long serialVersionUID = 1L;

    /** 管理员操作流程ID */
    private String uflowopmasterid;

    /** 流程ID */
    @Excel(name = "流程ID")
    private String uflowid;

    /** 流程名称(冗余数据) */
    @Excel(name = "流程名称(冗余数据)")
    private String uflowname;

    /** 申请类型（00.采购表,01.申购单表,02.入库单,03.出库单,04.申领单,05.请款单,06.请款收据,07.借款单,08.借款收据,09.报销单,10.请假单,11.其他单据） */
    @Excel(name = "申请类型", readConverterExp = "0=0.采购表,01.申购单表,02.入库单,03.出库单,04.申领单,05.请款单,06.请款收据,07.借款单,08.借款收据,09.报销单,10.请假单,11.其他单据")
    private String uappType;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /** 审核部门 部门（科室）ID */
    @Excel(name = "审核部门 部门", readConverterExp = "科=室")
    private String udepartmentid;

    /** 申请发起人员id */
    @Excel(name = "申请发起人员id")
    private String uemployeeid;

    /** 用户管理员申请表ID */
    @Excel(name = "用户管理员申请表ID")
    private Long userAppid;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ustartDate;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uendDate;

    /** 流程状态（发起状态，审核状态，结束状态） */
    @Excel(name = "流程状态", readConverterExp = "发=起状态，审核状态，结束状态")
    private String ustatus;

    /** 当前步骤名称 来源(xt_flowstep) */
    @Excel(name = "当前步骤名称 来源(xt_flowstep)")
    private String ustepname;

}
