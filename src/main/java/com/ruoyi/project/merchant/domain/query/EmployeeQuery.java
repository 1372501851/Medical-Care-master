package com.ruoyi.project.merchant.domain.query;

import lombok.Data;

/**
 * @author by hujun
 * @date 2022-11-23
 */
@Data
public class EmployeeQuery {
    /** 商家ID */

    private String ucompid;

    /** 部门(科室)ID */

    private String udepartmentid;

    /** 姓名 */

    private String uname;

    /** 用户职业 */
    private String uoccupation;

    /**行政职称*/
    private String administrative;


    private Integer pageNum;

    private Integer pageSize;
}
