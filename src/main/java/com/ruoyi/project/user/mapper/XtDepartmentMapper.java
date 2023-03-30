package com.ruoyi.project.user.mapper;

import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.user.domain.XtDepartment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门(科室)信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Repository
public interface XtDepartmentMapper
{
    /**
     * 查询部门(科室)信息,当id是空会查询部门(科室)与商家类别的所有组合(笛卡尔积)
     *
     * @param udepartmentid 部门(科室)信息主键
     * @return 部门(科室)信息
     */
    public XtDepartment selectXtDepartmentByUdepartmentid(String udepartmentid);

    /**
     * 查询部门(科室)信息列表
     *
     * @param xtDepartment 部门(科室)信息
     * @return 部门(科室)信息集合
     */
    public List<XtDepartment> selectXtDepartmentList(XtDepartment xtDepartment);

    /**
     * 新增部门(科室)信息
     *
     * @param xtDepartment 部门(科室)信息
     * @return 结果
     */
    public int insertXtDepartment(XtDepartment xtDepartment);

    /**
     * 修改部门(科室)信息
     *
     * @param xtDepartment 部门(科室)信息
     * @return 结果
     */
    public int updateXtDepartment(XtDepartment xtDepartment);

    /**
     * 删除部门(科室)信息
     *
     * @param udepartmentid 部门(科室)信息主键
     * @return 结果
     */
    public int deleteXtDepartmentByUdepartmentid(String udepartmentid);

    /**
     * 批量删除部门(科室)信息
     *
     * @param udepartmentids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtDepartmentByUdepartmentids(String[] udepartmentids);

    int hasChildByDeptId(String deptId);

    int checkDeptExistUser(String deptId);

    XtDepartment checkDeptNameUnique(String departmentId,String udepartmentname, String parentId);

    List<XtDepartment> selectChildrenDeptById(String deptId);

    int updateDeptChildren(@Param("depts") List<XtDepartment> depts);

    int selectNormalChildrenDeptById(String udepartmentid);

    @Select("select udepartmentid from TABLE ( xt_department )")
    List<Long> selectAllDepartmentId();

    @Select("SELECT udepartmentname FROM xt_department WHERE udepartmentid = #{id}")
    String selectudepartmentidNameById(String id);
}
