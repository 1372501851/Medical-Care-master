package com.ruoyi.project.merchant.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author by hujun
 * @date 2022-12-08
 */
@Data
public class EquipmentAppTypeVo {

    /** 设备类型ID */
    private String uequipmentTypeid;

    /** 设备类型名称 */
    private String utypename;

    /**设备类型图片*/
    private String pic;
}
