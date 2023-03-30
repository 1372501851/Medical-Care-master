package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.UserComp;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-04
 */
@Repository
public interface UserCompMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserComp selectUserCompById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userComp 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserComp> selectUserCompList(UserComp userComp);

    /**
     * 新增【请填写功能名称】
     *
     * @param userComp 【请填写功能名称】
     * @return 结果
     */
    public int insertUserComp(UserComp userComp);

    /**
     * 修改【请填写功能名称】
     *
     * @param userComp 【请填写功能名称】
     * @return 结果
     */
    public int updateUserComp(UserComp userComp);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserCompById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserCompByIds(String[] ids);

    List<UserComp> selectUserCompByCompId(String compId);
}
