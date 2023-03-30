package com.ruoyi.project.area.mapper;

import java.util.List;

import com.ruoyi.project.area.domain.PtArea;

/**
 * 地区Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-11
 */
public interface PtAreaMapper 
{
    /**
     * 查询地区
     * 
     * @param uareaid 地区主键
     * @return 地区
     */
    public PtArea selectPtAreaByUareaid(String uareaid);

    /**
     * 查询地区列表
     * 
     * @param ptArea 地区
     * @return 地区集合
     */
    public List<PtArea> selectPtAreaList(PtArea ptArea);

    /**
     * 新增地区
     * 
     * @param ptArea 地区
     * @return 结果
     */
    public int insertPtArea(PtArea ptArea);

    /**
     * 修改地区
     * 
     * @param ptArea 地区
     * @return 结果
     */
    public int updatePtArea(PtArea ptArea);

    /**
     * 删除地区
     * 
     * @param uareaid 地区主键
     * @return 结果
     */
    public int deletePtAreaByUareaid(String uareaid);

    /**
     * 批量删除地区
     * 
     * @param uareaids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePtAreaByUareaids(String[] uareaids);
}
