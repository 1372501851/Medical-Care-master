package com.ruoyi.project.approval.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程步骤对象 xt_flowstep
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtFlowstep
{
    private static final long serialVersionUID = 1L;

    /** 步骤id */
    private String ustepid;

    /** 流程ID */
    @Excel(name = "流程ID")
    private String uflowid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /** 审核部门 部门（科室）ID */
    @Excel(name = "审核部门 部门", readConverterExp = "科=室")
    private String udepartmentid;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private String uemployeeid;

    /** 步骤名称 */
    @Excel(name = "步骤名称")
    private String ustepname;

    /** 步骤排序 */
    @Excel(name = "步骤排序")
    private Long uorderno;

    /** 允许结束标志“0”：不允许   “1”：允许 */
    @Excel(name = "允许结束标志“0”：不允许   “1”：允许")
    private Integer ufinishflag;


}
