package com.ruoyi.project.find.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IdentityEnum {

    WORK("1","护工"),
    COMMON("0","普通用户"),
    DOCTOR("2","医生");

    private final String code;
    private final String name;


    /**将枚举类放入map中,方便使用*/
    public static final Map<String,String> identityEnumMap= new HashMap<>();

    static {
        for (IdentityEnum s : EnumSet.allOf(IdentityEnum.class)) {
            identityEnumMap.put(s.getCode(), s.getName());
        }
    }

    IdentityEnum(String code, String name){
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
