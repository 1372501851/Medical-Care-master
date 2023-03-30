package com.ruoyi.project.merchant.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.domain.query.EmployeeQuery;
import com.ruoyi.project.merchant.domain.vo.CompSelect;
import com.ruoyi.project.merchant.domain.vo.EmployeeVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtEmployeeService
{
     XtEmployee queryUserInfo(String userid);


    /**
     * 查询用户信息
     *
     * @param uemployeeid 用户信息主键
     * @return 用户信息
     */
    public XtEmployee selectXtEmployeeByUemployeeid(String uemployeeid);

    /**
     * 查询用户信息列表
     *
     * @param xtEmployee 用户信息
     * @return 用户信息集合
     */
    public List<XtEmployee> selectXtEmployeeList(XtEmployee xtEmployee);

    /**
     * 新增用户信息
     *
     * @param xtEmployee 用户信息
     * @return 结果
     */
    public int insertXtEmployee(XtEmployee xtEmployee ,  HttpServletRequest request);

    /**
     * 修改用户信息
     *
     * @param xtEmployee 用户信息
     * @return 结果
     */
    public int updateXtEmployee(XtEmployee xtEmployee);

    /**
     * 批量删除用户信息
     *
     * @param uemployeeids 需要删除的用户信息主键集合
     * @return 结果
     */
    public int deleteXtEmployeeByUemployeeids(String[] uemployeeids);

    /**
     * 删除用户信息信息
     *
     * @param uemployeeid 用户信息主键
     * @return 结果
     */
    public int deleteXtEmployeeByUemployeeid(String uemployeeid);


    PageInfo selectList(EmployeeQuery employeeQuery);

    AjaxResult selectByid();

    List<XtEmployee> getCompEmployeeList(XtEmployee xtEmployee);

    List<XtEmployee> getApplyEmployeeList();

    XtEmployee getInfoByToken();

    List<CompSelect> getAllEmployeeSelectList();

    /**
     * 获取员工id
     * @return
     */
    String huoquid(HttpServletRequest request);

    /**
     * 员工离职申请
     * @param uemployeeid 员工id
     * @param reasonOfQuit 员工领导的名字
     * @return
     */
    int quit(String uemployeeid , String reasonOfQuit ,String farendaibiao ,String zhuguanlingdao);

    List<XtEmployee> selectEmployListByComp(String status);

    List<XtEmployee> selectEmployListByComp2(String name);

    List<XtEmployee> selectMyAllEmploy();

    String selectMyOccupation();
}
