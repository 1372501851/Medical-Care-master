package com.ruoyi.project.myinfo.mapper;

import com.ruoyi.project.myinfo.domain.XtQinqing;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XtQinqingMapper {

    @Insert("INSERT INTO xt_qinqing (id,userid,touxiang,uname,qinqing,sheng,shi,qu,xiangxidizhi,suozhushequ,shouji,dianhua,xinxitype) values (#{id},#{userid},#{touxiang},#{uname} ,#{qinqing},#{sheng} ,#{shi},#{qu} ,#{xiangxidizhi},#{suozhushequ} ,#{shouji},#{dianhua} ,#{xinxitype})")
    Integer addXtQinqing(XtQinqing xtQinqing);

    @Select("SELECT * FROM xt_qinqing WHERE userid = #{userid}")
    List<XtQinqing> seleXtQinqing(String userid);

    @Select("SELECT * FROM xt_qinqing WHERE id = #{id}")
    XtQinqing seleXtQinqingInfo(String id);
}
