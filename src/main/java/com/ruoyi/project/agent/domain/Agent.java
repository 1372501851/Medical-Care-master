package com.ruoyi.project.agent.domain;

import lombok.Data;

/**
 * @author by hujun
 * @date 2023-02-15
 */
@Data
public class Agent {
    /** 代理id */
    private String Agentid;

    /** 代理人名称 */
    private String agname;

    /** 代理城市 */
    private String agcity;

    /** 代理人年龄 */
    private Integer agage;

    /** 代理人性别 */
    private String agsex;

    /** 代理人住址 */
    private String agaddress;

    /** 代理人身份证号码 */
    private String agidcard;

    /** 代理人身份证照片 */
    private String agidpic;

    /** 代理人电话号码 */
    private String agphone;

    /** 本APP的IP地址（虽然我也不知道这个是啥） */
    private String appip;

    /** 用户名 */
    private String uesrname;

    /** 信息传输方式*/
    private String information;

    /** 代理人类型 */
    private String agtype;

}
