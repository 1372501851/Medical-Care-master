package com.ruoyi.project.common.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户与排班对象 xt_usertopaiban
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtUsertopaiban extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排班ID */
    @Excel(name = "排班ID")
    private String upaibanid;

    /** 用户信息ID */
    @Excel(name = "用户信息ID")
    private String uemployeeid;

    /** 状态 1正，0请假 */
    @Excel(name = "状态 1正，0请假")
    private String ustatus;

    public void setUpaibanid(String upaibanid)
    {
        this.upaibanid = upaibanid;
    }

    public String getUpaibanid()
    {
        return upaibanid;
    }
    public void setUemployeeid(String uemployeeid)
    {
        this.uemployeeid = uemployeeid;
    }

    public String getUemployeeid()
    {
        return uemployeeid;
    }
    public void setUstatus(String ustatus)
    {
        this.ustatus = ustatus;
    }

    public String getUstatus()
    {
        return ustatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("upaibanid", getUpaibanid())
            .append("uemployeeid", getUemployeeid())
            .append("ustatus", getUstatus())
            .toString();
    }
}
