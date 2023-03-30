package com.ruoyi.project.common.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 聊天记录对象 xt_chat
 * 
 * @author ruoyi
 * @date 2022-10-14
 */
public class XtChat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 聊天ID */
    private String uchatid;

    /** 群ID */
    @Excel(name = "群ID")
    private String ugroupid;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String utaskid;

    /** 聊天内容 */
    @Excel(name = "聊天内容")
    private String ucontent;

    /** 说话用户ID */
    @Excel(name = "说话用户ID")
    private String userid;

    /** 接受信息用户ID */
    @Excel(name = "接受信息用户ID")
    private String touserid;

    public void setUchatid(String uchatid) 
    {
        this.uchatid = uchatid;
    }

    public String getUchatid() 
    {
        return uchatid;
    }
    public void setUgroupid(String ugroupid) 
    {
        this.ugroupid = ugroupid;
    }

    public String getUgroupid() 
    {
        return ugroupid;
    }
    public void setUtaskid(String utaskid) 
    {
        this.utaskid = utaskid;
    }

    public String getUtaskid() 
    {
        return utaskid;
    }
    public void setUcontent(String ucontent) 
    {
        this.ucontent = ucontent;
    }

    public String getUcontent() 
    {
        return ucontent;
    }
    public void setUserid(String userid) 
    {
        this.userid = userid;
    }

    public String getUserid() 
    {
        return userid;
    }
    public void setTouserid(String touserid) 
    {
        this.touserid = touserid;
    }

    public String getTouserid() 
    {
        return touserid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uchatid", getUchatid())
            .append("ugroupid", getUgroupid())
            .append("utaskid", getUtaskid())
            .append("ucontent", getUcontent())
            .append("userid", getUserid())
            .append("touserid", getTouserid())
            .append("createTime", getCreateTime())
            .toString();
    }
}
