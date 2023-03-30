package com.ruoyi.project.find.domain.query;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-12-05
 */

@Data
public class QueryWork {

    /**用户身份*/
    private String userType;


    /**纬度*/
    private String latitude;


    /**经度*/
    private String longitude;

}
