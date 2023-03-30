package com.ruoyi.project.task.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 任务结果 主要是看病医生填写的结果对象 xt_task_results
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Data
public class XtTaskResults
{
    private static final long serialVersionUID = 1L;

    /** 任务结果ID */
    private String utaskResultsid;

    /** 任务ID */
    @Excel(name = "任务ID")
    @NotBlank(message = "任务ID不能为空")
    private String utaskid;


    /** 医生ID */
    @Excel(name = "医生ID")
    @NotBlank(message = "医生信息ID不能为空")
    private String uemployeeid;

    /** 病因 */
    @Excel(name = "病因")
    @Size(max = 200,message = "病因描述过长")
    private String ubingyin;

    /** 处理结果 */
    @Excel(name = "处理结果")
    @Size(max = 200,message = "处理结果描述过长")
    private String uprocessingResults;


}
