package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtEquipment;

import java.util.List;

/**
 * 设备Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtEquipmentService
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
     * 批量删除设备
     *
     * @param uequipmentids 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteXtEquipmentByUequipmentids(String[] uequipmentids);

    /**
     * 删除设备信息
     *
     * @param uequipmentid 设备主键
     * @return 结果
     */
    public int deleteXtEquipmentByUequipmentid(String uequipmentid);

    void updateEquipmentStatus(String uequipmentid, String status);
}
