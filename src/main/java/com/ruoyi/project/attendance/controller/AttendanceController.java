package com.ruoyi.project.attendance.controller;




import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.attendance.domain.XtDailyAffairs;
import com.ruoyi.project.attendance.mapper.XtDailyAffairsMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.web.domain.AjaxResult;

@Api(tags = "日常事务检查模块")
@RestController
@RequestMapping("/Attendance")
public class AttendanceController
{
    @Autowired
    private XtDailyAffairsMapper xtDailyAffairsMapper;

    @ApiOperation(value = "查询检查列表")
    @GetMapping("/list")
    public AjaxResult list(){
        return AjaxResult.success(xtDailyAffairsMapper.list());
    }


    @ApiOperation(value = "增加检查列表")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtDailyAffairs xtDailyAffairs)
    {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        xtDailyAffairs.setUserid(userId);
        xtDailyAffairs.setId(IdUtil.getSnowflakeNextIdStr());
        return AjaxResult.success(xtDailyAffairsMapper.add(xtDailyAffairs));
    }
}

