package com.ruoyi.project.common.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户收藏 收藏医院、收藏医生、收藏语言内容对象 xt_userstore
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public class XtUserstore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户收藏ID */
    private String userstoreid;

    /** 收藏类型（0，医生，1，任务，2,医院,3 , 收藏药盒类型） */
    @Excel(name = "收藏类型", readConverterExp = "0=，医生，1，任务，2医院 , 3 , 收藏药盒类型")
    private String ustoreType;

    /** 收藏用户ID */
    @Excel(name = "收藏用户ID")
    private String userid;

    /** 被收藏对象ID,可能是uemployeeid，可能是utaskid等 */
    @Excel(name = "被收藏对象ID,可能是uemployeeid，可能是utaskid等")
    private String uinfoid;

    /** 收藏时间（时间戳） */
    @Excel(name = "收藏时间", readConverterExp = "时=间戳")
    private Long ucreatedate;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setUserstoreid(String userstoreid)
    {
        this.userstoreid = userstoreid;
    }

    public String getUserstoreid()
    {
        return userstoreid;
    }
    public void setUstoreType(String ustoreType)
    {
        this.ustoreType = ustoreType;
    }

    public String getUstoreType()
    {
        return ustoreType;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUserid()
    {
        return userid;
    }
    public void setUinfoid(String uinfoid)
    {
        this.uinfoid = uinfoid;
    }

    public String getUinfoid()
    {
        return uinfoid;
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
            .append("userstoreid", getUserstoreid())
            .append("ustoreType", getUstoreType())
            .append("userid", getUserid())
            .append("uinfoid", getUinfoid())
            .append("ucreatedate", getUcreatedate())
             .append("productName", getProductName())
            .toString();
    }
}
