package com.ruoyi.project.workrecord.domain;

import lombok.Data;

/**
 * @author by hujun
 * @date 2023-02-17
 */

@Data
public class XtAutomaticClockingIntervalSetting {
    /**自动打卡时间设置主键*/
    private String id;

    /**用户id*/
    private String userid;

    /**开始日期*/
    private String startDate;

    /**结束日期*/
    private String endDate;

    /**开始时间（早）*/
    private String startTimeMorning;

    /**结束时间（早）*/
    private String endTimeMorning;

    /**开始时间(晚)*/
    private String startTimeNight;

    /**结束时间(晚)*/
    private String endTimeNight;

    /**间隔时间*/
    private Integer uinterval;
}
