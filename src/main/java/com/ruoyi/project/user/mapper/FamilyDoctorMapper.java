package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.FamilyDoctor;
import com.ruoyi.project.user.domain.UserGuardianship;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-28
 */

@Repository
public interface FamilyDoctorMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteFamilyDoctorById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFamilyDoctorByIds(String[] ids);

    FamilyDoctor selectFamilyDoctorByLoginName(String userid, String doctorPhone);

    int signFamilyDoctorById(String id);

    int denyFamilyDoctorById(String id);
}
