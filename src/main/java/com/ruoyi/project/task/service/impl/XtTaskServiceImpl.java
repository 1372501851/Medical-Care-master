package com.ruoyi.project.task.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.task.domain.XtTask;
import com.ruoyi.project.task.mapper.XtTaskMapper;
import com.ruoyi.project.task.service.IXtTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务（图文问诊、护理、护工等）Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtTaskServiceImpl implements IXtTaskService
{
    @Autowired
    private XtTaskMapper xtTaskMapper;

    /**
     * 查询任务（图文问诊、护理、护工等）
     *
     * @param utaskid 任务（图文问诊、护理、护工等）主键
     * @return 任务（图文问诊、护理、护工等）
     */
    @Override
    public XtTask selectXtTaskByUtaskid(String utaskid)
    {
        return xtTaskMapper.selectXtTaskByUtaskid(utaskid);
    }

    /**
     * 查询任务（图文问诊、护理、护工等）列表
     *
     * @param xtTask 任务（图文问诊、护理、护工等）
     * @return 任务（图文问诊、护理、护工等）
     */
    @Override
    public List<XtTask> selectXtTaskList(XtTask xtTask)
    {
        //这个获取任务列表的操作,需要有限制条件,不能查看到所有的任务,应该查看当前医院的,或者附近的;
        //任务加上经纬度及位置信息,能更好的被搜索到,以及被推送给相应的用户;(经纬度,根据填入的商家id,直接获取商家的经纬度也可以)
        return xtTaskMapper.selectXtTaskList(xtTask);
    }

    /**
     * 新增任务（图文问诊、护理、护工等）
     *
     * @param xtTask 任务（图文问诊、护理、护工等）
     * @return 结果
     */
    @Override
    public int insertXtTask(XtTask xtTask)
    {
        xtTask.setUtaskid(IdUtil.getSnowflakeNextIdStr());
        return xtTaskMapper.insertXtTask(xtTask);
    }

    /**
     * 修改任务（图文问诊、护理、护工等）
     *
     * @param xtTask 任务（图文问诊、护理、护工等）
     * @return 结果
     */
    @Override
    public int updateXtTask(XtTask xtTask)
    {
        return xtTaskMapper.updateXtTask(xtTask);
    }

    /**
     * 批量删除任务（图文问诊、护理、护工等）
     *
     * @param utaskids 需要删除的任务（图文问诊、护理、护工等）主键
     * @return 结果
     */
    @Override
    public int deleteXtTaskByUtaskids(String[] utaskids)
    {
        return xtTaskMapper.deleteXtTaskByUtaskids(utaskids);
    }

    /**
     * 删除任务（图文问诊、护理、护工等）信息
     *
     * @param utaskid 任务（图文问诊、护理、护工等）主键
     * @return 结果
     */
    @Override
    public int deleteXtTaskByUtaskid(String utaskid)
    {
        return xtTaskMapper.deleteXtTaskByUtaskid(utaskid);
    }
}
