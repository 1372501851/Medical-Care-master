package com.ruoyi.project.common.service.impl;

import com.ruoyi.project.common.domain.XtUserstore;
import com.ruoyi.project.common.mapper.XtUserstoreMapper;
import com.ruoyi.project.common.service.IXtUserstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户收藏 收藏医院、收藏医生、收藏语言内容Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtUserstoreServiceImpl implements IXtUserstoreService
{
    @Autowired
    private XtUserstoreMapper xtUserstoreMapper;

    /**
     * 查询用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param userstoreid 用户收藏 收藏医院、收藏医生、收藏语言内容主键
     * @return 用户收藏 收藏医院、收藏医生、收藏语言内容
     */
    @Override
    public XtUserstore selectXtUserstoreByUserstoreid(String userstoreid)
    {
        return xtUserstoreMapper.selectXtUserstoreByUserstoreid(userstoreid);
    }

    /**
     * 查询用户收藏 收藏医院、收藏医生、收藏语言内容列表
     *
     * @param xtUserstore 用户收藏 收藏医院、收藏医生、收藏语言内容
     * @return 用户收藏 收藏医院、收藏医生、收藏语言内容
     */
    @Override
    public List<XtUserstore> selectXtUserstoreList(XtUserstore xtUserstore)
    {
        return xtUserstoreMapper.selectXtUserstoreList(xtUserstore);
    }

    /**
     * 新增用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param xtUserstore 用户收藏 收藏医院、收藏医生、收藏语言内容
     * @return 结果
     */
    @Override
    public int insertXtUserstore(XtUserstore xtUserstore)
    {
        return xtUserstoreMapper.insertXtUserstore(xtUserstore);
    }

    /**
     * 修改用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param xtUserstore 用户收藏 收藏医院、收藏医生、收藏语言内容
     * @return 结果
     */
    @Override
    public int updateXtUserstore(XtUserstore xtUserstore)
    {
        return xtUserstoreMapper.updateXtUserstore(xtUserstore);
    }

    /**
     * 批量删除用户收藏 收藏医院、收藏医生、收藏语言内容
     *
     * @param userstoreids 需要删除的用户收藏 收藏医院、收藏医生、收藏语言内容主键
     * @return 结果
     */
    @Override
    public int deleteXtUserstoreByUserstoreids(String[] userstoreids)
    {
        return xtUserstoreMapper.deleteXtUserstoreByUserstoreids(userstoreids);
    }

    /**
     * 删除用户收藏 收藏医院、收藏医生、收藏语言内容信息
     *
     * @param userstoreid 用户收藏 收藏医院、收藏医生、收藏语言内容主键
     * @return 结果
     */
    @Override
    public int deleteXtUserstoreByUserstoreid(String userstoreid)
    {
        return xtUserstoreMapper.deleteXtUserstoreByUserstoreid(userstoreid);
    }
}
