package com.ruoyi.project.common.domain.statistic;

import lombok.Data;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/2/3
 */
@Data
public class ToolTip {

    private String trigger = "axis";

    private AxisPointer axisPointer = new AxisPointer();

}
