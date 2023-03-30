package com.ruoyi.project.find.mapper;

import com.ruoyi.project.find.domain.FindModel;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-29
 */
public interface FindModelMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public FindModel selectFindModelById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param findModel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FindModel> selectFindModelList(FindModel findModel);

    /**
     * 新增【请填写功能名称】
     *
     * @param findModel 【请填写功能名称】
     * @return 结果
     */
    public int insertFindModel(FindModel findModel);

    /**
     * 修改【请填写功能名称】
     *
     * @param findModel 【请填写功能名称】
     * @return 结果
     */
    public int updateFindModel(FindModel findModel);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteFindModelById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFindModelByIds(String[] ids);

    List<FindModel> selectFindRobList(FindModel findModel);
}
