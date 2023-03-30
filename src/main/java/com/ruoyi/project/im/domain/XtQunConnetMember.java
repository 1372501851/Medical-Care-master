package com.ruoyi.project.im.domain;

import lombok.Data;

/**
 * 群与成员的中间表
 * @author by hujun
 * @date 2023-03-23
 */

@Data
public class XtQunConnetMember {
    /**中间表主键*/
    private String uqunConnectMemberId;
    /**群主键*/
    private String uqunId;
    /**成员主键*/
    private String umemberId;
}
