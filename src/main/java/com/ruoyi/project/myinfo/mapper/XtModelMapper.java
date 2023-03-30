package com.ruoyi.project.myinfo.mapper;

import com.ruoyi.project.myinfo.domain.XtModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface XtModelMapper {

    @Insert("INSERT INTO xt_moban (id,utypeid,uname,xiangxixinxi,userid) VALUES (#{id},#{utypeid},#{uname},#{xiangxixinxi},#{userid})")
    Integer addModel(XtModel xtModel);

    @Select("SELECT * FROM xt_moban WHERE utypeid = #{typeid} AND userid = #{userid}")
    List<XtModel> seleModelByTypeId(String typeid , String userid);

    @Select("SELECT * FROM xt_moban WHERE id = #{id}")
    XtModel seleXtModelInfoById(String id);

    @Update("UPDATE xt_moban set xiangxixinxi = #{xiangxixinxi} Where id = #{id} ")
    Integer updateXtModel(String xiangxixinxi , String id);

    @Select("SELECT * From xt_moban WHERE userid = #{userid} AND uname LIKE #{name}")
    List<XtModel> seleLikeModel(String userid ,String name);

    @Delete("DELETE FROM xt_moban WHERE id= #{id}")
    Integer delModel(String id);

}
