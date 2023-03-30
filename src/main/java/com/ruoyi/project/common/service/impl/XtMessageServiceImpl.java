package com.ruoyi.project.common.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.common.mapper.XtMessageMapper;
import com.ruoyi.project.common.domain.XtMessage;
import com.ruoyi.project.common.service.IXtMessageService;

/**
 * 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-14
 */
@Service
public class XtMessageServiceImpl implements IXtMessageService 
{
    @Autowired
    private XtMessageMapper xtMessageMapper;

    /**
     * 查询提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param umessageid 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)主键
     * @return 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     */
    @Override
    public XtMessage selectXtMessageByUmessageid(String umessageid)
    {
        return xtMessageMapper.selectXtMessageByUmessageid(umessageid);
    }

    /**
     * 查询提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)列表
     * 
     * @param xtMessage 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * @return 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     */
    @Override
    public List<XtMessage> selectXtMessageList(XtMessage xtMessage)
    {
        return xtMessageMapper.selectXtMessageList(xtMessage);
    }

    /**
     * 新增提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param xtMessage 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * @return 结果
     */
    @Override
    public int insertXtMessage(XtMessage xtMessage)
    {
        xtMessage.setCreateTime(DateUtils.getNowDate());
        return xtMessageMapper.insertXtMessage(xtMessage);
    }

    /**
     * 修改提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param xtMessage 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * @return 结果
     */
    @Override
    public int updateXtMessage(XtMessage xtMessage)
    {
        return xtMessageMapper.updateXtMessage(xtMessage);
    }

    /**
     * 批量删除提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param umessageids 需要删除的提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)主键
     * @return 结果
     */
    @Override
    public int deleteXtMessageByUmessageids(String[] umessageids)
    {
        return xtMessageMapper.deleteXtMessageByUmessageids(umessageids);
    }

    /**
     * 删除提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)信息
     * 
     * @param umessageid 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)主键
     * @return 结果
     */
    @Override
    public int deleteXtMessageByUmessageid(String umessageid)
    {
        return xtMessageMapper.deleteXtMessageByUmessageid(umessageid);
    }
}
