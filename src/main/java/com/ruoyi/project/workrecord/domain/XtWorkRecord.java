package com.ruoyi.project.workrecord.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-17
 */
@Data
public class XtWorkRecord {
    private String id;
    private String userid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;
    private String address;
}
