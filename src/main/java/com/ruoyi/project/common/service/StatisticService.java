package com.ruoyi.project.common.service;

import com.ruoyi.project.common.domain.statistic.Statistic;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/2/3
 */
public interface StatisticService {
    List<Statistic> getStatisticTable();

    List<Map<String,Object>> getStatisticTable2();

}
