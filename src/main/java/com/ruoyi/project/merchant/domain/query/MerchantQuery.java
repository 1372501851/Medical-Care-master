package com.ruoyi.project.merchant.domain.query;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-11-10
 */
@Data
public class MerchantQuery {
    /**
     *
     * 经纬度
     */

    private String latitude;
    private String longitude;
    /**
     * 商家名称
     * */
    private String merchantName;

    /**
     * 商家类型
     * */
    private String merchantType;

    /**
     * 区域id
     * */
    private String regionId;

    /**
     *
     * 分页参数
     *
     * */

    private Integer pageNum;
    private Integer pageSize;

}
