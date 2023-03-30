package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.merchant.domain.XtRoom;
import com.ruoyi.project.merchant.mapper.XtBedNoMapper;
import com.ruoyi.project.merchant.mapper.XtRoomMapper;
import com.ruoyi.project.merchant.service.IXtRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 病房Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtRoomServiceImpl implements IXtRoomService
{
    @Autowired
    private XtRoomMapper xtRoomMapper;

    @Autowired
    private XtBedNoMapper xtBedNoMapper;

    /**
     * 查询病房
     *
     * @param uroomid 病房主键
     * @return 病房
     */
    @Override
    public XtRoom selectXtRoomByUroomid(String uroomid)
    {
        return xtRoomMapper.selectXtRoomByUroomid(uroomid);
    }

    /**
     * 查询病房列表
     *
     * @param xtRoom 病房
     * @return 病房
     */
    @Override
    public List<XtRoom> selectXtRoomList(XtRoom xtRoom)
    {
        //病房号不能为空
        if (xtRoom.getUcompid() == null && xtRoom.getUcompid().equals("")){
            throw new ServiceException("请指定需要查询的商家");
        }
        return xtRoomMapper.selectXtRoomList(xtRoom);
    }

    /**
     * 新增病房
     *
     * @param xtRoom 病房
     * @return 结果
     */
    @Override
    public int insertXtRoom(XtRoom xtRoom)
    {
//        int count = xtRoomMapper.selectXtRoomByName(xtRoom.getUcompid(), xtRoom.getUbuildingname());
//        if (count > 0){
//            throw new ServiceException("楼栋名称已存在");
//        }

        int count2 = xtRoomMapper.selectXtRoomByRoomNo(xtRoom.getUcompid(), xtRoom.getUbuildingname(), xtRoom.getUfloorNo(), xtRoom.getUroomNo());
        if (count2 > 0){
            throw new ServiceException("该楼层房间编号已存在");
        }
        xtRoom.setUroomid(IdUtil.getSnowflakeNextIdStr());
        return xtRoomMapper.insertXtRoom(xtRoom);
    }

    /**
     * 修改病房
     *
     * @param xtRoom 病房
     * @return 结果
     */
    @Override
    public int updateXtRoom(XtRoom xtRoom)
    {

        int count2 = xtRoomMapper.selectXtRoomByRoomNo(xtRoom.getUcompid(), xtRoom.getUbuildingname(), xtRoom.getUfloorNo(), xtRoom.getUroomNo());
        if (count2 > 0){
            throw new ServiceException("该楼层房间编号已存在");
        }
        return xtRoomMapper.updateXtRoom(xtRoom);
    }

    /**
     * 批量删除病房
     *
     * @param uroomids 需要删除的病房主键
     * @return 结果
     */
    @Override
    public int deleteXtRoomByUroomids(String[] uroomids)
    {
        int count = 0;
        for (int i = 0; i < uroomids.length; i++) {
            if(hasUseBed(uroomids[i])){
                throw new ServiceException("病房中有床位正在使用");
            }
            xtRoomMapper.deleteXtRoomByUroomid(uroomids[i]);
            count ++ ;
        }
        return count;
    }

    private boolean hasUseBed(String roomId)
    {
        int result = xtBedNoMapper.hasUseBed(roomId);
        return result > 0;
    }


    /**
     * 删除病房信息
     *
     * @param uroomid 病房主键
     * @return 结果
     */
    @Override
    public int deleteXtRoomByUroomid(String uroomid)
    {
        return xtRoomMapper.deleteXtRoomByUroomid(uroomid);
    }
}
