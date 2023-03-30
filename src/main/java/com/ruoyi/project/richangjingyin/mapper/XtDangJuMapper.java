package com.ruoyi.project.richangjingyin.mapper;

import com.ruoyi.project.richangjingyin.domain.XtDangJu1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XtDangJuMapper {

    @Insert("INSERT INTO xt_dang_ju (id ,slbm ,slsy,pm1,dl1 ,sl1,pm2 ,dl2,sl2 ,pm3,dl3 ,sl3,pm4 ,dl4,sl4 ,pm5,dl5 ,sl5,pm6 ,dl6,sl6 ,pm7,dl7 ,sl7,pm8 ,dl8,sl8 ,pm9,dl9 ,sl9,slr ,bmsh,zbsh,sltype,date) VALUES (#{id} ,#{slbm} ,#{slsy},#{pm1},#{dl1} ,#{sl1},#{pm2} ,#{dl2},#{sl2} ,#{pm3},#{dl3} ,#{sl3},#{pm4} ,#{dl4},#{sl4} ,#{pm5},#{dl5} ,#{sl5},#{pm6} ,#{dl6},#{sl6} ,#{pm7},#{dl7} ,#{sl7},#{pm8} ,#{dl8},#{sl8} ,#{pm9},#{dl9} ,#{sl9},#{slr} ,#{bmsh},#{zbsh},#{sltype},#{date})")
    Integer add(XtDangJu1 xtDangJu);

    @Select("SELECT * FROM xt_dang_ju WHERE dl7 = #{sltype}")
    List<XtDangJu1> seleXtDangJu(String sltype);

    @Select("SELECT * FROM xt_dang_ju WHERE id = #{id}")
    List<XtDangJu1> seleXtDangJuById(String id);
}
