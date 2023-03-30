package com.ruoyi.project.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 【请填写功能名称】对象 family_doctor
 *
 * @author ruoyi
 * @date 2022-11-28
 */
@Data
public class FamilyDoctor
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 医生手机号 */
    @Excel(name = "医生手机号")
    private String doctorPhone;

    /** 医生id */
    @Excel(name = "医生id")
    private String doctorId;

    /** 信息id */
    @Excel(name = "信息id")
    private String infoId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String status;

    private String ucompid;

}
