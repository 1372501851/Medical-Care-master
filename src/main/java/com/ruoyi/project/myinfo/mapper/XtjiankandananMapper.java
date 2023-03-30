package com.ruoyi.project.myinfo.mapper;

import com.ruoyi.project.myinfo.domain.XtJiankandanan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface XtjiankandananMapper {

    @Select("SELECT * FROM xt_jiankandanan WHERE userid = #{userid}")
    XtJiankandanan selectXtjiankandanan(String userid);

    @Insert("INSERT INTO xt_jiankandanan (id,userid,uname,usex ,age,xuexing ,shenggao,tizhong ,jiehun,dianhua1 ,dianhua2,yisheng1 ,yisheng2,yisheng3 ,bingshi,guomingshi) VALUES (#{id},#{userid},#{uname},#{usex} ,#{age},#{xuexing} ,#{shenggao},#{tizhong} ,#{jiehun},#{dianhua1} ,#{dianhua2},#{yisheng1} ,#{yisheng2},#{yisheng3} ,#{bingshi},#{guomingshi})")
    Integer addXtjiankandanan(XtJiankandanan xtJiankandanan);

    @Update("UPDATE xt_jiankandanan SET uname=#{uname},usex=#{usex} ,age=#{age},xuexing=#{xuexing} ,shenggao=#{shenggao},tizhong=#{tizhong} ,jiehun=#{jiehun},dianhua1=#{dianhua1} ,dianhua2=#{dianhua2},yisheng1=#{yisheng1} ,yisheng2=#{yisheng2},yisheng3=#{yisheng3} ,bingshi=#{bingshi},guomingshi=#{guomingshi} WHERE id = #{id}")
    Integer updateXtjiankandanan(XtJiankandanan xtJiankandanan);
}
