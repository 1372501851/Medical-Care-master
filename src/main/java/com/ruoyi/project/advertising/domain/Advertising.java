package com.ruoyi.project.advertising.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Advertising)实体类
 *
 * @author makejava
 * @since 2022-11-23 09:45:41
 */
@Data
public class Advertising implements Serializable {
    private static final long serialVersionUID = -18828896368979136L;

    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 类型:1新闻;2:消息
     */
    private String type;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}

