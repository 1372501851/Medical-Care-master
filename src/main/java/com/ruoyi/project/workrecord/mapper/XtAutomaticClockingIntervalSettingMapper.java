package com.ruoyi.project.workrecord.mapper;

import com.ruoyi.project.workrecord.domain.XtAutomaticClockingIntervalSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface XtAutomaticClockingIntervalSettingMapper {
    @Insert("INSERT INTO xt_automatic_clocking_interval_setting (id,useri,start_date,end_date,start_time_morning,end_time_morning,start_timeNight,endTimeNight,uinterval) values (#{id},#{useri},#{startDate},#{endDate},#{startTimeMorning},#{endTimeMorning},#{startTimeNight},#{endTimeNight},#{uinterval})")
    Integer add(XtAutomaticClockingIntervalSetting xtAutomaticClockingIntervalSetting);

    @Select("SELECT * FROM xt_automatic_clocking_interval_setting WHERE userid = #{id}")
    XtAutomaticClockingIntervalSetting seleXtAutomaticClockingIntervalSettingByUserId(String id);


}

