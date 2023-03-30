package com.ruoyi.project.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysRoleDeptMapper;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.project.user.domain.SysUserRole;
import com.ruoyi.project.user.domain.UserComp;
import com.ruoyi.project.user.domain.XtOperComp;
import com.ruoyi.project.user.domain.XtOperUser;
import com.ruoyi.project.user.mapper.SysUserRoleMapper;
import com.ruoyi.project.user.mapper.UserCompMapper;
import com.ruoyi.project.user.mapper.XtOperUserMapper;
import com.ruoyi.project.user.service.IXtOperCompService;
import com.ruoyi.project.user.service.IXtOperUserService;
import com.ruoyi.project.login.Dto.WebLoginUser;
import com.ruoyi.project.login.TokenService.WebLoginTokenService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 系统管理员Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-27
 */
@Service
@Slf4j
public class XtOperUserServiceImpl implements IXtOperUserService
{
    @Autowired
    private XtOperUserMapper xtOperUserMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private WebLoginTokenService tokenService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private IXtOperCompService xtOperCompService;

    @Autowired
    private UserCompMapper userCompMapper;

    @Autowired
    private AppLoginTokenService appLoginTokenService;

    /**
     * 查询系统管理员
     *
     * @param uoperUserid 系统管理员主键
     * @return 系统管理员
     */
    @Override
    public XtOperUser selectXtOperUserByUoperUserid(String uoperUserid)
    {
        return xtOperUserMapper.selectXtOperUserByUoperUserid(uoperUserid);
    }

    /**
     * 查询系统管理员列表
     *
     * @param xtOperUser 系统管理员
     * @return 系统管理员
     *
     * 默认是按照当前登陆者所在的部门查找用户列表,如果传入了部门id那就是按照部门id找,
     * 统一写成按照部门id找
     */
    @Override
    public List<XtOperUser> selectXtOperUserList(XtOperUser xtOperUser)
    {
        //首先需要进行身份判断;如果是管理员就可以查询
        String compId = "";
        XtOperUser user = SecurityUtils.getWebLoginUser().getUser();
        //需要通过登陆者的地区id来确定查找范围；
        //主要是修改查找用户，和创建用户的部分，不需要再为每个部门添加员工；根据地区id就可以非常方便的找到自己所属的员工；
        //一些字段也可以去除了，比如祖级列表；（但是给前端就不方便显示层级关系了，这个到时候再说）
        //员工申请入职,还是需绑定对应的商家的,方便商家进行管理;代理和平台进行查询时,直接根据地区id,把所有的员工查询出来
        if (xtOperUser.getUoperCompid() == null){
            compId= user.getUoperCompid();
            xtOperUser.setUoperCompid(compId);
        }
//        List<UserComp> userComps = userCompMapper.selectUserCompByCompId(compId);
//        List<XtOperUser> xtOperUserList = new ArrayList<>();
//        for (int i = 0; i < userComps.size(); i++) {
//            XtOperUser xtOperUser1 = xtOperUserMapper.selectXtOperUserByUoperUserid(userComps.get(i).getUserId());
//            xtOperUserList.add(xtOperUser1);
//        }
        List<XtOperUser> xtOperUserList = xtOperUserMapper.selectWebUserList(xtOperUser);
        return xtOperUserList;
    }

    /**
     * 新增系统管理员
     *
     * @param xtOperUser 系统管理员
     * @return 结果
     */
    @Override
    public int insertXtOperUser(XtOperUser xtOperUser , HttpServletRequest request)
    {
        //设置注册时间
        xtOperUser.setUcreatedate(System.currentTimeMillis());

        //设置审核时间，默认为‘0’
        xtOperUser.setApprovalTime(0L);

        //设置id
        xtOperUser.setUoperUserid(IdUtil.getSnowflakeNextIdStr());

        //获取登录名和密码并且设置
        AppLoginUser loginMyUser1 = appLoginTokenService.getLoginMyUser(request);
        String uloginname = loginMyUser1.getUser().getUloginname();

        XtOperUser xtOperUser1 = xtOperUserMapper.selectXtOperUserByUserLoginName(uloginname);

        if(xtOperUser1 != null){
            throw new ServiceException("您已注册，请勿重复注册!");
        }

        xtOperUser.setUloginname(uloginname);
        xtOperUser.setUpassword(loginMyUser1.getUser().getUpassword());



        return xtOperUserMapper.insertXtOperUser(xtOperUser);
    }

    /**
     * 修改系统管理员
     *
     * @param xtOperUser 系统管理员
     * @return 结果
     */
    @Override
    public int updateXtOperUser(XtOperUser xtOperUser)
    {
        return xtOperUserMapper.updateXtOperUser(xtOperUser);
    }

    /**
     * 批量删除系统管理员
     *
     * @param uoperUserids 需要删除的系统管理员主键
     * @return 结果
     */
    @Override
    public int deleteXtOperUserByUoperUserids(String[] uoperUserids)
    {
        //父级公司里的可以不删啊,反正也查不到
        return xtOperUserMapper.deleteXtOperUserByUoperUserids(uoperUserids);
    }

    /**
     * 删除系统管理员信息
     *
     * @param uoperUserid 系统管理员主键
     * @return 结果
     */
    @Override
    public int deleteXtOperUserByUoperUserid(String uoperUserid)
    {
        return xtOperUserMapper.deleteXtOperUserByUoperUserid(uoperUserid);
    }

