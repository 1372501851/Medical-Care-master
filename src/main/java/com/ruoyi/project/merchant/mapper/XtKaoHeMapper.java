package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtKaohe;
import com.ruoyi.project.merchant.domain.vo.SelectKaoHeVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface XtKaoHeMapper {

    @Insert("INSERT INTO xt_kaohe (id,uesrid,uself,uwork,achievement,department,comprehensive,utime) values (#{id},#{uesrid},#{uself},#{uwork},#{achievement},#{department},#{comprehensive},#{utime})")
    Integer addKaoHe(XtKaohe xtKaohe);

    @Select("SELECT * FROM xt_kaohe WHERE uesrid = #{userid} AND utime  =  #{time1}  ")
    List<XtKaohe> seleKaoHe(SelectKaoHeVo selectKaoHeVo);

    @Update("UPDATE xt_kaohe set uself=#{uself},uwork=#{uwork},achievement=#{achievement},department=#{department},comprehensive=#{comprehensive} Where id = #{id}")
    Integer updateKaoHe(XtKaohe xtKaohe);



}
