package com.ruoyi.project.merchant.domain.vo;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.user.domain.vo.DeptVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductTypeVo {

    /** 产品类型ID */
    private String uproducttypeid;

    /** 类型（0医疗护理系统，1药店系统，2其他系统） */
    @Excel(name = "类型", readConverterExp = "0=医疗护理系统，1药店系统，2其他系统")
    private String uform;

    /** 产品类型名称 */
    @Excel(name = "产品类型名称")
    private String uproducttypename;

    /** 父级id */
    @Excel(name = "父级id")
    private String uparentid;

    /** 层级001001 */
    @Excel(name = "层级001001")
    private String ulevel;

    /** 商品类型图片 */
    @Excel(name = "商品类型图片")
    private String uproductTypePic;


    private List<ProductTypeVo> children = new ArrayList<ProductTypeVo>();
}
