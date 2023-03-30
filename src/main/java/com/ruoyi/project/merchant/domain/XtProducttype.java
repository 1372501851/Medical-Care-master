package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品类型（用在医疗护理系统，药店系统，其他系统）对象 xt_producttype
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtProducttype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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

    /** 商品类型位置 */
    @Excel(name = "商品类型位置")
    private String uproductTypeSpace;


    //private Integer cusnum;(购买过该商品种类的客户数量，只是一个统计，没必要在类里加属性，存在于数据库就行)


}
