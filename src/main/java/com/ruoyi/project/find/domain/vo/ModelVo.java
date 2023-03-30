package com.ruoyi.project.find.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2022-11-30
 */
@Data
public class ModelVo {

    /** id */
    private String id;

    /** 用户id */
    private String userId;

    /** 服务者id */
    private String serverId;


    private String serviceName;


    /** 服务类型:0:寻医问药,1寻找护理........ */
    private String serviceType;

    /** 状态:0待结单,1已接单,2拒接结单 */
    private String status;

    private String statusName;

    /** 聊天群id */

    private String chatId;

    @JsonFormat(pattern = "yyyy.MM.dd ", timezone="GMT+8")
    private Date createTime;


    private String avatar;


    private String message;



}
