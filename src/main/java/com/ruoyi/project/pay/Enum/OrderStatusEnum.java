package com.ruoyi.project.pay.Enum;

import lombok.Getter;

/**
 * @Description: 订单状态枚举
 * @Author: 小老鼠呀丶
 * @Date: 2022/11/29
 */
@Getter
public enum OrderStatusEnum {

        WaitForPayment("1","待付款"),
        WaitForDelivery("2","待发货"),
        WaitForReceipt("3","待收货"),
        Finished("4","已完成"),
        Cancelled("5","已取消"),
        Refunding("6","退款中"),
        RefundSuccess("7","已退款");
        private final String code;

        private final String message;
        OrderStatusEnum(String code,String message){
            this.code = code;
            this.message = message;
        }

}
