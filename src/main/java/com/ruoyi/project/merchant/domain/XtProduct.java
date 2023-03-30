package com.ruoyi.project.merchant.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;


/**
 * 商品对象 xt_product
 *
 * @author ruoyi
 * @date 2022-11-02
 */


@Data
public class XtProduct
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private String uproductid;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String uproductPic;

    /** 商品条形码 */
    @Excel(name = "商品条形码")
    private String ubarcode;

    /** 商品条形码数字代码 */
    @Excel(name = "商品条形码数字代码")
    private String ubarcodeNumericCode;

    /** 品类名称 */
    @Excel(name = "品类名称")
    private String ucategoryName;

    /**
     * 品类位置
     */
    private String ucategoryPlace;

    /** 种类名称 */
    @Excel(name = "种类名称")
    private String usortName;

    /** 种类位置 */
    @Excel(name = "种类位置")
    private String usortPlace;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String utradeName;

    /** 商品位置 */
    @Excel(name = "商品位置")
    private String utradePlace;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String uproductSpecs;

    /** 产品剂量 */
    @Excel(name = "产品剂量")
    private String uproductDose;

    /** 生产厂商 */
    @Excel(name = "生产厂商")
    private String umanufacturer;

    /** 物料编码 */
    @Excel(name = "物料编码")
    private String umaterialCode;

    /** 产品标准 */
    @Excel(name = "产品标准")
    private String uproductStandards;

    /** 批准文号 */
    @Excel(name = "批准文号")
    private String uapprovalNo;

    /** 主要成分 */
    @Excel(name = "主要成分")
    private String umainComponents;

    /** 主要功效 */
    @Excel(name = "主要功效")
    private String umainEfficacy;

    /** 使用方法 */
    @Excel(name = "使用方法")
    private String usageMethod;

    /** 单一疗程 */
    @Excel(name = "单一疗程")
    private String usingleTreatment;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String umattersAttention;

    /** 储藏方式 */
    @Excel(name = "储藏方式")
    private String ustorageMethod;

    /** 保质期限 */
    @Excel(name = "保质期限")
    private String ushelfLife;

    /** 失效日期 */
    @Excel(name = "失效日期")
    private String uexpirationDate;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private int uinventoryQuantity;

    /** 出库数量 */
    @Excel(name = "出库数量")
    private int udeliveryQuantity;

    /** 入库数量 */
    @Excel(name = "入库数量")
    private int ureceiptQuantity;

    /** 商品成交价 */
    @Excel(name = "商品成交价")
    private BigDecimal utransactionPrice;

    /** 商品零售价 */
    @Excel(name = "商品零售价")
    private BigDecimal uretailPrice;

    /** 商品会员价 */
    @Excel(name = "商品会员价")
    private BigDecimal umembershipPrice;

    /** 商品惠城价 */
    @Excel(name = "商品惠城价")
    private BigDecimal upriceHuicheng;

    /** 商品批发价 */
    @Excel(name = "商品批发价")
    private BigDecimal uwholesalePrice;

    /** 商品代理价 */
    @Excel(name = "商品代理价")
    private BigDecimal uagencyPrice;

    /** 外协采购价 */
    @Excel(name = "外协采购价")
    private BigDecimal uoutsourcingPrice;

    /** 商品进货价 */
    @Excel(name = "商品进货价")
    private BigDecimal upurchasePrice;

    /** 商品损耗 */
    @Excel(name = "商品损耗")
    private String ucommodityLosses;

    /** 其他备注1 */
    @Excel(name = "其他备注1")
    private String uremarks1;

    /** 其他备注2 */
    @Excel(name = "其他备注2")
    private String uremarks2;

    /** 其他备注3 */
    @Excel(name = "其他备注3")
    private String uremarks3;



////////上面的是主要的，看得见的，下面的是增加时看不见的

    /** 商品种类ID */
    @Excel(name = "产品类型ID")
    private String uproducttypeid;

    /** 商品品类ID */
    @Excel(name = "产品类型ID")
    private String ucategoryid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

//    /**
//     * 商家类型,已经和商家Id关联（上面），多余的字段，留着吧------留个毛，数据库都没有他的字段位置设计，还让程序出现bug，去你妈的，给你干掉
//     */
//    @Excel(name = "商家类型")
//    private String ucompType;

    /** 商户号 已经和商家Id关联（上面），多余的字段，留着吧*/
    @Excel(name = "商户号")
    private Long ucustomerid;

    /** 产品名称  上面有一个商品的名称了，这里怎么又来一个，酌情删除*/
    @Excel(name = "产品名称")
    private String uproductName;

    /** 产品来源（医疗，药店，其他行业）  前端页面里面没有这个属性显示，但可能其他APP有用到，留着传空值----不能传空值，数据库设计这个字段是非空，在其他地方用到了，前端加一个下拉框让用户自己选择*/
    @ApiParam("产品来源（0：医疗:，1：药店，2：其他行业）")
    @Excel(name = "产品来源", readConverterExp = "医=疗，药店，其他行业")
    private String uproductForm;

    /** 检验证书  物理属性，以后可能传照片或者扫码输入，先传空值*/
    @Excel(name = "检验证书")
    private String ucrtificateInspection;

    /** 合格证  物理属性，以后可能传照片或者扫码输入，先传空值*/
    @Excel(name = "合格证")
    private String ucertificate;

    /** 逻辑删除位;0:存在（默认）;2:删除*/
    @Excel(name = "逻辑删除位")
    private String flag;

    /** 上架状态位 0:上架（默认）  2:未上架*/
    @Excel(name = "上架状态位")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    /** 商品产地 一般为商家自己填的，可是前端页面没有显示字段，酌情增加或删除，也可能是其他APP再用，留着吧，传空值好了*/
    @Excel(name = "商品产地")
    private String utradeOriginplace;

    /** 产品型号  前无显 ， 传空 ， 留*/
    @Excel(name = "产品型号")
    private String uproductModel;

    /**销量(总)*/
    private Integer salesQuantity;

//    /**排序字段:1:按销量;2:按价格    多余字段，你就一个商品要什么排序，而且数据库中也没有这个属性的位置设计，干掉！！！！！！！！！*/
//    private Integer sort;

    /** 日销 */
    private Integer dailysales;

    /** 周销 */
    private Integer weeklysales;

    /** 月销 */
    private Integer monthsales;

    /** 季销 */
    private Integer quartersales;

    /** 年销 */
    private Integer annualsales;

}
