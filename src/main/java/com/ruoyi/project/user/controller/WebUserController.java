package com.ruoyi.project.user.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.login.security.permissionService.AppPermissionService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.project.user.domain.SysMenu;
import com.ruoyi.project.user.domain.XtOperUser;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.XtUserMapper;
import com.ruoyi.project.user.service.ISysMenuService;
import com.ruoyi.project.user.service.IXtOperUserService;
import com.ruoyi.project.login.security.permissionService.WebPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ruoyi.framework.web.domain.AjaxResult.error;
import static com.ruoyi.framework.web.domain.AjaxResult.success;

/**
 * @author by hujun
 * @date 2022-10-29
 */
@Api(tags = "Web用户管理模块")
@RestController
@RequestMapping("/web/user")
public class WebUserController  extends BaseController {
    @Autowired
    private IXtOperUserService iXtOperUserService;

    @Autowired
    private WebPermissionService webPermissionService;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private AppPermissionService appPermissionService;


    @Autowired
    private IXtEmployeeService xtEmployeeService;

    @Autowired
    private XtUserMapper xtUserMapper;


    /**
     *
     * 用户注册
     *
     * */

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public AjaxResult appRegister(@RequestParam("username") String username, @RequestParam("password")String password){
        String registerInfo = iXtOperUserService.register(username, password);
        return StringUtils.isEmpty(registerInfo) ? success() : error(registerInfo);
    }

    /**
     *
     * 用户信息
     *
     * */
    @ApiOperation(value = "用户信息")
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {

        //判断是否是管理员端登录
        WebLoginUser webLoginUser = SecurityUtils.getWebLoginUser();
        AjaxResult ajax = AjaxResult.success();
        if (webLoginUser != null) {
            XtOperUser user = webLoginUser.getUser();
            Set<String> roles = webPermissionService.getRolePermission(user);
            // 权限集合
            Set<String> permissions = webPermissionService.getMenuPermission(user);
            user.setUpassword("*******************");

            ajax.put("user", user);
//            ajax.put("roles", roles);
            ajax.put("userInfo",user);
            ajax.put("permissions", permissions);

        }else {
            //判断是否是商家端登录
            AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
            XtUser user = appLoginUser.getUser();
            Set<String> permissions = appPermissionService.getMenuPermission(user);
            XtEmployee xtEmployee = xtEmployeeService.queryUserInfo(user.getUserid());

            //处理头像问题
            String avatar = user.getAvatar();
            List<String> url = StringToURL.toURL(avatar);
//            user.setAvatar(url.get(0));
            user.setUpassword("*******************");

            ajax.put("user", user);
            ajax.put("userInfo",xtEmployee);
//            ajax.put("roles", roles);
            ajax.put("permissions", permissions);
        }

        return ajax;
    }


    /**
     * 获取路由信息
     *获取用户可以访问的菜单
     * @return 路由信息
     */
    @ApiOperation(value = "获取路由信息")
    @GetMapping("/getRouters")
    public AjaxResult getRouters()
    {
        //获取所有的菜单
        List<SysMenu> menus = iSysMenuService.selectMenuTreeByUserId();
        //拿到菜单后构建路由信息(这一步也可以由前端完成)
        return AjaxResult.success(iSysMenuService.buildMenus(menus));
    }



    /**
     *
     * 新增用户(效验用户填写的信息)
     *
     * */

