package com.ruoyi.project.user.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by hujun
 * @date 2022-11-01
 */
@Data
public class XtOperCompQueryList {

    /** 代理商ID */
    private String uoperCompid;

    /** 代理商商户号 */
    @Excel(name = "代理商商户号")
    private Long uoperCustomerid;

    /** 商家名称 */
    @Excel(name = "商家名称")
    private String ucompname;

    /** 父级ID */
    @Excel(name = "父级ID")
    private String uparentid;


    /**
     * 祖级列表
     * */
    private String ancestors;

    /** 所在城市（可以到区） */
    @Excel(name = "所在城市", readConverterExp = "可=以到区")
    private String uareaid;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String uaddress;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal ulatitude;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal ulongitude;

    /** 法人 */
    @Excel(name = "法人")
    private String ulegalPerson;

    /** 法人身份证号码 */
    @Excel(name = "法人身份证号码")
    private String ulegalIdcard;

    /** 法人手机号码 */
    @Excel(name = "法人手机号码")
    private String ulegalMobile;

    /** 办公场所电话 */
    @Excel(name = "办公场所电话")
    private String utel;

    /** 机构门脸照片 */
    @Excel(name = "机构门脸照片")
    private String udoorFacePic;

    /** 法人身份证正面 */
    @Excel(name = "法人身份证正面")
    private String uidcardzpic;

    /** 身份证反面 */
    @Excel(name = "身份证反面")
    private String uidcardfpic;

    /** 营业执照号码 */
    @Excel(name = "营业执照号码")
    private String ubusinessLicense;

    /** 营业执照图片 */
    @Excel(name = "营业执照图片")
    private String ubusinessLicensePic;

    /** 行业许可证照 */
    @Excel(name = "行业许可证照")
    private String uindustryLicensePic;

    /** .其他相关证件 */
    @Excel(name = ".其他相关证件")
    private String urelevantCertificatesPic;

    /** 创建时间(时间戳) */
    @Excel(name = "创建时间(时间戳)")
    private Long ucreatedate;

    /**
     * 创建者id
     *
     * */

    private String createrId;

    /**
     *
     * 创建者
     * */

    private String createBy;

    /**
     *
     * 创建时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     *
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /****
     * 更新者
     *
     * */

    private String updateBy;

    /**
     *
     * 逻辑删除标志位
     * */
    private String delFlag;

    /**
     * 状态位
     * */
    private String status;

    /**
     * 查询条件
     *
     * */
    private  String query;
}
