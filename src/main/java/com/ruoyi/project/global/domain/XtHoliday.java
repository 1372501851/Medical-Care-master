package com.ruoyi.project.global.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 假期（每年的假期都录入进来）平台管理录入对象 xt_holiday
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtHoliday
{
    private static final long serialVersionUID = 1L;

    /** 假期ID */
    private String uholidayid;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date udate;

    /** 假期名称 */
    @Excel(name = "假期名称")
    private String uholidayname;

    /** 是否补班日期（0是假期，1是补班日期） */
    @Excel(name = "是否补班日期", readConverterExp = "0=是假期，1是补班日期")
    private String uifbuban;


}
