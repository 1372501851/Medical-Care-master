package com.ruoyi.project.common.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.common.mapper.XtChatMapper;
import com.ruoyi.project.common.domain.XtChat;
import com.ruoyi.project.common.service.IXtChatService;

/**
 * 聊天记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-14
 */
@Service
public class XtChatServiceImpl implements IXtChatService 
{
    @Autowired
    private XtChatMapper xtChatMapper;

    /**
     * 查询聊天记录
     * 
     * @param uchatid 聊天记录主键
     * @return 聊天记录
     */
    @Override
    public XtChat selectXtChatByUchatid(String uchatid)
    {
        return xtChatMapper.selectXtChatByUchatid(uchatid);
    }

    /**
     * 查询聊天记录列表
     * 
     * @param xtChat 聊天记录
     * @return 聊天记录
     */
    @Override
    public List<XtChat> selectXtChatList(XtChat xtChat)
    {
        return xtChatMapper.selectXtChatList(xtChat);
    }

    /**
     * 新增聊天记录
     * 
     * @param xtChat 聊天记录
     * @return 结果
     */
    @Override
    public int insertXtChat(XtChat xtChat)
    {
        xtChat.setCreateTime(DateUtils.getNowDate());
        return xtChatMapper.insertXtChat(xtChat);
    }

    /**
     * 修改聊天记录
     * 
     * @param xtChat 聊天记录
     * @return 结果
     */
    @Override
    public int updateXtChat(XtChat xtChat)
    {
        return xtChatMapper.updateXtChat(xtChat);
    }

    /**
     * 批量删除聊天记录
     * 
     * @param uchatids 需要删除的聊天记录主键
     * @return 结果
     */
    @Override
    public int deleteXtChatByUchatids(String[] uchatids)
    {
        return xtChatMapper.deleteXtChatByUchatids(uchatids);
    }

    /**
     * 删除聊天记录信息
     * 
     * @param uchatid 聊天记录主键
     * @return 结果
     */
    @Override
    public int deleteXtChatByUchatid(String uchatid)
    {
        return xtChatMapper.deleteXtChatByUchatid(uchatid);
    }
}
