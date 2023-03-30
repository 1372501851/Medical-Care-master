package com.ruoyi.project.user.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2022-11-26
 */
@Data
public class UserGuardianshipVo {

    /** id */
    private String id;
    /** 用户id */
    @Excel(name = "用户id")
    private String userId;
    /** 监护人手机号 */
    @Excel(name = "监护人手机号")
    private String guardianshipPhone;
    /** 监护人Id */
    @Excel(name = "监护人Id")
    private String guardianshipId;
    /** 监护关系 */
    @Excel(name = "监护关系")
    private String relationship;
    /** 监护者信息Id */
    @Excel(name = "监护者信息Id")
    private String infoId;
    /**添加时间*/
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date createTime;
    private String avatar;//头像
    /**监护人姓名*/
    private String guardianshipName;

    private String ysorjhrorpb;

}
