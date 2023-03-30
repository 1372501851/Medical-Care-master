package com.ruoyi.project.merchant.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-11-22
 */
@Data
public class MallProductVo {


    /** 产品类型ID */
    private String uproducttypeid;

    /** 商品ID */
    private String uproductid;


    /** 商家ID */

    private String ucompid;

    private String uproductName;

    /** 商品图片 */

    private String uproductPic;

    /** 库存数量 */

    private int uinventoryQuantity;


    /** 商品零售价 */

    private BigDecimal uretailPrice;

    /** 商品会员价 */

    private BigDecimal umembershipPrice;

    /** 商品惠城价 */

    private BigDecimal upriceHuicheng;


    /** 其他备注1 */

    private String uremarks1;

    /** 其他备注2 */

    private String uremarks2;


    /**销量*/

    private Integer SalesQuantity;

    private String usingleTreatment;

    /**规格*/
    private String uproductSpecs;


}
