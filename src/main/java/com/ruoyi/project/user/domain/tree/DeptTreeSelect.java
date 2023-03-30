package com.ruoyi.project.user.domain.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.merchant.domain.vo.ProductTypeVo;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.user.domain.SysMenu;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.vo.DeptVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by hujun
 * @date 2022-11-12
 */
@Data
public class DeptTreeSelect  {
    /** 节点ID */
    private String id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptTreeSelect> children;

    public DeptTreeSelect()
    {

    }


    public DeptTreeSelect(DeptVo dept)
    {
        this.id = dept.getUdepartmentid();
        this.label = dept.getUdepartmentname();
        this.children = dept.getChildren().stream().map(DeptTreeSelect::new).collect(Collectors.toList());
    }


    public DeptTreeSelect(XtDepartment xtDepartment) {
    }


}
