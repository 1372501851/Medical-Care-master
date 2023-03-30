package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.UserGuardianship;
import com.ruoyi.project.user.domain.vo.UserGuardianshipVo;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-11-26
 */
public interface IUserGuardianshipService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserGuardianship selectUserGuardianshipById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userGuardianship 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserGuardianshipVo> selectUserGuardianshipList(UserGuardianship userGuardianship);

    /**
     * 新增【请填写功能名称】
     *
     * @param userGuardianship 【请填写功能名称】
     * @return 结果
     */
    public int insertUserGuardianship(UserGuardianship userGuardianship);

    /**
     * 修改【请填写功能名称】
     *
     * @param userGuardianship 【请填写功能名称】
     * @return 结果
     */
    public int updateUserGuardianship(UserGuardianship userGuardianship);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteUserGuardianshipByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserGuardianshipById(String id);
}
