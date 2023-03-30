package com.ruoyi.project.common.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商家（医院、药店）角色对象 xt_role
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private String uroleid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ucompid;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String urolename;

    /** 角色类型 */
    @Excel(name = "角色类型")
    private String uroletype;

    public void setUroleid(String uroleid)
    {
        this.uroleid = uroleid;
    }

    public String getUroleid()
    {
        return uroleid;
    }
    public void setUcompid(String ucompid)
    {
        this.ucompid = ucompid;
    }

    public String getUcompid()
    {
        return ucompid;
    }
    public void setUrolename(String urolename)
    {
        this.urolename = urolename;
    }

    public String getUrolename()
    {
        return urolename;
    }
    public void setUroletype(String uroletype)
    {
        this.uroletype = uroletype;
    }

    public String getUroletype()
    {
        return uroletype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uroleid", getUroleid())
            .append("ucompid", getUcompid())
            .append("urolename", getUrolename())
            .append("uroletype", getUroletype())
            .toString();
    }
}
