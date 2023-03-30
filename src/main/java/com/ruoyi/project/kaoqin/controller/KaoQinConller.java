package com.ruoyi.project.kaoqin.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.kaoqin.domain.KaoQin;
import com.ruoyi.project.kaoqin.mapper.KaoQinMapper;
import com.ruoyi.project.kaoqin.service.KaoQinService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.domain.dto.merchant.MerchantsApplyDto;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-14
 */
@Api(tags = "考勤打卡")
@RestController
@RequestMapping("/kaoqin")
public class KaoQinConller {

    @Autowired
    private KaoQinService kaoQinService;

    @Autowired
    private KaoQinMapper kaoQinMapper;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;



    @ApiOperation(value = "增加考勤")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody KaoQin kaoQin){

        return AjaxResult.success(kaoQinService.daKa(kaoQin));
    }

    @ApiOperation(value = "查看员工考勤")
    @GetMapping("/chaKan/{employId}")
    public AjaxResult chaKan(@PathVariable String employId){
        return AjaxResult.success(kaoQinService.chaKan(employId));
    }

    @ApiOperation(value = "获取打卡员工")
    @GetMapping("/seledakaEmploy")
    public AjaxResult seledakaEmploy(){
        return AjaxResult.success(kaoQinService.seledakaEmploy());
    }

    @ApiOperation(value = "小刘专用，渲染上班还是下班")
    @GetMapping("/onAndOffDuty")
    public AjaxResult onAndOffDuty(){
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUserId2(userId);
        List<String> data = kaoQinMapper.onAndOffDuty(xtEmployee.getUemployeeid());
        if(data.isEmpty()) {return AjaxResult.success("上班");}

        if("上班".equals(data.get(data.size()-1))){
            return AjaxResult.success("下班");
        }else {
            return AjaxResult.success("上班");
        }

    }

//    @ApiOperation(value = "模糊查询打卡员工")
//    @GetMapping("/lickseledakaEmploy/{name}")
//    public AjaxResult lickseledakaEmploy(@PathVariable String name){
////        return AjaxResult.success(KaoQinMapper.lickseledakaEmploy(name));
//    }


}
