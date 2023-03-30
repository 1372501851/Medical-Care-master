package com.ruoyi.project.myinfo.controller;

import cn.hutool.core.util.IdUtil;
import com.alipay.api.internal.util.StringUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.im.domain.Qun;
import com.ruoyi.project.im.domain.XtMember;
import com.ruoyi.project.im.mapper.XtChatMessageMapper;
import com.ruoyi.project.im.service.ChatService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.domain.XtOrderitem;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.myinfo.domain.*;

import com.ruoyi.project.myinfo.domain.vo.XtShouFeiVo;
import com.ruoyi.project.myinfo.mapper.*;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.XtUserMapper;
import com.ruoyi.project.user.service.IXtUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author by hujun
 * @date 2023-03-01
 */

@RestController
@RequestMapping("/MyInfo")
@Api(tags = "个人信息模块")
public class MyInfoController {

    @Autowired
    private XtjiankandananMapper xtjiankandananMapper;

    @Autowired
    private XtQinqingMapper xtQinqingMapper;

    @Autowired
    private XtModelTypeMapper xtModelTypeMapper;

    @Autowired
    private XtModelMapper xtModelMapper;

    @Autowired
    private IXtUserService iXtUserService;

    @Autowired
    private XtUserMapper xtUserMapper;

    @Autowired
    private XtShouFeiMapper xtShouFeiMapper;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private IXtEmployeeService iXtEmployeeService;


    @Autowired
    private XtFanKuiMapper xtFanKuiMapper;


    @ApiOperation(value = "查询健康档案")
    @GetMapping("/selectJianKangDangAn")
    public AjaxResult selectJianKangDangAn()
    {

        XtJiankandanan xtJiankandanan = xtjiankandananMapper.selectXtjiankandanan(SecurityUtils.getAppLoginUser().getUser().getUserid());

        return  AjaxResult.success(xtJiankandanan);
    }

    @ApiOperation(value = "增加或者修改健康档案")
    @PostMapping("/addJianKangDangAn")
    public AjaxResult addJianKangDangAn(@RequestBody XtJiankandanan xtJiankandanan)
    {
        XtJiankandanan xtJiankandanan1 = xtjiankandananMapper.selectXtjiankandanan(SecurityUtils.getAppLoginUser().getUser().getUserid());
        try {
            String id = xtJiankandanan1.getId();
        }catch (NullPointerException e){
            xtJiankandanan.setId(IdUtil.getSnowflakeNextIdStr());
            String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
            xtJiankandanan.setUserid(userid);
            Integer integer = xtjiankandananMapper.addXtjiankandanan(xtJiankandanan);
            return  AjaxResult.success(integer);
        }
        Integer integer = xtjiankandananMapper.updateXtjiankandanan(xtJiankandanan);
        return  AjaxResult.success("成功",integer);
    }

    @ApiOperation(value = "修改健康档案")
    @PutMapping("/updateJianKangDangAn")
    public AjaxResult updateJianKangDangAn(@RequestBody XtJiankandanan xtJiankandanan)
    {
        Integer integer = xtjiankandananMapper.updateXtjiankandanan(xtJiankandanan);
        return  AjaxResult.success("成功",integer);
    }

    @ApiOperation(value = "增加亲情人")
    @PostMapping("/addQinqing")
    public AjaxResult addQinqing(@RequestBody XtQinqing xtQinqing)
    {
        xtQinqing.setId(IdUtil.getSnowflakeNextIdStr());
        xtQinqing.setUserid(SecurityUtils.getAppLoginUser().getUser().getUserid());
        Integer integer = xtQinqingMapper.addXtQinqing(xtQinqing);
        return  AjaxResult.success("成功",integer);
    }

    @ApiOperation(value = "查看亲情人列表")
    @GetMapping("/seleQinqing")
    public AjaxResult seleQinqing()
    {
        List<XtQinqing> xtQinqings = xtQinqingMapper.seleXtQinqing(SecurityUtils.getAppLoginUser().getUser().getUserid());
        return  AjaxResult.success("成功",xtQinqings);
    }

