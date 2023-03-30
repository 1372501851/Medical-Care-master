package com.ruoyi.project.peixun.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-21
 */
@Data
public class XtEmployPeiXun {

    private String id;
    private String employid;
    private String peixunziliaoid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date utime;
    private Integer xuexistatus;
}
