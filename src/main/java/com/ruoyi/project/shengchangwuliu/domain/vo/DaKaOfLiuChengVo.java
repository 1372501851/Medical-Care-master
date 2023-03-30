package com.ruoyi.project.shengchangwuliu.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-28
 */
@Data
public class DaKaOfLiuChengVo {
    private String id;
    private String orderid;
    private String liuchengid;
    private List<String> status;
    private List<String> peoplename;
    private List<String> utime;
}
