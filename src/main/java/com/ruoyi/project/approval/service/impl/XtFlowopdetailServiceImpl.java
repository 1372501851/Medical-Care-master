package com.ruoyi.project.approval.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.approval.domain.XtFlowopdetail;
import com.ruoyi.project.approval.mapper.XtFlowopdetailMapper;
import com.ruoyi.project.approval.service.IXtFlowopdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程操作明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtFlowopdetailServiceImpl implements IXtFlowopdetailService
{
    @Autowired
    private XtFlowopdetailMapper xtFlowopdetailMapper;

    /**
     * 查询流程操作明细
     *
     * @param uflowopdetailid 流程操作明细主键
     * @return 流程操作明细
     */
    @Override
    public XtFlowopdetail selectXtFlowopdetailByUflowopdetailid(String uflowopdetailid)
    {
        return xtFlowopdetailMapper.selectXtFlowopdetailByUflowopdetailid(uflowopdetailid);
    }

    /**
     * 查询流程操作明细列表
     *
     * @param xtFlowopdetail 流程操作明细
     * @return 流程操作明细
     */
    @Override
    public List<XtFlowopdetail> selectXtFlowopdetailList(XtFlowopdetail xtFlowopdetail)
    {
        return xtFlowopdetailMapper.selectXtFlowopdetailList(xtFlowopdetail);
    }

    /**
     * 新增流程操作明细
     *
     * @param xtFlowopdetail 流程操作明细
     * @return 结果
     */
    @Override
    public int insertXtFlowopdetail(XtFlowopdetail xtFlowopdetail)
    {
        xtFlowopdetail.setUflowopdetailid(IdUtil.getSnowflakeNextIdStr());
        return xtFlowopdetailMapper.insertXtFlowopdetail(xtFlowopdetail);
    }

    /**
     * 修改流程操作明细
     *
     * @param xtFlowopdetail 流程操作明细
     * @return 结果
     */
    @Override
    public int updateXtFlowopdetail(XtFlowopdetail xtFlowopdetail)
    {
        return xtFlowopdetailMapper.updateXtFlowopdetail(xtFlowopdetail);
    }

    /**
     * 批量删除流程操作明细
     *
     * @param uflowopdetailids 需要删除的流程操作明细主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowopdetailByUflowopdetailids(String[] uflowopdetailids)
    {
        return xtFlowopdetailMapper.deleteXtFlowopdetailByUflowopdetailids(uflowopdetailids);
    }

    /**
     * 删除流程操作明细信息
     *
     * @param uflowopdetailid 流程操作明细主键
     * @return 结果
     */
    @Override
    public int deleteXtFlowopdetailByUflowopdetailid(String uflowopdetailid)
    {
        return xtFlowopdetailMapper.deleteXtFlowopdetailByUflowopdetailid(uflowopdetailid);
    }
}
