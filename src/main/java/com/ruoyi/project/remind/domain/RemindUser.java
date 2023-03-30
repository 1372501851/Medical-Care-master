package com.ruoyi.project.remind.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 remind_user
 *
 * @author ruoyi
 * @date 2022-12-13
 */
@Data
public class RemindUser{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 发送者id */
    @Excel(name = "发送者id")
    private String userId;

    /** 提醒者id */
    @Excel(name = "提醒者id")
    private String reminderId;

    /** 提醒项id */
    @Excel(name = "提醒项id")
    private String remindId;


    /**提醒次数*/
    private Integer count;

}
