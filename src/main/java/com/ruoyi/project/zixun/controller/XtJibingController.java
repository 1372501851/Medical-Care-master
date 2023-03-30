package com.ruoyi.project.zixun.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.OO;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.workrecord.domain.XtWorkRecord;
import com.ruoyi.project.workrecord.mapper.XtWorkRecardMapper;
import com.ruoyi.project.zixun.domain.XtJibing;
import com.ruoyi.project.zixun.domain.XtZixun;
import com.ruoyi.project.zixun.mapper.XtJibingMapper;
import com.ruoyi.project.zixun.mapper.XtZixunMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-03-24
 */
@Api(tags = "疾病库接口")
@RestController
@RequestMapping("/jibing")
public class XtJibingController {
    @Autowired
    private XtJibingMapper xtJibingMapper;

    @ApiOperation(value = "增加疾病记录")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtJibing xtJibing)
    {
        xtJibing.setUjibingId(IdUtil.getSnowflakeNextIdStr());
        Integer integer = xtJibingMapper.addXtjibing(xtJibing);
        return  AjaxResult.success("增加疾病记录成功");
    }

    @ApiOperation(value = "删除疾病记录")
    @DeleteMapping("/del")
    public AjaxResult del(@RequestBody String[] ujibingIds)
    {
        Integer integer = xtJibingMapper.delXtjibing(ujibingIds);
        return  AjaxResult.success("删除疾病记录成功");
    }

    @ApiOperation(value = "修改疾病记录")
    @PutMapping("/upd")
    public AjaxResult upd(@RequestBody XtJibing xtJibing)
    {
        xtJibingMapper.updXtjibing(xtJibing);
        return  AjaxResult.success("修改疾病记录成功");
    }

    @ApiOperation(value = "查询疾病记录")
    @GetMapping("/sele")
    public AjaxResult sele( XtJibing xtJibing , int pageSize, int pageNum)
    {
        if(pageSize == 0 || pageNum == 0  ){
            pageSize = 10;
            pageNum = 1;
        }
        ArrayList<XtJibing> xtJibings = xtJibingMapper.seleXtjibing(xtJibing);
        List<?> objects = OO.subList(xtJibings, pageSize, pageNum);
        return  AjaxResult.success(objects);
    }



}
