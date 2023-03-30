package com.ruoyi.project.remind.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 remind
 *
 * @author ruoyi
 * @date 2022-12-10
 */
@Data
public class Remind
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 提醒类型id */
    @Excel(name = "提醒类型id")
    private String typeId;

    /** 被提醒者id */
    @Excel(name = "被提醒者id")
    private String reminderId;

    /** 提醒内容 */
    @Excel(name = "提醒内容")
    private String content;

    /** 提醒时间 */
    @JsonFormat(pattern = "HH:mm")
    @Excel(name = "提醒时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 提醒天数 */
    @Excel(name = "提醒天数")
    private Integer days;

    /** 提醒日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提醒日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date remindDate;

    /** 用户Id */
    @Excel(name = "用户Id")
    private String userId;

    /** 商家Id */
    @Excel(name = "商家Id")
    private String ucompid;


}
