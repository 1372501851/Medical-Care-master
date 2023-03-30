package com.ruoyi.project.task.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.task.domain.XtTaskResults;
import com.ruoyi.project.task.mapper.XtTaskResultsMapper;
import com.ruoyi.project.task.service.IXtTaskResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务结果 主要是看病医生填写的结果Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtTaskResultsServiceImpl implements IXtTaskResultsService
{
    @Autowired
    private XtTaskResultsMapper xtTaskResultsMapper;

    /**
     * 查询任务结果 主要是看病医生填写的结果
     *
     * @param utaskResultsid 任务结果 主要是看病医生填写的结果主键
     * @return 任务结果 主要是看病医生填写的结果
     */
    @Override
    public XtTaskResults selectXtTaskResultsByUtaskResultsid(String utaskResultsid)
    {
        return xtTaskResultsMapper.selectXtTaskResultsByUtaskResultsid(utaskResultsid);
    }

    /**
     * 查询任务结果 主要是看病医生填写的结果列表
     *
     * @param xtTaskResults 任务结果 主要是看病医生填写的结果
     * @return 任务结果 主要是看病医生填写的结果
     */
    @Override
    public List<XtTaskResults> selectXtTaskResultsList(XtTaskResults xtTaskResults)
    {

        return xtTaskResultsMapper.selectXtTaskResultsList(xtTaskResults);
    }

    /**
     * 新增任务结果 主要是看病医生填写的结果
     *
     * @param xtTaskResults 任务结果 主要是看病医生填写的结果
     * @return 结果
     */
    @Override
    public int insertXtTaskResults(XtTaskResults xtTaskResults)
    {
        xtTaskResults.setUtaskResultsid(IdUtil.getSnowflakeNextIdStr());
        return xtTaskResultsMapper.insertXtTaskResults(xtTaskResults);
    }

    /**
     * 修改任务结果 主要是看病医生填写的结果
     *
     * @param xtTaskResults 任务结果 主要是看病医生填写的结果
     * @return 结果
     */
    @Override
    public int updateXtTaskResults(XtTaskResults xtTaskResults)
    {
        return xtTaskResultsMapper.updateXtTaskResults(xtTaskResults);
    }

    /**
     * 批量删除任务结果 主要是看病医生填写的结果
     *
     * @param utaskResultsids 需要删除的任务结果 主要是看病医生填写的结果主键
     * @return 结果
     */
    @Override
    public int deleteXtTaskResultsByUtaskResultsids(String[] utaskResultsids)
    {
        return xtTaskResultsMapper.deleteXtTaskResultsByUtaskResultsids(utaskResultsids);
    }

    /**
     * 删除任务结果 主要是看病医生填写的结果信息
     *
     * @param utaskResultsid 任务结果 主要是看病医生填写的结果主键
     * @return 结果
     */
    @Override
    public int deleteXtTaskResultsByUtaskResultsid(String utaskResultsid)
    {
        return xtTaskResultsMapper.deleteXtTaskResultsByUtaskResultsid(utaskResultsid);
    }
}
