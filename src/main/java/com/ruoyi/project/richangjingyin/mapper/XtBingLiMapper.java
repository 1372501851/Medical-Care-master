package com.ruoyi.project.richangjingyin.mapper;

import com.ruoyi.project.richangjingyin.domain.XtBingLi;
import com.ruoyi.project.richangjingyin.domain.vo.BingLiVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XtBingLiMapper {

    @Insert("INSERT INTO xt_bingli (productnum,jianchaxiangmu,yewushenhe,shengao,tizhong,menzhenhao,jiuzhenhao,huangzhedianhua,huangzhezhusubingqing,dayintime,yishengname,huanzhename,createtime,id,idcard,hospital,keshi,yishenguserid ,huangzheuserid,chushendate ,sex,age ,ismarry,guoming ,binghistory,linchuang ,productid,utype,haocainame  ,haocaidanjia ,haocainum,jianchaname  ,jianchandanjia,jianchanum  ,zhiliaoname,zhiliaodanjia  ,zhiliaonum,hulifei  ,saas,qita) VALUES (#{productnum},#{jianchaxiangmu},#{yewushenhe},#{shengao},#{tizhong},#{menzhenhao},#{jiuzhenhao},#{huangzhedianhua},#{huangzhezhusubingqing},#{dayintime},#{yishengname},#{huanzhename},#{createtime},#{id},#{idcard},#{hospital},#{keshi},#{yishenguserid} ,#{huangzheuserid},#{chushendate} ,#{sex},#{age} ,#{ismarry},#{guoming} ,#{binghistory},#{linchuang} ,#{productid},#{utype},#{haocainame} ,#{haocaidanjia},#{haocainum},#{jianchaname} ,#{jianchandanjia},#{jianchanum} ,#{zhiliaoname},#{zhiliaodanjia} ,#{zhiliaonum},#{hulifei} ,#{saas},#{qita})")
    Integer add(XtBingLi xtBingLi);
//

    @Select("SELECT * FROM xt_bingli WHERE huangzheuserid = #{userId} AND utype = #{utype}")
    List<XtBingLi> sele(String userId , String utype);

    @Select("SELECT * FROM xt_bingli WHERE yishenguserid = ${id}")
    List<XtBingLi>  selectBingLiByYiShengUserId(String id);

    @Select("SELECT * FROM xt_bingli WHERE id = #{id}")
    XtBingLi  selectBingLiById(String id);

    @Select("SELECT hospital,keshi,createtime, yishengname FROM xt_bingli WHERE  utype = #{utype}")
    List<BingLiVo> seleblfl(String utype);

     @Select("SELECT * FROM xt_bingli WHERE utype = #{utype} AND hospital = #{hospital} AND keshi = #{keshi} AND yishengname = #{yishengname}")
    List<XtBingLi> seleblBy(String utype,String hospital ,String keshi ,String yishengname);

     @Select("SELECT * FROM xt_bingli WHERE utype = #{tepe}")
    List<XtBingLi> selectAllBingLiOrChuFan(String type);

//    @Select("SELECT * FROM xt_bingli WHERE utype = #{tepe} And hospital Like #{name}")
//    List<XtBingLi> selectLikeAllBingLiOrChuFan(String type , String name);

}
