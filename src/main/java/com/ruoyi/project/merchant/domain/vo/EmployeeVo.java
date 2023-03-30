package com.ruoyi.project.merchant.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2022-11-23
 */

@Data
public class EmployeeVo {

    /** 用户信息ID */
    private String uemployeeid;

    /** 用户ID */

    private String userid;

    /** 商家ID */

    private String ucompid;

    /** 部门(科室)ID */

    private String udepartmentid;

    /** 姓名 */

    private String uname;

    /** 用户类型 普通用户，医生，护士，管理员 */
    private String usertype;

    private String avatar;


    /**部门名称*/
    private String deptName;

    /**医院名称*/
    private String companyName;

    /**身份名称*/
    private String usertypeName;


    /** 职称 */
    private String utechp;


    /**行政职称*/
    private String administrative;

}
