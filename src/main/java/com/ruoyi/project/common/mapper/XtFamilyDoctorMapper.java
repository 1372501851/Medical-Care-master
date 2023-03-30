package com.ruoyi.project.common.mapper;

import com.ruoyi.project.common.domain.XtFamilyDoctor;

import java.util.List;

/**
 * 我的家庭医生,主要是用户与医生签约Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtFamilyDoctorMapper
{
    /**
     * 查询我的家庭医生,主要是用户与医生签约
     *
     * @param ufamilyDoctorid 我的家庭医生,主要是用户与医生签约主键
     * @return 我的家庭医生,主要是用户与医生签约
     */
    public XtFamilyDoctor selectXtFamilyDoctorByUfamilyDoctorid(String ufamilyDoctorid);

    /**
     * 查询我的家庭医生,主要是用户与医生签约列表
     *
     * @param xtFamilyDoctor 我的家庭医生,主要是用户与医生签约
     * @return 我的家庭医生,主要是用户与医生签约集合
     */
    public List<XtFamilyDoctor> selectXtFamilyDoctorList(XtFamilyDoctor xtFamilyDoctor);

    /**
     * 新增我的家庭医生,主要是用户与医生签约
     *
     * @param xtFamilyDoctor 我的家庭医生,主要是用户与医生签约
     * @return 结果
     */
    public int insertXtFamilyDoctor(XtFamilyDoctor xtFamilyDoctor);

    /**
     * 修改我的家庭医生,主要是用户与医生签约
     *
     * @param xtFamilyDoctor 我的家庭医生,主要是用户与医生签约
     * @return 结果
     */
    public int updateXtFamilyDoctor(XtFamilyDoctor xtFamilyDoctor);

    /**
     * 删除我的家庭医生,主要是用户与医生签约
     *
     * @param ufamilyDoctorid 我的家庭医生,主要是用户与医生签约主键
     * @return 结果
     */
    public int deleteXtFamilyDoctorByUfamilyDoctorid(String ufamilyDoctorid);

    /**
     * 批量删除我的家庭医生,主要是用户与医生签约
     *
     * @param ufamilyDoctorids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtFamilyDoctorByUfamilyDoctorids(String[] ufamilyDoctorids);
}
