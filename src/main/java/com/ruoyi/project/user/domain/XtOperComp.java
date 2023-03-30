package com.ruoyi.project.user.domain;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 代理商对象 xt_oper_comp
 *
 * @author ruoyi
 * @date 2022-11-01
 */
@Data
public class XtOperComp
{
    private static final long serialVersionUID = 1L;

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

    private List<String> areaIdList;

//    public String getAncestors() {
//        return ancestors;
//    }
//
//    public void setAncestors(String ancestors) {
//        this.ancestors = ancestors;
//    }
//
//    public String getDelFlag() {
//        return delFlag;
//    }
//
//    public void setDelFlag(String delFlag) {
//        this.delFlag = delFlag;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getUoperCompid() {
//        return uoperCompid;
//    }
//
//    public void setUoperCompid(String uoperCompid) {
//        this.uoperCompid = uoperCompid;
//    }
//
//    public Long getUoperCustomerid() {
//        return uoperCustomerid;
//    }
//
//    public void setUoperCustomerid(Long uoperCustomerid) {
//        this.uoperCustomerid = uoperCustomerid;
//    }
//
//    public String getUcompname() {
//        return ucompname;
//    }
//
//    public void setUcompname(String ucompname) {
//        this.ucompname = ucompname;
//    }
//
//    public String getUparentid() {
//        return uparentid;
//    }
//
//    public void setUparentid(String uparentid) {
//        this.uparentid = uparentid;
//    }
//
//    public String getUareaid() {
//        return uareaid;
//    }
//
//    public void setUareaid(String uareaid) {
//        this.uareaid = uareaid;
//    }
//
//    public String getUaddress() {
//        return uaddress;
//    }
//
//    public void setUaddress(String uaddress) {
//        this.uaddress = uaddress;
//    }
//
//    public BigDecimal getUlatitude() {
//        return ulatitude;
//    }
//
//    public void setUlatitude(BigDecimal ulatitude) {
//        this.ulatitude = ulatitude;
//    }
//
//    public BigDecimal getUlongitude() {
//        return ulongitude;
//    }
//
//    public void setUlongitude(BigDecimal ulongitude) {
//        this.ulongitude = ulongitude;
//    }
//
//    public String getUlegalPerson() {
//        return ulegalPerson;
//    }
//
//    public void setUlegalPerson(String ulegalPerson) {
//        this.ulegalPerson = ulegalPerson;
//    }
//
//    public String getUlegalIdcard() {
//        return ulegalIdcard;
//    }
//
//    public void setUlegalIdcard(String ulegalIdcard) {
//        this.ulegalIdcard = ulegalIdcard;
//    }
//
//    public String getUlegalMobile() {
//        return ulegalMobile;
//    }
//
//    public void setUlegalMobile(String ulegalMobile) {
//        this.ulegalMobile = ulegalMobile;
//    }
//
//    public String getUtel() {
//        return utel;
//    }
//
//    public void setUtel(String utel) {
//        this.utel = utel;
//    }
//
//    public String getUdoorFacePic() {
//        return udoorFacePic;
//    }
//
//    public void setUdoorFacePic(String udoorFacePic) {
//        this.udoorFacePic = udoorFacePic;
//    }
//
//    public String getUidcardzpic() {
//        return uidcardzpic;
//    }
//
//    public void setUidcardzpic(String uidcardzpic) {
//        this.uidcardzpic = uidcardzpic;
//    }
//
//    public String getUidcardfpic() {
//        return uidcardfpic;
//    }
//
//    public void setUidcardfpic(String uidcardfpic) {
//        this.uidcardfpic = uidcardfpic;
//    }
//
//    public String getUbusinessLicense() {
//        return ubusinessLicense;
//    }
//
//    public void setUbusinessLicense(String ubusinessLicense) {
//        this.ubusinessLicense = ubusinessLicense;
//    }
//
//    public String getUbusinessLicensePic() {
//        return ubusinessLicensePic;
//    }
//
//    public void setUbusinessLicensePic(String ubusinessLicensePic) {
//        this.ubusinessLicensePic = ubusinessLicensePic;
//    }
//
//    public String getUindustryLicensePic() {
//        return uindustryLicensePic;
//    }
//
//    public void setUindustryLicensePic(String uindustryLicensePic) {
//        this.uindustryLicensePic = uindustryLicensePic;
//    }
//
//    public String getUrelevantCertificatesPic() {
//        return urelevantCertificatesPic;
//    }
//
//    public void setUrelevantCertificatesPic(String urelevantCertificatesPic) {
//        this.urelevantCertificatesPic = urelevantCertificatesPic;
//    }
//
//    public Long getUcreatedate() {
//        return ucreatedate;
//    }
//
//    public void setUcreatedate(Long ucreatedate) {
//        this.ucreatedate = ucreatedate;
//    }
//
//    public String getCreaterId() {
//        return createrId;
//    }
//
//    public void setCreaterId(String createrId) {
//        this.createrId = createrId;
//    }
//
//    public String getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(String createBy) {
//        this.createBy = createBy;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public String getUpdateBy() {
//        return updateBy;
//    }
//
//    public void setUpdateBy(String updateBy) {
//        this.updateBy = updateBy;
//    }
}
