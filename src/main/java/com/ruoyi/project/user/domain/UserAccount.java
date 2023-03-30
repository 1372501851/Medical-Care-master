package com.ruoyi.project.user.domain;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 user_account
 *
 * @author ruoyi
 * @date 2022-12-01
 */

@Data
public class UserAccount
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 现金 */
    @Excel(name = "现金")
    private BigDecimal cash;

    /** 健康生活通 */
    @Excel(name = "健康生活通")
    private Long life;

    /** 健康金 */
    @Excel(name = "健康金")
    private Long health;

    /** 卡券数量 */
    @Excel(name = "卡券数量")
    private Long cardCoupon;

    /** 优惠券数量 */
    @Excel(name = "优惠券数量")
    private Long discountCoupon;


}
