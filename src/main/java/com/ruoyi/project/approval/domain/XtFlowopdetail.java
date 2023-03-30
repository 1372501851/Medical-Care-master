package com.ruoyi.project.approval.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 流程操作明细对象 xt_flowopdetail
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtFlowopdetail
{
    private static final long serialVersionUID = 1L;

    /** 流程操作明细id */
    private String uflowopdetailid;

    /** 流程ID */
    @Excel(name = "流程ID")
    private String uflowid;

    /** 管理员操作流程ID */
    @Excel(name = "管理员操作流程ID")
    private String uflowopmasterid;

    /** 流程名称 来源xt_flowmaster */
    @Excel(name = "流程名称 来源xt_flowmaster")
    private String uflowname;

    /** 步骤ID */
    @Excel(name = "步骤ID")
    private String ustepid;

    /** 步骤名称 来源(xt_flowstep */
    @Excel(name = "步骤名称 来源(xt_flowstep")
    private String ustepname;

    /** 经办单位ID */
    @Excel(name = "经办单位ID")
    private String ucompid;

    /** 经办部门ID */
    @Excel(name = "经办部门ID")
    private String udepartmentid;

    /** 经办操作人ID */
    @Excel(name = "经办操作人ID")
    private String uemployeeid;

    /** 操作开始时间（时间戳）年月日时分秒 */
    @Excel(name = "操作开始时间", readConverterExp = "时=间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date uopstartTime;

    /** 操作结束时间（时间戳）年月日时分秒 */
    @Excel(name = "操作结束时间", readConverterExp = "时=间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date uopendTime;

    /** 经办结果“0”：表示未通过 “1”：表示通过 */
    @Excel(name = "经办结果“0”：表示未通过 “1”：表示通过")
    private String uagree;

    /** 经办意见 */
    @Excel(name = "经办意见")
    private String uidea;

    /** 退回发起人（0不退回，1退回） */
    @Excel(name = "退回发起人", readConverterExp = "0=不退回，1退回")
    private String ureturn;

    /** 结束标志“0”：不结束   “1”：结束 */
    @Excel(name = "结束标志“0”：不结束   “1”：结束")
    private String ufinishflag;

    /** 流程操作状态 */
    @Excel(name = "流程操作状态")
    private String ustatus;
}