    @ApiOperation(value = "查看亲情人详细信息")
    @GetMapping("/seleQinqingByid/{id}")
    public AjaxResult seleQinqingByid(@PathVariable String id)
    {
        return  AjaxResult.success("成功",xtQinqingMapper.seleXtQinqingInfo(id));
    }

    @ApiOperation(value = "增加模板类型")
    @PostMapping("/addModelType")
    public AjaxResult addModelType(@RequestBody XtModelType xtModelType)
    {
        xtModelType.setUserid(SecurityUtils.getAppLoginUser().getUser().getUserid());
        xtModelType.setId(IdUtil.getSnowflakeNextIdStr());
        return  AjaxResult.success("成功",xtModelTypeMapper.addXtModelType(xtModelType));
    }

    @ApiOperation(value = "查找模板类型列表")
    @GetMapping("/seleModelTypeList")
    public AjaxResult seleModelTypeList()
    {
        return  AjaxResult.success("成功",xtModelTypeMapper.seleXtModelType(SecurityUtils.getAppLoginUser().getUser().getUserid()));
    }

    @ApiOperation(value = "模糊查找模板类型")
    @GetMapping("/seleLikeModelType/{name}")
    public AjaxResult seleLikeModelType(@PathVariable String name)
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        return  AjaxResult.success("成功",xtModelTypeMapper.seleLikeModelType("%"+name+"%",userid));
    }

    @ApiOperation(value = "删除模板类型")
    @GetMapping("/delModelType/{id}")
    public AjaxResult delModelType(@PathVariable String id)
    {
        return  AjaxResult.success("成功",xtModelTypeMapper.delXtModelType(id));
    }

    @ApiOperation(value = "增加模板")
    @PostMapping("/addModel")
    public AjaxResult addModel(@RequestBody XtModel xtModel)
    {
        xtModel.setId(IdUtil.getSnowflakeNextIdStr());
        xtModel.setUserid(SecurityUtils.getAppLoginUser().getUser().getUserid());
        return  AjaxResult.success("成功",xtModelMapper.addModel(xtModel));
    }

    @ApiOperation(value = "查询模板列表")
    @GetMapping("/seleModelList/{type}")
    public AjaxResult seleModelList(@PathVariable String type)
    {
        return  AjaxResult.success("成功",xtModelMapper.seleModelByTypeId(type,SecurityUtils.getAppLoginUser().getUser().getUserid()));
    }

    @ApiOperation(value = "修改模板")
    @PutMapping("/updateModel")
    public AjaxResult updateModel( String xiangxixinxi , String id)
    {
        return  AjaxResult.success("成功",xtModelMapper.updateXtModel(xiangxixinxi,id));
    }

    @ApiOperation(value = "模糊查找模板")
    @PutMapping("/seleLikeModel/{name}")
    public AjaxResult seleLikeModel(@PathVariable String name)
    {
        return  AjaxResult.success("成功",xtModelMapper.seleLikeModel(SecurityUtils.getAppLoginUser().getUser().getUserid(),"%"+name+"%"));
    }

    @ApiOperation(value = "删除模板")
    @DeleteMapping ("/delModel")
    public AjaxResult delModel(String id)
    {
        return  AjaxResult.success("成功",xtModelMapper.delModel(id));
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/updatePassword")
    public AjaxResult updatePassword( String passWord1 , String passWord2 , String passWord3) {

        if(passWord1 == "" || passWord1 ==null){
            throw new ServiceException("请输入原密码");
        }

        XtUser user = SecurityUtils.getAppLoginUser().getUser();
        String upassword = user.getUpassword();
        boolean b = SecurityUtils.matchesPassword(passWord1, upassword);
        if(!b){
            throw new ServiceException("密码输入不正确，请重新输入");
        }

        if(passWord2 == null || passWord2 == "" || passWord3 == null || passWord3 == ""){
            throw new ServiceException("请输入新密码");
        }

        String pp = "1";
        if(passWord2.equals( passWord3)){
             pp = SecurityUtils.encryptPassword(passWord2);
        }else {
            throw new ServiceException("两次密码不相同");
        }
        int data = xtUserMapper.updatePassWord( pp, user.getUserid());
        return AjaxResult.success("成功", data);
    }

    @ApiOperation(value = "收费设置")
    @PostMapping  ("/setShouFei")
    public AjaxResult setShouFei(@RequestBody XtShouFeiVo xtShouFeiVo)
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        String join = String.join(",", xtShouFeiVo.getXuanxiang());
        String type = xtShouFeiVo.getType();
        XtShouFei xtShouFei = new XtShouFei();
        xtShouFei.setUserid(userid);
        xtShouFei.setXuanxiang(join);
        xtShouFei.setUtype(type);
        xtShouFeiMapper.updateShouFeiSet(xtShouFei);
        return  AjaxResult.success("成功");
    }

    @ApiOperation(value = "查看收费设置")
    @GetMapping  ("/seleShouFei/{utype}")
    public AjaxResult seleShouFei(@PathVariable String utype)
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtShouFei xtShouFei = xtShouFeiMapper.seleXtShouFei(userid, utype);
       if(xtShouFei==null){
           XtShouFei xtShouFei1 = new XtShouFei();
           xtShouFei1.setId(IdUtil.getSnowflakeNextIdStr());
           xtShouFei1.setUtype(utype);
           xtShouFei1.setXuanxiang("0,0,0,0,0,0,0");
           xtShouFei1.setUserid(userid);
           xtShouFeiMapper.addShouFeiSet(xtShouFei1);
           return  AjaxResult.success("成功",xtShouFei1.getXuanxiang().split(","));
       }else {
           return  AjaxResult.success("成功",xtShouFeiMapper.seleXtShouFei(userid , utype).getXuanxiang().split(","));
       }

    }

    @Autowired
    private ChatService chatService;

    @Autowired
    private XtChatMessageMapper xtChatMessageMapper;



    @ApiOperation(value = "员工入职，离职审核 , status（员工状态表示）: 0：入职待审核，1：入职状态，2：离职待审核，3：离职状态。applyStatus（审核状态表示）：0：待审核，1：审核通过，2：审核驳回。对应员工id")
    @PutMapping("/updateEmploy")
    public AjaxResult updateEmploy(String status , String applyStatus , String employId)
    {
        String employUserid=null;
        try {
             employUserid = xtEmployeeMapper.selectXtEmployeeByUemployeeid(employId).getUserid();
        }catch (MyBatisSystemException m){
            throw new ServiceException("你已经是员工了");
        }catch (NullPointerException n){
            throw new ServiceException("数据库没这个人");
        }




        try {
            Integer integer = xtEmployeeMapper.updateEmploy(status, applyStatus, employId);
        }catch (Exception e){
            throw new ServiceException("审核失败");
        }

        //员工的用户ID，用于自动创建部门群


        //拿到部门名字，后面自动创建群，自动拉人都要用；
        String udepartmentid = xtEmployeeMapper.selectXtEmployeeByUemployeeid(employId).getUdepartmentid();

        //拿到同登陆者的compid，后面查群，建群，拉人都要用
        XtEmployee xtEmployee = new XtEmployee();
        xtEmployee.setUcompid(xtEmployeeMapper.selectXtEmployeeByUserId(SecurityUtils.getAppLoginUser().getUser().getUserid()).getUcompid());


        //注册审核通过后的动作
        if( "2".equals(status) && "2".equals(applyStatus)){

            //判断群成员表里面是否有这个人了，有就不加入表了，没有就加入进去
            String memberId = xtChatMessageMapper.selectMemberIdByUserId(employUserid);
            if("".equals(memberId) || memberId == null){
                XtMember xtMember = new XtMember();
                xtMember.setUmemberId(IdUtil.getSnowflakeNextIdStr());
                xtMember.setUserId(xtEmployeeMapper.selectUserIdByEmployId(employId));
                xtChatMessageMapper.creatXtMember(xtMember);
            }


            //拿到属于商家的所有部门群
            HashSet<String> qunNames = xtChatMessageMapper.selectQunNameByCompId(xtEmployee.getUcompid());

            //如果商家没有部门群，直接给他自动创建
            if(qunNames.isEmpty()){
                String[] chenhyuanid = {employUserid};
                chatService.creatQun(chenhyuanid,"行政管理",udepartmentid , xtEmployee.getUcompid());
                chatService.creatQun(chenhyuanid,"行政监管",udepartmentid , xtEmployee.getUcompid());
                chatService.creatQun(chenhyuanid,"工作安排",udepartmentid , xtEmployee.getUcompid());

                return  AjaxResult.success("成功");
            }

            //员工所填部门是否属于同一部门群，如果是，就拉入到特定群里面；如果不是就在三个位置自动给他创建部门群
            if(qunNames.contains(udepartmentid)){
                Qun qun1 = xtChatMessageMapper.selectQunByCompIdAndQunmingziAndweizhi(xtEmployee.getUcompid(), udepartmentid, "行政管理");
                Qun qun2 = xtChatMessageMapper.selectQunByCompIdAndQunmingziAndweizhi(xtEmployee.getUcompid(), udepartmentid, "行政监管");
                Qun qun3 = xtChatMessageMapper.selectQunByCompIdAndQunmingziAndweizhi(xtEmployee.getUcompid(), udepartmentid, "工作安排");

                String qun1Id = qun1.getId();
                String qun2Id = qun2.getId();
                String qun3Id = qun3.getId();

                String s = xtChatMessageMapper.selectMember(employUserid);

                //自动拉入到部门群
                chatService.addchengyuangs(new String[]{s} , qun1Id);
                chatService.addchengyuangs(new String[]{s} , qun2Id);
                chatService.addchengyuangs(new String[]{s} , qun3Id);

            }else {
                //在需要自动创建部门群的位置上自动创群
                String[] chenhyuanid = {employUserid};
                chatService.creatQun(chenhyuanid,"行政管理",udepartmentid , xtEmployee.getUcompid());
                chatService.creatQun(chenhyuanid,"行政监管",udepartmentid , xtEmployee.getUcompid());
                chatService.creatQun(chenhyuanid,"工作安排",udepartmentid , xtEmployee.getUcompid());
            }


        }

        return  AjaxResult.success("成功");
    }

    @ApiOperation(value = "查部门")
    @GetMapping("/seleBuMen")
    public AjaxResult seleBuMen()
    {
        List<XtEmployee> list = iXtEmployeeService.selectEmployListByComp("2");
        Set<String> set = new HashSet<>();
        for (XtEmployee x:
                list) {
            set.add(x.getUdepartmentid());
        }

        return  AjaxResult.success("成功",set);
    }

    @ApiOperation(value = "查员工根据部门")
    @GetMapping("/seleEmployByBuMen")
    public AjaxResult seleEmployByBuMen(String buMenId)
    {
        List<XtEmployee> list = iXtEmployeeService.selectEmployListByComp("2");
        List<XtEmployee> list2 = new ArrayList<>();
        for (XtEmployee x:
                list) {
           if(buMenId.equals(x.getUdepartmentid())){
               list2.add(x);
           }
        }

        return  AjaxResult.success("成功",list2);
    }

    @ApiOperation(value = "增加反馈")
    @GetMapping("/addFanKui")
    public AjaxResult addFanKui(String neirong)
    {
        XtFanKui xtFanKui = new XtFanKui();
        xtFanKui.setNeirong(neirong);
        xtFanKui.setUserid(SecurityUtils.getAppLoginUser().getUser().getUserid());
        xtFanKui.setId(IdUtil.getSnowflakeNextIdStr());
        return  AjaxResult.success("成功",xtFanKuiMapper.addXtFanKui(xtFanKui));
    }

    @Autowired
    private XtBanbenMapper xtBanbenMapper;
    @ApiOperation(value = "查看版本")
    @GetMapping("/getBanben")
    public AjaxResult getBanben()
    {
        XtBanben xtBanben = xtBanbenMapper.selectBanben();

        return  AjaxResult.success("成功",xtBanben);
    }

}
