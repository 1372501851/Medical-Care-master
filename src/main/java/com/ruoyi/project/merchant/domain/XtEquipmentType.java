package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

/**
 * 设备类型对象 xt_equipment_type
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Data
public class XtEquipmentType
{
    private static final long serialVersionUID = 1L;

    /** 设备类型ID */
    private String uequipmentTypeid;

    /** 设备类型名称 */
    @Excel(name = "设备类型名称")
//    @NotBlank(message = "设备名称不能为空")
    private String utypename;

    /** 父级ID */
    @Excel(name = "父级ID")
    private String uparentid;


    /**设备类型图片*/
    private String pic;


}
