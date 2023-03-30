package com.ruoyi.project.im.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 群 （科室搭建一个群，值班人在群，不值班人不在群了）对象 xt_group
 *
 * @author ruoyi
 * @date 2022-10-14
 */

@Data
public class XtGroup
{
    private static final long serialVersionUID = 1L;

    /** 群ID */
    private String ugroupid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /**
     * 商家类型
     */
    @Excel
    private String ucompType;

    /** 部门(科室)ID */
    @Excel(name = "部门(科室)ID")
    private String udepartmentid;

    /** 群名称 */
    @Excel(name = "群名称")
    private String ugroupname;

    /** 群描述 */
    @Excel(name = "群描述")
    private String udesc;

    /** 群头像 */
    @Excel(name = "群头像")
    private String ugroupavatar;

    /** 群主 */
    @Excel(name = "群主")
    private String ugroupmaster;

    /** 审核 */
    @Excel(name = "审核")
    private String ugroupcheck;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Date createTime;

    /** 删除标志 */
    private String delFlag;

    private Date updateTime;

    private String createBy;
    private String updateBy;
    private String remark;


}
