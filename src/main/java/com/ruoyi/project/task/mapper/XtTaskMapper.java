package com.ruoyi.project.task.mapper;

import com.ruoyi.project.task.domain.XtTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务（图文问诊、护理、护工等）Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Repository
public interface XtTaskMapper
{
    /**
     * 查询任务（图文问诊、护理、护工等）
     *
     * @param utaskid 任务（图文问诊、护理、护工等）主键
     * @return 任务（图文问诊、护理、护工等）
     */
    public XtTask selectXtTaskByUtaskid(String utaskid);

    /**
     * 查询任务（图文问诊、护理、护工等）列表
     *
     * @param xtTask 任务（图文问诊、护理、护工等）
     * @return 任务（图文问诊、护理、护工等）集合
     */
    public List<XtTask> selectXtTaskList(XtTask xtTask);

    /**
     * 新增任务（图文问诊、护理、护工等）
     *
     * @param xtTask 任务（图文问诊、护理、护工等）
     * @return 结果
     */
    public int insertXtTask(XtTask xtTask);

    /**
     * 修改任务（图文问诊、护理、护工等）
     *
     * @param xtTask 任务（图文问诊、护理、护工等）
     * @return 结果
     */
    public int updateXtTask(XtTask xtTask);

    /**
     * 删除任务（图文问诊、护理、护工等）
     *
     * @param utaskid 任务（图文问诊、护理、护工等）主键
     * @return 结果
     */
    public int deleteXtTaskByUtaskid(String utaskid);

    /**
     * 批量删除任务（图文问诊、护理、护工等）
     *
     * @param utaskids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtTaskByUtaskids(String[] utaskids);
}
