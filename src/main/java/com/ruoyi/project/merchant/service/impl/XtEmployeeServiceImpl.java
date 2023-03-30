package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.domain.query.EmployeeQuery;
import com.ruoyi.project.merchant.domain.vo.CompSelect;
import com.ruoyi.project.merchant.domain.vo.EmployeeVo;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.domain.XtDepartment;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.XtDepartmentMapper;
import com.ruoyi.project.user.mapper.XtUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtEmployeeServiceImpl implements IXtEmployeeService
{
    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;


    @Autowired
    private XtCompMapper compMapper;

    @Autowired
    private XtDepartmentMapper departmentMapper;

    @Autowired
    private XtUserMapper userMapper;

    @Autowired
    private AppLoginTokenService appLoginTokenService;



    @Override
    public XtEmployee queryUserInfo(String userid) {
        XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUserId(userid);
        return xtEmployee;
    }

    /**
     * 查询用户信息
     *
     * @param uemployeeid 用户信息主键
     * @return 用户信息
     */
    @Override
    public XtEmployee selectXtEmployeeByUemployeeid(String uemployeeid)
    {
        return xtEmployeeMapper.selectXtEmployeeByUemployeeid(uemployeeid);
    }

    /**
     * 查询用户信息列表
     *
     * @param xtEmployee 用户信息
     * @return 用户信息
     */
    @Override
    public List<XtEmployee> selectXtEmployeeList(XtEmployee xtEmployee)
    {
        //这里需要添加限制条件(管理员可以查看所有员工信息,商家只能查看自己部门的员工)

        //如果是从用户表登录,只能查看当前商家的员工信息

        //如果是从管理员表登录,通过区域id来查找所有子区域内的员工;先通过区域id找到所有商家,再通过商家id找到所有员工;
        return xtEmployeeMapper.selectXtEmployeeList(xtEmployee);
    }

    /**
     * 新增用户信息
     *
     * @param xtEmployee 用户信息
     * @return 结果
     */
    @Override
    public int insertXtEmployee(XtEmployee xtEmployee , HttpServletRequest request)
    {
        //设置信息id;
        xtEmployee.setUemployeeid(IdUtil.getSnowflakeNextIdStr());


        //设置用户id
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        xtEmployee.setUserid(userid);

        try {
            xtEmployeeMapper.selectXtEmployeeByUserId2(userid).getUcompid();
            throw new ServiceException("你已经是员工了，不要重复注册");
        }catch (NullPointerException e){

        }

        xtEmployee.setStatus("0");

        xtEmployee.setApplyStatus("0");

        xtEmployee.setCreateTime(new Date());

//        if(xtEmployee.getUdepartmentid() == null || xtEmployee.getUdepartmentid() == null){
//            AjaxResult.error("员工的科室ID为空，重新填");
//        }

        return xtEmployeeMapper.insertXtEmployee(xtEmployee);
    }

    /**
     * 修改用户信息
     *
     * @param xtEmployee 用户信息
     * @return 结果
     */
    @Override
    public int updateXtEmployee(XtEmployee xtEmployee)
    {
        return xtEmployeeMapper.updateXtEmployee(xtEmployee);
    }

    /**
     * 批量删除用户信息
     *
     * @param uemployeeids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteXtEmployeeByUemployeeids(String[] uemployeeids)
    {
        return xtEmployeeMapper.deleteXtEmployeeByUemployeeids(uemployeeids);
    }

    /**
     * 删除用户信息信息
     *
     * @param uemployeeid 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteXtEmployeeByUemployeeid(String uemployeeid)
    {
        return xtEmployeeMapper.deleteXtEmployeeByUemployeeid(uemployeeid);
    }

    @Override
    public PageInfo selectList(EmployeeQuery employeeQuery) {

        if (employeeQuery.getPageNum() == null || employeeQuery.getPageSize() == null){
            employeeQuery.setPageSize(10);
            employeeQuery.setPageNum(1);
        }

        List<EmployeeVo> employeeVos = new ArrayList<>();
        XtEmployee employee = new XtEmployee();
        BeanUtils.copyProperties(employeeQuery,employee);
        employee.setApplyStatus("2");
        //这里开始分页
        //开始分页
        PageInfo<XtEmployee> source = PageHelper.startPage(employeeQuery.getPageNum(), employeeQuery.getPageSize()).doSelectPageInfo(()->{
            //查询语句
            xtEmployeeMapper.selectXtEmployeeListByQuery(employee);
        });
        List<XtEmployee> xtEmployees = source.getList().stream().collect(Collectors.toList());
//        List<XtEmployee> xtEmployees = xtEmployeeMapper.selectXtEmployeeListByQuery(employee);

        for (int i = 0; i < xtEmployees.size(); i++) {
            EmployeeVo employeeVo = new EmployeeVo();
            //商家名称
            XtComp xtComp = compMapper.selectXtCompByUcompid(xtEmployees.get(i).getUcompid());
            employeeVo.setCompanyName(xtComp.getUcompname());
            employeeVo.setUcompid(xtComp.getUcompid());

            //部门名称
//            XtDepartment xtDepartment = departmentMapper.selectXtDepartmentByUdepartmentid(xtEmployees.get(i).getUdepartmentid());
//            employeeVo.setUdepartmentid(xtDepartment.getUdepartmentid());
//            employeeVo.setDeptName(xtDepartment.getUdepartmentname());

            //用户身份

            employeeVo.setUserid(xtEmployees.get(i).getUserid());
            employeeVo.setUname(xtEmployees.get(i).getUname());
            employeeVo.setUemployeeid(xtEmployees.get(i).getUemployeeid());

            employeeVo.setUtechp(xtEmployees.get(i).getUtechp());

            employeeVo.setAdministrative(xtEmployees.get(i).getAdministrative());

            XtUser xtUser = userMapper.selectXtUserByUserid(xtEmployees.get(i).getUserid());
            String avatar = xtUser.getAvatar();
//            List<String> strings = StringToURL.toURL(avatar);
            employeeVo.setAvatar(avatar);

            employeeVo.setUsertype(xtEmployees.get(i).getUsertype());
            if("普通用户".equals(xtEmployees.get(i).getUoccupation())){
                employeeVo.setUsertypeName("普通用户");
            } else if ("医生".equals(xtEmployees.get(i).getUoccupation())) {
                employeeVo.setUsertypeName("医生");
            }else if("护士".equals(xtEmployees.get(i).getUoccupation())){
                employeeVo.setUsertypeName("护士");
            } else if ("护理".equals(xtEmployees.get(i).getUoccupation())) {
                employeeVo.setUsertypeName("护理");
            } else if ("紧急呼救".equals(xtEmployees.get(i).getUoccupation())) {
                employeeVo.setUsertypeName("紧急呼救");
            }

            employeeVos.add(employeeVo);


        }

        //需要转换的对象
        PageInfo<EmployeeVo> target = new PageInfo<>();
        //属性赋值
        BeanUtils.copyProperties(source,target);

        target.setList(employeeVos);

        return target;


    }

    @Override
    public AjaxResult selectByid() {
       List list = new ArrayList<XtEmployee>();
       list = xtEmployeeMapper.selectXtEmployeeByusertypeId("2","1");
       list.stream().sorted(Comparator.comparing(XtEmployee::getUpdateTime));
       return AjaxResult.success(list);
    }

    @Override
    public List<XtEmployee> getCompEmployeeList(XtEmployee xtEmployee) {
        String userId = SecurityUtils.getAppLoginUser().getUserId();
        XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(userId);
        xtEmployee.setUcompid(xtEmployeeByUserId.getUcompid());
        return xtEmployeeMapper.selectXtEmployeeListByCompId(xtEmployee);
    }

    @Override
    public List<XtEmployee> getApplyEmployeeList() {
        return xtEmployeeMapper.getApplyEmployeeList();
    }

    @Override
    public XtEmployee getInfoByToken() {
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        if (appLoginUser == null) {
            throw new ServiceException("token异常");
        }
        String userId = appLoginUser.getUserId();
        return xtEmployeeMapper.selectXtEmployeeByUserId(userId);
    }

    @Override
    public List<CompSelect> getAllEmployeeSelectList() {
        XtEmployee query = new XtEmployee();
        query.setApplyStatus("1");
        List<XtEmployee> xtEmployeeList = xtEmployeeMapper.selectXtEmployeeList(query);
        List<CompSelect> result = new ArrayList<>();
        for (XtEmployee xtEmployee : xtEmployeeList) {
            CompSelect compSelect = new CompSelect();
            compSelect.setValue(xtEmployee.getUemployeeid());
            compSelect.setLabel(xtEmployee.getUname());
            result.add(compSelect);
        }
        return result;
    }


    @Override
    public String huoquid(HttpServletRequest request) {
        String userId = appLoginTokenService.getLoginMyUser(request).getUserId();
        return xtEmployeeMapper.selectXtEmployeeByUserId(userId).getUemployeeid();
    }

    @Override
    public int quit(String uemployeeid , String reasonOfQuit ,String farendaibiao ,String zhuguanlingdao) {//,String farendaibiao ,String zhuguanlingdao
        return xtEmployeeMapper.quit(uemployeeid , reasonOfQuit ,farendaibiao ,zhuguanlingdao);
    }

    @Override
    public List<XtEmployee> selectEmployListByComp(String status) {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();

        List<XtComp> xtCompList = compMapper.selectCompByuserid(userId);

        List<XtEmployee> list = new ArrayList<>();
        for (XtComp xt:xtCompList) {
            String ucompid = xt.getUcompid();
            XtEmployee xtEmployee = new XtEmployee();
            if(status == "2") {
                xtEmployee.setStatus(status);
            }
            xtEmployee.setUcompid(ucompid);
            List<XtEmployee> xtEmployees = xtEmployeeMapper.selectXtEmployeeListByCompId(xtEmployee);
            list.addAll(xtEmployees);
        }
        return list;
    }

    @Override
    public List<XtEmployee> selectEmployListByComp2(String name) {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();

        List<XtComp> xtCompList = compMapper.selectCompByuserid(userId);

        List<XtEmployee> list = new ArrayList<>();
        for (XtComp xt:xtCompList) {
            String ucompid = xt.getUcompid();
            XtEmployee xtEmployee = new XtEmployee();
            xtEmployee.setUcompid(ucompid);
            xtEmployee.setUname(name);
            List<XtEmployee> xtEmployees = xtEmployeeMapper.selectXtEmployeeListByCompId(xtEmployee);
            list.addAll(xtEmployees);
        }
        return list;
    }

    @Override
    public List<XtEmployee> selectMyAllEmploy() {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();

        List<XtComp> xtCompList = compMapper.selectCompByUserId(userId);
        List<String> compidList = new ArrayList();
        for(int i = 0; i < xtCompList.size() ; i++){
            String ucompid = xtCompList.get(i).getUcompid();
            compidList.add(ucompid);
        }
        List<XtEmployee> list2 = new ArrayList<>();
        for (String x:
                compidList) {
            XtEmployee xtEmployee = new XtEmployee();
            xtEmployee.setUcompid(x);
            xtEmployeeMapper.selectXtEmployeeListByCompId(xtEmployee);
            for (XtEmployee xtEmployee1:
                    xtEmployeeMapper.selectXtEmployeeListByCompId(xtEmployee)) {
                list2.add(xtEmployee1);
            }
        }
        return list2;
    }

    @Override
    public String selectMyOccupation() {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        return xtEmployeeMapper.selectXtEmployeeByUserId2(userId).getUoccupation();
    }


}
