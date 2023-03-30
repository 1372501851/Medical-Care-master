package com.ruoyi.project.common.service;

import java.util.List;
import com.ruoyi.project.common.domain.XtChat;

/**
 * 聊天记录Service接口
 * 
 * @author ruoyi
 * @date 2022-10-14
 */
public interface IXtChatService 
{
    /**
     * 查询聊天记录
     * 
     * @param uchatid 聊天记录主键
     * @return 聊天记录
     */
    public XtChat selectXtChatByUchatid(String uchatid);

    /**
     * 查询聊天记录列表
     * 
     * @param xtChat 聊天记录
     * @return 聊天记录集合
     */
    public List<XtChat> selectXtChatList(XtChat xtChat);

    /**
     * 新增聊天记录
     * 
     * @param xtChat 聊天记录
     * @return 结果
     */
    public int insertXtChat(XtChat xtChat);

    /**
     * 修改聊天记录
     * 
     * @param xtChat 聊天记录
     * @return 结果
     */
    public int updateXtChat(XtChat xtChat);

    /**
     * 批量删除聊天记录
     * 
     * @param uchatids 需要删除的聊天记录主键集合
     * @return 结果
     */
    public int deleteXtChatByUchatids(String[] uchatids);

    /**
     * 删除聊天记录信息
     * 
     * @param uchatid 聊天记录主键
     * @return 结果
     */
    public int deleteXtChatByUchatid(String uchatid);
}
