package com.ruoyi.project.user.domain;
import lombok.Data;

import java.util.List;

/**
 * 全局角色与菜单关系表
 * @author 呵呵哒
 */

@Data
public class XtAllRoleToMenus
{
    /** 全局角色id

     */
    private Integer uallroleid;

    /** 菜单ID */
    private List<Integer> menuId;
}
