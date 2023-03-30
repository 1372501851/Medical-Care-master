package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 床位号对象 xt_bed_no
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtBedNo
{
    private static final long serialVersionUID = 1L;

    /** 床位号ID */
    private String ubednoid;

    /** 楼层 */
    @Excel(name = "病房号")
    @NotBlank(message = "病房号不能为空")
    private String uroomid;

    /** 床位号（同一个病房不能重复） */
    @Excel(name = "床位号", readConverterExp = "同=一个病房不能重复")
    @NotBlank(message = "病床编号不能为空")
    @Size(min = 1)
    @Size(max = 4,message = "病床编号过长" )
    private String ubedno;

    /** 状态 1有人，0空位 */
    @Excel(name = "状态 1有人，0空位")
    @NotBlank(message = "病床状态不能为空")
    @Size(min = 1,max = 1,message = "长度非法")
    private String ustatus;


    /**商家id*/
    private String ucompid;


}
