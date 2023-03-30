package com.ruoyi.project.user.domain;
import lombok.Data;


/**
 * 全局角色与菜单关系表
 */

@Data
public class XtAllRoleToMenu
{

    /** 全局角色id

     */
    private Integer uallroleid;

    /** 菜单ID */
    private Integer menuId;
}
