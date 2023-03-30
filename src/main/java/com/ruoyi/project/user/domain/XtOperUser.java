package com.ruoyi.project.user.domain;

import com.ruoyi.common.xss.Xss;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * 系统管理员对象 xt_oper_user
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@Data
public class XtOperUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系统管理员ID */
    private String uoperUserid;

    /** 代理商ID */
    @Excel(name = "商户ID")
    private String uoperCompid;

    /** 管理员商户号 */
    @NotBlank(message = "管理员商户号不能为空")
    @Excel(name = "管理员商户号")
    private Long uoperCustomerid;

    /** 登录用户名 */
    @Xss(message = "用户账号不能包含脚本字符")
//    @NotBlank(message = "用户账号不能为空")
//    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String uloginname;

    /** 密码 */
    @Excel(name = "密码")
//    @NotBlank(message = "密码不能为空")
    private String upassword;

    /** 父级ID */
    @Excel(name = "父级ID")
    private String uparentid;

    /** 是否根用户 0不是，1是 */
    @Excel(name = "是否根用户 0不是，1是")
    private String ifroot;

    /** 姓名 */
    @Excel(name = "姓名")
    @NotBlank(message = "姓名不能为空")
    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户姓名长度不能超过30个字符")
    private String uname;

    /** logo url */
    @Excel(name = "logo url")

    private String ulogo;

    /** Email */
    @Excel(name = "Email")
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String uemail;

    /** 手机号码 */
    @Excel(name = "手机号码")
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    private String umobile;

    /** 代理地区ID */
    @Excel(name = "代理地区ID")
    private String ueareid;

    /** 角色ID */
    @Excel(name = "角色ID")
    private Long uroleid;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间")
    private Long ulogintime;

    /** 登录IP */
    @Excel(name = "登录IP")
    private String uloginip;

    /** 管理员类型 0平台管理员,1代理商,2监控机关 */
    @Excel(name = "管理员类型 0平台管理员,1代理商,2监控机关")
    private Long usertype;

    /** 登录次数 */
    @Excel(name = "登录次数")
    private Long ulogins;

    /** 注册时间 时间戳 */
    @Excel(name = "注册时间 时间戳")
    private Long ucreatedate;

    /** 入职单位 */
    @Excel(name = "入职单位")
    private String uentryUnit;

    /** 入职部门 */
    @Excel(name = "入职部门")
    private String ueuntryDepartment;

    /** 单位地址 */
    @Excel(name = "单位地址")
    private String uworkAddress;

    /** 办公地址 （办公场所）*/
    @Excel(name = "办公地址 （办公场所）")
    private String uofficeSpace;

    /** 申请职务 */
    @Excel(name = "申请职务")
    private String uapplyPost;

    /** 拥有的权限 */
    @Excel(name = "拥有的权限")
    private String uhavePermissions;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String ucardid;

    /** 出生日期（时间戳） */
    @Excel(name = "出生日期（时间戳）")
    private Long ubirthdate;

    /** 性别，1：男，0：女 */
    @Excel(name = "性别，1：男，0：女")
    private String  usex;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer uage;

    /** 居住的城市 */
    @Excel(name = "居住的城市")
    private String uliveingCity;

    /** 详细住址 */
    @Excel(name = "详细住址")
    private String udetailedAddresse;

    /** 居住社区 */
    @Excel(name = "居住社区")
    private String ulivingCommunity;

    /** 文化程度 */
    @Excel(name = "文化程度")
    private String ukultur;

    /** 职业 */
    @Excel(name = "职业")
    private String uoccupation;

    /** 职务 */
    @Excel(name = "职务")
    private String upost;

    /** 职称 */
    @Excel(name = "职称")
    private String utitle;

    /** 亲情号码 */
    @Excel(name = "亲情号码")
    private String ukinshipNumber;

    /** 办公场所电话 */
    @Excel(name = "办公场所电话")
    private String utel;

    /** 头像 */
    @Excel(name = "头像")
    private String uheadPic;

    /** 身份证正面 */
    @Excel(name = "身份证正面")
    private String uidcardzPic;

    /** 身份证方面 */
    @Excel(name = "身份证方面")
    private String uidcardfPic;

    /** 学历证书照片 */
    @Excel(name = "学历证书照片")
    private String ueducationPic;

    /** 职称证书照片 */
    @Excel(name = "职称证书照片")
    private String utitlesPic;

    /** 职业许可证照片 */
    @Excel(name = "职业许可证照片")
    private String upracticingPic;

    /** 其他相关证书照片 */
    @Excel(name = "其他相关证书照片")
    private String uotherCertificatesPic;

    /** 政治面貌（0普通民众，1团员，2党员） */
    @Excel(name = "政治面貌（0普通民众，1团员，2党员）")
    private String uploity;

    /** 行业协会学会 */
    @Excel(name = "行业协会学会")
    private String undustryAssociation;

    /** 备注1 */
    @Excel(name = "备注1")
    private String uspareOne;

    /** 备注2 */
    @Excel(name = "备注2")
    private String uspareTwo;

    /**
     * 审核时间
     */
    @Excel(name = "审核时间")
    private long approvalTime;

    /**
     * 状态位 0：审核中(默认)  2：审核通过 3：审核未通过
     */
    private String status;


    /**
     * 删除标志位 0:在职 1:停职
     */
    private String delFlag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XtOperUser that = (XtOperUser) o;
        return approvalTime == that.approvalTime && Objects.equals(uoperUserid, that.uoperUserid) && Objects.equals(uoperCompid, that.uoperCompid) && Objects.equals(uoperCustomerid, that.uoperCustomerid) && Objects.equals(uloginname, that.uloginname) && Objects.equals(upassword, that.upassword) && Objects.equals(uparentid, that.uparentid) && Objects.equals(ifroot, that.ifroot) && Objects.equals(uname, that.uname) && Objects.equals(ulogo, that.ulogo) && Objects.equals(uemail, that.uemail) && Objects.equals(umobile, that.umobile) && Objects.equals(ueareid, that.ueareid) && Objects.equals(uroleid, that.uroleid) && Objects.equals(ulogintime, that.ulogintime) && Objects.equals(uloginip, that.uloginip) && Objects.equals(usertype, that.usertype) && Objects.equals(ulogins, that.ulogins) && Objects.equals(ucreatedate, that.ucreatedate) && Objects.equals(uentryUnit, that.uentryUnit) && Objects.equals(ueuntryDepartment, that.ueuntryDepartment) && Objects.equals(uworkAddress, that.uworkAddress) && Objects.equals(uofficeSpace, that.uofficeSpace) && Objects.equals(uapplyPost, that.uapplyPost) && Objects.equals(uhavePermissions, that.uhavePermissions) && Objects.equals(ucardid, that.ucardid) && Objects.equals(ubirthdate, that.ubirthdate) && Objects.equals(usex, that.usex) && Objects.equals(uage, that.uage) && Objects.equals(uliveingCity, that.uliveingCity) && Objects.equals(udetailedAddresse, that.udetailedAddresse) && Objects.equals(ulivingCommunity, that.ulivingCommunity) && Objects.equals(ukultur, that.ukultur) && Objects.equals(uoccupation, that.uoccupation) && Objects.equals(upost, that.upost) && Objects.equals(utitle, that.utitle) && Objects.equals(ukinshipNumber, that.ukinshipNumber) && Objects.equals(utel, that.utel) && Objects.equals(uheadPic, that.uheadPic) && Objects.equals(uidcardzPic, that.uidcardzPic) && Objects.equals(uidcardfPic, that.uidcardfPic) && Objects.equals(ueducationPic, that.ueducationPic) && Objects.equals(utitlesPic, that.utitlesPic) && Objects.equals(upracticingPic, that.upracticingPic) && Objects.equals(uotherCertificatesPic, that.uotherCertificatesPic) && Objects.equals(uploity, that.uploity) && Objects.equals(undustryAssociation, that.undustryAssociation) && Objects.equals(uspareOne, that.uspareOne) && Objects.equals(uspareTwo, that.uspareTwo) && Objects.equals(status, that.status) && Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uoperUserid, uoperCompid, uoperCustomerid, uloginname, upassword, uparentid, ifroot, uname, ulogo, uemail, umobile, ueareid, uroleid, ulogintime, uloginip, usertype, ulogins, ucreatedate, uentryUnit, ueuntryDepartment, uworkAddress, uofficeSpace, uapplyPost, uhavePermissions, ucardid, ubirthdate, usex, uage, uliveingCity, udetailedAddresse, ulivingCommunity, ukultur, uoccupation, upost, utitle, ukinshipNumber, utel, uheadPic, uidcardzPic, uidcardfPic, ueducationPic, utitlesPic, upracticingPic, uotherCertificatesPic, uploity, undustryAssociation, uspareOne, uspareTwo, approvalTime, status, delFlag);
    }

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
//    public void setUoperUserid(String uoperUserid)
//    {
//        this.uoperUserid = uoperUserid;
//    }
//
//    public String getUoperUserid()
//    {
//        return uoperUserid;
//    }
//    public void setUoperCompid(String uoperCompid)
//    {
//        this.uoperCompid = uoperCompid;
//    }
//
//    public String getUoperCompid()
//    {
//        return uoperCompid;
//    }
//    public void setUoperCustomerid(Long uoperCustomerid)
//    {
//        this.uoperCustomerid = uoperCustomerid;
//    }
//
//    public Long getUoperCustomerid()
//    {
//        return uoperCustomerid;
//    }
//    public void setUloginname(String uloginname)
//    {
//        this.uloginname = uloginname;
//    }
//
//    public String getUloginname()
//    {
//        return uloginname;
//    }
//    public void setUpassword(String upassword)
//    {
//        this.upassword = upassword;
//    }
//
//    public String getUpassword()
//    {
//        return upassword;
//    }
//    public void setUparentid(String uparentid)
//    {
//        this.uparentid = uparentid;
//    }
//
//    public String getUparentid()
//    {
//        return uparentid;
//    }
//    public void setIfroot(String ifroot)
//    {
//        this.ifroot = ifroot;
//    }
//
//    public String getIfroot()
//    {
//        return ifroot;
//    }
//    public void setUname(String uname)
//    {
//        this.uname = uname;
//    }
//
//    public String getUname()
//    {
//        return uname;
//    }
//    public void setUlogo(String ulogo)
//    {
//        this.ulogo = ulogo;
//    }
//
//    public String getUlogo()
//    {
//        return ulogo;
//    }
//    public void setUemail(String uemail)
//    {
//        this.uemail = uemail;
//    }
//
//    public String getUemail()
//    {
//        return uemail;
//    }
//    public void setUmobile(String umobile)
//    {
//        this.umobile = umobile;
//    }
//
//    public String getUmobile()
//    {
//        return umobile;
//    }
//    public void setUeareid(String ueareid)
//    {
//        this.ueareid = ueareid;
//    }
//
//    public String getUeareid()
//    {
//        return ueareid;
//    }
//    public void setUroleid(Long uroleid)
//    {
//        this.uroleid = uroleid;
//    }
//
//    public Long getUroleid()
//    {
//        return uroleid;
//    }
//    public void setUlogintime(Long ulogintime)
//    {
//        this.ulogintime = ulogintime;
//    }
//
//    public Long getUlogintime()
//    {
//        return ulogintime;
//    }
//    public void setUloginip(String uloginip)
//    {
//        this.uloginip = uloginip;
//    }
//
//    public String getUloginip()
//    {
//        return uloginip;
//    }
//
//    public void setUsertype(Long usertype)
//    {
//        this.usertype = usertype;
//    }
//
//    public Long getUsertype()
//    {
//        return usertype;
//    }
//
//    public void setUlogins(Long ulogins)
//    {
//        this.ulogins = ulogins;
//    }
//
//    public Long getUlogins()
//    {
//        return ulogins;
//    }
//    public void setUcreatedate(Long ucreatedate)
//    {
//        this.ucreatedate = ucreatedate;
//    }
//
//    public Long getUcreatedate()
//    {
//        return ucreatedate;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("uoperUserid", getUoperUserid())
//            .append("uoperCompid", getUoperCompid())
//            .append("uoperCustomerid", getUoperCustomerid())
//            .append("uloginname", getUloginname())
//            .append("upassword", getUpassword())
//            .append("uparentid", getUparentid())
//            .append("ifroot", getIfroot())
//            .append("uname", getUname())
//            .append("ulogo", getUlogo())
//            .append("uemail", getUemail())
//            .append("umobile", getUmobile())
//            .append("ueareid", getUeareid())
//            .append("uroleid", getUroleid())
//            .append("ulogintime", getUlogintime())
//            .append("uloginip", getUloginip())
//            .append("usertype", getUsertype())
//            .append("ulogins", getUlogins())
//            .append("ucreatedate", getUcreatedate())
//            .toString();
//    }
}
