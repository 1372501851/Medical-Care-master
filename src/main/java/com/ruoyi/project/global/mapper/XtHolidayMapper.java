package com.ruoyi.project.global.mapper;

import com.ruoyi.project.global.domain.XtHoliday;

import java.util.List;

/**
 * 假期（每年的假期都录入进来）平台管理录入Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtHolidayMapper
{
    /**
     * 查询假期（每年的假期都录入进来）平台管理录入
     *
     * @param uholidayid 假期（每年的假期都录入进来）平台管理录入主键
     * @return 假期（每年的假期都录入进来）平台管理录入
     */
    public XtHoliday selectXtHolidayByUholidayid(String uholidayid);

    /**
     * 查询假期（每年的假期都录入进来）平台管理录入列表
     *
     * @param xtHoliday 假期（每年的假期都录入进来）平台管理录入
     * @return 假期（每年的假期都录入进来）平台管理录入集合
     */
    public List<XtHoliday> selectXtHolidayList(XtHoliday xtHoliday);

    /**
     * 新增假期（每年的假期都录入进来）平台管理录入
     *
     * @param xtHoliday 假期（每年的假期都录入进来）平台管理录入
     * @return 结果
     */
    public int insertXtHoliday(XtHoliday xtHoliday);

    /**
     * 修改假期（每年的假期都录入进来）平台管理录入
     *
     * @param xtHoliday 假期（每年的假期都录入进来）平台管理录入
     * @return 结果
     */
    public int updateXtHoliday(XtHoliday xtHoliday);

    /**
     * 删除假期（每年的假期都录入进来）平台管理录入
     *
     * @param uholidayid 假期（每年的假期都录入进来）平台管理录入主键
     * @return 结果
     */
    public int deleteXtHolidayByUholidayid(String uholidayid);

    /**
     * 批量删除假期（每年的假期都录入进来）平台管理录入
     *
     * @param uholidayids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtHolidayByUholidayids(String[] uholidayids);
}
