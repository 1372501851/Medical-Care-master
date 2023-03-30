package com.ruoyi.project.myinfo.mapper;

import com.ruoyi.project.myinfo.domain.XtBanben;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author by hujun
 * @date 2023-03-25
 */
@Mapper
public interface XtBanbenMapper {
    @Select("SELECT * FROM xt_banben")
    XtBanben selectBanben();

    @Update("UPDATE xt_banben set  ubanben_name = #{ubanbenName},ubanben_number = #{ubanbenNumber},ubanben_url = #{ubanbenUrl} where ubanben_id = #{ubanbenId}")
    Integer updeteBanben(XtBanben xtBanben);
}
