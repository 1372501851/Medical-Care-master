package com.ruoyi.project.remind.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 remind_push
 *
 * @author ruoyi
 * @date 2022-12-13
 */
@Data
public class RemindPush
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

}
