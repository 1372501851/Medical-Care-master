package com.ruoyi.project.pay.Enum;

import lombok.Getter;

/**
 * @Description: 支付状态枚举
 * @Author: 小老鼠呀丶
 * @Date: 2022/11/29
 */
@Getter
public enum PaymentStatusEnum {

    NotPay("0","未付款"),
    SuccessPay("1","已付款"),
    Refund("2","已退款");

    private final String code;

    private final String message;

    PaymentStatusEnum(String code,String message){
        this.code = code;
        this.message = message;
    }
}
