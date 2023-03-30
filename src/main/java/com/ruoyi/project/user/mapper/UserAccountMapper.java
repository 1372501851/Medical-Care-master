package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.UserAccount;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-01
 */
public interface UserAccountMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserAccount selectUserAccountById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userAccount 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserAccount> selectUserAccountList(UserAccount userAccount);

    /**
     * 新增【请填写功能名称】
     *
     * @param userAccount 【请填写功能名称】
     * @return 结果
     */
    public int insertUserAccount(UserAccount userAccount);

    /**
     * 修改【请填写功能名称】
     *
     * @param userAccount 【请填写功能名称】
     * @return 结果
     */
    public int updateUserAccount(UserAccount userAccount);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserAccountById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserAccountByIds(String[] ids);

    UserAccount selectUserAccountByUserId(String userid);
}
