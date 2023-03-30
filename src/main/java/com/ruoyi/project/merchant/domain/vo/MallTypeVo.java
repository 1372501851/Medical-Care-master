package com.ruoyi.project.merchant.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2022-11-22
 */
@Data
public class MallTypeVo {
    /** 产品类型ID */
    private String uproducttypeid;

    /** 产品类型名称 */
    @Excel(name = "产品类型名称")
    private String uproducttypename;

    /** 父级id */
    @Excel(name = "父级id")
    private String uparentid;
}
