package com.ruoyi.project.merchant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 商家（医院、药店）对象 xt_comp
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtComp
{
    private static final long serialVersionUID = 1L;

    /** 商家ID */
    private String ucompid;

    /**
     * 用户id
     */
    private String userid;

    /** 商家名称 （药店名称）*/
    @Excel(name = "商家名称")
    private String ucompname;

    /** 父级ID */
    @Excel(name = "父级ID")
    private String uparentid;

    /**
     * 分公司名称
     */
    private String branchCompName;

    /** 企业经营模式:   独立/加盟/连锁 */
    @Excel(name = "企业经营模式:   0:独立/1:加盟/2:连锁")
    private String umanageModel;

    /**
     * 连锁机构名称
     */
    private String chainOrganizationName;

    /** 公司类型，医院、药店、沙发家居家具... */
    @Excel(name = "公司类型，医院、药店、沙发家居家具...")
    private String ucompType;


    /** 所在城市（可以到街道办） */
    @Excel(name = "所在城市", readConverterExp = "可=以到街道办")
    private String uareaid;//

    /** 所在社区 */
    @Excel(name = "所在社区")
    private String ucommunity;

    /** 公司地址 （单位地址）*/
    @Excel(name = "公司地址")
    private String uaddress;

    /**地址详情*/
    private String addressDetail;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal ulatitude;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal ulongitude;

    /** 法人 （法人代表姓名）*/
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

    /** 申请人学历证书照片 */
    @Excel(name = "申请人学历证书照片")
    private String udiplomaPic;

    /** 申请人职称证书照片 */
    @Excel(name = "申请人职称证书照片")
    private String uprofessionalTitlePic;

    /** 申请人执业许可证照片 */
    @Excel(name = "申请人执业许可证照片")
    private String upracticeLicensePic;

    /** 申请人其他相关证件照片 */
    @Excel(name = "申请人其他相关证件照片")
    private String uotherCertificatesPic;

    /** 创建时间(时间戳) 下面已经有一个注册时间了，这个有点多余，但还是给你留着吧*/
    @Excel(name = "创建时间(时间戳)")
    private Long ucreatedate;

    /**
     * 注册审核状态 0：注册审核中，1：注册审核通过，2：注册审核不通过
     */
    private String applyStatus;

    /**
     * 审核时间
     */
    private long uapprovalTime;

    /**
     * 注册时间（创建时间，加入时间）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    /**
     * 信息修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    /**
     * 申请人信息id（这个是员工id，应该用用户ID关联，在上面，userid）
     */
    private String employId;//给你放在这里吧，应该是关联用户表，而不是关联员工表的

    /**评分*/
    private Integer score;

    /**
     * 食品卫生许可证照片
     */
    private String foodHygieneLicensePic;

    /**
     * 行业协会学会
     */
    private String industryAssociation;

    /**
     * 备用1
     */
    private String spareOne;

    /**
     * 备用2
     */
    private String spareTwo;

    /**
     * 备用3
     */
    private String spareThree;

    /**
     * 办公场所
     */
    @ApiModelProperty("办公场所")
    private String officeSpace;

    /**
     * 删除标注位 0：正常状态（正常运营）   2：异常状态（停业整顿）
     */
    private char delFlag;

    /** 商户号（一个商家一个商户号，分部都用相同的商户号，一个10位的递增数，比如：1000000001） */
    @Excel(name = "商户号", readConverterExp = "一=个商家一个商户号，分部都用相同的商户号，一个10位的递增数，比如：1000000001")
    private Long ucustomerid;

    /**
     * 成立时间
     */
    private String setUpTime;

    /**
     * 注册资本
     */
    private String registerAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XtComp xtComp = (XtComp) o;
        return uapprovalTime == xtComp.uapprovalTime && delFlag == xtComp.delFlag && Objects.equals(ucompid, xtComp.ucompid) && Objects.equals(userid, xtComp.userid) && Objects.equals(ucompname, xtComp.ucompname) && Objects.equals(uparentid, xtComp.uparentid) && Objects.equals(branchCompName, xtComp.branchCompName) && Objects.equals(umanageModel, xtComp.umanageModel) && Objects.equals(chainOrganizationName, xtComp.chainOrganizationName) && Objects.equals(ucompType, xtComp.ucompType) && Objects.equals(uareaid, xtComp.uareaid) && Objects.equals(ucommunity, xtComp.ucommunity) && Objects.equals(uaddress, xtComp.uaddress) && Objects.equals(addressDetail, xtComp.addressDetail) && Objects.equals(ulatitude, xtComp.ulatitude) && Objects.equals(ulongitude, xtComp.ulongitude) && Objects.equals(ulegalPerson, xtComp.ulegalPerson) && Objects.equals(ulegalIdcard, xtComp.ulegalIdcard) && Objects.equals(ulegalMobile, xtComp.ulegalMobile) && Objects.equals(utel, xtComp.utel) && Objects.equals(udoorFacePic, xtComp.udoorFacePic) && Objects.equals(uidcardzpic, xtComp.uidcardzpic) && Objects.equals(uidcardfpic, xtComp.uidcardfpic) && Objects.equals(ubusinessLicense, xtComp.ubusinessLicense) && Objects.equals(ubusinessLicensePic, xtComp.ubusinessLicensePic) && Objects.equals(uindustryLicensePic, xtComp.uindustryLicensePic) && Objects.equals(upharmacistLicensePic, xtComp.upharmacistLicensePic) && Objects.equals(umedicalLicensePic, xtComp.umedicalLicensePic) && Objects.equals(ufireSafetyPic, xtComp.ufireSafetyPic) && Objects.equals(urelevantCertificatesPic, xtComp.urelevantCertificatesPic) && Objects.equals(udiplomaPic, xtComp.udiplomaPic) && Objects.equals(uprofessionalTitlePic, xtComp.uprofessionalTitlePic) && Objects.equals(upracticeLicensePic, xtComp.upracticeLicensePic) && Objects.equals(uotherCertificatesPic, xtComp.uotherCertificatesPic) && Objects.equals(ucreatedate, xtComp.ucreatedate) && Objects.equals(applyStatus, xtComp.applyStatus) && Objects.equals(createTime, xtComp.createTime) && Objects.equals(updateTime, xtComp.updateTime) && Objects.equals(employId, xtComp.employId) && Objects.equals(score, xtComp.score) && Objects.equals(foodHygieneLicensePic, xtComp.foodHygieneLicensePic) && Objects.equals(industryAssociation, xtComp.industryAssociation) && Objects.equals(spareOne, xtComp.spareOne) && Objects.equals(spareTwo, xtComp.spareTwo) && Objects.equals(spareThree, xtComp.spareThree) && Objects.equals(officeSpace, xtComp.officeSpace) && Objects.equals(ucustomerid, xtComp.ucustomerid) && Objects.equals(setUpTime, xtComp.setUpTime) && Objects.equals(registerAmount, xtComp.registerAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ucompid, userid, ucompname, uparentid, branchCompName, umanageModel, chainOrganizationName, ucompType, uareaid, ucommunity, uaddress, addressDetail, ulatitude, ulongitude, ulegalPerson, ulegalIdcard, ulegalMobile, utel, udoorFacePic, uidcardzpic, uidcardfpic, ubusinessLicense, ubusinessLicensePic, uindustryLicensePic, upharmacistLicensePic, umedicalLicensePic, ufireSafetyPic, urelevantCertificatesPic, udiplomaPic, uprofessionalTitlePic, upracticeLicensePic, uotherCertificatesPic, ucreatedate, applyStatus, uapprovalTime, createTime, updateTime, employId, score, foodHygieneLicensePic, industryAssociation, spareOne, spareTwo, spareThree, officeSpace, delFlag, ucustomerid, setUpTime, registerAmount);
    }
}
