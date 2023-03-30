package com.ruoyi.project.advertising.mapper;

import com.ruoyi.project.advertising.domain.GuangGaoOfGuanLi;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GuangGaoOfGuanLiMapper {
    @Insert("INSERT INTO guang_gao_of_guan_li (id,utype,neirong,fanwei,starttime,endtime,weizhi,status,userid) VALUES (#{id},#{utype},#{neirong},#{fanwei},#{starttime},#{endtime},#{weizhi},#{status},#{userid})")
    Integer add(GuangGaoOfGuanLi guangGaoOfGuanLi);

    @Select("SELECT * FROM guang_gao_of_guan_li WHERE userid = #{userId} AND utype = #{type}")
    List<GuangGaoOfGuanLi> seleGuangGaoOfGuanLiByUserId(String userId,String type);
}

