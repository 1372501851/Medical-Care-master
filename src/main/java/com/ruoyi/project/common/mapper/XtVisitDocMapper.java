package com.ruoyi.project.common.mapper;

import com.ruoyi.project.common.domain.XtVisitDoc;

import java.util.List;

/**
 * 就诊单据Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtVisitDocMapper
{
    /**
     * 查询就诊单据
     *
     * @param uvisitDocid 就诊单据主键
     * @return 就诊单据
     */
    public XtVisitDoc selectXtVisitDocByUvisitDocid(String uvisitDocid);

    /**
     * 查询就诊单据列表
     *
     * @param xtVisitDoc 就诊单据
     * @return 就诊单据集合
     */
    public List<XtVisitDoc> selectXtVisitDocList(XtVisitDoc xtVisitDoc);

    /**
     * 新增就诊单据
     *
     * @param xtVisitDoc 就诊单据
     * @return 结果
     */
    public int insertXtVisitDoc(XtVisitDoc xtVisitDoc);

    /**
     * 修改就诊单据
     *
     * @param xtVisitDoc 就诊单据
     * @return 结果
     */
    public int updateXtVisitDoc(XtVisitDoc xtVisitDoc);

    /**
     * 删除就诊单据
     *
     * @param uvisitDocid 就诊单据主键
     * @return 结果
     */
    public int deleteXtVisitDocByUvisitDocid(String uvisitDocid);

    /**
     * 批量删除就诊单据
     *
     * @param uvisitDocids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtVisitDocByUvisitDocids(String[] uvisitDocids);
}
