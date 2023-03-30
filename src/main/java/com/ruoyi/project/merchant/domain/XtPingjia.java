package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 任务评价对象 xt_pingjia
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtPingjia
{
    private static final long serialVersionUID = 1L;

    /** 评价ID */
    private String upingjiaid;

    /** 任务id */
    @Excel(name = "任务id")
    @NotBlank(message = "任务ID不能为空")
    private String utaskid;

    /** 评分总分5分 */
    @Excel(name = "评分总分5分")
    private BigDecimal upingfen;

    /** 评论图片最大5张 */
    @Excel(name = "评论图片最大5张")
    private String upic;

    /** 视频 */
    @Excel(name = "视频")
    private String uvideo;

    /** 内容 */
    @Excel(name = "内容")
    @Size(max = 300,message = "评论内容过长")
    private String ucontent;


}
