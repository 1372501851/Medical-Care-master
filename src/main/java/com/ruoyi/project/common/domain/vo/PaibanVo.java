package com.ruoyi.project.common.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.common.domain.select.Select;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/1/30
 */
@Data
public class PaibanVo {

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

    private List<Select> employeeList;

    private List<String> uemployeeids;

    private String upaibantime;

}
