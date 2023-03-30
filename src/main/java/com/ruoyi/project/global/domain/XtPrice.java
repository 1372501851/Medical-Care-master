package com.ruoyi.project.global.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 价格（全局）针对护工对象 xt_price
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtPrice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 价格ID */
    private String upriceid;

    /** 计费单位（0分钟，1小时，2天，3月） */
    @Excel(name = "计费单位", readConverterExp = "0=分钟，1小时，2天，3月")
    private String ucostType;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal uprice;

    /** 场景，护工，护理，挂号等场景 */
    @Excel(name = "场景，护工，护理，挂号等场景")
    private String uscene;

    public void setUpriceid(String upriceid)
    {
        this.upriceid = upriceid;
    }

    public String getUpriceid()
    {
        return upriceid;
    }
    public void setUcostType(String ucostType)
    {
        this.ucostType = ucostType;
    }

    public String getUcostType()
    {
        return ucostType;
    }
    public void setUprice(BigDecimal uprice)
    {
        this.uprice = uprice;
    }

    public BigDecimal getUprice()
    {
        return uprice;
    }
    public void setUscene(String uscene)
    {
        this.uscene = uscene;
    }

    public String getUscene()
    {
        return uscene;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("upriceid", getUpriceid())
            .append("ucostType", getUcostType())
            .append("uprice", getUprice())
            .append("uscene", getUscene())
            .toString();
    }
}
