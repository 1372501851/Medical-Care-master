package com.ruoyi.project.im.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.im.mapper.XtGroupMapper;
import com.ruoyi.project.im.domain.XtGroup;
import com.ruoyi.project.im.service.IXtGroupService;

/**
 * 群 （科室搭建一个群，值班人在群，不值班人不在群了）Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-14
 */
@Service
public class XtGroupServiceImpl implements IXtGroupService
{
    @Autowired
    private XtGroupMapper xtGroupMapper;

    /**
     * 查询群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param ugroupid 群 （科室搭建一个群，值班人在群，不值班人不在群了）主键
     * @return 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     */
    @Override
    public XtGroup selectXtGroupByUgroupid(String ugroupid)
    {
        return xtGroupMapper.selectXtGroupByUgroupid(ugroupid);
    }

    /**
     * 查询群 （科室搭建一个群，值班人在群，不值班人不在群了）列表
     *
     * @param xtGroup 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     * @return 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     */
    @Override
    public List<XtGroup> selectXtGroupList(XtGroup xtGroup)
    {
        return xtGroupMapper.selectXtGroupList(xtGroup);
    }

    /**
     * 新增群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param xtGroup 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     * @return 结果
     */
    @Override
    public int insertXtGroup(XtGroup xtGroup)
    {
        return xtGroupMapper.insertXtGroup(xtGroup);
    }

    /**
     * 修改群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param xtGroup 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     * @return 结果
     */
    @Override
    public int updateXtGroup(XtGroup xtGroup)
    {
//        xtGroup.setUpdateTime(DateUtils.getNowDate());
        return xtGroupMapper.updateXtGroup(xtGroup);
    }

    /**
     * 批量删除群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param ugroupids 需要删除的群 （科室搭建一个群，值班人在群，不值班人不在群了）主键
     * @return 结果
     */
    @Override
    public int deleteXtGroupByUgroupids(String[] ugroupids)
    {
        return xtGroupMapper.deleteXtGroupByUgroupids(ugroupids);
    }

    /**
     * 删除群 （科室搭建一个群，值班人在群，不值班人不在群了）信息
     *
     * @param ugroupid 群 （科室搭建一个群，值班人在群，不值班人不在群了）主键
     * @return 结果
     */
    @Override
    public int deleteXtGroupByUgroupid(String ugroupid)
    {
        return xtGroupMapper.deleteXtGroupByUgroupid(ugroupid);
    }

    @Override
    public List<XtGroup> getGroups(String userId) {
        List<XtGroup> xtGroups = xtGroupMapper.selectXtGroupListByUserId(userId);
        return xtGroups;
    }
}
