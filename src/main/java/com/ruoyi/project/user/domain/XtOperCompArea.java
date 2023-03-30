package com.ruoyi.project.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 代理商地区中间对象 xt_oper_comp_area
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public class XtOperCompArea
{

    /** 代理商id */
    private String uoperCompid;

    /** 地区id */
    private String uareaid;

    public void setUoperCompid(String uoperCompid) 
    {
        this.uoperCompid = uoperCompid;
    }

    public String getUoperCompid() 
    {
        return uoperCompid;
    }
    public void setUareaid(String uareaid) 
    {
        this.uareaid = uareaid;
    }

    public String getUareaid() 
    {
        return uareaid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uoperCompid", getUoperCompid())
            .append("uareaid", getUareaid())
            .toString();
    }
}
