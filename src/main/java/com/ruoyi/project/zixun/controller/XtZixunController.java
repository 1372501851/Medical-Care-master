package com.ruoyi.project.zixun.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.OO;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.workrecord.domain.XtWorkRecord;
import com.ruoyi.project.workrecord.mapper.XtWorkRecardMapper;
import com.ruoyi.project.zixun.domain.XtZixun;
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
@Api(tags = "资讯接口")
@RestController
@RequestMapping("/zixun")
public class XtZixunController {
    @Autowired
    private XtZixunMapper xtZixunMapper;

    @ApiOperation(value = "增加资讯")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtZixun xtZixun)
    {
        xtZixun.setUzixunId(IdUtil.getSnowflakeNextIdStr());
        Integer integer = xtZixunMapper.addZiXun(xtZixun);
        return  AjaxResult.success("增加资讯成功");
    }

    @ApiOperation(value = "删除资讯")
    @DeleteMapping("/del")
    public AjaxResult del(@RequestBody String[] uzixunIds)
    {
        Integer integer = xtZixunMapper.delZixun(uzixunIds);
        return  AjaxResult.success("删除资讯成功");
    }

    @ApiOperation(value = "修改资讯")
    @PutMapping("/upd")
    public AjaxResult upd(@RequestBody XtZixun uzixun)
    {
        xtZixunMapper.updZixun(uzixun);
        return  AjaxResult.success("修改资讯成功");
    }

    @ApiOperation(value = "查询资讯")
    @GetMapping("/sele")
    public AjaxResult sele( XtZixun uzixun , int pageSize, int pageNum)
    {
        if(pageSize == 0 || pageNum == 0){
            pageSize = 10;
            pageNum = 1;
        }
        ArrayList<XtZixun> xtZixuns =xtZixunMapper.seleXtZixun(uzixun);
        List<?> objects = OO.subList(xtZixuns, pageSize, pageNum);
        return  AjaxResult.success(objects);
    }



}
