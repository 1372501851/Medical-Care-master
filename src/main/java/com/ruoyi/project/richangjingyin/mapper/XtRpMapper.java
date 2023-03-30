package com.ruoyi.project.richangjingyin.mapper;

import com.ruoyi.project.richangjingyin.domain.XtRp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XtRpMapper {

    @Insert("INSERT INTO xt_rp (id,productid,productname,productguige,productxinghao,productnum) VALUES (#{id},#{productid},#{productname},#{productguige},#{productxinghao},#{productnum})")
    Integer addXtRp(XtRp xtRp);

    @Select("SELECT * FROM xt_rp WHERE id = #{id}")
    XtRp  sele(String id);
}
