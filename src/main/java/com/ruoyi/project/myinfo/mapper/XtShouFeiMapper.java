package com.ruoyi.project.myinfo.mapper;

import com.ruoyi.project.myinfo.domain.XtShouFei;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author by hujun
 * @date 2023-03-02
 */

@Mapper
public interface XtShouFeiMapper {

    @Insert("INSERT INTO xt_shoufei (id,userid,utype,xuanxiang) values (#{id},#{userid},#{utype},#{xuanxiang})")
    Integer addShouFeiSet(XtShouFei xtShouFei);

    @Update("UPDATE xt_shoufei set xuanxiang =  #{xuanxiang} ")
    Integer updateShouFeiSet(XtShouFei xtShouFei);

    @Select("SELECT xuanxiang FROM xt_shoufei WHERE userid = #{userid} AND utype = #{utype}")
    XtShouFei seleXtShouFei(String userid,String utype);
}
