package com.ruoyi.project.fileknowledge.domain;

import lombok.Data;

/**
 * @author by hujun
 * @date 2023-02-15
 */
@Data
public class XtFileknowledge {
    /**
     * id
     */
    private String id;

    /**
     * 文件路径
     */
    private String filepath;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件类型
     */
    private String filetype;

}
