package com.ruoyi.project.merchant.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.dto.merchant.ApplyMemberDto;
import com.ruoyi.project.merchant.domain.dto.merchant.MerchantsApplyDto;

import java.util.List;

public interface ApplyService {
    void applyMerchant(MerchantsApplyDto applyDto);

    void applyMember(ApplyMemberDto applyMemberDto);

    void auditMerchant(String compnyId,String employeeId, String status);

    void auditMember(String employeeId, String status);

    AjaxResult addcomp(String userid);

    void auditcomp(String compid,String status);

    List<XtComp> selectcomp();
}
