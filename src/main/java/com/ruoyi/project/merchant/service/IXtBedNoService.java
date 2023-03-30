package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtBedNo;

import java.util.List;

/**
 * 床位号Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtBedNoService
{
    /**
     * 查询床位号
     *
     * @param ubednoid 床位号主键
     * @return 床位号
     */
    public XtBedNo selectXtBedNoByUbednoid(String ubednoid);

    /**
     * 查询床位号列表
     *
     * @param xtBedNo 床位号
     * @return 床位号集合
     */
    public List<XtBedNo> selectXtBedNoList(XtBedNo xtBedNo);

    /**
     * 新增床位号
     *
     * @param xtBedNo 床位号
     * @return 结果
     */
    public int insertXtBedNo(XtBedNo xtBedNo);

    /**
     * 修改床位号
     *
     * @param xtBedNo 床位号
     * @return 结果
     */
    public int updateXtBedNo(XtBedNo xtBedNo);

    /**
     * 批量删除床位号
     *
     * @param ubednoids 需要删除的床位号主键集合
     * @return 结果
     */
    public int deleteXtBedNoByUbednoids(String[] ubednoids);

    /**
     * 删除床位号信息
     *
     * @param ubednoid 床位号主键
     * @return 结果
     */
    public int deleteXtBedNoByUbednoid(String ubednoid);
}
