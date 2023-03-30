package com.ruoyi.project.find.domain.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author by hujun
 * @date 2022-11-29
 */
@Data
public class AddModel {
    private String compId;
    private String[] guardianIds;
    private String[] serviceId;
    private String serviceType;

    @Size(max = 250,message = "字数超过限制")
    private String message;
}
