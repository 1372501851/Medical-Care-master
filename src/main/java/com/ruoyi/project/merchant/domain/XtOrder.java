package com.ruoyi.project.merchant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对象 xt_order
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtOrder
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private String uorderid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /**
     * 商家类型
     */
    @Excel(name = "商家类型")
    private String ucompType;

    /** 商户ID */
    @Excel(name = "商户ID")
    private Long ucustomerid;

    /** 商家名称 */
    @Excel(name = "商家ID")
    private String ucompname;

    /** 商家地址 */
    @Excel(name = "商家地址")
    private String uaddress;

    /** 商家电话 */
    @Excel(name = "商家电话")
    private String utel;

    /** 购买用户ID */
    @Excel(name = "购买用户ID")
    private String userid;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String userAddress;

    /** 用户手机号码 */
    @Excel(name = "用户手机号码")
    private String userMobile;

    /** 支付状态（0，下单，1已支付，2已完成） */
    @Excel(name = "状态", readConverterExp = "0=，下单，1已支付，2已完成")
    private String ustatus;

    /** 备注 */
    @Excel(name = "备注")
    private String uremark;

    /** 支付时间 */
    @Excel(name = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date upaymentTime;

    /** 发货时间 */
    @Excel(name = "发货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date usendGoodsTime;

    /** 快递名称 */
    @Excel(name = "快递名称")
    private String uexpressname;

    /** 快递编号 */
    @Excel(name = "快递编号")
    private String uexpressCode;

    /** 下单时间（时间戳） */
    @Excel(name = "下单时间", readConverterExp = "时=间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date ucreatedate;

    /** 总价格 */
    @Excel(name = "总价格")
    private BigDecimal utotalprice;

    /**订单编号*/
    private String orderNo;

    /**收件者姓名*/

    private String userName;

    /**收件地区*/

    private String region;

    /**所购产品Id*/
    private String productId;

    /** 产品名称 */
    private String uproductName;

    /**所购产品数量*/

    private Integer productNum;



}
