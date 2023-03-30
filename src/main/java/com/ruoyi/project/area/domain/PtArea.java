package com.ruoyi.project.area.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 地区对象 pt_area
 * 
 * @author ruoyi
 * @date 2023-01-11
 */
public class PtArea
{

    /** ID */
    private String uareaid;

    /** 父级层ID */
    @Excel(name = "父级层ID")
    private String uparentid;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String uareaname;

    /** 级层 */
    @Excel(name = "级层")
    private Integer ulevel;

    /** 首字母 */
    @Excel(name = "首字母")
    private String ucode;

    /** 国家语言 */
    @Excel(name = "国家语言")
    private String ulanguage;

    public void setUareaid(String uareaid) 
    {
        this.uareaid = uareaid;
    }

    public String getUareaid() 
    {
        return uareaid;
    }
    public void setUparentid(String uparentid) 
    {
        this.uparentid = uparentid;
    }

    public String getUparentid() 
    {
        return uparentid;
    }
    public void setUareaname(String uareaname) 
    {
        this.uareaname = uareaname;
    }

    public String getUareaname() 
    {
        return uareaname;
    }
    public void setUlevel(Integer ulevel) 
    {
        this.ulevel = ulevel;
    }

    public Integer getUlevel() 
    {
        return ulevel;
    }
    public void setUcode(String ucode) 
    {
        this.ucode = ucode;
    }

    public String getUcode() 
    {
        return ucode;
    }
    public void setUlanguage(String ulanguage) 
    {
        this.ulanguage = ulanguage;
    }

    public String getUlanguage() 
    {
        return ulanguage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uareaid", getUareaid())
            .append("uparentid", getUparentid())
            .append("uareaname", getUareaname())
            .append("ulevel", getUlevel())
            .append("ucode", getUcode())
            .append("ulanguage", getUlanguage())
            .toString();
    }
}
