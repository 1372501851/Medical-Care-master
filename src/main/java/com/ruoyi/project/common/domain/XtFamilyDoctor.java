package com.ruoyi.project.common.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 我的家庭医生,主要是用户与医生签约对象 xt_family_doctor
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtFamilyDoctor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 家庭医生ID */
    private String ufamilyDoctorid;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userid;

    /** 医生ID */
    @Excel(name = "医生ID")
    private String uemployeeid;

    /** 状态（0等待支付，1正常，2过期） */
    @Excel(name = "状态", readConverterExp = "0=等待支付，1正常，2过期")
    private String ustatus;

    /** 家庭医生单价、按月收费 */
    @Excel(name = "家庭医生单价、按月收费")
    private BigDecimal uprice;

    /** 总价格 */
    @Excel(name = "总价格")
    private BigDecimal utotalprice;

    /** 开始服务时间（时间戳） */
    @Excel(name = "开始服务时间", readConverterExp = "时=间戳")
    private Long ustartTime;

    /** 结束服务时间（时间戳） */
    @Excel(name = "结束服务时间", readConverterExp = "时=间戳")
    private Long uendTime;

    /** 备注 */
    @Excel(name = "备注")
    private String uremark;

    /** 创建时间（时间戳） */
    @Excel(name = "创建时间", readConverterExp = "时=间戳")
    private Long ucreatedate;

    public void setUfamilyDoctorid(String ufamilyDoctorid)
    {
        this.ufamilyDoctorid = ufamilyDoctorid;
    }

    public String getUfamilyDoctorid()
    {
        return ufamilyDoctorid;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUserid()
    {
        return userid;
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
    public void setUprice(BigDecimal uprice)
    {
        this.uprice = uprice;
    }

    public BigDecimal getUprice()
    {
        return uprice;
    }
    public void setUtotalprice(BigDecimal utotalprice)
    {
        this.utotalprice = utotalprice;
    }

    public BigDecimal getUtotalprice()
    {
        return utotalprice;
    }
    public void setUstartTime(Long ustartTime)
    {
        this.ustartTime = ustartTime;
    }

    public Long getUstartTime()
    {
        return ustartTime;
    }
    public void setUendTime(Long uendTime)
    {
        this.uendTime = uendTime;
    }

    public Long getUendTime()
    {
        return uendTime;
    }
    public void setUremark(String uremark)
    {
        this.uremark = uremark;
    }

    public String getUremark()
    {
        return uremark;
    }
    public void setUcreatedate(Long ucreatedate)
    {
        this.ucreatedate = ucreatedate;
    }

    public Long getUcreatedate()
    {
        return ucreatedate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ufamilyDoctorid", getUfamilyDoctorid())
            .append("userid", getUserid())
            .append("uemployeeid", getUemployeeid())
            .append("ustatus", getUstatus())
            .append("uprice", getUprice())
            .append("utotalprice", getUtotalprice())
            .append("ustartTime", getUstartTime())
            .append("uendTime", getUendTime())
            .append("uremark", getUremark())
            .append("ucreatedate", getUcreatedate())
            .toString();
    }
}
