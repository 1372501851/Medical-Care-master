package com.ruoyi.project.kaoqin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-14
 */
@Data
public class KaoQin {

    /** id */
    private String id;

    /** 员工id */
    private String employid;

    /** 考勤地点 */
    private String address;

    @JsonFormat(pattern ="yyyy-MM-dd hh:mm:ss")
    /** 考勤时间 */
    private Date kaoqintime;

    /** 考勤状态 */
    private String kaoqinstatus;

    /** 上班打卡还是下班打卡 */
    private String shangxiaban;

}
