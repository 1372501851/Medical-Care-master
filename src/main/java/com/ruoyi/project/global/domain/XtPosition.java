package com.ruoyi.project.global.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 职位 提供选择（传职位名称给用户信息）对象 xt_position
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtPosition
{


    private String upositionid;
    /** 职位名称（住院医师、主治医师、副主任医师、主任医师） */
    private String upositionname;

    /** 职位描述 */
    @Excel(name = "职位描述")
    private String udesc;


}
