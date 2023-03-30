package com.ruoyi.project.merchant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-17
 */
@Data
public class XtKaohe {

    private String id;

    private String uesrid;

    /**自己考核，默认为0，0就是空（表示没填），下面也是一样*/
    private String uself;

    /**工作考核*/
    private String uwork;

    /**业绩考核*/
    private String achievement;


    private String department;


    private String comprehensive;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date utime;
}
