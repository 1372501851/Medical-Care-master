package com.ruoyi.project.common.service.impl;

import com.ruoyi.project.common.domain.XtUsertopaiban;
import com.ruoyi.project.common.domain.select.Select;
import com.ruoyi.project.common.mapper.XtUsertopaibanMapper;
import com.ruoyi.project.common.service.IXtUsertopaibanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户与排班Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtUsertopaibanServiceImpl implements IXtUsertopaibanService
{
    @Autowired
    private XtUsertopaibanMapper xtUsertopaibanMapper;

    /**
     * 查询用户与排班
     *
     * @param upaibanid 用户与排班主键
     * @return 用户与排班
     */
    @Override
    public XtUsertopaiban selectXtUsertopaibanByUpaibanid(String upaibanid)
    {
        return xtUsertopaibanMapper.selectXtUsertopaibanByUpaibanid(upaibanid);
    }

    /**
     * 查询用户与排班列表
     *
     * @param xtUsertopaiban 用户与排班
     * @return 用户与排班
     */
    @Override
    public List<XtUsertopaiban> selectXtUsertopaibanList(XtUsertopaiban xtUsertopaiban)
    {
        return xtUsertopaibanMapper.selectXtUsertopaibanList(xtUsertopaiban);
    }

    /**
     * 新增用户与排班
     *
     * @param xtUsertopaiban 用户与排班
     * @return 结果
     */
    @Override
    public int insertXtUsertopaiban(XtUsertopaiban xtUsertopaiban)
    {
        return xtUsertopaibanMapper.insertXtUsertopaiban(xtUsertopaiban);
    }

    /**
     * 修改用户与排班
     *
     * @param xtUsertopaiban 用户与排班
     * @return 结果
     */
    @Override
    public int updateXtUsertopaiban(XtUsertopaiban xtUsertopaiban)
    {
        return xtUsertopaibanMapper.updateXtUsertopaiban(xtUsertopaiban);
    }

    /**
     * 批量删除用户与排班
     *
     * @param upaibanids 需要删除的用户与排班主键
     * @return 结果
     */
    @Override
    public int deleteXtUsertopaibanByUpaibanids(String[] upaibanids)
    {
        return xtUsertopaibanMapper.deleteXtUsertopaibanByUpaibanids(upaibanids);
    }

    /**
     * 删除用户与排班信息
     *
     * @param upaibanid 用户与排班主键
     * @return 结果
     */
    @Override
    public int deleteXtUsertopaibanByUpaibanid(String upaibanid)
    {
        return xtUsertopaibanMapper.deleteXtUsertopaibanByUpaibanid(upaibanid);
    }

    @Override
    public List<Select> selectEmployeeListByPaibanId(String upaibanid) {
        return xtUsertopaibanMapper.selectEmployeeListByPaibanId(upaibanid);
    }

    @Override
    public int insertXtUsertopaibanBatch(String upaibanid, List<String> uemployeeids) {
        return xtUsertopaibanMapper.insertXtUsertopaibanBatch(upaibanid,uemployeeids);
    }

}
