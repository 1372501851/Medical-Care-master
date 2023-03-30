package com.ruoyi.project.remind.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.UserUtil;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.merchant.domain.XtEmployee;
import com.ruoyi.project.merchant.mapper.XtEmployeeMapper;
import com.ruoyi.project.remind.domain.Dto.RemindDTO;
import com.ruoyi.project.remind.domain.Remind;
import com.ruoyi.project.remind.mapper.RemindMapper;
import com.ruoyi.project.remind.service.IRemindService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-10
 */
@Service
public class RemindServiceImpl implements IRemindService
{
    @Autowired
    private RemindMapper remindMapper;

    @Autowired
    private XtEmployeeMapper xtEmployeeMapper;

    @Autowired
    private UserUtil userUtil;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Remind selectRemindById(String id)
    {
        return remindMapper.selectRemindById(id);
    }

    /**
     * 查询已提醒列表
     *
     * @param remind 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Remind> selectRemindList(Remind remind)
    {
        //查询当前登录用户的userid
        AppLoginUser appLoginUser = SecurityUtils.getAppLoginUser();
        if (appLoginUser != null) {
            //商家
            XtEmployee xtEmployeeByUserId = xtEmployeeMapper.selectXtEmployeeByUserId(appLoginUser.getUserId());
            remind.setUcompid(xtEmployeeByUserId.getUcompid());
        } else {
            remind.setUcompid(null);
        }
        return remindMapper.selectRemindList(remind);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param remindDTO 【请填写功能名称】
     * @return 结果
     */
    @Override
    public void insertRemind(RemindDTO remindDTO)
    {


        Remind remind = new Remind();
        remind.setId(IdUtil.getSnowflakeNextIdStr());
        BeanUtils.copyProperties(remindDTO,remind);
        remind.setUcompid(userUtil.getLoginCompId());
        remind.setUserId(userUtil.getLoginUserId());
        remindMapper.insertRemind(remind);


    }

    /**
     * 修改【请填写功能名称】
     *
     * @param remind 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateRemind(Remind remind)
    {
        return remindMapper.updateRemind(remind);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindByIds(String[] ids)
    {
        return remindMapper.deleteRemindByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteRemindById(String id)
    {
        return remindMapper.deleteRemindById(id);
    }
}
