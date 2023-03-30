package com.ruoyi.project.richangjingyin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-23
 */

@Data
public class XtDangJu1 {

    private String id;
    private String slbm;
    private String slsy;
    private String pm1;
    private String dl1;
    private String sl1;
    private String pm2;
    private String dl2;
    private String sl2;
    private String pm3;
    private String dl3;
    private String sl3;
    private String pm4;
    private String dl4;
    private String sl4;
    private String pm5;
    private String dl5;
    private String sl5;
    private String pm6;
    private String dl6;
    private String sl6;
    private String pm7;
    private String dl7;
    private String sl7;
    private String pm8;
    private String dl8;
    private String sl8;
    private String pm9;
    private String dl9;
    private String sl9;
    private String slr;
    private String bmsh;
    private String zbsh;
    private String sltype;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date date;

}


