package com.ruoyi.project.task.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 任务（图文问诊、护理、护工等）对象 xt_task
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Data
public class XtTask
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private String utaskid;

    /** 任务名称 */
    @Excel(name = "任务名称")
    @NotBlank(message = "任务名称不能为空")
    private String utaskname;

    /** 用户ID(发布用户) */
    @Excel(name = "用户ID(发布用户)")
    @NotBlank(message = "用户Id不能为空")
    private String userid;

    /** 病房号ID */
    @Excel(name = "病房号ID")
    private String ubednoid;

    /** 病房号 */
    @Excel(name = "病房号")
    private String ubedno;

    /** 病房ID */
    @Excel(name = "病房ID")
    private String uroomid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;
    /**
     * 商家类型
     */
    @Excel(name = "商家ID")
    private String ucompType;

    /** 属于那个部门（科室）id */
    @Excel(name = "属于那个部门", readConverterExp = "科=室")
    private String udepartmentid;

    /** 医生ID */
    @Excel(name = "医生ID")
    private String uemployeeid;

    /** 任务来源类型（0图文问诊，1找护工，2找护理） */
    @Excel(name = "任务来源类型", readConverterExp = "0=图文问诊，1找护工，2找护理")
    private String utaskForm;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String udesc;

    /** 任务开始时间（年月日时分秒） */
    @Excel(name = "任务开始时间", readConverterExp = "年=月日时分秒")
    private Date ushowStartTime;

    /** 任务结束时间（年月日时分秒） */
    @Excel(name = "任务结束时间", readConverterExp = "年=月日时分秒")
    private Date ushowEndTime;

    /** 任务类型（0，一次性任务，1，多次循环，2，特殊包干） */
    @Excel(name = "任务类型", readConverterExp = "0=，一次性任务，1，多次循环，2，特殊包干")
    private String utaskType;

    /** 任务具体开始时间，时分秒 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务具体开始时间，时分秒", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ustartTime;

    /** 任务具体结束时间（时分秒） */
    @Excel(name = "任务具体结束时间", readConverterExp = "时=分秒")
    private Date uendTime;

    /** 性别要求 0不要求 1只限男生 2只限女生 默认不限 */
    @Excel(name = "性别要求 0不要求 1只限男生 2只限女生 默认不限")
    private String usexType;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal uprice;

    /** 计费单位（0分钟，1小时，2天，3月） */
    @Excel(name = "计费单位", readConverterExp = "0=分钟，1小时，2天，3月")
    private String ucostType;

    /** 服务时长(根据计费单位，提供选择)分钟 10,20,30,40,50;小时 1,2,3,4,5,6,7,8,9,10,11,12;天  1,2,3,4,5,6,7,8,9,10,11,12,13,14,15;月 1,2,3,4,5,6,7,8,9,10,11,12 */
    @Excel(name = "服务时长(根据计费单位，提供选择)分钟 10,20,30,40,50;小时 1,2,3,4,5,6,7,8,9,10,11,12;天  1,2,3,4,5,6,7,8,9,10,11,12,13,14,15;月 1,2,3,4,5,6,7,8,9,10,11,12")
    private Long userviceTimelength;

    /** 人数要求 -1 表示不要求 */
    @Excel(name = "人数要求 -1 表示不要求")
    private String upersonLimit;

    /** 状态（0，草稿；1，发布状态；2，接单中，3，完成服务，4，失败服务） */
    @Excel(name = "状态", readConverterExp = "0=，草稿；1，发布状态；2，接单中，3，完成服务，4，失败服务")
    private String ustatus;

    /** 需要被服务的人ID */
    @Excel(name = "需要被服务的人ID")
    private String utouserid;

    /** 服务人员姓名（可以从选择用户里获得） */
    @Excel(name = "服务人员姓名", readConverterExp = "可=以从选择用户里获得")
    private String uname;

    /** 性别 */
    @Excel(name = "性别")
    private String usex;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String ucardid;

    /** 出生年月（时间戳） */
    @Excel(name = "出生年月", readConverterExp = "时=间戳")
    private Long ubirthdate;

    /** 身高 */
    @Excel(name = "身高")
    private Long uheight;

    /** 政治面貌（0普通民众，1团员，2党员） */
    @Excel(name = "政治面貌", readConverterExp = "0=普通民众，1团员，2党员")
    private String uploity;

    /** 通信地址 */
    @Excel(name = "通信地址")
    private String uaddress;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String umarriage;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String umobile;

    /** 紧急联系电话 */
    @Excel(name = "紧急联系电话")
    private String uemertel;

    /** 创建时间（时间戳） */
    @Excel(name = "创建时间", readConverterExp = "时=间戳")
    private Long ucreatedate;


}
