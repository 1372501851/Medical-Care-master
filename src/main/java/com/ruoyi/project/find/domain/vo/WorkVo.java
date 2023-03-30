package com.ruoyi.project.find.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2022-12-05
 */

@Data
public class WorkVo {

    private String id;

    /** 姓名 */
    private String userName;

    /** 用户id */
    private String userid;

    /** 就诊医院 */
    private String hospital;

    /** 就诊科室 */
    private String dept;

    /** 就诊床位 */
    private String bed;


    /**用户身份*/
    private String userType;

    private String userTypeName;

    private String avatar;




}
