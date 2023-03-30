package com.ruoyi.project.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.user.constant.UserConstants;
import com.ruoyi.project.user.domain.UserAccount;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.domain.query.UserQuery;
import com.ruoyi.project.user.domain.register.RegisterDto;
import com.ruoyi.project.user.domain.vo.UserSelect;
import com.ruoyi.project.user.mapper.UserAccountMapper;
import com.ruoyi.project.user.mapper.XtUserMapper;
import com.ruoyi.project.user.service.IXtUserService;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.login.security.AuthenticationToken.AppAuthenticationToken;
import com.ruoyi.project.login.security.provider.AppAuthenticationProvider;

import javafx.scene.chart.XYChart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有用户登录（所有用户，医生、护士、患者、家属）Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
@Slf4j
public class XtUserServiceImpl implements IXtUserService
{
    @Autowired
    private XtUserMapper xtUserMapper;

    @Autowired
    private AppLoginTokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppAuthenticationProvider appAuthenticationProvider;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 查询所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param userid 所有用户登录（所有用户，医生、护士、患者、家属）主键
     * @return 所有用户登录（所有用户，医生、护士、患者、家属）
     */
    @Override
    public XtUser selectXtUserByUserid(String userid)
    {
        return xtUserMapper.selectXtUserByUserid(userid);
    }

    /**
     * 查询所有用户登录（所有用户，医生、护士、患者、家属）列表
     *
     * @param xtUser 所有用户登录（所有用户，医生、护士、患者、家属）
     * @return 所有用户登录（所有用户，医生、护士、患者、家属）
     */
    @Override
    public List<XtUser> selectXtUserList(XtUser xtUser)
    {
        return xtUserMapper.selectXtUserList(xtUser);
    }

    /**
     * 新增所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param xtUser 所有用户登录（所有用户，医生、护士、患者、家属）
     * @return 结果
     */
    @Override
    public int insertXtUser(XtUser xtUser)
    {
        xtUser.setUserid(IdUtil.getSnowflakeNextIdStr());
        //判断账号是否重复
        XtUser xtUser1 = xtUserMapper.selectXtUserByUserLoginName(xtUser.getUloginname());
        if (xtUser1 !=null){
            throw  new ServiceException("该账号已存在.");
        }
        //密码加密
        xtUser.setUpassword(SecurityUtils.encryptPassword(xtUser.getUpassword()));
        return xtUserMapper.insertXtUser(xtUser);
    }

    /**
     * 修改所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param xtUser 所有用户登录（所有用户，医生、护士、患者、家属）
     * @return 结果
     */
    @Override
    public int updateXtUser(XtUser xtUser)
    {
        return xtUserMapper.updateXtUser(xtUser);
    }

    /**
     * 批量删除所有用户登录（所有用户，医生、护士、患者、家属）
     *
     * @param userids 需要删除的所有用户登录（所有用户，医生、护士、患者、家属）主键
     * @return 结果
     */
    @Override
    public int deleteXtUserByUserids(String[] userids)
    {
        return xtUserMapper.deleteXtUserByUserids(userids);
    }

    /**
     * 删除所有用户登录（所有用户，医生、护士、患者、家属）信息
     *
     * @param userid 所有用户登录（所有用户，医生、护士、患者、家属）主键
     * @return 结果
     */
    @Override
    public int deleteXtUserByUserid(String userid)
    {
        return xtUserMapper.deleteXtUserByUserid(userid);
    }

    @Override
    public XtUser selectXtUserByUserLoginName(String username) {
        XtUser xtUser = xtUserMapper.selectXtUserByUserLoginName(username);
        return xtUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String register(RegisterDto registerDto) {

        //注册效验

        XtUser xtUser1 = xtUserMapper.selectXtUserByUserLoginName(registerDto.getUloginname());
        if (xtUser1 != null){
            throw new ServiceException("该账户已被注册,请更换注册账号");
        }

        String msg = "";
        XtUser xtUser = new XtUser();
        BeanUtils.copyProperties(registerDto,xtUser);

        xtUser.setUserid(IdUtil.getSnowflakeNextIdStr());
        xtUser.setUpassword(SecurityUtils.encryptPassword(registerDto.getUpassword()));
        xtUser.setAvatar(UserConstants.AVATAR_PATH);
        xtUser.setUregdate(new Date());
        xtUser.setUeflag("0");

        boolean regFlag = xtUserMapper.insertXtUser(xtUser) > 0;

        //初始化账户
        UserAccount account = new UserAccount();

        account.setId(IdUtil.getSnowflakeNextIdStr());
        account.setUserId(xtUser.getUserid());
        account.setCash(BigDecimal.ZERO);
        account.setLife(0L);
        account.setHealth(0L);
        account.setCardCoupon(0L);
        account.setDiscountCoupon(0L);
        userAccountMapper.insertUserAccount(account);


        if (!regFlag)
        {
            msg = "注册失败,请联系系统管理人员";
        }
        else
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(registerDto.getUsername(), Constants.REGISTER, MessageUtils.message("user.register.success")));
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
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
//                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }

        AppLoginUser loginUser = (AppLoginUser) authentication.getPrincipal();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    @Override
    public String appLogin(String username, String password) {
        /**
         * 登录成功后从上下文拿到登录时处理后的用户信息
         * 然后把他们利用jwt生成token,返回给前端
         *
         * */

        XtUser xtUser = xtUserMapper.selectXtUserByUserLoginName(username);

        if (StringUtils.isNull(xtUser)){
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(xtUser.getUeflag()))
        {
            //2是删除
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }

        //执行到这里说明登录已经成功了
        //这个信息是在provider里面设置存储的
        // 用户验证
        Authentication authentication = null;
        AppAuthenticationToken appAuthenticationToken = new AppAuthenticationToken(username, password);

        authentication = authenticationManager.authenticate(appAuthenticationToken);

        AppLoginUser appUser = (AppLoginUser)authentication.getPrincipal();

        return tokenService.createToken(appUser);

    }

    @Override
    public List<UserSelect> getSelectList() {
        List<XtUser> xtUsers = xtUserMapper.selectXtUserList(new XtUser());
        List<String> UserIdList = xtEmployeeMapper.selectUserIdList();
        return xtUsers.stream().filter(user -> !UserIdList.contains(user.getUserid())).map(user -> {
            UserSelect userSelect = new UserSelect();
            userSelect.setValue(user.getUserid());
            userSelect.setLabel(user.getUsername());
            return userSelect;
        }).collect(Collectors.toList());
    }

//    @Override
//    public List<XtUser> selectXtUserListByQuery(UserQuery userQuery) {
//
//        if (userQuery.getUserType().equals("0")) {
//            //普通用户(不存在用户信息;或者用户信息里没有商家id的)
//            XtUser xtUser = new XtUser();
//            BeanUtils.copyProperties(userQuery,xtUser);
//            List<XtUser> xtUsers = xtUserMapper.selectCommmonXtUserList(xtUser);
//
//        }else{
//            //商家用户
//            //用户信息存在商家id
//
//
//        }
//
//
//
//    }


}
