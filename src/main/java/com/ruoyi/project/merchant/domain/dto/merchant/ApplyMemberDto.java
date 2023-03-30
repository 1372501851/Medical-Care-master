package com.ruoyi.project.merchant.domain.dto.merchant;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-11-09
 */
@Data
public class ApplyMemberDto {
    /** 用户ID */
    @Excel(name = "用户ID")
    @NotBlank(message = "用户Id不能为空")
    private String userid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /** 部门(科室)ID */
    @Excel(name = "部门(科室)ID")
    private String udepartmentid;

    /** 姓名 */
    @Excel(name = "姓名")
    private String uname;

    /** 证件类型（0身份证、1警官证、2护照） */
    @Excel(name = "证件类型", readConverterExp = "0=身份证、1警官证、2护照")
    private String ucardtype;

    /** 证件号码 */
    @Excel(name = "证件号码")
    @NotBlank(message = "证件号不能为空")
    @NotNull(message = "证件号不能为空")
    private String ucardid;

    /** 性别 */
    @Excel(name = "性别")
    private String usex;

    /** 民族 */
    @Excel(name = "民族")
    private String unation;

    /** 出生年月（时间戳） */
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

    /** 手机 */
    @Excel(name = "手机")
    private String umobile;

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

    /** 学历 */
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


    /** 用户类型 普通用户，医生，护士，管理员 */
    @Excel(name = "用户类型 普通用户，医生，护士，管理员")
    private String usertype;

}
