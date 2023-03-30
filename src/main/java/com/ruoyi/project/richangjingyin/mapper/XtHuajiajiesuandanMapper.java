package com.ruoyi.project.richangjingyin.mapper;

import com.ruoyi.project.richangjingyin.domain.XtHuajiajiesuandan;
import com.ruoyi.project.richangjingyin.domain.vo.XtHuajiajiesuandanVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-27
 */

@Mapper
public interface XtHuajiajiesuandanMapper {

    @Insert("INSERT INTO xt_huajiajiesuandan (id,huangzheid,huangzhename,yishengid ,yishengname,yaopinid ,yaopinnum,haocainame ,haocainum,haocaiguige ,haocaidanjia,jianchafei ,jianchaguige,jianchashuliang ,jianchadanjia,zhiliaoname ,zhiliaoguige,zhiliaodanjia ,zhiliaoshuliang,saas ,uother) VALUES (    #{id},#{huangzheid},#{huangzhename},#{yishengid} ,#{yishengname},#{yaopinid} ,#{yaopinnum},#{haocainame} ,#{haocainum},#{haocaiguige} ,#{haocaidanjia},#{jianchafei} ,#{jianchaguige},#{jianchashuliang} ,#{jianchadanjia},#{zhiliaoname} ,#{zhiliaoguige},#{zhiliaodanjia} ,#{zhiliaoshuliang},#{saas} ,#{uother})")
    Integer addXtHuajiajiesuandan(XtHuajiajiesuandan xtHuajiajiesuandan);

    @Select("SELECT * FROM xt_huajiajiesuandan WHERE id = #{id}")
    XtHuajiajiesuandan seleXtHuajiajiesuandan(String id);
}
