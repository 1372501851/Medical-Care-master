package com.ruoyi.project.approval.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.approval.domain.XtFlowmaster;
import com.ruoyi.project.approval.mapper.XtFlowmasterMapper;
import com.ruoyi.project.approval.service.IXtFlowmasterService;
import com.ruoyi.project.common.domain.select.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程主Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtFlowmasterServiceImpl implements IXtFlowmasterService
{
    @Autowired
    private XtFlowmasterMapper xtFlowmasterMapper;

    /**
     * 查询流程主
     *
     * @param uflowid 流程主主键
     * @return 流程主
     */
    @Override
    public XtFlowmaster selectXtFlowmasterByUflowid(String uflowid)
    {
        return xtFlowmasterMapper.selectXtFlowmasterByUflowid(uflowid);
    }

    /**
     * 查询流程主列表
     *
     * @param xtFlowmaster 流程主
     * @return 流程主
     */
    @Override
    public List<XtFlowmaster> selectXtFlowmasterList(XtFlowmaster xtFlowmaster)
    {
        return xtFlowmasterMapper.selectXtFlowmasterList(xtFlowmaster);
    }

    /**
     * 新增流程主
     *
     * @param xtFlowmaster 流程主
     * @return 结果
     */
    @Override
    public int insertXtFlowmaster(XtFlowmaster xtFlowmaster)
    {
        xtFlowmaster.setUflowid(IdUtil.getSnowflakeNextIdStr());
        return xtFlowmasterMapper.insertXtFlowmaster(xtFlowmaster);
    }

    /**
     * 修改流程主
     *
     * @param xtFlowmaster 流程主
     * @return 结果
     */
    @Override
    public int updateXtFlowmaster(XtFlowmaster xtFlowmaster)
    {
        return xtFlowmasterMapper.updateXtFlowmaster(xtFlowmaster);
    }

    /**
     * 批量删除流程主
     *
     * @param uflowids 需要删除的流程主主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowmasterByUflowids(String[] uflowids)
    {
        return xtFlowmasterMapper.deleteXtFlowmasterByUflowids(uflowids);
    }

    /**
     * 删除流程主信息
     *
     * @param uflowid 流程主主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowmasterByUflowid(String uflowid)
    {
        return xtFlowmasterMapper.deleteXtFlowmasterByUflowid(uflowid);
    }

    @Override
    public List<Select> getAllFlowSelectList() {
        XtFlowmaster query = new XtFlowmaster();
        List<XtFlowmaster> xtFlowmasters = xtFlowmasterMapper.selectXtFlowmasterList(query);
        List<Select> result = new ArrayList<>();
        for (XtFlowmaster xtFlowmaster : xtFlowmasters) {
            Select select = new Select();
            select.setValue(xtFlowmaster.getUflowid());
            select.setLabel(xtFlowmaster.getUflowname());
            result.add(select);
        }
        return result;
    }

}
