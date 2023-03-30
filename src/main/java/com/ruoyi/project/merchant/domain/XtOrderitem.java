package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 订单详情对象 xt_orderitem
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtOrderitem
{
    private static final long serialVersionUID = 1L;

    /** 订单详细ID */
    private String uorderitemid;

    /** 订单ID */
    @Excel(name = "订单ID")
    private String uorderid;

    /** 商品ID */
    @Excel(name = "商品ID")
    private String uproductid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /** 商户号 */
    @Excel(name = "商户号")
    private Long ucustomerid;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String uproductName;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal uprice;

    /** 订购数量 */
    @Excel(name = "订购数量")
    private int unum;

    /** 产品来源（医疗，药店，其他行业） */
    @Excel(name = "产品来源", readConverterExp = "医=疗，药店，其他行业")
    private String uproductForm;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String uproductPic;

    /** 商品条码 */
    @Excel(name = "商品条码")
    private String ubarcode;

    /** 商品条形码数字代码 */
    @Excel(name = "商品条形码数字代码")
    private String ubarcodeNumericCode;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String uproductModel;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String uproductSpecs;

    /** 下单时间（时间戳） */
    @Excel(name = "下单时间", readConverterExp = "时=间戳")
    private Long ucreatedate;


    /**总价*/
    private BigDecimal totalPrice;


}
