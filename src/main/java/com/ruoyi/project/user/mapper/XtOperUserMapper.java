package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.XtOperUser;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 系统管理员Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@Repository
public interface XtOperUserMapper
{
    /**
     * 查询系统管理员
     *
     * @param uoperUserid 系统管理员主键
     * @return 系统管理员
     */
    public XtOperUser selectXtOperUserByUoperUserid(String uoperUserid);

    /**
     * 查询系统管理员列表
     *
     * @param xtOperUser 系统管理员
     * @return 系统管理员集合
     */
    public List<XtOperUser> selectXtOperUserList(XtOperUser xtOperUser);

    /**
     * 新增系统管理员
     *
     * @param xtOperUser 系统管理员
     * @return 结果
     */
    public int insertXtOperUser(XtOperUser xtOperUser);

    /**
     * 修改系统管理员
     *
     * @param xtOperUser 系统管理员
     * @return 结果
     */
    public int updateXtOperUser(XtOperUser xtOperUser);

    /**
     * 删除系统管理员
     *
     * @param uoperUserid 系统管理员主键
     * @return 结果
     */
    public int deleteXtOperUserByUoperUserid(String uoperUserid);

    /**
     * 批量删除系统管理员
     *
     * @param uoperUserids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtOperUserByUoperUserids(String[] uoperUserids);

    XtOperUser selectXtOperUserByUserLoginName(String username);

//    int countUserRoleByRoleId(Long roleId);
//
    XtOperUser selectXtOperUserByPhoneNumber(String umobile);

    XtOperUser selectXtOperUserBEmail(String uemail);

    List<XtOperUser> selectWebUserList(XtOperUser xtOperUser);

        /**
     * 审核平台管理员注册
     * @return
     */
    @Update("update xt_oper_user SET status = #{status} ,ucreatedate = #{ucreatedate} WHERE uoper_userid = #{uoperUserid};")
    int auditSystemPlatformAdministratorRegistration(String status , String uoperUserid ,long ucreatedate);

    /**
     * 查询所有平台系统管理员
     * @return
     */
    @Select("select * from xt_oper_user where usertype = 0")
    List<XtOperUser> selectAllPlatformAdministrator();

    /**
     * 模糊查询所有平台系统管理员（姓名）
     */
    @Select("select * from xt_oper_user where usertype = 0 and uname like concat('%',#{name},'%')")
    List<XtOperUser> vagueSelectAllPlatformAdministrator(String name);

    /**
     * 决定系统平台管理员是否停职和恢复
     * @param uoperUserid 系统平台管理员id
     * @param delFlag 删除标志位 0：在职（正常）状态 2：停职（删除）状态
     * @return
     */
    @Update("update xt_oper_user SET del_flag = #{delFlag} ,ucreatedate = #{ucreatedate} WHERE uoper_userid = #{uoperUserid};")
    int suspensionAndReinstatementSystemPlatformAdministrator(String uoperUserid,String delFlag,long ucreatedate);

//    /**
//     * 注册后的渲染
//     */
//    @Select("select * from xt_oper_user where  uloginname = #{logname}")
//    XtOperUser renderAdministratorRegistration(String logname);

    @Select("select * from  xt_oper_user  where uoper_userid = #{id}")
    XtOperUser viewAdministratorDetailsByID(String id);
}
