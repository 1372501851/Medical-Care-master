package com.ruoyi.project.remind.domain.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-12-13
 */

@Data
public class RemindDTO {

    /** 提醒类型id */
    @Excel(name = "提醒类型id")
    @ApiModelProperty(value = "提醒类型id")
    private String typeId;

//    /** 被提醒者id */
//    @Excel(name = "被提醒者id")
//    @ApiModelProperty(value = "被提醒者id")
//    private String[] reminderIds;

    /** 被提醒者id */
    @Excel(name = "被提醒者id")
    @ApiModelProperty(value = "被提醒者id")
    private String reminderId;

    /** 提醒内容 */
    @Excel(name = "提醒内容")
    @ApiModelProperty(value = "提醒内容")
    private String content;

    /** 提醒时间 */
    @ApiModelProperty(value = "提醒时间")
    @JsonFormat(pattern = "HH:mm")
    private Date time;

    /** 提醒天数 */
    @ApiModelProperty(value = "提醒天数")
    @Excel(name = "提醒天数")
    private Integer days;

    /** 提醒日期 */
    @ApiModelProperty(value = "提醒日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "提醒日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date remindDate;

}
