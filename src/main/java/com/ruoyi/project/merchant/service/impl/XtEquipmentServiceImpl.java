package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.merchant.domain.XtEquipment;
import com.ruoyi.project.merchant.mapper.XtEquipmentMapper;
import com.ruoyi.project.merchant.service.IXtEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtEquipmentServiceImpl implements IXtEquipmentService
{
    @Autowired
    private XtEquipmentMapper xtEquipmentMapper;

    /**
     * 查询设备
     *
     * @param uequipmentid 设备主键
     * @return 设备
     */
    @Override
    public XtEquipment selectXtEquipmentByUequipmentid(String uequipmentid)
    {
        return xtEquipmentMapper.selectXtEquipmentByUequipmentid(uequipmentid);
    }

    /**
     * 查询设备列表
     *
     * @param xtEquipment 设备
     * @return 设备
     */
    @Override
    public List<XtEquipment> selectXtEquipmentList(XtEquipment xtEquipment)
    {
        return xtEquipmentMapper.selectXtEquipmentList(xtEquipment);
    }

    /**
     * 新增设备
     *
     * @param xtEquipment 设备
     * @return 结果
     */
    @Override
    public int insertXtEquipment(XtEquipment xtEquipment)
    {
        xtEquipment.setUequipmentid(IdUtil.getSnowflakeNextIdStr());
        return xtEquipmentMapper.insertXtEquipment(xtEquipment);
    }

    /**
     * 修改设备
     *
     * @param xtEquipment 设备
     * @return 结果
     */
    @Override
    public int updateXtEquipment(XtEquipment xtEquipment)
    {
        return xtEquipmentMapper.updateXtEquipment(xtEquipment);
    }

    /**
     * 批量删除设备
     *
     * @param uequipmentids 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteXtEquipmentByUequipmentids(String[] uequipmentids)
    {
        return xtEquipmentMapper.deleteXtEquipmentByUequipmentids(uequipmentids);
    }

    /**
     * 删除设备信息
     *
     * @param uequipmentid 设备主键
     * @return 结果
     */
    @Override
    public int deleteXtEquipmentByUequipmentid(String uequipmentid)
    {
        return xtEquipmentMapper.deleteXtEquipmentByUequipmentid(uequipmentid);
    }

    @Override
    public void updateEquipmentStatus(String uequipmentid, String status) {
        XtEquipment equipment = new XtEquipment();
        equipment.setUequipmentid(uequipmentid);
        equipment.setUequipmentStatus(status);
        int count = xtEquipmentMapper.updateXtEquipment(equipment);
        if (count == 0){
            throw new ServiceException("操作失败");
        }
    }
}
