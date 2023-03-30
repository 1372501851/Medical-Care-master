package com.ruoyi.project.zixun.mapper;

import com.ruoyi.project.zixun.domain.XtJibing;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface XtJibingMapper {
    @Insert("INSERT INTO xt_jibing (ujibing_id ,uname,ubuwei,ukeshi,ujiancha,uzhengzhuang,utitle,uyuanyin_content,ugaishu,uzhengzhuang_content,ujiancha_content,uzhengduan_content,ubaojian_content,uhuli_content,ubinfazheng_content) VALUES (#{ujibingId},#{uname},#{ubuwei},#{ukeshi},#{ujiancha},#{uzhengzhuang},#{utitle},#{uyuanyinContent},#{ugaishu},#{uzhengzhuangContent},#{ujianchaContent},#{uzhengduanContent},#{ubaojianContent},#{uhuliContent},#{ubinfazhengContent})")
    Integer addXtjibing(XtJibing jibin);

    Integer delXtjibing(String[] ujibingIds);

    @Update("UPDATE xt_jibing set ujibing_id = #{ujibingId} ,uname= #{uname},ubuwei= #{ubuwei},ukeshi= #{ukeshi},ujiancha= #{ujiancha},uzhengzhuang= #{uzhengzhuang},utitle= #{utitle},uyuanyin_content= #{uyuanyinContent},ugaishu= #{ugaishu},uzhengzhuang_content= #{uzhengzhuangContent},ujiancha_content= #{ujianchaContent},uzhengduan_content= #{uzhengduanContent},ubaojian_content= #{ubaojianContent},uhuli_content= #{uhuliContent},ubinfazheng_content= #{ubinfazhengContent}")
    Integer updXtjibing(XtJibing jibin);

    ArrayList<XtJibing> seleXtjibing(XtJibing jibin);
}
