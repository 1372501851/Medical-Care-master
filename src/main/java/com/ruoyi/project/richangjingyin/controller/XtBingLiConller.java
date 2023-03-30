package com.ruoyi.project.richangjingyin.controller;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.richangjingyin.domain.XtBingLi;
import com.ruoyi.project.richangjingyin.domain.XtDangJu1;
import com.ruoyi.project.richangjingyin.domain.XtHuajiajiesuandan;
import com.ruoyi.project.richangjingyin.domain.XtRp;
import com.ruoyi.project.richangjingyin.domain.vo.BingLiVo;
import com.ruoyi.project.richangjingyin.domain.vo.XtHuajiajiesuandanVo;
import com.ruoyi.project.richangjingyin.mapper.XtBingLiMapper;
import com.ruoyi.project.richangjingyin.mapper.XtDangJuMapper;
import com.ruoyi.project.richangjingyin.mapper.XtHuajiajiesuandanMapper;
import com.ruoyi.project.richangjingyin.mapper.XtRpMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author by hujun
 * @date 2023-02-23
 */
@RestController
@RequestMapping("/richangjingying")
@Api(tags = "日常经营模块模块")
public class XtBingLiConller {

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private XtBingLiMapper xtBingLiMapper;

    @Autowired
    private XtDangJuMapper xtDangJuMapper;

    @Autowired
    private XtRpMapper xtRpMapper;

    @Autowired
    private XtHuajiajiesuandanMapper xtHuajiajiesuandanMapper;

    @ApiOperation(value = "增加病历或处方或化验单检查申请单")
    @PostMapping("/addBingLi")
    public AjaxResult addBingLi(@RequestBody XtBingLi xtBingLi)
    {
        String snowflakeNextIdStr = IdUtil.getSnowflakeNextIdStr();
        xtBingLi.setId(snowflakeNextIdStr);
//        xtBingLi.
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtEmployee xtEmployee = xtEmployeeMapper.selectXtEmployeeByUserId2(userId);
        String uemployeeid = xtEmployee.getUemployeeid();

        xtBingLi.setYishenguserid(uemployeeid);

        xtBingLi.setCreatetime(new Date());

        return AjaxResult.success("成功",snowflakeNextIdStr);
    }

    @ApiOperation(value = "查询患者病历或处方")
    @GetMapping("/selectBingLi")
    public AjaxResult  selectBingLi(String userId , String utype , Boolean isLastst)
    {
        if(isLastst){
            List<XtBingLi> sele = xtBingLiMapper.sele(userId, utype);
            return AjaxResult.success(sele.get(sele.size()-1));
        }
        return AjaxResult.success(xtBingLiMapper.sele(userId , utype));
    }

    @ApiOperation(value = "查询全部病历处方")
    @GetMapping("/selectAllBingLiOrChuFan")
    public AjaxResult  selectAllBingLiOrChuFan(String type)
    {
        return AjaxResult.success(xtBingLiMapper.selectAllBingLiOrChuFan(type));
    }

    @ApiOperation(value = "模糊查询全部病历或处方")
    @GetMapping("/selectLikeAllBingLiOrChuFan")
    public AjaxResult  selectLikeAllBingLiOrChuFan(String type , String name)
    {
        List<XtBingLi> data = xtBingLiMapper.selectAllBingLiOrChuFan(type);
        Set<XtBingLi> xtBingLis = new HashSet<>();

        Calendar cal = Calendar.getInstance();

        for (XtBingLi x:
                data) {
            cal.setTime(x.getCreatetime());
            if(name.equals(x.getHospital())){
                xtBingLis.add(x);
            }
            if(name.equals(x.getYishengname())){
                xtBingLis.add(x);
            }
            if(name.equals(x.getKeshi())){
                xtBingLis.add(x);
            }
            if (name.equals(x.getHuanzhename())){
                xtBingLis.add(x);
            }
            if(name.equals(""+cal.get(Calendar.YEAR))){
                xtBingLis.add(x);
            }
            if(name.equals(""+cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1)+"")){
                xtBingLis.add(x);
            }
            if(name.equals(""+cal.get(Calendar.YEAR) + "-" + ""+(cal.get(Calendar.MONTH)+1) + "-" + ""+cal.get(Calendar.DAY_OF_MONTH) )){
                xtBingLis.add(x);
            }

            System.out.println(("" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + ""));System.out.println(("" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + ""));System.out.println(("" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + ""));System.out.println(("" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + ""));System.out.println(("" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + ""));System.out.println(("" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + ""));System.out.println(("" + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + ""));
        }

