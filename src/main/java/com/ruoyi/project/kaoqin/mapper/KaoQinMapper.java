package com.ruoyi.project.kaoqin.mapper;

import com.ruoyi.project.kaoqin.domain.KaoQin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KaoQinMapper {

    @Insert("INSERT INTO xt_kaoqin (id,employid,address,kaoqintime,kaoqinstatus,shangxiaban) VALUES (#{id},#{employid},#{address},#{kaoqintime},#{kaoqinstatus},#{shangxiaban})")
    Integer add(KaoQin kaoQin);

    @Select("SELECT * FROM xt_kaoqin WHERE employid = ${id}")
    List<KaoQin> chaKan(String id);
    @Select("SELECT shangxiaban FROM xt_kaoqin WHERE employid = #{id}")
    List<String> onAndOffDuty(String id);

//    KaoQin lickseledakaEmploy(name)
}
