package com.ruoyi.project.im.service;

import java.util.List;
import com.ruoyi.project.im.domain.XtGroup;

/**
 * 群 （科室搭建一个群，值班人在群，不值班人不在群了）Service接口
 *
 * @author ruoyi
 * @date 2022-10-14
 */
public interface IXtGroupService
{
    /**
     * 查询群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param ugroupid 群 （科室搭建一个群，值班人在群，不值班人不在群了）主键
     * @return 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     */
    public XtGroup selectXtGroupByUgroupid(String ugroupid);

    /**
     * 查询群 （科室搭建一个群，值班人在群，不值班人不在群了）列表
     *
     * @param xtGroup 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     * @return 群 （科室搭建一个群，值班人在群，不值班人不在群了）集合
     */
    public List<XtGroup> selectXtGroupList(XtGroup xtGroup);

    /**
     * 新增群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param xtGroup 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     * @return 结果
     */
    public int insertXtGroup(XtGroup xtGroup);

    /**
     * 修改群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param xtGroup 群 （科室搭建一个群，值班人在群，不值班人不在群了）
     * @return 结果
     */
    public int updateXtGroup(XtGroup xtGroup);

    /**
     * 批量删除群 （科室搭建一个群，值班人在群，不值班人不在群了）
     *
     * @param ugroupids 需要删除的群 （科室搭建一个群，值班人在群，不值班人不在群了）主键集合
     * @return 结果
     */
    public int deleteXtGroupByUgroupids(String[] ugroupids);

    /**
     * 删除群 （科室搭建一个群，值班人在群，不值班人不在群了）信息
     *
     * @param ugroupid 群 （科室搭建一个群，值班人在群，不值班人不在群了）主键
     * @return 结果
     */
    public int deleteXtGroupByUgroupid(String ugroupid);


    /**
     * 查询用户所在的群
     *
     * */
    List<XtGroup> getGroups(String userId);
}
