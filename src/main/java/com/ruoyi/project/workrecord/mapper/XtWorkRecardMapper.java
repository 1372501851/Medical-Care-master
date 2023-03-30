package com.ruoyi.project.workrecord.mapper;

import com.ruoyi.project.workrecord.domain.XtWorkRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-17
 */
@Mapper
public interface XtWorkRecardMapper {

    @Insert("INSERT INTO xt_work_record (id ,userid ,time ,address) values (#{id} ,#{userid} ,#{time} ,#{address})")
    Integer add(XtWorkRecord xtWorkRecord);

    @Select("SELECT * FROM xt_work_record WHERE userid = #{id}")
    List<XtWorkRecord> seleXtWorkRecordByUserid(String id);

    @Delete("DELETE FROM xt_work_record WHERE userid = #{id}")
    Integer delXtWorkRecordByUserid(String id);

    @Select("SELECT * FROM xt_work_record WHERE (DATEDIFF(time,#{date}) = 0) AND userid = #{userid}")
    List<XtWorkRecord> seleXtWorkRecordByUseridAndDate(String userid , Date date);
}
