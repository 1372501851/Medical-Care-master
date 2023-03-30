package com.ruoyi.project.remind.domain.Dto;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * @author by hujun
 * @date 2022-12-13
 */

@Data
public class RemindUserDTO {
    /** 提醒者id */

    private String[] reminderIds;

    /** 提醒项id */

    private String remindId;
}
