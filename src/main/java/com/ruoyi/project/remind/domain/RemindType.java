package com.ruoyi.project.remind.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 remind_type
 *
 * @author ruoyi
 * @date 2022-12-10
 */

@Data
public class RemindType
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;


}
