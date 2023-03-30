package com.ruoyi.project.merchant.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author by hujun
 * @date 2023-02-22
 *
 * String userid , String time1 , String time2
 */
@Data
public class SelectKaoHeVo {
    private String userid;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time1;

}
