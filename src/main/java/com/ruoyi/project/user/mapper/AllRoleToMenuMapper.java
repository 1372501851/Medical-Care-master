package com.ruoyi.project.user.mapper;


import com.ruoyi.project.user.domain.XtAllRole;
import com.ruoyi.project.user.domain.XtAllRoleToMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 呵呵哒
 */
@Repository
public interface AllRoleToMenuMapper {

  int insertXtAllRoleToMenu(XtAllRoleToMenu xtAllRoleToMenu);
  int insertBatch(@Param("entities") List<XtAllRoleToMenu> entities);
  int deleteXtAllRoleToMenu(@Param("uallroleid") Integer uallroleid);

  int addrole(@Param("id") String id,@Param("name") String name,
                 @Param("uroledesc") String uroledesc,@Param("type") String type);

  void accredit(@Param("id") int id,@Param("menuid") int menuid);

  int revamp(int id);

  void updataByid(XtAllRole xtAllRole);


}
