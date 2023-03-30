package com.ruoyi.project.shengchangwuliu.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-27
 */
@Data
public class LiuchengbianzhiVo {

    private String id;

    private String uname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date utime;

    private List<String> buzhoulist;

}
