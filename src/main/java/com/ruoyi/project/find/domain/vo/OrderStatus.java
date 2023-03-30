package com.ruoyi.project.find.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2022-11-30
 */
@Data
public class OrderStatus {

    private String id;
    private String hospitalName;
    private String deptName;
    private String status;
    private String statusName;

    private String chatId;

    @JsonFormat(pattern = "yyyy.MM.dd ", timezone="GMT+8")
    private Date date;

    private String avatar;
}
