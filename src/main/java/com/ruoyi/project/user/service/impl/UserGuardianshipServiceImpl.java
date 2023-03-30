package com.ruoyi.project.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.task.upload.until.StringToURL;
import com.ruoyi.project.user.domain.UserGuardianship;
import com.ruoyi.project.user.domain.UserInfo;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.domain.vo.UserGuardianshipVo;
import com.ruoyi.project.user.mapper.UserGuardianshipMapper;
import com.ruoyi.project.user.mapper.UserInfoMapper;
import com.ruoyi.project.user.mapper.XtUserMapper;
import com.ruoyi.project.user.service.IUserGuardianshipService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-26
 */
@Service
public class UserGuardianshipServiceImpl implements IUserGuardianshipService
{
    @Autowired
    private UserGuardianshipMapper userGuardianshipMapper;

    @Autowired
    private XtUserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserGuardianship selectUserGuardianshipById(String id)
    {
        return userGuardianshipMapper.selectUserGuardianshipById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userGuardianship 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserGuardianshipVo> selectUserGuardianshipList(UserGuardianship userGuardianship)
    {
        List<UserGuardianshipVo> userGuardianshipVos = new ArrayList<>();
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        userGuardianship.setUserId(userid);
        List<UserGuardianship> userGuardianships = userGuardianshipMapper.selectUserGuardianshipList(userGuardianship);
        for (int i = 0; i < userGuardianships.size(); i++) {

            UserGuardianshipVo userGuardianshipVo = new UserGuardianshipVo();
            BeanUtils.copyProperties(userGuardianships.get(i),userGuardianshipVo);
            String guardianshipId = userGuardianships.get(i).getGuardianshipId();
            XtUser xtUser = userMapper.selectXtUserByUserid(guardianshipId);
            userGuardianshipVo.setGuardianshipName(xtUser.getUsername());

            String path = xtUser.getAvatar();
            if (path == null){
                userGuardianshipVo.setAvatar("");
            }else{
                List<String> strings = StringToURL.toURL(path);
                userGuardianshipVo.setAvatar(strings.get(0));
            }

            userGuardianshipVos.add(userGuardianshipVo);
        }



        return userGuardianshipVos;
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param userGuardianship 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserGuardianship(UserGuardianship userGuardianship)
    {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        userGuardianship.setId(IdUtil.getSnowflakeNextIdStr());
        String uloginname = SecurityUtils.getAppLoginUser().getUser().getUloginname();
        if (uloginname.equals(userGuardianship.getGuardianshipPhone())){
            throw new ServiceException("不能添加自己为监护人");
        }

        //判断该监护人是否已经被添加
        UserGuardianship userGuardianship1 = userGuardianshipMapper.selectUserGuardianshipByLoginName(userid, userGuardianship.getGuardianshipPhone());
        if (userGuardianship1 != null){
            throw new ServiceException("该用户已经是您的监护人");
        }


        XtUser xtUser = userMapper.selectXtUserByshouji(userGuardianship.getGuardianshipPhone());
        if (xtUser == null){
            throw new ServiceException("该用户不存在");
        }

        userGuardianship.setGuardianshipId(xtUser.getUserid());
        userGuardianship.setUserId(userid);
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(xtUser.getUserid());
        userGuardianship.setCreateTime(new Date());

        if (userInfo != null){
            userGuardianship.setInfoId(userInfo.getId());
        }else {
            userGuardianship.setInfoId("");
        }
        return userGuardianshipMapper.insertUserGuardianship(userGuardianship);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param userGuardianship 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserGuardianship(UserGuardianship userGuardianship)
    {
        return userGuardianshipMapper.updateUserGuardianship(userGuardianship);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserGuardianshipByIds(String[] ids)
    {
        return userGuardianshipMapper.deleteUserGuardianshipByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserGuardianshipById(String id)
    {
        return userGuardianshipMapper.deleteUserGuardianshipById(id);
    }
}
