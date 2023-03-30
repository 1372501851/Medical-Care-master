package com.ruoyi.project.myinfo.mapper;

import com.ruoyi.project.myinfo.domain.XtModelType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XtModelTypeMapper {

    @Insert("INSERT INTO xt_modeltype (id,utypename,userid) VALUES (#{id},#{utypename},#{userid})")
    Integer addXtModelType(XtModelType xtModelType);

    @Delete("DELETE FROM xt_modeltype WHERE  id = #{id}")
    Integer delXtModelType(String id);

    @Select("SELECT * FROM xt_modeltype WHERE userid = #{userid} ")
    List<XtModelType> seleXtModelType(String userid);

    @Select("SELECT * FROM xt_modeltype WHERE userid = #{userid} AND utypename LIKE #{name} ")
    List<XtModelType> seleLikeModelType(String name , String userid);
}
