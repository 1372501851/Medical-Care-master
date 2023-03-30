package com.ruoyi.project.task.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设置评价词语对象 xt_pingjia_words
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtPingjiaWords
{
    private static final long serialVersionUID = 1L;

    /** 设置评价词语 */
    private String upingjiaWordsid;

    /** 任务来源类型（0图文问诊，1找护工，2找护理） */
    @Excel(name = "任务来源类型", readConverterExp = "0=图文问诊，1找护工，2找护理")
    private String utaskForm;

    /** 词语 */
    @Excel(name = "词语")
    private String uwords;


}
