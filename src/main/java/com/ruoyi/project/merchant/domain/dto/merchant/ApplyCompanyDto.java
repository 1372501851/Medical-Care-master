package com.ruoyi.project.merchant.domain.dto.merchant;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-11-09
 */
@Data
public class ApplyCompanyDto {


    /** 商户号（一个商家一个商户号，分部都用相同的商户号，一个10位的递增数，比如：1000000001） */
    @Excel(name = "商户号", readConverterExp = "一=个商家一个商户号，分部都用相同的商户号，一个10位的递增数，比如：1000000001")
    private Long ucustomerid;

    /** 商家名称 */
    @Excel(name = "商家名称")
    private String ucompname;

    /** 父级ID */
    @Excel(name = "父级ID")
    private String uparentid;

    /** 企业经营模式:   独立/加盟/连锁 */
    @Excel(name = "企业经营模式:   独立/加盟/连锁")
    private String umanageModel;

    /** 公司类型，医院、药店、沙发家居家具... */
    @Excel(name = "公司类型，医院、药店、沙发家居家具...")
    private String ucompType;

    /** 所在城市（可以到街道办） */
    @Excel(name = "所在城市", readConverterExp = "可=以到街道办")
    private String uareaid;

    /** 所在社区 */
    @Excel(name = "所在社区")
    private String ucommunity;

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

    /** 药师执业许可证 */
    @Excel(name = "药师执业许可证")
    private String upharmacistLicensePic;

    /** 医师执业许可证 */
    @Excel(name = "医师执业许可证")
    private String umedicalLicensePic;

    /** 消防安全许可证 */
    @Excel(name = "消防安全许可证")
    private String ufireSafetyPic;

    /** .其他相关证件 */
    @Excel(name = ".其他相关证件")
    private String urelevantCertificatesPic;
}
