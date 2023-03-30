package com.ruoyi.project.merchant.domain.query;

import lombok.Data;

/**
 * @author by hujun
 * @date 2022-11-22
 */
@Data
public class ProductQuery {
    /**排序字段:1:按销量;2:按价格*/
    private Integer sort;

    /** 商品ID */
    private String uproductid;


    /** 产品类型ID */
    private String uproducttypeid;

    /** 商家ID */
    private String ucompid;

    /** 产品名称 */
    private String uproductName;

    /** 产品来源（医疗，药店，其他行业） */
    private String uproductForm;
}
