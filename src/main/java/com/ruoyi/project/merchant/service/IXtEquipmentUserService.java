package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtEquipmentUser;
import com.ruoyi.project.merchant.domain.vo.EquipmentAppVo;

import java.util.List;

/**
 * 设备与用户关系Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtEquipmentUserService
{
    /**
     * 查询设备与用户关系
     *
     * @param uequipmentUserid 设备与用户关系主键
     * @return 设备与用户关系
     */
    public XtEquipmentUser selectXtEquipmentUserByUequipmentUserid(String uequipmentUserid);

    /**
     * 查询设备与用户关系列表
     *
     * @param xtEquipmentUser 设备与用户关系
     * @return 设备与用户关系集合
     */
    public List<XtEquipmentUser> selectXtEquipmentUserList(XtEquipmentUser xtEquipmentUser);

    /**
     * 新增设备与用户关系
     *
     * @param xtEquipmentUser 设备与用户关系
     * @return 结果
     */
    public int insertXtEquipmentUser(XtEquipmentUser xtEquipmentUser);

    /**
     * 修改设备与用户关系
     *
     * @param xtEquipmentUser 设备与用户关系
     * @return 结果
     */
    public int updateXtEquipmentUser(XtEquipmentUser xtEquipmentUser);

    /**
     * 批量删除设备与用户关系
     *
     * @param uequipmentUserids 需要删除的设备与用户关系主键集合
     * @return 结果
     */
    public int deleteXtEquipmentUserByUequipmentUserids(String[] uequipmentUserids);

    /**
     * 删除设备与用户关系信息
     *
     * @param uequipmentUserid 设备与用户关系主键
     * @return 结果
     */
    public int deleteXtEquipmentUserByUequipmentUserid(String uequipmentUserid);

    List<EquipmentAppVo> selectEquipments(String uequipmentTypeid);
}
