package com.ruoyi.project.user.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.user.domain.XtDepartment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-11-12
 */
@Data
public class DeptVo {

    /** 部门(科室)ID */

    private String udepartmentid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;


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


    /** 子部门 */
    private List<DeptVo> children = new ArrayList<DeptVo>();
}
