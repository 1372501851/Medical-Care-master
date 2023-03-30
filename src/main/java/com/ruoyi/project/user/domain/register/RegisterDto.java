package com.ruoyi.project.user.domain.register;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author by hujun
 * @date 2022-11-09
 */
@Data
public class RegisterDto {

    /** 登录名 */
    @NotBlank(message = "姓名不能为空")
    private String uloginname;

    /** 密码 */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8,message = "密码不能少于8位")
    @Size(max = 40,message = "密码过长,请重新输入.")
    private String upassword;

    /** 昵称 */
    private String unickname;

    /** 姓名 */
    @NotBlank(message = "姓名不能为空")
    @Size(min = 1,max = 64,message = "姓名过长,请重新输入.")
    private String username;

    /** 出生日期（时间戳） */
    private Long ubirthday;

    /** 性别 */
    private String usex;

    /** 微信 */
    private String uweixin;
}
