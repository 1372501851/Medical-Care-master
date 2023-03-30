package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备与用户关系对象 xt_equipment_user
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtEquipmentUser
{
    private static final long serialVersionUID = 1L;

    /** 设备与用户关系ID */
    private String uequipmentUserid;

    /** 设备ID */
    @Excel(name = "设备ID")
    private String uquipmentid;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userid;

    /** 用户信息表ID */
    @Excel(name = "用户信息表ID")
    private String uemployeeid;

    /** 是否设备管理员（0不是，1是）可以有多个管理员 */
    @Excel(name = "是否设备管理员", readConverterExp = "0=不是，1是")
    private String uifAdmin;

    /** 是否限制时间（0没限制，1有限制） */
    @Excel(name = "是否限制时间", readConverterExp = "0=没限制，1有限制")
    private String uifLimitDate;

    /** 开始时间（年月日时分秒）时间戳 */
    @Excel(name = "开始时间", readConverterExp = "年=月日时分秒")
    private Long ustartDatetime;

    /** 结束时间（年月日时分秒）时间戳 */
    @Excel(name = "结束时间", readConverterExp = "年=月日时分秒")
    private Long uendDatetime;

    /** 状态（0暂停，1正常） */
    @Excel(name = "状态", readConverterExp = "0=暂停，1正常")
    private String ustatus;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Long ucreatedate;

    /**设备类型Id*/
    private String uequipmentTypeid;


}
