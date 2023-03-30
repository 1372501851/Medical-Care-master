package com.ruoyi.project.user.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.user.domain.UserInfo;
import com.ruoyi.project.user.domain.XtUser;
import com.ruoyi.project.user.mapper.UserInfoMapper;
import com.ruoyi.project.user.mapper.XtUserMapper;
import com.ruoyi.project.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private XtUserMapper userMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserInfo selectUserInfoById(String id)
    {
        return userInfoMapper.selectUserInfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param userInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo)
    {
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param userInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserInfo(UserInfo userInfo)
    {
        XtUser user = SecurityUtils.getAppLoginUser().getUser();
        userInfo.setUserId(user.getUserid());
        userInfo.setId(IdUtil.getSnowflakeNextIdStr());
        return userInfoMapper.insertUserInfo(userInfo);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param userInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfo userInfo)
    {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoByIds(String[] ids)
    {
        return userInfoMapper.deleteUserInfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoById(String id)
    {
        return userInfoMapper.deleteUserInfoById(id);
    }

    @Override
    public UserInfo getInfo() {
        String userid = SecurityUtils.getAppLoginUser().getUser().getUserid();
        XtUser user = userMapper.selectXtUserByUserid(userid);
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(userid);
        if (userInfo == null){
            //在这里需要插入一个新的用户信息
            UserInfo userInfo1 = new UserInfo();
            userInfo1.setId(IdUtil.getSnowflakeNextIdStr());
            userInfo1.setUserId(userid);
            userInfo1.setCurrentPosition("");
            userInfo1.setPushMessage("");
            userInfo1.setUserName(user.getUsername());
            userInfo1.setSex(user.getUsex());
            userInfo1.setAge("");
            userInfo1.setCity("");
            userInfo1.setCityId("");
            userInfo1.setDetailAddress("");
            userInfo1.setCommunity("");
            userInfo1.setHomeAddress("");
            userInfo1.setPhone("");
            userInfo1.setHospital("");
            userInfo1.setDept("");
            userInfo1.setBed("");
            userInfoMapper.insertUserInfo(userInfo1);
            return userInfo1;
        }
        return userInfo;
    }
}
