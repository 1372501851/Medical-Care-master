package com.ruoyi.project.common.domain.statistic;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/2/3
 */
@Data
public class Statistic {

    private Title title;

    private ToolTip tooltip = new ToolTip();

    private XAxis xAxis;

    private Map yAxis;

    private List<Series> series;

}
