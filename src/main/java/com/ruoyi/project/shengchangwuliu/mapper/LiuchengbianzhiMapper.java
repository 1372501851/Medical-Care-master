package com.ruoyi.project.shengchangwuliu.mapper;

import com.ruoyi.project.shengchangwuliu.domain.Liuchengbianzhi;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-25
 */

@Repository
public interface LiuchengbianzhiMapper {

//    @Insert("INSERT INTO xt_liuchengbianzhi (id,productid,utime,buzhoulist) VALUES (#{id},#{productid},#{utime},#{buzhoulist})")
    @Insert("INSERT INTO xt_liuchengbianzhi (id,uname,utime,buzhoulist) VALUES (#{id},#{uname},#{utime},#{buzhoulist})")
    Integer addLiucheng(Liuchengbianzhi liuchengbianzhi);

    @Select("SELECT * FROM xt_liuchengbianzhi WHERE uname LIKE  #{name}")
    List<Liuchengbianzhi>  likeseleLiucheng(String name);

    @Select("SELECT * FROM xt_liuchengbianzhi WHERE id = ${id}")
    Liuchengbianzhi seleLiuchengById(String id);

    @Select("SELECT * FROM xt_liuchengbianzhi")
    List<Liuchengbianzhi> seleAllLiucheng();


    @Update("UPDATE xt_liuchengbianzhi set uname = #{uname},utime = #{utime},buzhoulist = #{buzhoulist} Where id = #{id}")
    Integer updateLiucheng(Liuchengbianzhi liuchengbianzhi);
}