    @ApiOperation(value = "新增用户")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtOperUser user)
    {
        //效验填入的信息是否有效与重复

        //这些代码可以优化,多线程去查询判断,最后查看结果
        if (UserConstants.NOT_UNIQUE.equals(iXtOperUserService.checkUserNameUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUloginname() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getUmobile())
                && UserConstants.NOT_UNIQUE.equals(iXtOperUserService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUmobile() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getUemail())
                && UserConstants.NOT_UNIQUE.equals(iXtOperUserService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUemail() + "'失败，邮箱账号已存在");
        }

        return toAjax(iXtOperUserService.insertUser(user));
    }



    /**
     * 查看用户列表
     *
     * */
    @ApiOperation("查看用户列表")
    @GetMapping("/list")
    public AjaxResult list( XtOperUser xtOperUser)
    {
        startPage();
        List<XtOperUser> list = iXtOperUserService.selectXtOperUserList(xtOperUser);
        return AjaxResult.success(list);
    }

    /**
     * 按条件查看用户列表
     *(可以和上面接口合成一个,但是需要连表查询,在sql层面完成信息的读取)
     * */


    /**
     *
     * 修改用户
     *
     * */
    @ApiOperation(value = "修改用户")
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtOperUser xtOperUser)
    {
        return toAjax(iXtOperUserService.updateXtOperUser(xtOperUser));
    }



    @ApiOperation(value = "删除用户")
    @DeleteMapping("/del/{uoperUserids}")
    public AjaxResult remove(@PathVariable String[] uoperUserids)
    {
        System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");
        for (String x:
             uoperUserids) {
            System.out.println(x);
        }
        return toAjax(iXtOperUserService.deleteXtOperUserByUoperUserids(uoperUserids));
    }


    @ApiOperation(value = "根据id查看用户详情")
    @GetMapping("/infoById")
    public AjaxResult info(String userId){
        XtOperUser xtOperUser = iXtOperUserService.selectXtOperUserByUoperUserid(userId);
        return AjaxResult.success(xtOperUser);
    }

    @ApiOperation(value = "查询所有系统平台管理员")
    @GetMapping("/ selectAllPlatformAdministrator")
    public AjaxResult selectAllPlatformAdministrator(){
        List<XtOperUser> xtOperUserList = iXtOperUserService.selectAllPlatformAdministrator();
        return AjaxResult.success(xtOperUserList);
    }

    @ApiOperation(value = "模糊查询系统管理员（管理员名字）")
    @GetMapping("/vagueSelectAllPlatformAdministrator")
    public AjaxResult vagueSelectAllPlatformAdministrator(String name){
        List<XtOperUser> xtOperUserList = iXtOperUserService.vagueSelectAllPlatformAdministrator(name);
        return AjaxResult.success(xtOperUserList);
    }



    /**
     *
     * 审核平台管理员注册
     *
     * */
    @ApiOperation(value = "审核平台管理员注册")
    @PutMapping("/auditSystemPlatformAdministratorRegistration")
    public AjaxResult auditSystemPlatformAdministratorRegistration(String status , String id )
    {
        return toAjax(iXtOperUserService.auditSystemPlatformAdministratorRegistration(status,id));
    }


    @ApiOperation(value = "小刘专用，根据获取管理员详细信息")
    @PutMapping("/getAdministratorDetails")
    public AjaxResult getAdministratorDetails(String id )
    {
        XtOperUser administratorDetails = xtUserMapper.getAdministratorDetails(id);
        return AjaxResult.success(administratorDetails);
    }

    @ApiOperation(value = "小刘专用，决定是否让管理员停职和恢复（不可修改版），delFlag：0在职（恢复），1：停职（删除）")
    @PutMapping("/auditAdministratorIsSuspendedOrRestored")
    public AjaxResult auditAdministratorIsSuspendedOrRestored(String delFlag , String id)
    {
        return AjaxResult.success(iXtOperUserService.suspensionAndReinstatementSystemPlatformAdministrator(id , delFlag));
    }

    @ApiOperation(value = "系统管理员置顶")
    @PostMapping("/top")
    public AjaxResult top(@PathVariable String[] element , String ob)
    {
        Set<XtOperUser> obj = new HashSet<>();
        obj.add(iXtOperUserService.selectXtOperUserByUoperUserid(ob));
        for (String x:
            element) {
            obj.add(iXtOperUserService.selectXtOperUserByUoperUserid(x));
        }
        return AjaxResult.success(obj);
    }


    @ApiOperation(value = "小刘专用，获取本人ID")
    @PutMapping("/getMyId")
    public AjaxResult getMyId()
    {
        return AjaxResult.success(SecurityUtils.getAppLoginUser().getUser().getUserid());
    }

    @ApiOperation(value = "随便搞两个用户")
    @PutMapping("/kuaca")
    public AjaxResult kuaca()
    {
        return AjaxResult.success(xtUserMapper.kuaca());
    }

    @ApiOperation(value = "修改用户接单状态")
    @PutMapping("/updateJiedanstatus")
    public AjaxResult updateJiedanstatus( String status,  String id)
    {
        return AjaxResult.success(xtUserMapper.updateJiedanstatus(status,id));
    }

    @ApiOperation(value = "小刘专用，获取本人名字")
    @GetMapping("/getMyName")
    public AjaxResult getMyName()
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        return AjaxResult.success("成功",xtUserMapper.selectXtUserByUserid(userid).getUsername());
    }

    @ApiOperation(value = "平台系统管理员注册")
    @PostMapping("/insertXtOperUser")
    public AjaxResult insertXtOperUser(XtOperUser xtOperUser , HttpServletRequest request)
    {
        return toAjax(iXtOperUserService.insertXtOperUser(xtOperUser,request));
    }

}
