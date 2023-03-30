package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.merchant.domain.XtEquipment;
import com.ruoyi.project.merchant.domain.XtEquipmentUser;
import com.ruoyi.project.merchant.domain.vo.EquipmentAppVo;
import com.ruoyi.project.merchant.mapper.XtEquipmentMapper;
import com.ruoyi.project.merchant.mapper.XtEquipmentUserMapper;
import com.ruoyi.project.merchant.service.IXtEquipmentUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备与用户关系Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtEquipmentUserServiceImpl implements IXtEquipmentUserService
{
    @Autowired
    private XtEquipmentUserMapper xtEquipmentUserMapper;

    @Autowired
    private XtEquipmentMapper equipmentMapper;

    /**
     * 查询设备与用户关系
     *
     * @param uequipmentUserid 设备与用户关系主键
     * @return 设备与用户关系
     */
    @Override
    public XtEquipmentUser selectXtEquipmentUserByUequipmentUserid(String uequipmentUserid)
    {
        return xtEquipmentUserMapper.selectXtEquipmentUserByUequipmentUserid(uequipmentUserid);
    }

    /**
     * 查询设备与用户关系列表
     *
     * @param xtEquipmentUser 设备与用户关系
     * @return 设备与用户关系
     */
    @Override
    public List<XtEquipmentUser> selectXtEquipmentUserList(XtEquipmentUser xtEquipmentUser)
    {
        return xtEquipmentUserMapper.selectXtEquipmentUserList(xtEquipmentUser);
    }

    /**
     * 新增设备与用户关系
     *
     * @param xtEquipmentUser 设备与用户关系
     * @return 结果
     */
    @Override
    public int insertXtEquipmentUser(XtEquipmentUser xtEquipmentUser)
    {
        xtEquipmentUser.setUequipmentUserid(IdUtil.getSnowflakeNextIdStr());
        String userId = SecurityUtils.getAppLoginUser().getUserId();
        xtEquipmentUser.setUserid(userId);

        //根据设备id查看该设备的类型id
        XtEquipment xtEquipment = equipmentMapper.selectXtEquipmentByUequipmentid(xtEquipmentUser.getUquipmentid());
        xtEquipmentUser.setUequipmentTypeid(xtEquipment.getUequipmentTypeid());

        return xtEquipmentUserMapper.insertXtEquipmentUser(xtEquipmentUser);
    }

    /**
     * 修改设备与用户关系
     *
     * @param xtEquipmentUser 设备与用户关系
     * @return 结果
     */
    @Override
    public int updateXtEquipmentUser(XtEquipmentUser xtEquipmentUser)
    {
        return xtEquipmentUserMapper.updateXtEquipmentUser(xtEquipmentUser);
    }

    /**
     * 批量删除设备与用户关系
     *
     * @param uequipmentUserids 需要删除的设备与用户关系主键
     * @return 结果
     */
    @Override
    public int deleteXtEquipmentUserByUequipmentUserids(String[] uequipmentUserids)
    {
        return xtEquipmentUserMapper.deleteXtEquipmentUserByUequipmentUserids(uequipmentUserids);
    }

    /**
     * 删除设备与用户关系信息
     *
     * @param uequipmentUserid 设备与用户关系主键
     * @return 结果
     */
    @Override
    public int deleteXtEquipmentUserByUequipmentUserid(String uequipmentUserid)
    {
        return xtEquipmentUserMapper.deleteXtEquipmentUserByUequipmentUserid(uequipmentUserid);
    }

    @Override
    public List<EquipmentAppVo> selectEquipments(String uequipmentTypeid) {

        String userId = SecurityUtils.getAppLoginUser().getUserId();

        //根据用户id和设备类型id,找出相应的设备
        XtEquipmentUser xtEquipmentUser = new XtEquipmentUser();
        xtEquipmentUser.setUequipmentTypeid(uequipmentTypeid);
        xtEquipmentUser.setUserid(userId);
        List<XtEquipmentUser> xtEquipmentUsers = xtEquipmentUserMapper.selectXtEquipmentUserList(xtEquipmentUser);
        List<EquipmentAppVo> equipmentAppVos = new ArrayList<>();

        for (int i = 0; i < xtEquipmentUsers.size(); i++) {
            EquipmentAppVo equipmentAppVo = new EquipmentAppVo();
            XtEquipment xtEquipment = equipmentMapper.selectXtEquipmentByUequipmentid(xtEquipmentUsers.get(i).getUquipmentid());
            equipmentAppVo.setUequipmentid(xtEquipment.getUequipmentid());
            equipmentAppVo.setUequipmentname(xtEquipment.getUequipmentname());
            equipmentAppVos.add(equipmentAppVo);
        }
        return equipmentAppVos;
    }
}
