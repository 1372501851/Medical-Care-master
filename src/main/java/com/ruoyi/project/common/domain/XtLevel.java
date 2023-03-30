package com.ruoyi.project.common.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 等级对象 xt_level
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 等级ID */
    private String ulevelid;

    /** 等级数 */
    @Excel(name = "等级数")
    private Long ulevelNum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ulevelDesc;

    public void setUlevelid(String ulevelid)
    {
        this.ulevelid = ulevelid;
    }

    public String getUlevelid()
    {
        return ulevelid;
    }
    public void setUlevelNum(Long ulevelNum)
    {
        this.ulevelNum = ulevelNum;
    }

    public Long getUlevelNum()
    {
        return ulevelNum;
    }
    public void setUlevelDesc(String ulevelDesc)
    {
        this.ulevelDesc = ulevelDesc;
    }

    public String getUlevelDesc()
    {
        return ulevelDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ulevelid", getUlevelid())
            .append("ulevelNum", getUlevelNum())
            .append("ulevelDesc", getUlevelDesc())
            .toString();
    }
}
