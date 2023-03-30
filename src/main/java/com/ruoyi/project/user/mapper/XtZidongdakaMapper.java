package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.XtZidongdaka;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface XtZidongdakaMapper {

    @Insert("INSERT INTO xt_zidongdaka (id,userid,kaishidate,jieshudate,kaishitime1,jieshutime1,kaishitime2,jieshutime2,jiange) VALUES (#{id},#{userid},#{kaishidate},#{jieshudate},#{kaishitime1},#{jieshutime1},#{kaishitime2},#{jieshutime2},#{jiange})")
    Integer addXtZidongdaka(XtZidongdaka xtZidongdaka);

    @Update("UPDATE xt_zidongdaka set kaishidate=#{kaishidate},jieshudate=#{jieshudate},kaishitime1=#{kaishitime1},jieshutime1=#{jieshutime1},kaishitime2=#{kaishitime2},jieshutime2=#{jieshutime2},jiange=#{jiange} Where userid = #{userid}")
    Integer updateXtZidongdaka(XtZidongdaka xtZidongdaka);

    @Select("SELECT * FROM xt_zidongdaka WHERE userid = #{userid}")
    XtZidongdaka seleXtZidongdaka(String userid);

    @Select("SELECT qunliaozhuangtaiys,qlzths,qlzthg,qlzthl,qlztcnmd,qlztnmb,qlztsb FROM xt_user WHERE userid = #{userid}")
    HashMap<String , String> qunliaozhuangtai(String userid);

    @Update(" UPDATE xt_user set qunliaozhuangtaiys  = ${qunliaozhuangtaiys},qlzths = ${qlzths},qlzthg = ${qlzthg},qlzthl = ${qlzthl},qlztcnmd = ${qlztcnmd},qlztnmb = ${qlztnmb},qlztsb = ${qlztsb} WHERE userid = #{userid}")
    Integer gaiqunliaozhuangtai(String userid,String qunliaozhuangtaiys,String qlzthg,String qlzths,String qlzthl,String qlztcnmd,String qlztnmb,String qlztsb);

}

