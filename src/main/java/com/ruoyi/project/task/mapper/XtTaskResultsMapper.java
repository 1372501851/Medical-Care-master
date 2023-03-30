package com.ruoyi.project.task.mapper;

import com.ruoyi.project.task.domain.XtTaskResults;

import java.util.List;

/**
 * 任务结果 主要是看病医生填写的结果Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtTaskResultsMapper
{
    /**
     * 查询任务结果 主要是看病医生填写的结果
     *
     * @param utaskResultsid 任务结果 主要是看病医生填写的结果主键
     * @return 任务结果 主要是看病医生填写的结果
     */
    public XtTaskResults selectXtTaskResultsByUtaskResultsid(String utaskResultsid);

    /**
     * 查询任务结果 主要是看病医生填写的结果列表
     *
     * @param xtTaskResults 任务结果 主要是看病医生填写的结果
     * @return 任务结果 主要是看病医生填写的结果集合
     */
    public List<XtTaskResults> selectXtTaskResultsList(XtTaskResults xtTaskResults);

    /**
     * 新增任务结果 主要是看病医生填写的结果
     *
     * @param xtTaskResults 任务结果 主要是看病医生填写的结果
     * @return 结果
     */
    public int insertXtTaskResults(XtTaskResults xtTaskResults);

    /**
     * 修改任务结果 主要是看病医生填写的结果
     *
     * @param xtTaskResults 任务结果 主要是看病医生填写的结果
     * @return 结果
     */
    public int updateXtTaskResults(XtTaskResults xtTaskResults);

    /**
     * 删除任务结果 主要是看病医生填写的结果
     *
     * @param utaskResultsid 任务结果 主要是看病医生填写的结果主键
     * @return 结果
     */
    public int deleteXtTaskResultsByUtaskResultsid(String utaskResultsid);

    /**
     * 批量删除任务结果 主要是看病医生填写的结果
     *
     * @param utaskResultsids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtTaskResultsByUtaskResultsids(String[] utaskResultsids);
}
