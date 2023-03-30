package com.ruoyi.project.global.mapper;

import com.ruoyi.project.global.domain.XtPosition;

import java.util.List;

/**
 * 职位 提供选择（传职位名称给用户信息）Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtPositionMapper
{
    /**
     * 查询职位 提供选择（传职位名称给用户信息）
     *
     * @param upositionname 职位 提供选择（传职位名称给用户信息）主键
     * @return 职位 提供选择（传职位名称给用户信息）
     */
    public XtPosition selectXtPositionByUpositionname(String upositionname);

    /**
     * 查询职位 提供选择（传职位名称给用户信息）列表
     *
     * @param xtPosition 职位 提供选择（传职位名称给用户信息）
     * @return 职位 提供选择（传职位名称给用户信息）集合
     */
    public List<XtPosition> selectXtPositionList(XtPosition xtPosition);

    /**
     * 新增职位 提供选择（传职位名称给用户信息）
     *
     * @param xtPosition 职位 提供选择（传职位名称给用户信息）
     * @return 结果
     */
    public int insertXtPosition(XtPosition xtPosition);

    /**
     * 修改职位 提供选择（传职位名称给用户信息）
     *
     * @param xtPosition 职位 提供选择（传职位名称给用户信息）
     * @return 结果
     */
    public int updateXtPosition(XtPosition xtPosition);

    /**
     * 删除职位 提供选择（传职位名称给用户信息）
     *
     * @param upositionname 职位 提供选择（传职位名称给用户信息）主键
     * @return 结果
     */
    public int deleteXtPositionByUpositionname(String upositionname);

    /**
     * 批量删除职位 提供选择（传职位名称给用户信息）
     *
     * @param upositionnames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtPositionByUpositionIds(String[] upositionIds);

    XtPosition selectXtPositionByUpositionId(String upositionid);
}
