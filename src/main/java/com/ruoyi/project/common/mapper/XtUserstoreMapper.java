package com.ruoyi.project.common.mapper;

import com.ruoyi.project.common.domain.XtUserstore;

import java.util.List;

/**
 * 用户收藏 收藏医院、收藏医生、收藏语言内容Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtUserstoreMapper
{
    /**
     * 查询用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param userstoreid 用户收藏 收藏医院、收藏医生、收藏语言内容主键
     * @return 用户收藏 收藏医院、收藏医生、收藏语言内容
     */
    public XtUserstore selectXtUserstoreByUserstoreid(String userstoreid);

    /**
     * 查询用户收藏 收藏医院、收藏医生、收藏语言内容列表
     *
     * @param xtUserstore 用户收藏 收藏医院、收藏医生、收藏语言内容
     * @return 用户收藏 收藏医院、收藏医生、收藏语言内容集合
     */
    public List<XtUserstore> selectXtUserstoreList(XtUserstore xtUserstore);

    /**
     * 新增用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param xtUserstore 用户收藏 收藏医院、收藏医生、收藏语言内容
     * @return 结果
     */
    public int insertXtUserstore(XtUserstore xtUserstore);

    /**
     * 修改用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param xtUserstore 用户收藏 收藏医院、收藏医生、收藏语言内容
     * @return 结果
     */
    public int updateXtUserstore(XtUserstore xtUserstore);

    /**
     * 删除用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param userstoreid 用户收藏 收藏医院、收藏医生、收藏语言内容主键
     * @return 结果
     */
    public int deleteXtUserstoreByUserstoreid(String userstoreid);

    /**
     * 批量删除用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param userstoreids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtUserstoreByUserstoreids(String[] userstoreids);
}
