package com.ruoyi.project.find.domain.query;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-11-30
 */

@Data
public class QueryModel {

    private String serviceType;

    /**纬度*/
    private BigDecimal latitude;


    /**经度*/
    private BigDecimal longitude;
}
