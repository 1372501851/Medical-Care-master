package com.ruoyi.project.common.service.impl;

import com.ruoyi.project.common.domain.XtFamilyDoctor;
import com.ruoyi.project.common.mapper.XtFamilyDoctorMapper;
import com.ruoyi.project.common.service.IXtFamilyDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的家庭医生,主要是用户与医生签约Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtFamilyDoctorServiceImpl implements IXtFamilyDoctorService
{
    @Autowired
    private XtFamilyDoctorMapper xtFamilyDoctorMapper;

    /**
     * 查询我的家庭医生,主要是用户与医生签约
     *
     * @param ufamilyDoctorid 我的家庭医生,主要是用户与医生签约主键
     * @return 我的家庭医生,主要是用户与医生签约
     */
    @Override
    public XtFamilyDoctor selectXtFamilyDoctorByUfamilyDoctorid(String ufamilyDoctorid)
    {
        return xtFamilyDoctorMapper.selectXtFamilyDoctorByUfamilyDoctorid(ufamilyDoctorid);
    }

    /**
     * 查询我的家庭医生,主要是用户与医生签约列表
     *
     * @param xtFamilyDoctor 我的家庭医生,主要是用户与医生签约
     * @return 我的家庭医生,主要是用户与医生签约
     */
    @Override
    public List<XtFamilyDoctor> selectXtFamilyDoctorList(XtFamilyDoctor xtFamilyDoctor)
    {
        return xtFamilyDoctorMapper.selectXtFamilyDoctorList(xtFamilyDoctor);
    }

    /**
     * 新增我的家庭医生,主要是用户与医生签约
     *
     * @param xtFamilyDoctor 我的家庭医生,主要是用户与医生签约
     * @return 结果
     */
    @Override
    public int insertXtFamilyDoctor(XtFamilyDoctor xtFamilyDoctor)
    {
        return xtFamilyDoctorMapper.insertXtFamilyDoctor(xtFamilyDoctor);
    }

    /**
     * 修改我的家庭医生,主要是用户与医生签约
     *
     * @param xtFamilyDoctor 我的家庭医生,主要是用户与医生签约
     * @return 结果
     */
    @Override
    public int updateXtFamilyDoctor(XtFamilyDoctor xtFamilyDoctor)
    {
        return xtFamilyDoctorMapper.updateXtFamilyDoctor(xtFamilyDoctor);
    }

    /**
     * 批量删除我的家庭医生,主要是用户与医生签约
     *
     * @param ufamilyDoctorids 需要删除的我的家庭医生,主要是用户与医生签约主键
     * @return 结果
     */
    @Override
    public int deleteXtFamilyDoctorByUfamilyDoctorids(String[] ufamilyDoctorids)
    {
        return xtFamilyDoctorMapper.deleteXtFamilyDoctorByUfamilyDoctorids(ufamilyDoctorids);
    }

    /**
     * 删除我的家庭医生,主要是用户与医生签约信息
     *
     * @param ufamilyDoctorid 我的家庭医生,主要是用户与医生签约主键
     * @return 结果
     */
    @Override
    public int deleteXtFamilyDoctorByUfamilyDoctorid(String ufamilyDoctorid)
    {
        return xtFamilyDoctorMapper.deleteXtFamilyDoctorByUfamilyDoctorid(ufamilyDoctorid);
    }
}
