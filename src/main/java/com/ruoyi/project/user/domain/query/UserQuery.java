package com.ruoyi.project.user.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author by hujun
 * @date 2022-11-17
 */
@Data
public class UserQuery {
    /** 用户ID */
    private String userid;

    /** 登录名 */
    @Excel(name = "登录名")
    @NotBlank(message = "账号不能为空")
    private String uloginname;

    /** 昵称 */
    @Excel(name = "昵称")
    private String unickname;

    /** 姓名 */
    @Excel(name = "姓名")
    private String username;
    /** 是否有效 */
    @Excel(name = "是否有效")
    private String ueflag;


    private String status;

    private String delFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uregdate;

    /**用户类型:0普通用户;1商家用户*/
    @NotBlank(message = "用户类型不能为空")
    private String userType;

}
