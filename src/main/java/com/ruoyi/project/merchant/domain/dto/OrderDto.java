package com.ruoyi.project.merchant.domain.dto;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2022-11-24
 */

@Data
public class OrderDto {

    /** 商家ID */
    private String ucompid;

    /**所购产品Id*/
    private String productId;

    /**所购产品数量*/

    private Integer productNum;

    /** 备注 */

    private String uremark;


    /** 购买用户ID */
    private String userid;

    /**收件者姓名*/

    private String userName;

    /**收件地区*/

    private String region;

    /** 收件详细地址 */
    private String userAddress;

    /** 收件人手机号码 */

    private String userMobile;













}
