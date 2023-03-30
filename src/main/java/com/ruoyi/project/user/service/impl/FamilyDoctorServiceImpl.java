package com.ruoyi.project.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.project.user.domain.FamilyDoctor;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.domain.vo.FamilyDoctorVo;
import com.ruoyi.project.user.mapper.FamilyDoctorMapper;
import com.ruoyi.project.user.mapper.XtUserMapper;
import com.ruoyi.project.user.service.IFamilyDoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-28
 */
@Service
public class FamilyDoctorServiceImpl implements IFamilyDoctorService
{
    @Autowired
    private FamilyDoctorMapper familyDoctorMapper;

    @Autowired
    private XtUserMapper userMapper;


    @Autowired
    private XtEmployeeMapper employeeMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public FamilyDoctor selectFamilyDoctorById(String id)
    {
        return familyDoctorMapper.selectFamilyDoctorById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param familyDoctor 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FamilyDoctor> selectFamilyDoctorList(FamilyDoctor familyDoctor)
    {
        return familyDoctorMapper.selectFamilyDoctorList(familyDoctor);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param familyDoctor 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFamilyDoctor(FamilyDoctor familyDoctor)
    {


        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        String uloginname = SecurityUtils.getAppLoginUser().getUser().getUloginname();


        //判断该监护人是否已经被添加
        FamilyDoctor familyDoctor1 = familyDoctorMapper.selectFamilyDoctorByLoginName(userid, familyDoctor.getDoctorPhone());
        if (familyDoctor1 != null){
            throw new ServiceException("该用户已经是您的家庭医生");
        }


        XtUser xtUser = userMapper.selectXtUserByUserLoginName(familyDoctor.getDoctorPhone());
        if (xtUser == null){
            throw new ServiceException("该用户不存在");
        }


        familyDoctor.setDoctorId(xtUser.getUserid());
        familyDoctor.setUserId(userid);

        XtEmployee employee = employeeMapper.selectXtEmployeeByUserId(xtUser.getUserid());

        if (employee != null){
            familyDoctor.setInfoId(employee.getUemployeeid());
            familyDoctor.setUcompid(employee.getUcompid());
        }else {
            familyDoctor.setInfoId("");
        }


        familyDoctor.setId(IdUtil.getSnowflakeNextIdStr());
        familyDoctor.setCreateTime(new Date());
        familyDoctor.setStatus(UserConstants.NOT_SIGN);
        return familyDoctorMapper.insertFamilyDoctor(familyDoctor);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param familyDoctor 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFamilyDoctor(FamilyDoctor familyDoctor)
    {
        return familyDoctorMapper.updateFamilyDoctor(familyDoctor);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFamilyDoctorByIds(String[] ids)
    {
        return familyDoctorMapper.deleteFamilyDoctorByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFamilyDoctorById(String id)
    {
        return familyDoctorMapper.deleteFamilyDoctorById(id);
    }

    @Override
    public List<FamilyDoctorVo> selectFamilyDoctorVoList(FamilyDoctor familyDoctor) {

        List<FamilyDoctorVo> familyDoctorVos = new ArrayList<>();
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        familyDoctor.setUserId(userid);
        List<FamilyDoctor> familyDoctors = familyDoctorMapper.selectFamilyDoctorList(familyDoctor);
        for (int i = 0; i < familyDoctors.size(); i++) {

            FamilyDoctorVo familyDoctorVo = new FamilyDoctorVo();
            BeanUtils.copyProperties(familyDoctors.get(i),familyDoctorVo);

            if(familyDoctors.get(i).getStatus().equals(UserConstants.NOT_SIGN)){
                familyDoctorVo.setStatusName("待签约");
            } else if (familyDoctors.get(i).getStatus().equals(UserConstants.IS_SIGN)) {
                familyDoctorVo.setStatusName("已签约");
            }else if(familyDoctors.get(i).getStatus().equals(UserConstants.DENY_SIGN)){
                familyDoctorVo.setStatusName("拒绝签约");
            }
            String doctorId = familyDoctors.get(i).getDoctorId();
            XtUser xtUser = userMapper.selectXtUserByUserid(doctorId);
            familyDoctorVo.setDoctorName(xtUser.getUsername());

            String path = xtUser.getAvatar();
            if (path == null){
                familyDoctorVo.setAvatar("");
            }else{
                List<String> strings = StringToURL.toURL(path);
                familyDoctorVo.setAvatar(strings.get(0));
            }



            familyDoctorVos.add(familyDoctorVo);
        }
        return familyDoctorVos;
    }

    @Override
    public void sign(String id) {
        int count = familyDoctorMapper.signFamilyDoctorById(id);
        if (count == 0){
            throw new ServiceException("签约失败");
        }
    }

    @Override
    public void deny(String id) {
        int count = familyDoctorMapper.denyFamilyDoctorById(id);
        if (count == 0){
            throw new ServiceException("操作时失败");
        }
    }

    @Override
    public int addFamilyDoctorByBack(FamilyDoctor familyDoctor) {
        //判断该监护人是否已经被添加
        FamilyDoctor familyDoctor1 = familyDoctorMapper.selectFamilyDoctorByLoginName(familyDoctor.getUserId(), familyDoctor.getDoctorPhone());
        if (familyDoctor1 != null){
            throw new ServiceException("该用户已经添加该家庭医生");
        }

        XtUser xtUser = userMapper.selectXtUserByUserLoginName(familyDoctor.getDoctorPhone());
        if (xtUser == null){
            throw new ServiceException("该医生不存在");
        }
        familyDoctor.setDoctorId(xtUser.getUserid());
        XtEmployee employee = employeeMapper.selectXtEmployeeByUserId(xtUser.getUserid());
        if (employee != null){
            familyDoctor.setInfoId(employee.getUemployeeid());
            familyDoctor.setUcompid(employee.getUcompid());
        }else {
            familyDoctor.setInfoId("");
        }
        familyDoctor.setId(IdUtil.getSnowflakeNextIdStr());
        familyDoctor.setCreateTime(new Date());
        familyDoctor.setStatus(UserConstants.NOT_SIGN);
        return familyDoctorMapper.insertFamilyDoctor(familyDoctor);
    }

    @Override
    public List<FamilyDoctorVo> getListByBack(FamilyDoctor familyDoctor) {
        List<FamilyDoctorVo> familyDoctorVos = new ArrayList<>();
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        if (appLoginUser != null) {
            //是商家
            XtEmployee employee = employeeMapper.selectXtEmployeeByUserId(appLoginUser.getUserId());
            familyDoctor.setUcompid(employee.getUcompid());
        }
        List<FamilyDoctor> familyDoctors = familyDoctorMapper.selectFamilyDoctorList(familyDoctor);
        for (FamilyDoctor doctor : familyDoctors) {
            FamilyDoctorVo familyDoctorVo = new FamilyDoctorVo();
            BeanUtils.copyProperties(doctor, familyDoctorVo);
            switch (doctor.getStatus()) {
                case UserConstants.NOT_SIGN:
                    familyDoctorVo.setStatusName("待签约"); break;
                case UserConstants.IS_SIGN:
                    familyDoctorVo.setStatusName("已签约"); break;
                case UserConstants.DENY_SIGN:
                    familyDoctorVo.setStatusName("拒绝签约"); break;
            }
            String doctorId = doctor.getDoctorId();
            XtUser xtUser = userMapper.selectXtUserByUserid(doctorId);
            familyDoctorVo.setDoctorName(xtUser.getUsername());
            String path = xtUser.getAvatar();
            if (path == null) {
                familyDoctorVo.setAvatar("");
            } else {
                List<String> strings = StringToURL.toURL(path);
                familyDoctorVo.setAvatar(strings.get(0));
            }
            familyDoctorVos.add(familyDoctorVo);
        }
        return familyDoctorVos;
    }

}
