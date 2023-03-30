package com.ruoyi.project.myinfo.mapper;

import com.ruoyi.project.myinfo.domain.XtFanKui;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XtFanKuiMapper {
    @Insert("INSERT INTO xt_fankui (id,userid,neirong) VALUES (#{id},#{userid},#{neirong})")
    Integer addXtFanKui(XtFanKui xtFanKui);
}
