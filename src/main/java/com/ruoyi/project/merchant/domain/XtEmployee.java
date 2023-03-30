package com.ruoyi.project.merchant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 用户信息对象 xt_employee
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtEmployee
{
    private static final long serialVersionUID = 1L;

    /** 用户信息ID */
    private String uemployeeid;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /** 部门(科室)ID 入职部门*/
    @Excel(name = "部门(科室)ID")
    private String udepartmentid;

    /** 姓名 申请人 */
    @Excel(name = "姓名")
    private String uname;

    /** 证件类型（0身份证、1警官证、2护照） */
    @Excel(name = "证件类型", readConverterExp = "0=身份证、1警官证、2护照")
    private String ucardtype;

    /** 证件号码 */
    @Excel(name = "证件号码 身份证号")
    private String ucardid;

    /** 性别 */
    @Excel(name = "性别")
    private String usex;

    /** 民族 */
    @Excel(name = "民族")
    private String unation;

    /** 出生年月（时间戳）出生日期 */
    @Excel(name = "出生年月", readConverterExp = "时=间戳")
    private Long ubirthdate;

    /** 身高 */
    @Excel(name = "身高")
    private BigDecimal uheight;

    /** 政治面貌（0普通民众，1团员，2党员） */
    @Excel(name = "政治面貌", readConverterExp = "0=普通民众，1团员，2党员")
    private String uploity;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String unative;

    /** 户籍所在地 */
    @Excel(name = "户籍所在地")
    private String uresidence;

    /** 通讯地址 */
    @Excel(name = "通讯地址")
    private String uaddress;

    /** 申请人手机号码 */
    @Excel(name = "手机号码")
    private String umobile;

    /** 申请人亲情号码 */
    @Excel(name = "申请人亲情号码")
    private String ukinnumber;

    /** 紧急联系电话 */
    @Excel(name = "紧急联系电话")
    private String uemertel;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String umarriage;

    /** 配偶所在地户口 */
    @Excel(name = "配偶所在地户口")
    private String uspresidence;

    /** 配偶姓名及工作单位 */
    @Excel(name = "配偶姓名及工作单位")
    private String uspunit;



    /** 参加工作时间 */
    @Excel(name = "参加工作时间")
    private String uworkdate;

    /** 学历 （文化程度）*/
    @Excel(name = "学历")
    private String ukultur;

    /** 学位 */
    @Excel(name = "学位")
    private String udegree;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String uacademy;

    /** 毕业时间 */
    @Excel(name = "毕业时间")
    private String ugraduate;

    /** 所学专业 */
    @Excel(name = "所学专业")
    private String uspecialty;

    /** 外语水平 */
    @Excel(name = "外语水平")
    private String ufllevel;

    /** 计算机水平 */
    @Excel(name = "计算机水平")
    private String ucomlevel;

    /** 职称 */
    @Excel(name = "职称")
    private String utechp;

    /** 爱好及特长 */
    @Excel(name = "爱好及特长")
    private String uspecialties;

    /** 头像照片 */
    @Excel(name = "头像照片")
    private String uheadPic;

    /** 身份证正 */
    @Excel(name = "身份证正")
    private String uidcardzPic;

    /** 身份证反 */
    @Excel(name = "身份证反")
    private String uidcardfPic;

    /** 学历证书照片 */
    @Excel(name = "学历证书照片")
    private String ueducationPic;

    /** 职称证书照片 */
    @Excel(name = "职称证书照片")
    private String utitlesPic;

    /** 执业许可证照片 */
    @Excel(name = "执业许可证照片")
    private String upracticingPic;

    /** 创建时间(时间戳) */
    @Excel(name = "创建时间(时间戳)")
    private Long ucreatedate;

    /** 用户类型 普通用户，医生，护士，管理员 */
    @Excel(name = "用户类型 普通用户，医生，护士，管理员")
    private String usertype;


    /** 角色ID */
    @Excel(name = "角色ID")
    private Long uroleid;

    /**
     * 员工注册审核状态
     */
    private String applyStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    /** 员工状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /**行政职称*/
    private String administrative;
    /**
     * 办公场所
     */
    private String uofficeSpace;

    /** 入职单位 */
    @Excel(name = "入职单位")
    private String company;

    /**
     * 居住城市，所住城市
     */
    private String ucity;

    /**
     * 年龄
     */
    private Integer uage;

    /**
     * 详细住址
     */
    private String udetailedAddress;

    /**
     * 职业，干什么活
     */
    private String uoccupation;

    /**
     * 职务，行政职权
     */
    private String upost;

    /**
     * 职称，业务等级
     */
    private String autitele;

    /**
     * 其他相关证书照片
     */
    private String uotherrelevantpic;

    /**
     * 备用1
     */
    private String uspareOne;

    /**
     * 备用2
     */
    private String uspareTwo;

    /**
     * 备用3
     */
    private String uspareThree;

    /**
     * 员工的领导
     */
    private String uleader;

    /**
     * 单位地址
     */
    private String uworkaddress;

    /**
     * 所住社区
     */
    private String residentialcommunity;

    /**
     * 申请职务
     */
    private String applyforjob;

    /**
     * 办公场所电话
     */
    private String utel;

    /**
     * 行业协会学会
     */
    private String industryAssociation;

    /**
     * 离职原因
     */
    private String reasonOfQuit;

    /**
     * 好评数量（默认0）
     */
    private Integer haopingnum;

    /**
     * 差评数量（默认0）
     */
    private Integer chapingnum;

    /**
     * 一般评数量（默认0）
     */
    private Integer yibannum;

    /**
     * 居住社区
     */
    private String juzhushequ;

    /**
     * 权限区域
     */
    private String quanxianquyu;

    private String farendaibiao;

    private String zhuguanlingdao;

    @Override
    public boolean equals(Object o) {
        XtEmployee x = (XtEmployee) o;
        if(this.getUemployeeid().equals(x.getUemployeeid())){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 5;
    }
}







//        uemployeeid
//    userid
//            ucompid
//    ucompType
//            udepartmentid
//    uname
//            ucardtype
//    ucardid
//            usex
//    unation
//            ubirthdate
//    uheight
//            uploity
//    unative
//            uresidence
//    uaddress
//            umobile
//    ukinnumber
//            uemertel
//    umarriage
//            uspresidence
//    uspunit
//            uworkdate
//    ukultur
//            udegree
//    uacademy
//            ugraduate
//    uspecialty
//            ufllevel
//    ucomlevel
//            utechp
//    uspecialties
//            uheadPic
//    uidcardzPic
//            uidcardfPic
//    ueducationPic
//            utitlesPic
//    upracticingPic
//            ucreatedate
//    usertype
//            uroleid
//    applyStatus
//            createTime
//    updateTime
//            status
//    delFlag
//            administrative
//    uofficeSpace
//            company
//    ucity
//            uage
//    udetailedAddress
//            uoccupation
//    upost
//            autitele
//    uotherrelevantpic
//            uspareOne
//    uspareTwo
//            uspareThree
//    uleader
//            uworkaddress
//    residentialcommunity
//            applyforjob
//    utel
//            industryAssociation
//    reasonOfQuit
//            haopingnum
//    chapingnum
//            yibannum
//    juzhushequ
//            quanxianquyu
