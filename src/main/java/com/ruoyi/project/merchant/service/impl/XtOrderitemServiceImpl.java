package com.ruoyi.project.merchant.service.impl;

import com.ruoyi.project.merchant.domain.XtOrderitem;
import com.ruoyi.project.merchant.mapper.XtOrderitemMapper;
import com.ruoyi.project.merchant.service.IXtOrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单详情Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtOrderitemServiceImpl implements IXtOrderitemService
{
    @Autowired
    private XtOrderitemMapper xtOrderitemMapper;

    /**
     * 查询订单详情
     *
     * @param uorderitemid 订单详情主键
     * @return 订单详情
     */
    @Override
    public XtOrderitem selectXtOrderitemByUorderitemid(String uorderitemid)
    {
        return xtOrderitemMapper.selectXtOrderitemByUorderitemid(uorderitemid);
    }

    /**
     * 查询订单详情列表
     *
     * @param xtOrderitem 订单详情
     * @return 订单详情
     */
    @Override
    public List<XtOrderitem> selectXtOrderitemList(XtOrderitem xtOrderitem)
    {
        return xtOrderitemMapper.selectXtOrderitemList(xtOrderitem);
    }

    /**
     * 新增订单详情
     *
     * @param xtOrderitem 订单详情
     * @return 结果
     */
    @Override
    public int insertXtOrderitem(XtOrderitem xtOrderitem)
    {
        return xtOrderitemMapper.insertXtOrderitem(xtOrderitem);
    }

    /**
     * 修改订单详情
     *
     * @param xtOrderitem 订单详情
     * @return 结果
     */
    @Override
    public int updateXtOrderitem(XtOrderitem xtOrderitem)
    {
        return xtOrderitemMapper.updateXtOrderitem(xtOrderitem);
    }

    /**
     * 批量删除订单详情
     *
     * @param uorderitemids 需要删除的订单详情主键
     * @return 结果
     */
    @Override
    public int deleteXtOrderitemByUorderitemids(String[] uorderitemids)
    {
        return xtOrderitemMapper.deleteXtOrderitemByUorderitemids(uorderitemids);
    }

    /**
     * 删除订单详情信息
     *
     * @param uorderitemid 订单详情主键
     * @return 结果
     */
    @Override
    public int deleteXtOrderitemByUorderitemid(String uorderitemid)
    {
        return xtOrderitemMapper.deleteXtOrderitemByUorderitemid(uorderitemid);
    }
}
