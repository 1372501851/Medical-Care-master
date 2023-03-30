package com.ruoyi.project.fileknowledge.mapper;

import com.ruoyi.project.fileknowledge.domain.XtFileknowledge;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface XtFileknowledgeMapper {

    @Insert("INSERT INTO xt_fileknowledge (id,filepath,filename,filetype) values (#{id},#{filepath},#{filename},#{filetype})")
    Integer addFileknowledge(XtFileknowledge xtFileknowledge);

    @Select("SELECT * FROM xt_fileknowledge WHERE filetype = #{filetype}")
    List<XtFileknowledge> seleXtFileknowledgeByFiletype(String filetype);

    @Delete("DELETE FROM xt_fileknowledge WHERE id = #{id}")
    Integer delXtFileknowledge(String id);

    @Select("SELECT * FROM xt_fileknowledge ")
    List<XtFileknowledge> seleLikeXtFileknowledge(String name);

}
