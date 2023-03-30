package com.ruoyi.project.merchant.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-12-10
 */

@Data
public class EvaluationVo {

    /** 订单id */
    @NotBlank(message = "订单id不能为空")
    private String utaskid;

    /** 评分总分5分 */
    @Excel(name = "评分总分5分")
    private BigDecimal upingfen;

    /** 评论图片最大5张 */
    @Excel(name = "评论图片最大5张")
    private List<String> upic;

    /** 视频 */
    @Excel(name = "视频")
    private String uvideo;

    /** 内容 */
    @Excel(name = "内容")
    @Size(max = 300,message = "评论内容过长")
    private String ucontent;
}
