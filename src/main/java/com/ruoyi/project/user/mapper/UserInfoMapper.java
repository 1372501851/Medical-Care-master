package com.ruoyi.project.user.mapper;

import java.util.List;
import com.ruoyi.project.user.domain.UserInfo;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-25
 */
public interface UserInfoMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserInfo selectUserInfoById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增【请填写功能名称】
     *
     * @param userInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 修改【请填写功能名称】
     *
     * @param userInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserInfoById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserInfoByIds(String[] ids);

    UserInfo selectUserInfoByUserId(String userid);
}
