package com.ruoyi.project.shengchangwuliu.mapper;

import com.ruoyi.project.shengchangwuliu.domain.DaKaOfLiuCheng;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DaKaOfLiuChengMapper {

    @Insert("insert into dakaofliucheng (id ,orderid ,liuchengid ,status,peoplename , utime) values (#{id},#{orderid},#{liuchengid},#{status},#{peoplename} ,#{utime} )")
    Integer addDaKa(DaKaOfLiuCheng daKaOfLiuCheng);

    @Update("UPDATE dakaofliucheng SET status = #{status} ,peoplename = #{peoplename} , utime = #{utime} Where id = #{id}")
    Integer updateDaKa(DaKaOfLiuCheng daKaOfLiuCheng);

    @Select("select * FROM dakaofliucheng where orderid = #{orderid} And liuchengid =#{liuchenid}")
    List<DaKaOfLiuCheng> selectDaKaOfLiuCheng(String orderid , String liuchenid);
}
