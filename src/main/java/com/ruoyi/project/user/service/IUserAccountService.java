package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.UserAccount;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-12-01
 */
public interface IUserAccountService
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
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteUserAccountByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserAccountById(String id);

    UserAccount selectUserAccountByUserId();

}
