package com.ruoyi.project.peixun.mapper;

import com.ruoyi.project.peixun.domain.XtPeiXunZiLiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XtPeiXunZiLiaoMapper {

    @Select("SELECT * FROM xt_peixun_ziliao where ziliaotype = #{type}")
    List<XtPeiXunZiLiao> selectAllPeiXunZiLiao(Integer type);

    @Select("SELECT name FROM xt_peixun_ziliao WHERE  id = #{id}")
    String selectZiLiaoName(String id);
}
