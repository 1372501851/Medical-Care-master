package com.ruoyi.project.global.service;

import com.ruoyi.project.global.domain.XtPrice;

import java.util.List;

/**
 * 价格（全局）针对护工Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtPriceService
{
    /**
     * 查询价格（全局）针对护工
     *
     * @param upriceid 价格（全局）针对护工主键
     * @return 价格（全局）针对护工
     */
    public XtPrice selectXtPriceByUpriceid(String upriceid);

    /**
     * 查询价格（全局）针对护工列表
     *
     * @param xtPrice 价格（全局）针对护工
     * @return 价格（全局）针对护工集合
     */
    public List<XtPrice> selectXtPriceList(XtPrice xtPrice);

    /**
     * 新增价格（全局）针对护工
     *
     * @param xtPrice 价格（全局）针对护工
     * @return 结果
     */
    public int insertXtPrice(XtPrice xtPrice);

    /**
     * 修改价格（全局）针对护工
     *
     * @param xtPrice 价格（全局）针对护工
     * @return 结果
     */
    public int updateXtPrice(XtPrice xtPrice);

    /**
     * 批量删除价格（全局）针对护工
     *
     * @param upriceids 需要删除的价格（全局）针对护工主键集合
     * @return 结果
     */
    public int deleteXtPriceByUpriceids(String[] upriceids);

    /**
     * 删除价格（全局）针对护工信息
     *
     * @param upriceid 价格（全局）针对护工主键
     * @return 结果
     */
    public int deleteXtPriceByUpriceid(String upriceid);
}
