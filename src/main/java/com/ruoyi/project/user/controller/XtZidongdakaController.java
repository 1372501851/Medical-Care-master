package com.ruoyi.project.user.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.user.domain.XtOperComp;
import com.ruoyi.project.user.domain.XtZidongdaka;
import com.ruoyi.project.user.mapper.XtZidongdakaMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author by hujun
 * @date 2023-03-10
 */
@RestController
@RequestMapping("/Zidongdaka")
@Api(tags = "自动打卡")
public class XtZidongdakaController {

    @Autowired
    private XtZidongdakaMapper xtZidongdakaMapper;

    @ApiOperation(value = "查时间")
    @GetMapping("/sele")
    public AjaxResult seleXtZidongdaka()
    {
        XtZidongdaka xtZidongdaka = xtZidongdakaMapper.seleXtZidongdaka(SecurityUtils.getAppLoginUser().getUser().getUserid());
        if(xtZidongdaka == null){
            xtZidongdaka = new XtZidongdaka();
            xtZidongdaka.setId(IdUtil.getSnowflakeNextIdStr());
            xtZidongdaka.setUserid( SecurityUtils.getAppLoginUser().getUser().getUserid());
            xtZidongdakaMapper.addXtZidongdaka(xtZidongdaka);
        }
        return AjaxResult.success(xtZidongdaka);
    }

    @ApiOperation(value = "改时间")
    @PostMapping("/update")
    public AjaxResult updateXtZidongdaka(@RequestBody XtZidongdaka xtZidongdaka)
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        xtZidongdaka.setUserid(userid);
        Integer xtZidongdaka1 = xtZidongdakaMapper.updateXtZidongdaka(xtZidongdaka);
        return AjaxResult.success(xtZidongdaka1);
    }


    @ApiOperation(value = "查用户群聊状态")
    @GetMapping("/qunliaozhuangtai")
    public AjaxResult qunliaozhuangtai()
    {
        Map<String , String> xtZidongdaka = xtZidongdakaMapper.qunliaozhuangtai(SecurityUtils.getAppLoginUser().getUser().getUserid());
        return AjaxResult.success(xtZidongdaka);
    }

    @ApiOperation(value = "改用户群聊状态")
    @GetMapping("/gaiqunliaozhuangtai")
    public AjaxResult gaiqunliaozhuangtai(String qunliaozhuangtaiys,String qlzthg,String qlzths,String qlzthl,String qlztcnmd,String qlztnmb,String qlztsb)
    {
        Integer gaiqunliaozhuangtai = xtZidongdakaMapper.gaiqunliaozhuangtai(SecurityUtils.getAppLoginUser().getUser().getUserid(),qunliaozhuangtaiys,qlzthg,qlzths,qlzthl,qlztcnmd,qlztnmb,qlztsb);
          return AjaxResult.success(gaiqunliaozhuangtai);
    }




}