        return AjaxResult.success(xtBingLis);
    }

    @ApiOperation(value = "查询患者病历详情")
    @GetMapping("/selectBingLiById")
    public AjaxResult  selectBingLiById(String Id )
    {
//        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();

        return AjaxResult.success(xtBingLiMapper.selectBingLiById(Id ));
    }

    @ApiOperation(value = "查询医生处理的所有病历")
    @GetMapping("/selectBingLiByYiShengUserId")
    public AjaxResult  selectBingLiByYiShengUserId()
    {
        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        return AjaxResult.success(xtBingLiMapper. selectBingLiByYiShengUserId(userId));
    }



    @ApiOperation(value = "增加单据")
    @PostMapping("/addDangJu")
    public AjaxResult addDangJu(@RequestBody XtDangJu1 xtDangJu)
    {
        String snowflakeNextIdStr = IdUtil.getSnowflakeNextIdStr();
        xtDangJu.setId(snowflakeNextIdStr);
        Integer add = xtDangJuMapper.add(xtDangJu);
        return AjaxResult.success(snowflakeNextIdStr);
    }

    @ApiOperation(value = "查询单据")
    @GetMapping("/seleDangJu/{type}")
    public AjaxResult  seleDangJu(@PathVariable String type)
    {
//        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        return AjaxResult.success(xtDangJuMapper.seleXtDangJu(type));
    }

    @ApiOperation(value = "查询单据通过id")
    @GetMapping("/seleDangJuById/{id}")
    public AjaxResult  seleDangJuById(@PathVariable String id)
    {
//        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        List<XtDangJu1> data = xtDangJuMapper.seleXtDangJuById(id);
        return AjaxResult.success(data.get(0));
    }

    @ApiOperation(value = "查询病历或处方分类")
    @PostMapping("/seleblfl")
    public AjaxResult seleblfl(String utype)
    {
        List<BingLiVo> seleblfl = xtBingLiMapper.seleblfl(utype);
        Collections.reverse(seleblfl);
        HashSet<BingLiVo> set=new HashSet<>(seleblfl);
        return AjaxResult.success(set);
    }

    @ApiOperation(value = "通过医院，医生，科室查询病历或处方")
    @PostMapping("/seleblBy")
    public AjaxResult seleblBy( String utype,String hospital,String keshi ,String yishengname)
    {
        List<XtBingLi> seleblfl = xtBingLiMapper.seleblBy(utype,hospital ,keshi ,yishengname);
        return AjaxResult.success(seleblfl);
    }

    @ApiOperation(value = "增加Rp")
    @PostMapping("/addRp")
    public AjaxResult addRp(@RequestBody List<XtRp>  xtRps)
    {
        List<String> x = new ArrayList<>();
        for (XtRp xtRp:
                xtRps) {
            String snowflakeNextIdStr = IdUtil.getSnowflakeNextIdStr();
            xtRp.setId(snowflakeNextIdStr);
            try {
                xtRpMapper.addXtRp(xtRp);
            }catch (Exception c){
                throw new ServiceException("增加Rp失败");
            }
            x.add(snowflakeNextIdStr);
        }
        return AjaxResult.success("成功",x);
    }

    @ApiOperation(value = "查询Rp")
    @GetMapping("/seleRp/{id}")
    public AjaxResult  seleRp(@PathVariable String id)
    {
//        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        return AjaxResult.success(xtRpMapper.sele(id));
    }

    @ApiOperation(value = "增加化价结算单")
    @PostMapping("/addXtHuajiajiesuandan")
    public AjaxResult addXtHuajiajiesuandan(@RequestBody XtHuajiajiesuandanVo xtHuajiajiesuandanVo)
    {
        XtHuajiajiesuandan xtHuajiajiesuandan = new XtHuajiajiesuandan();
        BeanUtils.copyProperties(xtHuajiajiesuandanVo,xtHuajiajiesuandan);

        List<String> yaopinid = xtHuajiajiesuandanVo.getYaopinid();
        List<String> yaopinnum = xtHuajiajiesuandanVo.getYaopinnum();
        String join1 = String.join(",", yaopinid);
        String join2 = String.join(",", yaopinnum);

        xtHuajiajiesuandan.setYaopinid(join1);
        xtHuajiajiesuandan.setYaopinnum(join2);
        xtHuajiajiesuandan.setId(IdUtil.getSnowflakeNextIdStr());

        return AjaxResult.success(xtHuajiajiesuandanMapper.addXtHuajiajiesuandan(xtHuajiajiesuandan));
    }

    @GetMapping("/seleXtHuajiajiesuandan/{id}")
    @ApiOperation(value = "查询化价结算单")
    public AjaxResult  seleXtHuajiajiesuandan(@PathVariable String id)
    {
//        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtHuajiajiesuandan data = xtHuajiajiesuandanMapper.seleXtHuajiajiesuandan(id);
        XtHuajiajiesuandanVo xtHuajiajiesuandanVo = new XtHuajiajiesuandanVo();
        BeanUtils.copyProperties(data , xtHuajiajiesuandanVo);

        String yaopinid = data.getYaopinid();
        String yaopinnum = data.getYaopinnum();

        String[] split1 = yaopinid.split(",");
        String[] split2 = yaopinnum.split(",");

        xtHuajiajiesuandanVo.setYaopinid(Arrays.asList(split1));
        xtHuajiajiesuandanVo.setYaopinnum(Arrays.asList(split2));

        return AjaxResult.success(xtHuajiajiesuandanVo);
    }





//    List<String> letters= Arrays.asList("A", "B", "C", "D");
//    String lettersCommaSeparated = String.join(",", letters);
//    System.out.println(lettersCommaSeparated );

//    str.split("");


//    @ApiOperation(value = "增加病历")
//    @PostMapping("/addBingLi")
//    public AjaxResult addBingLi()
//    {
//        return AjaxResult.success();
//    }
//
//    @ApiOperation(value = "查询病历")
//    @GetMapping("/selectBingLi/{}")
//    public AjaxResult  selectBingLi(@PathVariable String userId)
//    {
////        String userId = SecurityUtils.getAppLoginUser().getUser().getUserid();
//        return AjaxResult.success();
//    }

    //    @ApiOperation(value = "增加病历")
//    @PostMapping("/addBingLi")
//    public AjaxResult addBingLi()
//    {
//        return AjaxResult.success();
//    }


}

