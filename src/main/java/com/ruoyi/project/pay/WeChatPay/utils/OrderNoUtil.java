package com.ruoyi.project.pay.WeChatPay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description: 订单号工具类
 * @Author: 小老鼠呀丶
 * @Date: 2022/11/29
 */
public class OrderNoUtil {

    /**
     * 获取订单编号
     */
    public static String getOrderNo() {
        return "ORDER_" + getNo();
    }

    /**
     * 获取退款单号
     */
    public static String getRefundNo() {
        return "REFUND_" + getNo();
    }

    /**
     * 获取编号
     */
    private static String getNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result.append(random.nextInt(10));
        }
        return newDate + result;
    }

}