    @Override
    public XtOperUser selectXtOperUserByUserLoginName(String username) {
        XtOperUser xtOperUser = xtOperUserMapper.selectXtOperUserByUserLoginName(username);
        return xtOperUser;
    }

    @Override
    public String register(String username, String password) {
        log.info("开始注册账号:{}",username);
        String msg = "";
        XtOperUser xtOperUser = new XtOperUser();
        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        } else
        {
            xtOperUser.setUoperUserid(IdUtil.getSnowflakeNextIdStr());
            xtOperUser.setUloginname(username);
            xtOperUser.setUpassword(SecurityUtils.encryptPassword(password));
            xtOperUser.setUcreatedate(System.currentTimeMillis());
            boolean regFlag = xtOperUserMapper.insertXtOperUser(xtOperUser) > 0;
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
                log.error("{}账号注册失败",username);
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
                log.info("账号:{},注册成功",username);
            }
        }

        return msg;

    }

    @Override
    public String login(String username, String password) {

        // 用户验证
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }

        WebLoginUser loginUser = (WebLoginUser) authentication.getPrincipal();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(XtOperUser user) {
        //加密密码
        user.setUpassword(SecurityUtils.encryptPassword(user.getUpassword()));
        user.setUoperUserid(IdUtil.getSnowflakeNextIdStr());
        XtOperUser xtOperUser = SecurityUtils.getWebLoginUser().getUser();
        //设置归属公司
        if (user.getUoperCompid() == null){
            //如果没有选择归属公司,那就默认是归属于当前登录者的公司
            user.setUoperCompid(xtOperUser.getUoperCompid());

        }

        //user-role
        if (user.getUroleid() != null){
            sysUserRoleMapper.insertUserRole(user.getUoperUserid(),user.getUroleid());
        }

        //需要为父级公司加入该用户
        //先找到所有的父级公司,依次记录
        XtOperComp xtOperComp = xtOperCompService.selectXtOperCompByUoperCompid(user.getUoperCompid());
        String ancestors = xtOperComp.getAncestors() + "," + user.getUoperCompid();
        List<String> compIds = Arrays.asList(ancestors.split(","));
        for (int i = 0; i < compIds.size(); i++) {
            UserComp userComp = new UserComp();
            userComp.setId(IdUtil.getSnowflakeNextIdStr());
            userComp.setUserId(user.getUoperUserid());
            userComp.setCompId(compIds.get(i));
            userCompMapper.insertUserComp(userComp);
        }

        return  xtOperUserMapper.insertXtOperUser(user);
    }

    @Override
    public String checkUserNameUnique(XtOperUser user) {

        XtOperUser xtOperUser = xtOperUserMapper.selectXtOperUserByUserLoginName(user.getUloginname());
        if (xtOperUser != null){
            return UserConstants.NOT_UNIQUE;
        }

            return UserConstants.UNIQUE;
    }

    @Override
    public String checkPhoneUnique(XtOperUser user) {
        XtOperUser xtOperUser = xtOperUserMapper.selectXtOperUserByPhoneNumber(user.getUmobile());
        if (xtOperUser != null){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkEmailUnique(XtOperUser user) {
        XtOperUser xtOperUser = xtOperUserMapper.selectXtOperUserBEmail(user.getUemail());
        if (xtOperUser != null){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

//    @Override
//    public int auditSystemPlatformAdministratorRegistration(String status, XtOperUser xtOperUser) {
//        xtOperUser.setStatus(status);
//        return xtOperUserMapper.updateXtOperUser(xtOperUser);
//    }

        @Override
    public int auditSystemPlatformAdministratorRegistration(String status , String id) {
        long uapprovalTime = System.currentTimeMillis();
        return xtOperUserMapper.auditSystemPlatformAdministratorRegistration(status ,id ,uapprovalTime);
    }

    @Override
    public List<XtOperUser> selectAllPlatformAdministrator() {
        return xtOperUserMapper.selectAllPlatformAdministrator();
    }

    @Override
    public List<XtOperUser> vagueSelectAllPlatformAdministrator(String name) {
        return xtOperUserMapper.vagueSelectAllPlatformAdministrator(name);
    }



//    @Override
//    public int suspensionAndReinstatementSystemPlatformAdministrator(String uoperUserid, char delFlag) {
//        return 0;
//    }

    @Override
    public int suspensionAndReinstatementSystemPlatformAdministrator(String uoperUserid, String delFlag) {
        long uapprovalTime = new Date().getTime();
        return xtOperUserMapper.suspensionAndReinstatementSystemPlatformAdministrator(uoperUserid,delFlag,uapprovalTime);
    }

//    @Override
//    public XtOperUser renderAdministratorRegistration(HttpServletRequest request) {
//        AppLoginUser loginMyUser1 = appLoginTokenService.getLoginMyUser(request);
//        String uloginname = loginMyUser1.getUser().getUloginname();
//
//        XtOperUser xtOperUser = xtOperUserMapper.renderAdministratorRegistration(uloginname);
//        System.out.println(xtOperUser);
//        return xtOperUser;
//    }

    @Override
    public XtOperUser viewAdministratorDetailsByID(String id) {
        return xtOperUserMapper.viewAdministratorDetailsByID(id);
    }

//    @Override
//    public int auditAdministratorIsSuspendedOrRestored(String delFlag, String id) {
//        long uapprovalTime = System.currentTimeMillis();
//        xtOperUser.setDelFlag(delFlag);
//        xtOperUser.setUcreatedate(uapprovalTime);
//        return xtOperUserMapper.updateXtOperUser(xtOperUser);
//    }

}
