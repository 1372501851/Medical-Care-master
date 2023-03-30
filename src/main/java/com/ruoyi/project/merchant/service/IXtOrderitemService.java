package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtOrderitem;

import java.util.List;

/**
 * 订单详情Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtOrderitemService
{
    /**
     * 查询订单详情
     *
     * @param uorderitemid 订单详情主键
     * @return 订单详情
     */
    public XtOrderitem selectXtOrderitemByUorderitemid(String uorderitemid);

    /**
     * 查询订单详情列表
     *
     * @param xtOrderitem 订单详情
     * @return 订单详情集合
     */
    public List<XtOrderitem> selectXtOrderitemList(XtOrderitem xtOrderitem);

    /**
     * 新增订单详情
     *
     * @param xtOrderitem 订单详情
     * @return 结果
     */
    public int insertXtOrderitem(XtOrderitem xtOrderitem);

    /**
     * 修改订单详情
     *
     * @param xtOrderitem 订单详情
     * @return 结果
     */
    public int updateXtOrderitem(XtOrderitem xtOrderitem);

    /**
     * 批量删除订单详情
     *
     * @param uorderitemids 需要删除的订单详情主键集合
     * @return 结果
     */
    public int deleteXtOrderitemByUorderitemids(String[] uorderitemids);

    /**
     * 删除订单详情信息
     *
     * @param uorderitemid 订单详情主键
     * @return 结果
     */
    public int deleteXtOrderitemByUorderitemid(String uorderitemid);
}
