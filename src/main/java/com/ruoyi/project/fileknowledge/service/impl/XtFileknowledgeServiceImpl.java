package com.ruoyi.project.fileknowledge.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.fileknowledge.domain.XtFileknowledge;
import com.ruoyi.project.fileknowledge.service.XtFileknowledgeService;

import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-15
 */

public class XtFileknowledgeServiceImpl implements XtFileknowledgeService {

    @Override
    public Integer addFileknowledge(XtFileknowledge xtFileknowledge) {
        xtFileknowledge.setId(IdUtil.getSnowflakeNextIdStr());
        return null;
    }

    @Override
    public List<XtFileknowledge> seleXtFileknowledgeByFiletype(String filetype) {
        return null;
    }

    @Override
    public Integer delXtFileknowledge(String id) {
        return null;
    }

    @Override
    public List<XtFileknowledge> seleLikeXtFileknowledge(String name) {
        return null;
    }
}
