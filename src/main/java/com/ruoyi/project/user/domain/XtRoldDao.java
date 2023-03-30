package com.ruoyi.project.user.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2023-01-04
 */

@Data
public class XtRoldDao {
    private String uroleid;


    private String ucompid;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String urolename;

    /** 角色类型 */
    @Excel(name = "角色类型")
    private String uroletype;
}
