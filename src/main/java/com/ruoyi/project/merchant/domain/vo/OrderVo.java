package com.ruoyi.project.merchant.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-11-24
 */
@Data
public class OrderVo {

    /**商家名称*/
    private String ucompname;
    /**商家ID*/
    private String ucompid;

    /**商品图片*/
    private String uproductPic;
    /**商品id*/
    private String productId;

    /**商品单价*/
    private BigDecimal uretailPrice;

    /**商品名称*/
    private String uproductName;
    /**购买数量*/
    private Integer productNum;
    /***总金额*/
    private BigDecimal utotalprice;

    /** 订单ID */
    private String uorderid;

/**订单状态:0:待支付,1:支付完成;2:取消订单*/

    private String ustatus;

    /**状态名称*/
    private String statusName;

    /**商品规格*/
    private String uproductSpecs;


}
