package com.ruoyi.project.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 部门(科室)信息对象 xt_department
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtDepartment
{
    private static final long serialVersionUID = 1L;

    /** 部门(科室)ID */

    private String udepartmentid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    @Excel(name = "商家类型")
    private String ucompType;


    /** 层级 */
    @Excel(name = "层级")
    private String ulevel;

    /** 部门(科室)名称 */
    @Excel(name = "部门(科室)名称")
    private String udepartmentname;

    /** 类型 */
    @Excel(name = "类型")
    private String uoptype;

    /** 部门(科室)描述 */
    @Excel(name = "部门(科室)描述")
    private String udesc;

    /** 父部门ID */
    private String parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;


    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
