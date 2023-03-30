package com.ruoyi.project.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2022-12-27
 */
@Data
public class UserCollection {

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 商品id */
    @Excel(name = "商品id")
    private String productId;




}
