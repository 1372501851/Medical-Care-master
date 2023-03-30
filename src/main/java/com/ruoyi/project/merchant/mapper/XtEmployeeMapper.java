package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtEmployee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Repository
public interface XtEmployeeMapper
{
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

    public int insertXtEmployee(XtEmployee xtEmployee);

    /**
     * 修改用户信息
     *
     * @param xtEmployee 用户信息
     * @return 结果
     */
    public int updateXtEmployee(XtEmployee xtEmployee);

    /**
     * 删除用户信息
     *
     * @param uemployeeid 用户信息主键
     * @return 结果
     */
    public int deleteXtEmployeeByUemployeeid(String uemployeeid);

    /**
     * 批量删除用户信息
     *
     * @param uemployeeids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtEmployeeByUemployeeids(String[] uemployeeids);

    XtEmployee selectXtEmployeeByUserId(String userid);

    @Select("SELECT * FROM xt_employee where userid = #{userid}")
    XtEmployee selectXtEmployeeByUserId2(String userid);

//    XtEmployee selectXtEmployeeByLoginName(String loginAcct);

    List<XtEmployee> selectXtEmployeeByusertypeId(String usertype,String apply_status);

    int updateXtEmployeeApplyStatus(String employeeId, String status);

    List<XtEmployee> selectXtEmployeeListByQuery(XtEmployee employee);

    List<XtEmployee> selectXtEmployeeListByCompId(XtEmployee xtEmployee);


    List<XtEmployee> getApplyEmployeeList();

    List<String> selectUserIdList();

    /**
     * 员工离职申请
     * @param uemployeeid
     * @return
     */
    @Update("UPDATE xt_employee set status = '1' , reason_of_quit = #{reasonOfQuit} , apply_status = '0',farendaibiao = #{farendaibiao} , zhuguanlingdao = #{zhuguanlingdao}  where uemployeeid = #{uemployeeid}")
    int quit(String uemployeeid , String reasonOfQuit , String farendaibiao ,String zhuguanlingdao);

    @Select("select  distinct upost from xt_employee ")
    List<String> selectAllpost();

    @Select("SELECT * FROM xt_employee WHERE upost = #{post}")
    List<XtEmployee> selectAllEmployByPost(String post);

    @Select("SELECT * FROM xt_employee where  uname like #{name}")
    List<XtEmployee> selectLikeEmployByName(String post , String name);

    @Select("SELECT * FROM xt_employee WHERE ucompid = #{id}")
    List<XtEmployee> selecomid(String id);

    @Update("UPDATE xt_employee SET status = #{status} , apply_status = #{applyStatus} WHERE  uemployeeid = #{employId} ")
    Integer updateEmploy(String status , String applyStatus , String employId);

    @Select("SELECT userid FROM xt_employee WHERE uemployeeid = #{employId}")
    String selectUserIdByEmployId(String employId);

}
