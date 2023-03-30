package com.ruoyi.project.user.domain;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * 商家全局角色表
 */

@Data
public class XtAllRole
{
    /** 全局角色ID */
    private String uallroleid;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String urolename;

    /** 角色描述 */
    @Excel(name = "角色描述")
    private String uroledesc;

    /** 角色类型 */
    @Excel(name = "行业类型")
    private String ucompType;


}
