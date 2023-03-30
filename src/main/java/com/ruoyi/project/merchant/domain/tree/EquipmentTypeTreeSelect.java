package com.ruoyi.project.merchant.domain.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.project.merchant.domain.XtEquipmentType;
import com.ruoyi.project.merchant.domain.vo.EquipmentTypeVo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by hujun
 * @date 2022-11-16
 */
@Data
public class EquipmentTypeTreeSelect {

    private String id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<EquipmentTypeTreeSelect> children;


    public EquipmentTypeTreeSelect()
    {

    }


    public EquipmentTypeTreeSelect(EquipmentTypeVo type)
    {
        this.id = type.getUequipmentTypeid();
        this.label = type.getUtypename();
        this.children = type.getChildren().stream().map(EquipmentTypeTreeSelect::new).collect(Collectors.toList());
    }


    public EquipmentTypeTreeSelect(XtEquipmentType equipmentType) {
    }
}
