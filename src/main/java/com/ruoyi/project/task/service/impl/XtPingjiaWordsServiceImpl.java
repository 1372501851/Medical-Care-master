package com.ruoyi.project.task.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.task.domain.XtPingjiaWords;
import com.ruoyi.project.task.mapper.XtPingjiaWordsMapper;
import com.ruoyi.project.task.service.IXtPingjiaWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设置评价词语Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtPingjiaWordsServiceImpl implements IXtPingjiaWordsService
{
    @Autowired
    private XtPingjiaWordsMapper xtPingjiaWordsMapper;

    /**
     * 查询设置评价词语
     *
     * @param upingjiaWordsid 设置评价词语主键
     * @return 设置评价词语
     */
    @Override
    public XtPingjiaWords selectXtPingjiaWordsByUpingjiaWordsid(String upingjiaWordsid)
    {
        return xtPingjiaWordsMapper.selectXtPingjiaWordsByUpingjiaWordsid(upingjiaWordsid);
    }

    /**
     * 查询设置评价词语列表
     *
     * @param xtPingjiaWords 设置评价词语
     * @return 设置评价词语
     */
    @Override
    public List<XtPingjiaWords> selectXtPingjiaWordsList(XtPingjiaWords xtPingjiaWords)
    {
        return xtPingjiaWordsMapper.selectXtPingjiaWordsList(xtPingjiaWords);
    }

    /**
     * 新增设置评价词语
     *
     * @param xtPingjiaWords 设置评价词语
     * @return 结果
     */
    @Override
    public int insertXtPingjiaWords(XtPingjiaWords xtPingjiaWords)
    {
        xtPingjiaWords.setUpingjiaWordsid(IdUtil.getSnowflakeNextIdStr());
        return xtPingjiaWordsMapper.insertXtPingjiaWords(xtPingjiaWords);
    }

    /**
     * 修改设置评价词语
     *
     * @param xtPingjiaWords 设置评价词语
     * @return 结果
     */
    @Override
    public int updateXtPingjiaWords(XtPingjiaWords xtPingjiaWords)
    {
        return xtPingjiaWordsMapper.updateXtPingjiaWords(xtPingjiaWords);
    }

    /**
     * 批量删除设置评价词语
     *
     * @param upingjiaWordsids 需要删除的设置评价词语主键
     * @return 结果
     */
    @Override
    public int deleteXtPingjiaWordsByUpingjiaWordsids(String[] upingjiaWordsids)
    {
        return xtPingjiaWordsMapper.deleteXtPingjiaWordsByUpingjiaWordsids(upingjiaWordsids);
    }

    /**
     * 删除设置评价词语信息
     *
     * @param upingjiaWordsid 设置评价词语主键
     * @return 结果
     */
    @Override
    public int deleteXtPingjiaWordsByUpingjiaWordsid(String upingjiaWordsid)
    {
        return xtPingjiaWordsMapper.deleteXtPingjiaWordsByUpingjiaWordsid(upingjiaWordsid);
    }
}
