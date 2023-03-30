package com.ruoyi.project.task.mapper;

import com.ruoyi.project.task.domain.XtPingjiaWords;

import java.util.List;

/**
 * 设置评价词语Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtPingjiaWordsMapper
{
    /**
     * 查询设置评价词语
     *
     * @param upingjiaWordsid 设置评价词语主键
     * @return 设置评价词语
     */
    public XtPingjiaWords selectXtPingjiaWordsByUpingjiaWordsid(String upingjiaWordsid);

    /**
     * 查询设置评价词语列表
     *
     * @param xtPingjiaWords 设置评价词语
     * @return 设置评价词语集合
     */
    public List<XtPingjiaWords> selectXtPingjiaWordsList(XtPingjiaWords xtPingjiaWords);

    /**
     * 新增设置评价词语
     *
     * @param xtPingjiaWords 设置评价词语
     * @return 结果
     */
    public int insertXtPingjiaWords(XtPingjiaWords xtPingjiaWords);

    /**
     * 修改设置评价词语
     *
     * @param xtPingjiaWords 设置评价词语
     * @return 结果
     */
    public int updateXtPingjiaWords(XtPingjiaWords xtPingjiaWords);

    /**
     * 删除设置评价词语
     *
     * @param upingjiaWordsid 设置评价词语主键
     * @return 结果
     */
    public int deleteXtPingjiaWordsByUpingjiaWordsid(String upingjiaWordsid);

    /**
     * 批量删除设置评价词语
     *
     * @param upingjiaWordsids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtPingjiaWordsByUpingjiaWordsids(String[] upingjiaWordsids);
}
