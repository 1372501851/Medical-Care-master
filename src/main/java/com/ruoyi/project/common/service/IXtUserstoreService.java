package com.ruoyi.project.common.service;

import com.ruoyi.project.common.domain.XtUserstore;

import java.util.List;

/**
 * 用户收藏 收藏医院、收藏医生、收藏语言内容Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtUserstoreService
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
     * 批量删除用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param userstoreids 需要删除的用户收藏 收藏医院、收藏医生、收藏语言内容主键集合
     * @return 结果
     */
    public int deleteXtUserstoreByUserstoreids(String[] userstoreids);

    /**
     * 删除用户收藏 收藏医院、收藏医生、收藏语言内容信息
     *
     * @param userstoreid 用户收藏 收藏医院、收藏医生、收藏语言内容主键
     * @return 结果
     */
    public int deleteXtUserstoreByUserstoreid(String userstoreid);
}
