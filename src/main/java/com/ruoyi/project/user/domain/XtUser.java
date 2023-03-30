package com.ruoyi.project.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 所有用户登录（所有用户，医生、护士、患者、家属）对象 xt_user
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Data
public class XtUser
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private String userid;

    /** 登录名 */
    @Excel(name = "登录名")
    @NotBlank(message = "账号不能为空")
    private String uloginname;

    /** 密码 */
    @Excel(name = "密码")
    @NotBlank(message = "密码不能空")
    private String upassword;

    /** 昵称 */
    @Excel(name = "昵称")
    private String unickname;

    /** 姓名 */
    @Excel(name = "姓名")
    private String username;

    /** 出生日期（时间戳） */
    @Excel(name = "出生日期", readConverterExp = "时=间戳")
    private Long ubirthday;

    /** 性别 */
    @Excel(name = "性别")
    private String usex;

    /** 微信 */
    @Excel(name = "微信")
    private String uweixin;

    /** 登录次数 */
    @Excel(name = "登录次数")
    private Long ulogins;

    /** 密码输入错误次数 */
    @Excel(name = "密码输入错误次数")
    private Long upaserrnum;

    /** 密码输入错误时间 */
    @Excel(name = "密码输入错误时间")
    private Long upaserrtime;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uregdate;

    /** 最后一次成功登录时间 */
    @Excel(name = "最后一次成功登录时间")
    private Long ulastlogtime;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private String ueflag;


    private String status;

    private String delFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**头像*/

    private String avatar;

    private String phone;

    private String jiedanstatus;
}
