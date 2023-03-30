package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2023-02-07
 */
@Data
public class XtCategory {

    /** 品类ID */
    private String id;

    /** 品类名字 */
    @Excel(name = "品类的名字")
    private String uname;

    /** 品类位置 */
    @Excel(name = "品类位置")
    private String uplace;

    /** 删除标志位 0：存在   2：删除*/
    @Excel(name = "删除标志位")
    private String delFle;

    //private Integer cusnum;(购买过该商品品类的客户数量，只是一个统计，没必要在类里加属性，存在于数据库就行)

}
