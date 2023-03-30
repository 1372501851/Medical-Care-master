package com.ruoyi.project.attendance.domain;

import lombok.Data;

/**
 * 日常事务检查
 * @author by hujun
 * @date 2023-02-13
 */
@Data
public class XtDailyAffairs {

    /** 日常事务检查主键*/
    private String id;

    /** 用户id*/
    private String userid;

    /**
     * 打卡人姓名*/
    private String name;

    /** 打卡时间*/
    private String creatTime;

    /** 地址*/
    private String address;

    /** 今日环境卫生检查情况（0：待检查，1：合格，2：不合格*/
    private String environment;

    /** 今日用电安全检查情况（0：待检查，1：合格，2：不合格）*/
    private String electric;

    /** 今日消防安全检查情况（0：待检查，1：合格，2：不合格）*/
    private String firecontrol;

    /** 今日防盗安全检查情况（0：待检查，1：合格，2：不合格）*/
    private String theft;

    /** 今日防抢安全检查情况（0：待检查，1：合格，2：不合格）*/
    private String robbery;

    /** 今日门前规范检查情况（0：待检查，1：合格，2：不合格）*/
    private String neat;

    /** 今日食药规范检查情况（0：待检查，1：合格，2：不合格）*/
    private String drug;

    /** 今日疫情规范检查情况（0：待检查，1：合格，2：不合格）*/
    private String disease;

    /** 今日医疗废弃物检查情况（0：待检查，1：合格，2：不合格）*/
    private String waste;

    /** 今日商务规范检查情况（0：待检查，1：合格，2：不合格）*/
    private String commerce;

    /** 今日纳税规范检查情况（0：待检查，1：合格，2：不合格）*/
    private String tax;

    /** 今日业务学习检查情况（0：待检查，1：合格，2：不合格）*/
    private String business;

    /** 今日法规学习检查情况（0：待检查，1：合格，2：不合格）*/
    private String law;
}
