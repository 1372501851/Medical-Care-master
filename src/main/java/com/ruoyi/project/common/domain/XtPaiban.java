package com.ruoyi.project.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 排班对象 xt_paiban
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtPaiban extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排班ID */
    private String upaibanid;

    /** 属于部门（科室）ID */
    @Excel(name = "属于部门", readConverterExp = "科=室")
    private String udepartmentid;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date udate;

    /** 星期(周一，周二，周三，周四，周五，周六，周日) */
    @Excel(name = "星期(周一，周二，周三，周四，周五，周六，周日)")
    private Long uweek;

    /** 时间段（上午、下午、晚上，全天） */
    @Excel(name = "时间段", readConverterExp = "上午、下午、晚上，全天")
    private String utimeQuantum;

    /** 是否假期（1是，0不是） */
    @Excel(name = "是否假期", readConverterExp = "1=是，0=不是")
    private String uholiday;

    /** 管理端用的 */
    private String upaibantime;

    public String getUpaibantime() {
        return upaibantime;
    }

    public void setUpaibantime(String upaibantime) {
        this.upaibantime = upaibantime;
    }

    public void setUpaibanid(String upaibanid)
    {
        this.upaibanid = upaibanid;
    }

    public String getUpaibanid()
    {
        return upaibanid;
    }
    public void setUdepartmentid(String udepartmentid)
    {
        this.udepartmentid = udepartmentid;
    }

    public String getUdepartmentid()
    {
        return udepartmentid;
    }
    public void setUdate(Date udate)
    {
        this.udate = udate;
    }

    public Date getUdate()
    {
        return udate;
    }
    public void setUweek(Long uweek)
    {
        this.uweek = uweek;
    }

    public Long getUweek()
    {
        return uweek;
    }
    public void setUtimeQuantum(String utimeQuantum)
    {
        this.utimeQuantum = utimeQuantum;
    }

    public String getUtimeQuantum()
    {
        return utimeQuantum;
    }
    public void setUholiday(String uholiday)
    {
        this.uholiday = uholiday;
    }

    public String getUholiday()
    {
        return uholiday;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("upaibanid", getUpaibanid())
            .append("udepartmentid", getUdepartmentid())
            .append("udate", getUdate())
            .append("uweek", getUweek())
            .append("utimeQuantum", getUtimeQuantum())
            .append("uholiday", getUholiday())
            .toString();
    }
}
