package com.ruoyi.project.merchant.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author by hujun
 * @date 2022-12-08
 */
@Data
public class EquipmentInfoVo {

    /** 设备ID */
    private String uequipmentid;

    /** 设备类型ID */
    private String uequipmentTypeid;

    /** 设备名称 */
    private String uequipmentname;


    /** 设备状态（1设备开启、0设备关闭,2暂停） */
    private String uequipmentStatus;

    /**设备状态名称*/
    private String equipmentStatusName;

    /** 滴速(每分钟的滴速，20,30,40,50,60,70,80,90,100,110,120) */
    private Long drippingSpeed;



    /** 网络(1异常,0正常） */
    private String unetworkError;

    private String unetworkErrorName;


    /** 欠压报警(0正常，1报警) */
    private String ubrownOut;


    private String ubrownOutName;


    /** 商家ID */
    private String ucompid;


    /** 商家名称 */
    private String ucompname;


    /**设备类型的图片*/
    private String pic;

    private Boolean open;
    private Boolean play;



}
