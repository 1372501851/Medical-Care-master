package com.ruoyi.project.attendance.mapper;

import com.ruoyi.project.attendance.domain.XtDailyAffairs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XtDailyAffairsMapper {

    @Insert("INSERT INTO xt_daily_affairs (id,userid,name,creat_time,address,environment,electric,firecontrol,theft,robbery,neat,drug,disease,waste,commerce,tax,business,law) VALUES (#{id},#{userid},#{name},#{creatTime},#{address},#{environment},#{electric},#{firecontrol},#{theft},#{robbery},#{neat},#{drug},#{disease},#{waste},#{commerce},#{tax},#{business},#{law})")
    int add(XtDailyAffairs xtDailyAffairs);


    @Select("SELECT * FROM xt_daily_affairs")
    List<XtDailyAffairs> list();
}
