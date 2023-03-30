package com.ruoyi.project.workrecord.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.date.Timeutils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.user.domain.XtOperComp;
import com.ruoyi.project.workrecord.domain.XtWorkRecord;
import com.ruoyi.project.workrecord.mapper. XtWorkRecardMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author by hujun
 * @date 2023-02-17
 */
@Api(tags = "工作记录模块")
@RestController
@RequestMapping("/WorkRecard")
public class XtWorkRecardController {

    @Autowired
    private XtWorkRecardMapper xtWorkRecardMapper;

    @ApiOperation(value = "新增工作记录")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtWorkRecord xtWorkRecord)
    {
        xtWorkRecord.setId(IdUtil.getSnowflakeNextIdStr());
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        xtWorkRecord.setUserid(userId);
        Integer add = xtWorkRecardMapper.add(xtWorkRecord);
        if(add == 1){
            return  AjaxResult.success(xtWorkRecord);
        }else {
            return  AjaxResult.success("失败");
        }

    }

    @ApiOperation(value = "小刘专用，查看工作记录")
    @GetMapping("/seleXtWorkRecordByUserid/{id}")
    public AjaxResult seleXtWorkRecordByUserid(@PathVariable String id)
    {
        return AjaxResult.success("成功", xtWorkRecardMapper.seleXtWorkRecordByUserid(id));
    }

    @ApiOperation(value = "小刘专用，删除工作记录")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delXtWorkRecordByUserid/{ids}")
    public AjaxResult delXtWorkRecordByUserid(@PathVariable String[] ids)
    {
        for (String x:
             ids) {
            xtWorkRecardMapper.delXtWorkRecordByUserid(x);
        }
        return  AjaxResult.success(1);
    }

    @ApiOperation(value = "小刘专用，查看某人哪天的工作记录")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @GetMapping("/seleXtWorkRecordByUseridAndDate")
    public AjaxResult seleXtWorkRecordByUseridAndDate(String id , String timet)
    {
        Date time = Timeutils.transferString2Date(timet);
        return  AjaxResult.success(xtWorkRecardMapper.seleXtWorkRecordByUseridAndDate(id,time));
    }
}
