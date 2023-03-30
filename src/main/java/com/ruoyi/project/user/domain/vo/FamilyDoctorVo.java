package com.ruoyi.project.user.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2022-11-28
 */
@Data
public class FamilyDoctorVo {
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
    @JsonFormat(pattern = "yyyy.MM.dd ")
    private Date createTime;


    private String avatar;
    /**监护人姓名*/
    private String doctorName;

    private String status;

    private String statusName;
}
