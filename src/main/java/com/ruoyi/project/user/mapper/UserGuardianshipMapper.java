package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.UserGuardianship;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-26
 */
public interface UserGuardianshipMapper
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
    public List<UserGuardianship> selectUserGuardianshipList(UserGuardianship userGuardianship);

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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserGuardianshipById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserGuardianshipByIds(String[] ids);

    UserGuardianship selectUserGuardianshipByLoginName(String userId,String guardianshipPhone);
}
