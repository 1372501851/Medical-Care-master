package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtEquipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 设备Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Mapper
public interface XtEquipmentMapper
{
    /**
     * 查询设备
     *
     * @param uequipmentid 设备主键
     * @return 设备
     */
    public XtEquipment selectXtEquipmentByUequipmentid(String uequipmentid);

    /**
     * 查询设备列表
     *
     * @param xtEquipment 设备
     * @return 设备集合
     */
    public List<XtEquipment> selectXtEquipmentList(XtEquipment xtEquipment);

    /**
     * 新增设备
     *
     * @param xtEquipment 设备
     * @return 结果
     */
    public int insertXtEquipment(XtEquipment xtEquipment);

    /**
     * 修改设备
     *
     * @param xtEquipment 设备
     * @return 结果
     */
    public int updateXtEquipment(XtEquipment xtEquipment);

    /**
     * 删除设备
     *
     * @param uequipmentid 设备主键
     * @return 结果
     */
    public int deleteXtEquipmentByUequipmentid(String uequipmentid);

    /**
     * 批量删除设备
     *
     * @param uequipmentids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtEquipmentByUequipmentids(String[] uequipmentids);

    @Update("UPDATE xt_equipment set dripping_speed_min = #{min},dripping_speed_max = #{max} ")
    Integer shuyequntishezhi(Integer min , Integer max);
}
