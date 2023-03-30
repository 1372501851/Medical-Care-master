package com.ruoyi.project.common.domain.statistic;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/2/3
 */
@Data
public class Series {

    private String name;

    private String type;

    private List<String> data;

}
