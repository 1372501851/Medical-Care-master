package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 设备对象 xt_equipment
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Data
public class XtEquipment
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private String uequipmentid;

    /** 设备类型ID */
    @Excel(name = "设备类型ID")
    private String uequipmentTypeid;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String uequipmentname;

    /** 设备二维码信息(二维码编码，用的时候前端生成二维码) */
    @Excel(name = "设备二维码信息(二维码编码，用的时候前端生成二维码)")
    private String uqrCode;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String uquipmentCode;

    /** 创建用户ID(当商家ID为空的时候设备属有个人) */
    @Excel(name = "创建用户ID(当商家ID为空的时候设备属有个人)")
    private String userid;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String ucompid;

    /**
     * 商家类型
     */
    @Excel(name = "商家类型")
    private String ucompType;

    /** 部门(科室)ID */
    @Excel(name = "部门(科室)ID")
    private String udepartmentid;

    /** 所在城市（可以到街道办） */
    @Excel(name = "所在城市", readConverterExp = "可=以到街道办")
    private String uareaid;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal ulatitude;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal ulongitude;

    /** 地址 */
    @Excel(name = "地址")
    private String uaddress;

    /** 设备状态（1设备开启、0设备关闭,2暂停） */
    @Excel(name = "设备状态", readConverterExp = "1=设备开启、0设备关闭,2暂停")
    private String uequipmentStatus;

    /** 故障(1故障报警、0结束报警) */
    @Excel(name = "故障(1故障报警、0结束报警)")
    private String uequipmentError;

    /** 检测(0检测中、1恢复检测,2停止检测） */
    @Excel(name = "检测(0检测中、1恢复检测,2停止检测）")
    private String uequipmentTesting;

    /** 欠压报警(0正常，1报警) */
    @Excel(name = "欠压报警(0正常，1报警)")
    private String ubrownOut;

    /** 网络(1异常,0正常） */
    @Excel(name = "网络(1异常,0正常）")
    private String unetworkError;

    /** 1上传、2下传，0不传 */
    @Excel(name = "1上传、2下传，0不传")
    private String uploadDownload;

    /** 滴速(每分钟的滴速，20,30,40,50,60,70,80,90,100,110,120)  最小滴速*/
    @Excel(name = "滴速(每分钟的滴速，20,30,40,50,60,70,80,90,100,110,120)")
    private Long drippingSpeedMin;

    /**最大滴速*/
    private Long drippingSpeedMax;

    /** 设备设置密码，设置了没有需要有密码才能开启（由设备属有人员处理） */
    @Excel(name = "设备设置密码，设置了没有需要有密码才能开启", readConverterExp = "由=设备属有人员处理")
    private String uequipmentPwd;

    /** 创建时间（时间戳） */
    @Excel(name = "创建时间", readConverterExp = "时=间戳")
    private Long ucreatedate;


}
