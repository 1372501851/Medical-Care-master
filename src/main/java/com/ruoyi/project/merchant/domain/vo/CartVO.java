package com.ruoyi.project.merchant.domain.vo;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-11-17
 */
@Data
public class CartVO {
    /**
     *
     */

    private String id;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 选中状态:0未选中,1选中
     */
    private String selected;

    /**
     * 数量
     */
    private int quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private String productName;

    private String productImage;

}
