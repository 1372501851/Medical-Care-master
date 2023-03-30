package com.ruoyi.project.peixun.mapper;

import com.ruoyi.project.peixun.domain.XtEmployPeiXun;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface XtEmployPeiXunMapper {

    @Insert("INSERT INTO xt_employ_peixun (id,employid,peixunziliaoid,utime,xuexistatus) VALUES (#{id},#{employid},#{peixunziliaoid},#{utime},#{xuexistatus})")
    Integer addXtEmployPeiXun(XtEmployPeiXun xtEmployPeiXun);

    @Select("SELECT * FROM xt_employ_peixun WHERE employid = #{employId}")
    List<XtEmployPeiXun> selectEmployJiLu(String employId);

    @Update("UPDATE xt_employ_peixun SET xuexistatus = #{xuexistatus} WHERE id = #{id}")
    Integer updateEmployJiLu(String xuexistatus , String id);
}
