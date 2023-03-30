package com.ruoyi.project.area.domain.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.project.area.domain.vo.PtAreaVo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/1/13
 */
@Data
public class AreaTreeSelect {
    /** 节点ID */
    private String id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AreaTreeSelect> children;

    public AreaTreeSelect(PtAreaVo ptAreaVo)
    {
        this.id = ptAreaVo.getUareaid();
        this.label = ptAreaVo.getUareaname();
        this.children = ptAreaVo.getChildren().stream().map(AreaTreeSelect::new).collect(Collectors.toList());
    }

}
