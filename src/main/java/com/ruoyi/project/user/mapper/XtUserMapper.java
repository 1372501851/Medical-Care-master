package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.XtOperUser;
import com.ruoyi.project.user.domain.XtUser;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 所有用户登录（所有用户，医生、护士、患者、家属）Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Repository
public interface XtUserMapper
{
    /**
     * 查询所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param userid 所有用户登录（所有用户，医生、护士、患者、家属）主键
     * @return 所有用户登录（所有用户，医生、护士、患者、家属）
     */
    public XtUser selectXtUserByUserid(String userid);

    /**
     * 查询所有用户登录（所有用户，医生、护士、患者、家属）列表
     *
     * @param xtUser 所有用户登录（所有用户，医生、护士、患者、家属）
     * @return 所有用户登录（所有用户，医生、护士、患者、家属）集合
     */
    public List<XtUser> selectXtUserList(XtUser xtUser);

    /**
     * 新增所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param xtUser 所有用户登录（所有用户，医生、护士、患者、家属）
     * @return 结果
     */
    public int insertXtUser(XtUser xtUser);

    /**
     * 修改所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param xtUser 所有用户登录（所有用户，医生、护士、患者、家属）
     * @return 结果
     */
    public int updateXtUser(XtUser xtUser);

    /**
     * 删除所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param userid 所有用户登录（所有用户，医生、护士、患者、家属）主键
     * @return 结果
     */
    public int deleteXtUserByUserid(String userid);

    /**
     * 批量删除所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param userids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtUserByUserids(String[] userids);

    XtUser selectXtUserByUserLoginName(String loginname);

    @Select("SELECT * FROM xt_user WHERE phone = #{shouji}")
    XtUser selectXtUserByshouji(String shouji);

    List<XtUser> selectCommmonXtUserList(XtUser xtUser);

    @Select("SELECT * FROM xt_oper_user where uoper_userid = #{id}")
    XtOperUser getAdministratorDetails(String id );

    @Select("SELECT * FROM xt_user WHERE usex = '7'")
    List<XtUser> kuaca();

    @Update("UPDATE xt_user set jiedanstatus = #{s} WHERE userid = #{id}")
    Integer updateJiedanstatus(String s , String id);

    @Update("UPDATE xt_user set upassword = #{s} WHERE userid = #{id}")
    Integer updatePassWord(String s , String id);
}
