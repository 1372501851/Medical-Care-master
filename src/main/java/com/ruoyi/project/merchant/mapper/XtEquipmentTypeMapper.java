package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtEquipmentType;

import java.util.List;

/**
 * 设备类型Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtEquipmentTypeMapper
{
    /**
     * 查询设备类型
     *
     * @param uequipmentTypeid 设备类型主键
     * @return 设备类型
     */
    public XtEquipmentType selectXtEquipmentTypeByUequipmentTypeid(String uequipmentTypeid);

    /**
     * 查询设备类型列表
     *
     * @param xtEquipmentType 设备类型
     * @return 设备类型集合
     */
    public List<XtEquipmentType> selectXtEquipmentTypeList(XtEquipmentType xtEquipmentType);

    /**
     * 新增设备类型
     *
     * @param xtEquipmentType 设备类型
     * @return 结果
     */
    public int insertXtEquipmentType(XtEquipmentType xtEquipmentType);

    /**
     * 修改设备类型
     *
     * @param xtEquipmentType 设备类型
     * @return 结果
     */
    public int updateXtEquipmentType(XtEquipmentType xtEquipmentType);

    /**
     * 删除设备类型
     *
     * @param uequipmentTypeid 设备类型主键
     * @return 结果
     */
    public int deleteXtEquipmentTypeByUequipmentTypeid(String uequipmentTypeid);

    /**
     * 批量删除设备类型
     *
     * @param uequipmentTypeids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtEquipmentTypeByUequipmentTypeids(String[] uequipmentTypeids);

    List<XtEquipmentType> selectXtEquipmentTypeListAll();

}
