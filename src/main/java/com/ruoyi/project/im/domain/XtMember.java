package com.ruoyi.project.im.domain;

import lombok.Data;

/**
 * @author by hujun
 * @date 2023-03-23
 */
@Data
public class XtMember {

    /**成员表id*/
    private String umemberId;

    /**成员的用户id，也是登录连接聊天的id*/
    private String userId;
}
