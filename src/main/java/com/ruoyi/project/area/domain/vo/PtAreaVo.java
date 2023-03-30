package com.ruoyi.project.area.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 小老鼠呀丶
 * @Date: 2023/1/13
 */
@Data
public class PtAreaVo {

    /** ID */
    private String uareaid;

    /** 父级层ID */
    @Excel(name = "父级层ID")
    private String uparentid;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String uareaname;

    /** 级层 */
    @Excel(name = "级层")
    private Integer ulevel;

    /** 首字母 */
    @Excel(name = "首字母")
    private String ucode;

    /** 国家语言 */
    @Excel(name = "国家语言")
    private String ulanguage;

    private List<PtAreaVo> children = new ArrayList<>();
}
