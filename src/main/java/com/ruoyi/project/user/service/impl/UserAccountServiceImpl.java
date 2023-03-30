package com.ruoyi.project.user.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.user.domain.UserAccount;
import com.ruoyi.project.user.mapper.UserAccountMapper;
import com.ruoyi.project.user.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-01
 */
@Service
public class UserAccountServiceImpl implements IUserAccountService
{
    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserAccount selectUserAccountById(String id)
    {
        return userAccountMapper.selectUserAccountById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userAccount 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserAccount> selectUserAccountList(UserAccount userAccount)
    {
        return userAccountMapper.selectUserAccountList(userAccount);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param userAccount 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserAccount(UserAccount userAccount)
    {
        return userAccountMapper.insertUserAccount(userAccount);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param userAccount 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserAccount(UserAccount userAccount)
    {
        return userAccountMapper.updateUserAccount(userAccount);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserAccountByIds(String[] ids)
    {
        return userAccountMapper.deleteUserAccountByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserAccountById(String id)
    {
        return userAccountMapper.deleteUserAccountById(id);
    }

    @Override
    public UserAccount selectUserAccountByUserId() {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        UserAccount account = userAccountMapper.selectUserAccountByUserId(userid);
        return account;
    }
}
