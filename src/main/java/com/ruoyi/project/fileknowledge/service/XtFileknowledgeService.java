package com.ruoyi.project.fileknowledge.service;

import com.ruoyi.project.fileknowledge.domain.XtFileknowledge;

import java.util.List;

public interface XtFileknowledgeService {

    Integer addFileknowledge(XtFileknowledge xtFileknowledge);

    List<XtFileknowledge> seleXtFileknowledgeByFiletype(String filetype);

    Integer delXtFileknowledge(String id);

    List<XtFileknowledge> seleLikeXtFileknowledge(String name);
}
