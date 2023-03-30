package com.ruoyi.project.common.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)对象 xt_message
 * 
 * @author ruoyi
 * @date 2022-10-14
 */
public class XtMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 提醒信息ID */
    private String umessageid;

    /** 信息类型 患者朋友,相关医生,相关护士,相关护工,社区义工 */
    @Excel(name = "信息类型 患者朋友,相关医生,相关护士,相关护工,社区义工")
    private String umessageType;

    /** 接收人 */
    @Excel(name = "接收人")
    private String toId;

    /** 内容 */
    @Excel(name = "内容")
    private String ucontent;

    /** 相关表对应ID 可能是uemployeeid，可能是utaskid等 */
    @Excel(name = "相关表对应ID 可能是uemployeeid，可能是utaskid等")
    private String uinfoid;

    /** 状态 0未读，1已读 */
    @Excel(name = "状态 0未读，1已读")
    private String ustatus;

    /** 发送人 */
    @Excel(name = "发送人")
    private String fromId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String utaskid;

    public void setUmessageid(String umessageid) 
    {
        this.umessageid = umessageid;
    }

    public String getUmessageid() 
    {
        return umessageid;
    }
    public void setUmessageType(String umessageType) 
    {
        this.umessageType = umessageType;
    }

    public String getUmessageType() 
    {
        return umessageType;
    }
    public void setToId(String toId) 
    {
        this.toId = toId;
    }

    public String getToId() 
    {
        return toId;
    }
    public void setUcontent(String ucontent) 
    {
        this.ucontent = ucontent;
    }

    public String getUcontent() 
    {
        return ucontent;
    }
    public void setUinfoid(String uinfoid) 
    {
        this.uinfoid = uinfoid;
    }

    public String getUinfoid() 
    {
        return uinfoid;
    }
    public void setUstatus(String ustatus) 
    {
        this.ustatus = ustatus;
    }

    public String getUstatus() 
    {
        return ustatus;
    }
    public void setFromId(String fromId) 
    {
        this.fromId = fromId;
    }

    public String getFromId() 
    {
        return fromId;
    }
    public void setUtaskid(String utaskid) 
    {
        this.utaskid = utaskid;
    }

    public String getUtaskid() 
    {
        return utaskid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("umessageid", getUmessageid())
            .append("umessageType", getUmessageType())
            .append("toId", getToId())
            .append("ucontent", getUcontent())
            .append("uinfoid", getUinfoid())
            .append("ustatus", getUstatus())
            .append("createTime", getCreateTime())
            .append("fromId", getFromId())
            .append("utaskid", getUtaskid())
            .toString();
    }
}
