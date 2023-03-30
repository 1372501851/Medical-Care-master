package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtOrderitem;
import com.ruoyi.project.merchant.domain.dto.HotProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Repository
public interface XtOrderitemMapper
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
     * 删除订单详情
     *
     * @param uorderitemid 订单详情主键
     * @return 结果
     */
    public int deleteXtOrderitemByUorderitemid(String uorderitemid);

    /**
     * 批量删除订单详情
     *
     * @param uorderitemids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtOrderitemByUorderitemids(String[] uorderitemids);

    List<HotProduct> selectXtOrderitemListByCompId(String ucompid);
}
