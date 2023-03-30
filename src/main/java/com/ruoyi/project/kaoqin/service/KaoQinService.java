package com.ruoyi.project.kaoqin.service;

import com.ruoyi.project.kaoqin.domain.KaoQin;
import com.ruoyi.project.merchant.domain.XtEmployee;

import java.util.List;
import java.util.Set;

public interface KaoQinService {

    KaoQin daKa(KaoQin kaoQin);

    List<KaoQin> chaKan(String employid);

    Set<XtEmployee> seledakaEmploy();
}
