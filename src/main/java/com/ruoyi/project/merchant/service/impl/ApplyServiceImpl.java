package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.domain.XtRole;
import com.ruoyi.project.common.mapper.XtRoleMapper;
import com.ruoyi.project.common.service.IXtRoleService;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.merchant.service.IXtCompService;
import com.ruoyi.project.merchant.domain.dto.merchant.ApplyCompanyDto;
import com.ruoyi.project.merchant.domain.dto.merchant.ApplyMemberDto;
import com.ruoyi.project.merchant.domain.dto.merchant.MerchantsApplyDto;
import com.ruoyi.project.merchant.service.ApplyService;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.mapper.XtDepartmentMapper;
import com.ruoyi.project.user.service.IallRoleService;
import com.ruoyi.project.user.util.KeyId;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-11-08
 */
@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private XtCompMapper xtCompMapper;

    @Autowired
    private XtDepartmentMapper xtDepartmentMapper;

    @Autowired
    private XtRoleMapper xtRoleMapper;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyMerchant(MerchantsApplyDto applyDto) {

        //插入商家信息
        ApplyCompanyDto applyCompanyDto = applyDto.getApplyCompanyDto();
        XtComp xtComp = new XtComp();
        BeanUtils.copyProperties(applyCompanyDto,xtComp);
        //设置id
        xtComp.setUcompid(IdUtil.getSnowflakeNextIdStr());
        //0:待审核
        xtComp.setApplyStatus("0");

        xtComp.setCreateTime(new Date());



        //插入用户信息
        ApplyMemberDto applyMemberDto = applyDto.getApplyMemberDto();
        XtEmployee xtEmployee = new XtEmployee();
        BeanUtils.copyProperties(applyMemberDto,xtEmployee);

        xtEmployee.setUemployeeid(IdUtil.getSnowflakeNextIdStr());
        //0:待审核
        xtEmployee.setApplyStatus("0");
        //拿到刚生成的商家id
        xtEmployee.setUcompid(xtComp.getUcompid());
        xtEmployee.setCreateTime(new Date());

        xtComp.setEmployId(xtEmployee.getUemployeeid());

        int num1 = xtEmployeeMapper.insertXtEmployee(xtEmployee);
        int num2 = xtCompMapper.insertXtComp(xtComp);

        if (num1 == 0 || num2 == 0){
            throw new ServiceException("申请失败,请重新申请.");
        }

    }

    @Override
    public void applyMember(ApplyMemberDto applyMemberDto) {
        //插入用户信息
        XtEmployee xtEmployee = new XtEmployee();
        BeanUtils.copyProperties(applyMemberDto,xtEmployee);
        xtEmployee.setUemployeeid(IdUtil.getSnowflakeNextIdStr());
        //0:待审核
        xtEmployee.setApplyStatus("0");
        xtEmployee.setCreateTime(new Date());
        int num1 = xtEmployeeMapper.insertXtEmployee(xtEmployee);
        if (num1 == 0){
            throw  new ServiceException("申请失败,请重新申请.");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditMerchant(String compnyId, String employeeId,String status) {

        //这里需要判断审核状态,如果通过则创建部门,更新信息,如果拒绝则不创建部门;


        int num1 = xtCompMapper.updateXtCompApplyStatus(compnyId, status);
        int num2 = xtEmployeeMapper.updateXtEmployeeApplyStatus(employeeId, status);
        if (num1 == 0 || num2 ==0){
            throw new ServiceException("审核失败,请重试.");
        }



        //拒绝则不创建部门信息
        if (status.equals("1")){
            //审核成功后创建,自动创建一个总部门
            XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUemployeeid(employeeId);
            XtComp xtComp = xtCompMapper.selectXtCompByUcompid(compnyId);

            XtDepartment xtDepartment = new XtDepartment();
            xtDepartment.setUdepartmentid(IdUtil.getSnowflakeNextIdStr());
            xtDepartment.setUdepartmentname(xtComp.getUcompname()+"-总部门");
            xtDepartment.setLeader(xtEmployee.getUname());
            xtDepartment.setPhone(xtEmployee.getUmobile());
            xtDepartment.setUcompid(compnyId);
            xtDepartment.setParentId("0");
            xtDepartment.setAncestors("0");
            //不可操作的状态
            xtDepartment.setStatus("3");


            //将部门id赋予管理员
            xtEmployee.setUdepartmentid(xtDepartment.getUdepartmentid());
            int up = xtEmployeeMapper.updateXtEmployee(xtEmployee);

            if (up == 0){
                throw new ServiceException("审核失败,请重试.");
            }

            //创建部门后,需要将管理员加入这个部门(更新信息)
            int result = xtDepartmentMapper.insertXtDepartment(xtDepartment);

            if (result == 0){
                throw new ServiceException("审核失败,请重试.");
            }
        }

    }

    @Override
    public void auditMember(String employeeId, String status) {
        int num2 = xtEmployeeMapper.updateXtEmployeeApplyStatus(employeeId, status);
        if ( num2 ==0){
            throw new ServiceException("审核失败,请重试.");
        }
    }

    @Override
    @Transactional
    public AjaxResult addcomp(String userid) {
        Date d = new Date();
        XtEmployee xtEmployee = new XtEmployee();
        String udepartmentid = KeyId.nextId();
        String uemployeeid = KeyId.nextId();
        xtEmployee.setUemployeeid(uemployeeid);
        xtEmployee.setUdepartmentid(udepartmentid);
        xtEmployee.setUserid(userid);
        String ucompid = KeyId.nextId();
        xtEmployee.setUcompid(ucompid);
        xtEmployee.setCreateTime(d);
        xtEmployeeMapper.insertXtEmployee(xtEmployee);
        XtComp xtComp = new XtComp();
        xtComp.setUcompid(ucompid);
        xtComp.setCreateTime(d);
        xtCompMapper.insertXtComp(xtComp);
        XtRole xtRole = new XtRole();
        xtRole.setUroleid(KeyId.nextId());
        xtRole.setUcompid(ucompid);
        xtRole.setUrolename("管理员");
        xtRole.setUroletype("商家");
        xtRole.setCreateTime(d);
        xtRoleMapper.insertXtRole(xtRole);
        XtDepartment xtDepartment = new XtDepartment();
        xtDepartment.setUcompid(ucompid);
        xtDepartment.setUdepartmentid(udepartmentid);
        xtDepartment.setCreateTime(d);
        xtDepartmentMapper.insertXtDepartment(xtDepartment);
        return AjaxResult.success();
    }

    @Override
    public void auditcomp(String compid, String status) {
        xtCompMapper.updateXtCompApplyStatus(compid,status);
    }

    @Override
    public List<XtComp> selectcomp() {
        return xtCompMapper.selectByid("0");
    }

}
