package com.ruoyi.project.zixun.mapper;


import com.ruoyi.project.zixun.domain.XtZixun;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface XtZixunMapper {
    @Insert("INSERT INTO xt_zixun (uzixun_id,ulujing,utype,ucompid,utitle) VALUES (#{uzixunId},#{ulujing},#{utype},#{ucompid},#{utitle})")
    Integer addZiXun(XtZixun xtZixun);

    Integer delZixun(String[] uzixunId);

    @Update("UPDATE xt_zixun SET  (ulujing = #{ulujing},utype = #{utype},ucompid = #{ucompid}) WHERE uzixun_id = #{uzixunId}")
    Integer updZixun(XtZixun xtZixun);

    ArrayList<XtZixun> seleXtZixun(XtZixun xtZixun);
}
