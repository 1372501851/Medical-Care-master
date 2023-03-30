package com.ruoyi.project.merchant.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EquipmentEnum {

    /**设备状态*/
    START("1","开启"),
    STOP("0","停止"),
    SUSPENDED("2","暂停"),

    /**网络状态*/
    NTEWORK_NORMAL("0","正常"),
    NTEWORK_ERROR("1","异常"),

    /**欠压状态*/
    UNDER_VOLTAGE_NORMAL("0","正常"),
    UNDER_VOLTAGE_ERROR("1","异常");

//    public static final Map<String,String> equipmentEnumMap = new HashMap<>();
//
//    static {
//        for(EquipmentEnum e : EnumSet.allOf(EquipmentEnum.class)){
//            equipmentEnumMap.put(e.getCode(),e.getName());
//        }
//    }
    private final String code;
    private final String name;
    EquipmentEnum(String code, String name){
        this.code = code;
        this.name = name;
    }


    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }
}
