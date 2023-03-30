package com.ruoyi.project.pay.WeChatPay.enums;

import lombok.Getter;

/**
 * @Description: 通知类型枚举
 * @Author: 小老鼠呀丶
 * @Date: 2022/11/29
 */
@Getter
public enum NotifyType {

    /**
     * 支付结果通知
     */
    PAY_NOTIFY("/wechatPayApi/payNotify"),

    /**
     * 退款结果通知
     */
    REFUND_NOTIFY("/wechatPayApi/refundNotify");

    private final String type;

    NotifyType(String type){
        this.type = type;
    }

}
