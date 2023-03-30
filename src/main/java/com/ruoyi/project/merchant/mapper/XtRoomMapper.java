package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtRoom;

import java.util.List;

/**
 * 病房Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtRoomMapper
{
    /**
     * 查询病房
     *
     * @param uroomid 病房主键
     * @return 病房
     */
    public XtRoom selectXtRoomByUroomid(String uroomid);

    /**
     * 查询病房列表
     *
     * @param xtRoom 病房
     * @return 病房集合
     */
    public List<XtRoom> selectXtRoomList(XtRoom xtRoom);

    /**
     * 新增病房
     *
     * @param xtRoom 病房
     * @return 结果
     */
    public int insertXtRoom(XtRoom xtRoom);

    /**
     * 修改病房
     *
     * @param xtRoom 病房
     * @return 结果
     */
    public int updateXtRoom(XtRoom xtRoom);

    /**
     * 删除病房
     *
     * @param uroomid 病房主键
     * @return 结果
     */
    public int deleteXtRoomByUroomid(String uroomid);

    /**
     * 批量删除病房
     *
     * @param uroomids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtRoomByUroomids(String[] uroomids);

    int selectXtRoomByName(String ucompid,String ubuildingname);

    int selectXtRoomByRoomNo(String ucompid, String ubuildingname, String ufloorNo, String uroomNo);
}
