package com.ruoyi.project.merchant.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-11-16
 */
@Data
public class EquipmentTypeVo {
    /** 设备类型ID */
    private String uequipmentTypeid;

    /** 设备类型名称 */
    @Excel(name = "设备类型名称")
    @NotBlank(message = "设备名称不能为空")
    private String utypename;

    /** 父级ID */
    @Excel(name = "父级ID")
    private String uparentid;

    private List<EquipmentTypeVo> children = new ArrayList<>();
}
