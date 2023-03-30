package com.ruoyi.project.kaoqin.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.kaoqin.domain.KaoQin;
import com.ruoyi.project.kaoqin.mapper.KaoQinMapper;
import com.ruoyi.project.kaoqin.service.KaoQinService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author by hujun
 * @date 2023-02-14
 */
@Service
public class KaoQinServiceImpl implements KaoQinService {
    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private KaoQinMapper kaoQinMapper;

    @Override
    public KaoQin daKa(KaoQin kaoQin) {
        kaoQin.setId(IdUtil.getSnowflakeNextIdStr());
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();

        String uemployeeid = xtEmployeeMapper.selectXtEmployeeByUserId(userId).getUemployeeid();
        kaoQin.setEmployid(uemployeeid);
        kaoQinMapper.add(kaoQin);

        return kaoQin;
    }

    @Override
    public List<KaoQin> chaKan(String employid) {
        List<KaoQin> kaoQins = kaoQinMapper.chaKan(employid);
        return kaoQins;
    }

    @Override
    public Set<XtEmployee> seledakaEmploy() {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtEmployee xtEmployee ;
        try {
            xtEmployee = xtEmployeeMapper.selectXtEmployeeByUserId2(userId);
            xtEmployee.getUemployeeid();
        } catch (NullPointerException e){
            throw new ServiceException("获取员工id为空，你不是一个员工");
        }
        Set<XtEmployee> set = new HashSet<>();
        set.add(xtEmployee);
        List<XtEmployee> list = xtEmployeeMapper.selecomid(xtEmployee.getUcompid());
        for (XtEmployee x : list) {
            set.add(x);
        }
        return set;
    }
}
