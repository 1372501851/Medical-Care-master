package com.ruoyi.project.merchant.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.query.MerchantQuery;
import com.ruoyi.project.merchant.mapper.XtCompMapper;
import com.ruoyi.project.merchant.service.IXtCompService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author by hujun
 * @date 2022-11-09
 */

@Api(tags = "商家信息模块")
@RestController
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    @Autowired
    private IXtCompService xtCompService;

    @Autowired
    private AppLoginTokenService appLoginTokenService;

    @Autowired
    private XtCompMapper xtCompMapper;

    /**
     * 查询商家（医院、药店）列表
     * (按照定位来查询)
     */
    @ApiOperation(value = "手机端查询商家列表")
    @GetMapping("/app/list")
    public AjaxResult appList(MerchantQuery merchantQuery) {
        PageInfo pageInfo = xtCompService.selectAppXtCompList(merchantQuery);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 管理端查询商家列表
     */

    @ApiOperation(value = "后台查询商家列表")
    @GetMapping("/web/list")
    public AjaxResult webList(XtComp xtComp, @RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        PageInfo pageInfo = xtCompService.selectXtCompList(xtComp, pageSize, pageNum);
        //这里需要将申请人的信息同时展现出来,一起审核,然后确定审核结果;
        return AjaxResult.success(pageInfo);
    }

    /**
     * 导出商家（医院、药店）列表
     */
    @ApiOperation(value = "导出商家（医院、药店）列表")
    @Log(title = "商家（医院、药店）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtComp xtComp) {
        List<XtComp> list = xtCompService.selectXtCompListAll(xtComp);
        ExcelUtil<XtComp> util = new ExcelUtil<XtComp>(XtComp.class);
        util.exportExcel(response, list, "商家（医院、药店）数据");
    }

    /**
     * 获取商家（医院、药店）详细信息
     */
    @ApiOperation(value = "获取商家（医院、药店）详细信息")
    @GetMapping(value = "/{ucompid}")
    public AjaxResult getInfo(@PathVariable("ucompid") String ucompid) {
        XtComp xtComp = xtCompService.selectXtCompByUcompid(ucompid);
        return AjaxResult.success("成功", xtCompService.selectXtCompByUcompid(ucompid));
    }

    /**
     * 新增商家（医院、药店）,商家注册
     */
    @ApiOperation(value = "新增商家（医院、药店）,商家注册")
    @Log(title = "商家（医院、药店）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtComp xtComp, HttpServletRequest request) {
        return toAjax(xtCompService.insertXtComp(xtComp, request));
    }

    /**
     * 修改商家（医院、药店）
     */
    @ApiOperation(value = "修改商家（医院、药店）")
    @Log(title = "商家（医院、药店）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtComp xtComp) {
        return toAjax(xtCompService.updateXtComp(xtComp));
    }

    /**
     * 删除商家（医院、药店）
     */
    @ApiOperation(value = "删除商家（医院、药店）")
    @Log(title = "商家（医院、药店）", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ucompids}")
    public AjaxResult remove(@PathVariable String[] ucompids) {
        return toAjax(xtCompService.deleteXtCompByUcompids(ucompids));
    }

    /**
     * 获取商家下拉列表;
     */
    @ApiOperation(value = "获取商家下拉列表")
    @GetMapping("/selectList")
    public AjaxResult getSelectList() {
        return AjaxResult.success(xtCompService.getSelectList());
    }

    @ApiOperation(value = "获取商家部门联动下拉列表")
    @GetMapping("/department/selectList")
    public AjaxResult getCompAndDeptSelectList() {
        return AjaxResult.success(xtCompService.getCompAndDeptSelectList());
    }

    @ApiOperation(value = "小刘专用,获取商家的ID和名字")
    @GetMapping("/getCompIdAndCompNamet")
    public AjaxResult getCompIdAndCompNamet() {

        return AjaxResult.success(xtCompService.getCompIdAndCompNamet());
    }

    @ApiOperation(value = "小刘专用,获取所有商家的详细信息")
    @GetMapping("/selectAllCompInfo")
    public AjaxResult selectAllCompInfo() {

        return AjaxResult.success(xtCompService.selectAllCompInfo());
    }

//    @ApiOperation(value = "(可修改数据版)小刘专用,审核商家注册是否通过 applyStatus：0：审核中  1：审核通过 2：审核不通过")
//    @GetMapping("/reviewCompRegistration")
//    public AjaxResult reviewCompRegistration(XtComp xtComp, String applyStatus) {
//        return AjaxResult.success(reviewCompRegistration(xtComp, applyStatus));
//    }

    @ApiOperation(value = "(不可修改数据版)小刘专用,审核商家注册是否通过 applyStatus：0：审核中  1：审核通过 2：审核不通过")
    @GetMapping("/reviewCompRegistration")
    public AjaxResult reviewCompRegistration(String ucompid, String applyStatus) {
        return AjaxResult.success(xtCompService.reviewCompRegistration(ucompid, applyStatus));
    }

    @ApiOperation(value = "小刘专用,模糊查询商家名字")
    @GetMapping("/fuzzyQueryCompName")
    public AjaxResult fuzzyQueryCompName(String ucompname) {
        return AjaxResult.success(xtCompMapper.fuzzyQueryCompName(ucompname));
    }

    @ApiOperation(value = "小刘专用,token判断商家注册状态")
    @GetMapping("/merchantRegistrationStatus")
    public AjaxResult merchantRegistrationStatus(HttpServletRequest request) {
        //判断登录账号是不是商家
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        List<XtComp> xtCompList = xtCompMapper.selectCompByuserid(userId);
        if(xtCompList.isEmpty()){
            return AjaxResult.success("");
        }
        return AjaxResult.success(xtCompService.merchantRegistrationStatus(request));
    }

    @ApiOperation(value = "小刘专用，根据商家id查询商家名字")
    @GetMapping("/selectNameById")
    public AjaxResult selectNameById(String id) {
        return AjaxResult.success(xtCompMapper.selectNameById(id));
    }

    @ApiOperation(value = "商家置顶")
    @PostMapping("/top")
    public AjaxResult top(@PathVariable List<XtComp> element, XtComp ob) {
        Set<XtComp> xtComps = new HashSet<>();
        xtComps.add(ob);
        for (XtComp x : element) {
            xtComps.add(x);
        }
        return AjaxResult.success(xtComps);
    }

    /**
     * 查询商家（医院、药店）名字
     */
    @ApiOperation(value = "查询商家（医院、药店）名字")
    @Log(title = "查询商家（医院、药店）名字", businessType = BusinessType.DELETE)
    @PostMapping("/sele/{ucompids}")
    public AjaxResult selectCompnmae(@PathVariable String[] ucompids) {
        List<String> ob = new ArrayList<>();
        for (String x :
                ucompids) {
            String s = xtCompMapper.selectCompnmae(x);
            ob.add(s);
        }
        return AjaxResult.success(ob);
    }

    @ApiOperation(value = "小刘专用,查看我开了哪些店")
    @GetMapping("/selectMyComp")
    public AjaxResult selectMyComp() {
        return AjaxResult.success(xtCompService.selectMyComp());
    }

}
