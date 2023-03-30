package com.ruoyi.project.common.service;

import java.util.List;
import com.ruoyi.project.common.domain.XtMessage;

/**
 * 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)Service接口
 * 
 * @author ruoyi
 * @date 2022-10-14
 */
public interface IXtMessageService 
{
    /**
     * 查询提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param umessageid 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)主键
     * @return 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     */
    public XtMessage selectXtMessageByUmessageid(String umessageid);

    /**
     * 查询提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)列表
     * 
     * @param xtMessage 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * @return 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)集合
     */
    public List<XtMessage> selectXtMessageList(XtMessage xtMessage);

    /**
     * 新增提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param xtMessage 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * @return 结果
     */
    public int insertXtMessage(XtMessage xtMessage);

    /**
     * 修改提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param xtMessage 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * @return 结果
     */
    public int updateXtMessage(XtMessage xtMessage);

    /**
     * 批量删除提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     * 
     * @param umessageids 需要删除的提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)主键集合
     * @return 结果
     */
    public int deleteXtMessageByUmessageids(String[] umessageids);

    /**
     * 删除提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)信息
     * 
     * @param umessageid 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)主键
     * @return 结果
     */
    public int deleteXtMessageByUmessageid(String umessageid);
}
