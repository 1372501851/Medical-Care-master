package com.ruoyi.project.merchant.domain.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.project.merchant.domain.XtProducttype;
import com.ruoyi.project.merchant.domain.vo.ProductTypeVo;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.tree.DeptTreeSelect;
import com.ruoyi.project.user.domain.vo.DeptVo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductTypeTreeSelect {
    /** 节点ID */
    private String id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProductTypeTreeSelect> children;


    public ProductTypeTreeSelect()
    {

    }


    public ProductTypeTreeSelect(ProductTypeVo type)
    {
        this.id = type.getUproducttypeid();
        this.label = type.getUproducttypename();
        this.children = type.getChildren().stream().map(ProductTypeTreeSelect::new).collect(Collectors.toList());
    }


    public ProductTypeTreeSelect(XtProducttype xtProducttype) {
    }
}
