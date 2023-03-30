package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.tree.DeptTreeSelect;
import com.ruoyi.project.user.domain.vo.DeptVo;

import java.util.List;

/**
 * 部门(科室)信息Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtDepartmentService
{
    /**
     * 查询部门(科室)信息
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
     * 批量删除部门(科室)信息
     *
     * @param udepartmentids 需要删除的部门(科室)信息主键集合
     * @return 结果
     */
    public int deleteXtDepartmentByUdepartmentids(String[] udepartmentids);

    /**
     * 删除部门(科室)信息信息
     *
     * @param udepartmentid 部门(科室)信息主键
     * @return 结果
     */
    public int deleteXtDepartmentByUdepartmentid(String udepartmentid);



    List<DeptTreeSelect> selectDeptTreeList(XtDepartment dept);

    List<DeptVo> getXtDepartmentTree(XtDepartment xtDepartment);

    List<DeptTreeSelect> getDeptTreeList(XtDepartment dept);

}
