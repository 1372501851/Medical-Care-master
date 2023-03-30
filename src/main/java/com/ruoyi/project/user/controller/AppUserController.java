package com.ruoyi.project.user.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.merchant.service.IXtEmployeeService;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.domain.register.RegisterDto;
import com.ruoyi.project.user.service.ISysMenuService;
import com.ruoyi.project.user.service.IXtUserService;
import com.ruoyi.project.login.security.permissionService.AppPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.ruoyi.framework.web.domain.AjaxResult.error;
import static com.ruoyi.framework.web.domain.AjaxResult.success;

/**
 * @author by hujun
 * @date 2022-10-29
 */
@Api(tags = "App用户管理模块")
@RestController
@RequestMapping("/app/user")
public class AppUserController extends BaseController {
    @Autowired
    private IXtUserService iXtUserService;

    @Autowired
    private AppPermissionService appPermissionService;

    @Autowired
    private IXtEmployeeService xtEmployeeService;

    @Autowired
    private ISysMenuService iSysMenuService;

    /**
     *
     * 用户注册
     *
     * */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public AjaxResult appRegister(@Validated  @RequestBody RegisterDto registerDto){
        String registerInfo = iXtUserService.register(registerDto);
        return StringUtils.isEmpty(registerInfo) ? success() : error(registerInfo);
    }

    /**
     *
     * 用户信息
     *
     * */
    @GetMapping("/getInfo")
    @ApiOperation(value = "获取用户信息")
    public AjaxResult getInfo()
    {
        XtUser user = SecurityUtils.getAppLoginUser().getUser();
//        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = appPermissionService.getMenuPermission(user);
        XtEmployee xtEmployee = xtEmployeeService.queryUserInfo(user.getUserid());
        AjaxResult ajax = AjaxResult.success();

        user.setUpassword("*****************");

        //处理头像问题
        String avatar = user.getAvatar();
        List<String> url = StringToURL.toURL(avatar);
        user.setAvatar(url.get(0));

        ajax.put("user", user);
        ajax.put("userInfo",xtEmployee);
//        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }


    /**
     * 登录后台获取路由信息
     * */
//    @ApiOperation(value = "登录后台获取路由信息")
//    @GetMapping("/getRouters")
//    public AjaxResult getRouters(){
//        //获取所有的菜单
//        List<SysMenu> menus = iSysMenuService.selectMenuTreeByUserId();
//        //拿到菜单后构建路由信息(这一步也可以由前端完成)
//        return AjaxResult.success(iSysMenuService.buildMenus(menus));
//    }


    /**
     * 查询所有用户登录（所有用户，医生、护士、患者、家属）列表
     */

    @ApiOperation(value = "查询用户")
    @GetMapping("/list")
    public TableDataInfo list(XtUser xtUser)
    {
        startPage();
        List<XtUser> list = iXtUserService.selectXtUserList(xtUser);
//        List<XtUser> list = iXtUserService.selectXtUserListByQuery(userQuery);
        return getDataTable(list);
    }



    /**
     * 获取所有用户登录（所有用户，医生、护士、患者、家属）详细信息
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping(value = "/info/{userid}")
    public AjaxResult getInfo(@PathVariable("userid") String userid)
    {
        XtUser xtUser = iXtUserService.selectXtUserByUserid(userid);
//        xtUser.setUpassword("");
        return AjaxResult.success(xtUser);
    }

    /**
     * 新增所有用户登录（所有用户，医生、护士、患者、家属）
     */
    @ApiOperation(value = "新增用户")
    @Log(title = "所有用户登录（所有用户，医生、护士、患者、家属）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody XtUser xtUser)
    {
        return toAjax(iXtUserService.insertXtUser(xtUser));
    }

    @ApiOperation(value = "新增用户(不用认证)")
    @PostMapping("/add/test")
    public AjaxResult addUser(XtUser xtUser) {
        return toAjax(iXtUserService.insertXtUser(xtUser));
    }

    /**
     * 修改所有用户登录（所有用户，医生、护士、患者、家属）
     */
    @ApiOperation(value = "修改用户信息")
    @Log(title = "所有用户登录（所有用户，医生、护士、患者、家属）", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtUser xtUser)
    {
        return toAjax(iXtUserService.updateXtUser(xtUser));
    }

    /**
     * 删除所有用户登录（所有用户，医生、护士、患者、家属）
     */
    @ApiOperation(value = "删除用户信息")
    @Log(title = "所有用户登录（所有用户，医生、护士、患者、家属）", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{userids}")
    public AjaxResult remove(@PathVariable String[] userids)
    {
        return toAjax(iXtUserService.deleteXtUserByUserids(userids));
    }

    /**
     *
     * 获取用户下拉列表;
     */
    @ApiOperation(value = "获取非员工用户下拉列表")
    @GetMapping("/selectList")
    public AjaxResult getSelectList() {
        return AjaxResult.success(iXtUserService.getSelectList());
    }

}
