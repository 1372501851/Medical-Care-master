package com.ruoyi.project.merchant.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.Search;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.kaoqin.service.KaoQinService;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.domain.XtKaohe;
import com.ruoyi.project.merchant.domain.XtworkSummary;
import com.ruoyi.project.merchant.domain.query.EmployeeQuery;
import com.ruoyi.project.merchant.domain.vo.EmployeeVo;
import com.ruoyi.project.merchant.domain.vo.SelectKaoHeVo;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.merchant.mapper.XtKaoHeMapper;
import com.ruoyi.project.merchant.mapper.XtworkSummaryMapper;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.user.domain.XtUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author by hujun
 * @date 2022-11-09
 */
@Api(tags = "员工信息模块")
@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
    @Autowired
    private IXtEmployeeService xtEmployeeService;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private XtworkSummaryMapper xtworkSummaryMapper;

    @Autowired
    private KaoQinService kaoQinService;

    @Autowired
    private XtKaoHeMapper xtKaoHeMapper;

    /**员工列表*/
    @ApiOperation(value = "查询员工列表")
    @GetMapping("/app/list")
    public AjaxResult empList(EmployeeQuery employeeQuery){
        PageInfo employeeVos = xtEmployeeService.selectList(employeeQuery);
        return AjaxResult.success(employeeVos);
    }





    /**
     * 查询用户信息列表
     */

    @ApiOperation(value = "查询员工信息列表")
    @GetMapping("/list")
    public TableDataInfo list( XtEmployee xtEmployee)
    {
        startPage();
        List<XtEmployee> list = xtEmployeeService.selectXtEmployeeList(xtEmployee);
        return getDataTable(list);
    }

    /**
     * 查询公司部门列表
     */
    @ApiOperation(value = "查询公司部门列表")
    @GetMapping("/bumenlist")
    public AjaxResult bumenlist( String compid)
    {
        startPage();
        XtEmployee xtEmployee = new XtEmployee();
        HashSet<String> strings = new HashSet<>();
        xtEmployee.setUcompid(compid);
        List<XtEmployee> list = xtEmployeeService.selectXtEmployeeList(xtEmployee);
        for (XtEmployee s:
             list) {
            strings.add(s.getUdepartmentid());
        }
        return AjaxResult.success(strings);
    }


    /**
     * 导出用户信息列表
     */
    @ApiOperation(value = "导出用户信息列表")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtEmployee xtEmployee)
    {
        List<XtEmployee> list = xtEmployeeService.selectXtEmployeeList(xtEmployee);
        ExcelUtil<XtEmployee> util = new ExcelUtil<XtEmployee>(XtEmployee.class);
        util.exportExcel(response, list, "用户信息数据");
    }


    /**
     * 获取用户信息详细信息
     */
    @ApiOperation(value = "获取用户信息详细信息")
    @GetMapping(value = "/info/{uemployeeid}")
    public AjaxResult getInfo(@PathVariable("uemployeeid") String uemployeeid)
    {
        return AjaxResult.success(xtEmployeeService.selectXtEmployeeByUemployeeid(uemployeeid));
    }

    @ApiOperation(value = "通过token获取员工信息")
    @GetMapping(value = "/info/token")
    public AjaxResult getInfoByToken(){
        return AjaxResult.success(xtEmployeeService.getInfoByToken());
    }

    /**
     * 获取用户信息详细信息
     */
    @ApiOperation(value = "获取用户ID详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfoByUserId(String userId)
    {
        XtEmployee xtEmployee = xtEmployeeService.queryUserInfo(userId);
        return AjaxResult.success(xtEmployee);
    }

    /**
     * 新增用户信息
     */
    @ApiOperation(value = "新增员工信息")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtEmployee xtEmployee , HttpServletRequest request)
    {
        return toAjax(xtEmployeeService.insertXtEmployee(xtEmployee , request));
    }

    /**
     * 修改用户信息
     */
    @ApiOperation(value = "修改用户信息")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtEmployee xtEmployee)
    {
        return toAjax(xtEmployeeService.updateXtEmployee(xtEmployee));
    }

    /**
     * 删除用户信息
     */
    @ApiOperation(value = "删除用户信息")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{uemployeeids}")
    public AjaxResult remove(@PathVariable String[] uemployeeids)
    {
        return toAjax(xtEmployeeService.deleteXtEmployeeByUemployeeids(uemployeeids));
    }


    /**
     *   护士(按日期排版)
     */
    @ApiOperation(value = "护士(按日期排版)")
    @Log(title = "护士(按日期排版)", businessType = BusinessType.DELETE)
    @GetMapping("/nurse")
    public AjaxResult listnurse() {
     return    xtEmployeeService.selectByid();
    }

    @ApiOperation(value = "后台：查询商家用户列表")
    @GetMapping("/comp/list")
    public TableDataInfo getCompEmployeeList(XtEmployee xtEmployee){
        startPage();
        List<XtEmployee> employeeList = xtEmployeeService.getCompEmployeeList(xtEmployee);
        return getDataTable(employeeList);
    }

    @ApiOperation(value = "后台：查询待审核员工列表")
    @GetMapping("/apply/list")
    public TableDataInfo getApplyEmployeeList() {
        startPage();
        List<XtEmployee> list = xtEmployeeService.getApplyEmployeeList();
        return getDataTable(list);
    }

    /**
     *
     * 获取员工下拉列表;
     */
    @ApiOperation(value = "获取所有员工下拉列表")
    @GetMapping("/selectList")
    public AjaxResult getSelectList() {
        return AjaxResult.success(xtEmployeeService.getAllEmployeeSelectList());
    }


    /**
     * 获取员工id
     */
    @ApiOperation(value = "获取员工id")
    @GetMapping("/huoquid")
    public AjaxResult huoqu(HttpServletRequest request)
    {
        return AjaxResult.success("成功",xtEmployeeService.huoquid(request));
    }

    /**
     * 员工离职申请
     */
    @ApiOperation(value = "员工离职申请")
    @GetMapping("/quit")
    public AjaxResult quit(String uemployeeid , String reasonOfQuit ,String farendaibiao, String zhuguanlingdao)
    {
        return AjaxResult.success("成功",xtEmployeeService.quit(uemployeeid , reasonOfQuit ,farendaibiao ,zhuguanlingdao));
    }

    @ApiOperation(value = "在审核注册那里搜索员工")
    @GetMapping("/selectEmployListByComp2")
    public AjaxResult  selectEmployListByComp2(String name)
    {
        return AjaxResult.success("成功",xtEmployeeService.selectEmployListByComp2(name));
    }

    @ApiOperation(value = "在员工管理那里搜部门")
    @GetMapping("/selectBuMen")
    public AjaxResult  selectBuMen(String name , String[] list)
    {
        List<String> strings = Arrays.asList(list);
        return AjaxResult.success(Search.search(name,strings));
    }
    /**
     * 员根据商家id查询员工列表
     */
    @ApiOperation(value = "员根据商家id查询员工列表")
    @GetMapping("/selectEmployListByComp")
    public AjaxResult selectEmployListByComp(String status)
    {
        return AjaxResult.success("成功",xtEmployeeService.selectEmployListByComp(status));
    }

    @ApiOperation(value = "小刘专用，查看有哪些职务")
    @GetMapping("/selectAllpost")
    public AjaxResult selectAllpost()
    {
        return AjaxResult.success("成功", xtEmployeeMapper.selectAllpost());
    }

    @ApiOperation(value = "小刘专用，查看与职务对应的有哪些员工")
    @GetMapping("/selectAllEmployByPost")
    public AjaxResult selectAllEmployByPost(String post)
    {
        return AjaxResult.success("成功", xtEmployeeMapper.selectAllEmployByPost(post));
    }

    @ApiOperation(value = "小刘专用，模糊查找员工")
    @GetMapping("/selectLikeEmployByName")
    public AjaxResult selectLikeEmployByName(String post , String name)
    {
        return AjaxResult.success("成功", xtEmployeeMapper.selectLikeEmployByName("","%"+name+"%"));
    }

    @ApiOperation(value = "小刘专用，增加工作总结")
    @PostMapping("/workSummary")
    public AjaxResult workSummary(@RequestBody XtworkSummary xtworkSummary)
    {
        xtworkSummary.setId(IdUtil.getSnowflakeNextIdStr());
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        xtworkSummary.setUserid(userId);
        xtworkSummary.setCreatDate(new Date());
        return AjaxResult.success("成功", xtworkSummaryMapper.add(xtworkSummary));
    }

    @ApiOperation(value = "小刘专用，查看工作总结，通过用户id")
    @GetMapping("/seleXtworkSummaryById/{id}")
    public AjaxResult seleXtworkSummaryById(@PathVariable String id)
    {
        try {
            List<XtworkSummary> data = xtworkSummaryMapper.seleXtworkSummaryById(id);
            return AjaxResult.success("成功", data.get(data.size()-1));
        }catch (IndexOutOfBoundsException e){
            return AjaxResult.error("用户暂时没有进行工作总结");
        }

    }

    @ApiOperation(value = "小刘专用，获取和登陆者同一个公司的员工")
    @GetMapping("/selectSameCompEmployer")
    public AjaxResult selectSameCompEmployer(){
        return AjaxResult.success(kaoQinService.seledakaEmploy());
    }


    @ApiOperation(value = "小刘专用，增加员工考核")
    @PostMapping("/addkaohe")
    public AjaxResult addkaohe(@RequestBody XtKaohe xtKaohe)
    {
        xtKaohe.setId(IdUtil.getSnowflakeNextIdStr());
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        xtKaohe.setUesrid(userId);
        xtKaohe.setUtime(new Date());
        return AjaxResult.success("成功", xtKaoHeMapper.addKaoHe(xtKaohe));
    }

    @ApiOperation(value = "小刘专用，查看员工考核")
    @PostMapping("/seleKaoHe")
    public AjaxResult seleKaoHe(@RequestBody SelectKaoHeVo selectKaoHeVo)
    {
        List<XtKaohe> data = xtKaoHeMapper.seleKaoHe(selectKaoHeVo);
        return AjaxResult.success("成功", data);
    }

    @ApiOperation(value = "小刘专用，修改员工考核")
    @PostMapping("/updateKaoHe")
    public AjaxResult updateKaoHe(@RequestBody XtKaohe xtKaohe)
    {
        return AjaxResult.success("成功",  xtKaoHeMapper.updateKaoHe(xtKaohe));
    }




//    @ApiOperation(value = "小刘专用，查看员工考核")
//    @GetMapping("/selectkaohe/{employid}")
//    public AjaxResult selectkaohe(@PathVariable String employid)
//    {
//        return AjaxResult.success("成功", xtworkSummaryMapper.selectkaohe(employid));
//    }

    @ApiOperation(value = "小刘专用，获取我旗下所有公司的所有员工")
    @GetMapping("/selectMyAllEmploy")
    public AjaxResult selectMyAllEmploy(){
        return AjaxResult.success(xtEmployeeService.selectMyAllEmploy());
    }

    @ApiOperation(value = "小刘专用，查看登陆者的职业")
    @GetMapping("/selectMyOccupation")
    public AjaxResult selectMyOccupation(){
        return AjaxResult.success(xtEmployeeService.selectMyOccupation());
    }
}
