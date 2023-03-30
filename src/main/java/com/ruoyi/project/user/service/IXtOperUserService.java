package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.XtOperUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 系统管理员Service接口
 *
 * @author ruoyi
 * @date 2022-10-27
 */
public interface IXtOperUserService
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
    public int insertXtOperUser(XtOperUser xtOperUser , HttpServletRequest request);

    /**
     * 修改系统管理员
     *
     * @param xtOperUser 系统管理员
     * @return 结果
     */
    public int updateXtOperUser(XtOperUser xtOperUser);

    /**
     * 批量删除系统管理员
     *
     * @param uoperUserids 需要删除的系统管理员主键集合
     * @return 结果
     */
    public int deleteXtOperUserByUoperUserids(String[] uoperUserids);

    /**
     * 删除系统管理员信息
     *
     * @param uoperUserid 系统管理员主键
     * @return 结果
     */
    public int deleteXtOperUserByUoperUserid(String uoperUserid);

    XtOperUser selectXtOperUserByUserLoginName(String username);

    String register(String username, String password);

    String login(String username, String password);

    int insertUser(XtOperUser user);

    String checkUserNameUnique(XtOperUser user);

    String checkPhoneUnique(XtOperUser user);

    String checkEmailUnique(XtOperUser user);


    /**
     * 审核平台管理员注册
     * @param status 0：审核中 1：通过 2：驳回
     * @return
     */
    int auditSystemPlatformAdministratorRegistration(String status , String id ) ;

    /**
     * 查询所有平台系统管理员
     */
    List<XtOperUser> selectAllPlatformAdministrator();

    /**
     * 模糊查询所有平台系统管理员（姓名）
     */
    List<XtOperUser> vagueSelectAllPlatformAdministrator(String name);

    /**
     * 决定系统平台管理员是否停职和恢复
     * @param uoperUserid 系统平台管理员id
     * @param delFlag 删除标志位 0：在职（正常）状态 1：停职（删除）状态
     * @return
     */
    int suspensionAndReinstatementSystemPlatformAdministrator(String uoperUserid,String delFlag);

    /**
     *
     * 注册后的渲染
     *
     * */
//    XtOperUser renderAdministratorRegistration(HttpServletRequest request);

    /**
     * 查看管理员详细信息
     * @param id
     * @return
     */
    XtOperUser viewAdministratorDetailsByID(String id);

//    /**
//     * 审核平台管理员停职和恢复
//     * @param delFlag 0：审核中 1：通过 2：驳回
//     * @return
//     */
//    int auditAdministratorIsSuspendedOrRestored(String delFlag , String OperUserxtId ) ;
}
