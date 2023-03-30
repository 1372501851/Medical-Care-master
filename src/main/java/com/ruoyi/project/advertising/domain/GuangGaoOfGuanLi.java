package com.ruoyi.project.advertising.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @author by hujun
 * @date 2023-02-22
 */
@Data
public class GuangGaoOfGuanLi {
    private String id;
    private String userid;
    private String utype;
    private String neirong;
    private String fanwei;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date starttime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endtime;
    private String weizhi;

    /**
     * 审核状态
     */
    private String status;


}
