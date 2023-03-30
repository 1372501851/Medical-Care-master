package com.ruoyi.project.merchant.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 病房对象 xt_room
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Data
public class XtRoom
{
    private static final long serialVersionUID = 1L;

    /** 病房ID */
    private String uroomid;

    /** 商家ID */
    @Excel(name = "商家ID")
    @NotBlank(message = "商家ID不能为空")
    private String ucompid;

    /** 属于那个部门（科室）id */
    @Excel(name = "属于那个部门", readConverterExp = "科=室")
    @NotBlank(message = "部门ID不能为空")
    private String udepartmentid;

    /** 楼栋名称（一个医院的楼栋名称不能重复） */
    @Excel(name = "楼栋名称", readConverterExp = "一=个医院的楼栋名称不能重复")
    @NotBlank(message = "楼栋名称不能为空")
    private String ubuildingname;

    /** 楼层（选择） */
    @Excel(name = "楼层", readConverterExp = "选=择")
    @NotBlank(message = "楼层编号不能为空")
    private String ufloorNo;

    /** 房间号（同一层楼不能重复） */
    @Excel(name = "房间号", readConverterExp = "同=一层楼不能重复")
    @NotBlank(message = "病房编号不能为空")
    @Size(min = 1,max = 5,message = "病房编号长度不能超过5")
    private String uroomNo;

    /** 描述 */
    @Excel(name = "描述")
    @Size(max = 50,message = "描述信息过长")
    private String udesc;


}
