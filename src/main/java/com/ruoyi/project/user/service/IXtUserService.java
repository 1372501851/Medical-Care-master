package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.domain.query.UserQuery;
import com.ruoyi.project.user.domain.register.RegisterDto;
import com.ruoyi.project.user.domain.vo.UserSelect;

import java.util.List;

/**
 * 所有用户登录（所有用户，医生、护士、患者、家属）Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtUserService
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
     * 批量删除所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param userids 需要删除的所有用户登录（所有用户，医生、护士、患者、家属）主键集合
     * @return 结果
     */
    public int deleteXtUserByUserids(String[] userids);

    /**
     * 删除所有用户登录（所有用户，医生、护士、患者、家属）信息
     *
     * @param userid 所有用户登录（所有用户，医生、护士、患者、家属）主键
     * @return 结果
     */
    public int deleteXtUserByUserid(String userid);

    XtUser selectXtUserByUserLoginName(String username);

    String register(RegisterDto registerDto);

    String login(String username, String password);


    String appLogin(String username, String password);

    List<UserSelect> getSelectList();

//    List<XtUser> selectXtUserListByQuery(UserQuery userQuery);
}
