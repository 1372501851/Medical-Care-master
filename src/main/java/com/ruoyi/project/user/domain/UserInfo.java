package com.ruoyi.project.user.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 【请填写功能名称】对象 user_info
 *
 * @author ruoyi
 * @date 2022-11-25
 */
@Data
public class UserInfo
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 当前位置 */
    @Excel(name = "当前位置")
    private String currentPosition;

    /** 推送消息 */
    @Excel(name = "推送消息")
    private String pushMessage;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 年龄 */
    @Excel(name = "年龄")
    private String age;

    private String cityId;
    /** 所住城市 */
    @Excel(name = "所住城市")
    private String city;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailAddress;

    /** 社区 */
    @Excel(name = "社区")
    private String community;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String homeAddress;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 就诊医院 */
    @Excel(name = "就诊医院")
    private String hospital;

    /** 就诊科室 */
    @Excel(name = "就诊科室")
    private String dept;

    /** 就诊床位 */
    @Excel(name = "就诊床位")
    private String bed;


    /**用户身份*/
    private String userType;


    /**纬度*/
    private BigDecimal latitude;


    /**经度*/
    private BigDecimal longitude;



}
