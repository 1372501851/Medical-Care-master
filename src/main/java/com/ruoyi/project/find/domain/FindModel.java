package com.ruoyi.project.find.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【请填写功能名称】对象 find_model
 *
 * @author ruoyi
 * @date 2022-11-29
 */
@Data
public class FindModel
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 服务者id */
    @Excel(name = "服务者id")
    private String serverId;

    /** 服务类型:0:寻医问药,1寻找护理........ */
    @Excel(name = "服务类型:0:寻医问药,1寻找护理........")
    private String serviceType;

    /** 状态:0待结单,1已接单,2拒接结单 */
    @Excel(name = "状态:0待结单,1已接单,2拒接结单")
    private String status;

    /** 聊天群id */
    @Excel(name = "聊天群id")
    private String chatId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   private Date createTime;


   private String compId;

   /**监护人Ids*/
    private String guardianIds;

    /**抢单状态*/
    private String robStatus;


    /**基本信息*/
    private String message;


    /**纬度*/
    private BigDecimal latitude;


    /**经度*/
    private BigDecimal longitude;
}
