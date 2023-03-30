package com.ruoyi.project.im.Dto;

import lombok.Data;

/**
 * @author by hujun
 * @date 2022-10-19
 */

@Data
public class QueryHistoryMessageDto {
    private Integer pageNum;
    private Integer pageSize;
    private String fromId;
    private String type;
    private long timeStamp;
    private String weizhi;
}
