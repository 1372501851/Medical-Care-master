package com.ruoyi.project.merchant.domain;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName mall_cart
 */

@Data
public class MallCart implements Serializable {
    /**
     *id
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
    private Integer quantity;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

//    /**
//     * 商品名字
//     */
//    private String productName;
//
//    /**
//     * 商品单价
//     */
//    private String productPrice;
//
//    /**
//     * 商品图片
//     */
//    private String productPic;



    private static final long serialVersionUID = 1L;
}
