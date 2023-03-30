package com.ruoyi.project.merchant.domain.vo;

import com.ruoyi.project.user.domain.tree.DeptTreeSelect;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/1/31
 */
@Data
public class CompAndDeptSelect {

    /** 节点ID */
    private String value;

    /** 节点名称 */
    private String label;

    private List<DeptTreeSelect> departmentList;

}
