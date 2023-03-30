package com.ruoyi.project.common.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 申请产品列(也可以是报销项目)对象 xt_app_product
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtAppProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请产品ID */
    private String uappProductid;

    /** 用户管理员申请表ID */
    @Excel(name = "用户管理员申请表ID")
    private String userAppid;

    /** 产品ID */
    @Excel(name = "产品ID")
    private String uproductid;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String uproductName;

    /** 申请数量 */
    @Excel(name = "申请数量")
    private Long uappNum;

    /** 申请单价 */
    @Excel(name = "申请单价")
    private BigDecimal uappPrice;

    /** 产品单位 */
    @Excel(name = "产品单位")
    private String uappUnit;

    public void setUappProductid(String uappProductid)
    {
        this.uappProductid = uappProductid;
    }

    public String getUappProductid()
    {
        return uappProductid;
    }
    public void setUserAppid(String userAppid)
    {
        this.userAppid = userAppid;
    }

    public String getUserAppid()
    {
        return userAppid;
    }
    public void setUproductid(String uproductid)
    {
        this.uproductid = uproductid;
    }

    public String getUproductid()
    {
        return uproductid;
    }
    public void setUproductName(String uproductName)
    {
        this.uproductName = uproductName;
    }

    public String getUproductName()
    {
        return uproductName;
    }
    public void setUappNum(Long uappNum)
    {
        this.uappNum = uappNum;
    }

    public Long getUappNum()
    {
        return uappNum;
    }
    public void setUappPrice(BigDecimal uappPrice)
    {
        this.uappPrice = uappPrice;
    }

    public BigDecimal getUappPrice()
    {
        return uappPrice;
    }
    public void setUappUnit(String uappUnit)
    {
        this.uappUnit = uappUnit;
    }

    public String getUappUnit()
    {
        return uappUnit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uappProductid", getUappProductid())
            .append("userAppid", getUserAppid())
            .append("uproductid", getUproductid())
            .append("uproductName", getUproductName())
            .append("uappNum", getUappNum())
            .append("uappPrice", getUappPrice())
            .append("uappUnit", getUappUnit())
            .toString();
    }
}
