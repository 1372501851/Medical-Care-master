package com.ruoyi.project.global.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.global.domain.XtPrice;
import com.ruoyi.project.global.mapper.XtPriceMapper;
import com.ruoyi.project.global.service.IXtPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 价格（全局）针对护工Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtPriceServiceImpl implements IXtPriceService
{
    @Autowired
    private XtPriceMapper xtPriceMapper;

    /**
     * 查询价格（全局）针对护工
     *
     * @param upriceid 价格（全局）针对护工主键
     * @return 价格（全局）针对护工
     */
    @Override
    public XtPrice selectXtPriceByUpriceid(String upriceid)
    {
        return xtPriceMapper.selectXtPriceByUpriceid(upriceid);
    }

    /**
     * 查询价格（全局）针对护工列表
     *
     * @param xtPrice 价格（全局）针对护工
     * @return 价格（全局）针对护工
     */
    @Override
    public List<XtPrice> selectXtPriceList(XtPrice xtPrice)
    {
        return xtPriceMapper.selectXtPriceList(xtPrice);
    }

    /**
     * 新增价格（全局）针对护工
     *
     * @param xtPrice 价格（全局）针对护工
     * @return 结果
     */
    @Override
    public int insertXtPrice(XtPrice xtPrice)
    {
        xtPrice.setUpriceid(IdUtil.getSnowflakeNextIdStr());
        return xtPriceMapper.insertXtPrice(xtPrice);
    }

    /**
     * 修改价格（全局）针对护工
     *
     * @param xtPrice 价格（全局）针对护工
     * @return 结果
     */
    @Override
    public int updateXtPrice(XtPrice xtPrice)
    {
        return xtPriceMapper.updateXtPrice(xtPrice);
    }

    /**
     * 批量删除价格（全局）针对护工
     *
     * @param upriceids 需要删除的价格（全局）针对护工主键
     * @return 结果
     */
    @Override
    public int deleteXtPriceByUpriceids(String[] upriceids)
    {
        return xtPriceMapper.deleteXtPriceByUpriceids(upriceids);
    }

    /**
     * 删除价格（全局）针对护工信息
     *
     * @param upriceid 价格（全局）针对护工主键
     * @return 结果
     */
    @Override
    public int deleteXtPriceByUpriceid(String upriceid)
    {
        return xtPriceMapper.deleteXtPriceByUpriceid(upriceid);
    }
}
