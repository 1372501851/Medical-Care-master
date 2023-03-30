package com.ruoyi.project.im.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 用户对应群对象 xt_user_to_group
 *
 * @author ruoyi
 * @date 2022-10-14
 */

@Data
public class XtUserToGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String usergroupid;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userid;

    /** 群ID */
    @Excel(name = "群ID")
    private String ugroupid;

    /** 状态（0未审核，1通过，2剔除） */
    @Excel(name = "状态", readConverterExp = "0=未审核，1通过，2剔除")
    private String ustatus;

    /** 申请信息 */
    @Excel(name = "申请信息")
    private String message;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private Date creatTime;

    /** 删除标志:0:存在;1:删除 */
    private String delFlag;


}
