package com.ruoyi.project.approval.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程主对象 xt_flowmaster
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtFlowmaster
{
    private static final long serialVersionUID = 1L;

    /** 流程主表ID */
    private String uflowid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String uflowname;

    /** 申请类型（00.采购表,01.申购单表,02.入库单,03.出库单,04.申领单,05.请款单,06.请款收据,07.借款单,08.借款收据,09.报销单,10.请假单,11.其他单据） */
    @Excel(name = "申请类型", readConverterExp = "0=0.采购表,01.申购单表,02.入库单,03.出库单,04.申领单,05.请款单,06.请款收据,07.借款单,08.借款收据,09.报销单,10.请假单,11.其他单据")
    private String uappType;

    /** 流程排序 */
    @Excel(name = "流程排序")
    private Long uorderno;

    /** 流程描述 */
    @Excel(name = "流程描述")
    private String udesc;

    /** 有效标志 */
    @Excel(name = "有效标志")
    private String ueflag;


}
