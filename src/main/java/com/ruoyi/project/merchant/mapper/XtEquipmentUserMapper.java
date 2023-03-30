package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtEquipmentUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 设备与用户关系Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Mapper
public interface XtEquipmentUserMapper
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
     * 删除设备与用户关系
     *
     * @param uequipmentUserid 设备与用户关系主键
     * @return 结果
     */
    public int deleteXtEquipmentUserByUequipmentUserid(String uequipmentUserid);

    /**
     * 批量删除设备与用户关系
     *
     * @param uequipmentUserids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtEquipmentUserByUequipmentUserids(String[] uequipmentUserids);


    @Update("UPDATE xt_equipment_type set ustatus = #{status} Where uequipment_typeid = #{typeId}")
    Integer updateEquipmentStatus(String status , String typeId);
}
