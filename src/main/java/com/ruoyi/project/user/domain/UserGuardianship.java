package com.ruoyi.project.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 【请填写功能名称】对象 user_guardianship
 *
 * @author ruoyi
 * @date 2022-11-26
 */
@Data
public class UserGuardianship
{
    private static final long serialVersionUID = 1L;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String ysorjhrorpb;


}
