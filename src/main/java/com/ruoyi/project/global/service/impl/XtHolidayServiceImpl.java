package com.ruoyi.project.global.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.global.domain.XtHoliday;
import com.ruoyi.project.global.mapper.XtHolidayMapper;
import com.ruoyi.project.global.service.IXtHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 假期（每年的假期都录入进来）平台管理录入Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtHolidayServiceImpl implements IXtHolidayService
{
    @Autowired
    private XtHolidayMapper xtHolidayMapper;

    /**
     * 查询假期（每年的假期都录入进来）平台管理录入
     *
     * @param uholidayid 假期（每年的假期都录入进来）平台管理录入主键
     * @return 假期（每年的假期都录入进来）平台管理录入
     */
    @Override
    public XtHoliday selectXtHolidayByUholidayid(String uholidayid)
    {
        return xtHolidayMapper.selectXtHolidayByUholidayid(uholidayid);
    }

    /**
     * 查询假期（每年的假期都录入进来）平台管理录入列表
     *
     * @param xtHoliday 假期（每年的假期都录入进来）平台管理录入
     * @return 假期（每年的假期都录入进来）平台管理录入
     */
    @Override
    public List<XtHoliday> selectXtHolidayList(XtHoliday xtHoliday)
    {
        return xtHolidayMapper.selectXtHolidayList(xtHoliday);
    }

    /**
     * 新增假期（每年的假期都录入进来）平台管理录入
     *
     * @param xtHoliday 假期（每年的假期都录入进来）平台管理录入
     * @return 结果
     */
    @Override
    public int insertXtHoliday(XtHoliday xtHoliday)
    {
        xtHoliday.setUholidayid(IdUtil.getSnowflakeNextIdStr());
        return xtHolidayMapper.insertXtHoliday(xtHoliday);
    }

    /**
     * 修改假期（每年的假期都录入进来）平台管理录入
     *
     * @param xtHoliday 假期（每年的假期都录入进来）平台管理录入
     * @return 结果
     */
    @Override
    public int updateXtHoliday(XtHoliday xtHoliday)
    {
        return xtHolidayMapper.updateXtHoliday(xtHoliday);
    }

    /**
     * 批量删除假期（每年的假期都录入进来）平台管理录入
     *
     * @param uholidayids 需要删除的假期（每年的假期都录入进来）平台管理录入主键
     * @return 结果
     */
    @Override
    public int deleteXtHolidayByUholidayids(String[] uholidayids)
    {
        return xtHolidayMapper.deleteXtHolidayByUholidayids(uholidayids);
    }

    /**
     * 删除假期（每年的假期都录入进来）平台管理录入信息
     *
     * @param uholidayid 假期（每年的假期都录入进来）平台管理录入主键
     * @return 结果
     */
    @Override
    public int deleteXtHolidayByUholidayid(String uholidayid)
    {
        return xtHolidayMapper.deleteXtHolidayByUholidayid(uholidayid);
    }
}
