package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.FamilyDoctor;
import com.ruoyi.project.user.domain.vo.FamilyDoctorVo;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2022-11-28
 */
public interface IFamilyDoctorService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public FamilyDoctor selectFamilyDoctorById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param familyDoctor 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FamilyDoctor> selectFamilyDoctorList(FamilyDoctor familyDoctor);

    /**
     * 新增【请填写功能名称】
     *
     * @param familyDoctor 【请填写功能名称】
     * @return 结果
     */
    public int insertFamilyDoctor(FamilyDoctor familyDoctor);

    /**
     * 修改【请填写功能名称】
     *
     * @param familyDoctor 【请填写功能名称】
     * @return 结果
     */
    public int updateFamilyDoctor(FamilyDoctor familyDoctor);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteFamilyDoctorByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteFamilyDoctorById(String id);

    List<FamilyDoctorVo> selectFamilyDoctorVoList(FamilyDoctor familyDoctor);

    void sign(String id);

    void deny(String id);

    int addFamilyDoctorByBack(FamilyDoctor familyDoctor);

    List<FamilyDoctorVo> getListByBack(FamilyDoctor familyDoctor);

}
