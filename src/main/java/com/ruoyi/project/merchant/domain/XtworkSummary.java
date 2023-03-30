package com.ruoyi.project.merchant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-17
 */
@Data
public class XtworkSummary {

    private String id;

    private String userid;

    private Integer t1;

    private Integer t2;

    private Integer t3;

    private Integer t4;

    private Integer t5;

    @JsonFormat(pattern ="yyyy-MM-dd hh:mm:ss")
    private Date creatDate;

    /**文字*/
    private String words;
}
