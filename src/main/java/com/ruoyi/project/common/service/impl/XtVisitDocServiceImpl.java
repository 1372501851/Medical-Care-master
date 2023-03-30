package com.ruoyi.project.common.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.common.domain.XtVisitDoc;
import com.ruoyi.project.common.mapper.XtVisitDocMapper;
import com.ruoyi.project.common.service.IXtVisitDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 就诊单据Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtVisitDocServiceImpl implements IXtVisitDocService
{
    @Autowired
    private XtVisitDocMapper xtVisitDocMapper;

    /**
     * 查询就诊单据
     *
     * @param uvisitDocid 就诊单据主键
     * @return 就诊单据
     */
    @Override
    public XtVisitDoc selectXtVisitDocByUvisitDocid(String uvisitDocid)
    {
        return xtVisitDocMapper.selectXtVisitDocByUvisitDocid(uvisitDocid);
    }

    /**
     * 查询就诊单据列表
     *
     * @param xtVisitDoc 就诊单据
     * @return 就诊单据
     */
    @Override
    public List<XtVisitDoc> selectXtVisitDocList(XtVisitDoc xtVisitDoc)
    {
        return xtVisitDocMapper.selectXtVisitDocList(xtVisitDoc);
    }

    /**
     * 新增就诊单据
     *
     * @param xtVisitDoc 就诊单据
     * @return 结果
     */
    @Override
    public int insertXtVisitDoc(XtVisitDoc xtVisitDoc)
    {
        xtVisitDoc.setUvisitDocid(IdUtil.getSnowflakeNextIdStr());
        return xtVisitDocMapper.insertXtVisitDoc(xtVisitDoc);
    }

    /**
     * 修改就诊单据
     *
     * @param xtVisitDoc 就诊单据
     * @return 结果
     */
    @Override
    public int updateXtVisitDoc(XtVisitDoc xtVisitDoc)
    {
        return xtVisitDocMapper.updateXtVisitDoc(xtVisitDoc);
    }

    /**
     * 批量删除就诊单据
     *
     * @param uvisitDocids 需要删除的就诊单据主键
     * @return 结果
     */
    @Override
    public int deleteXtVisitDocByUvisitDocids(String[] uvisitDocids)
    {
        return xtVisitDocMapper.deleteXtVisitDocByUvisitDocids(uvisitDocids);
    }

    /**
     * 删除就诊单据信息
     *
     * @param uvisitDocid 就诊单据主键
     * @return 结果
     */
    @Override
    public int deleteXtVisitDocByUvisitDocid(String uvisitDocid)
    {
        return xtVisitDocMapper.deleteXtVisitDocByUvisitDocid(uvisitDocid);
    }
}
