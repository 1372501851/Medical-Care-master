package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtworkSummary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XtworkSummaryMapper {

    @Insert("INSERT INTO xt_worksummary (id,userid,t1,t2,t3,t4,t5,creat_date,words) values (#{id},#{userid},#{t1},#{t2},#{t3},#{t4},#{t5},#{creatDate},#{words})")
    Integer add(XtworkSummary xtworkSummary);

    @Select("SELECT * FROM xt_worksummary WHERE userid = #{employid}")
    List<XtworkSummary> seleXtworkSummaryById(String employid);


}
