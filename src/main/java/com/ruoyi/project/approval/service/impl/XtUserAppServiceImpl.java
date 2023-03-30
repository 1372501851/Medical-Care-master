package com.ruoyi.project.approval.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.approval.domain.XtUserApp;
import com.ruoyi.project.approval.mapper.XtUserAppMapper;
import com.ruoyi.project.approval.service.IXtUserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理员申请Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtUserAppServiceImpl implements IXtUserAppService
{
    @Autowired
    private XtUserAppMapper xtUserAppMapper;

    /**
     * 查询用户管理员申请
     *
     * @param userAppid 用户管理员申请主键
     * @return 用户管理员申请
     */
    @Override
    public XtUserApp selectXtUserAppByUserAppid(String userAppid)
    {
        return xtUserAppMapper.selectXtUserAppByUserAppid(userAppid);
    }

    /**
     * 查询用户管理员申请列表
     *
     * @param xtUserApp 用户管理员申请
     * @return 用户管理员申请
     */
    @Override
    public List<XtUserApp> selectXtUserAppList(XtUserApp xtUserApp)
    {
        return xtUserAppMapper.selectXtUserAppList(xtUserApp);
    }

    /**
     * 新增用户管理员申请
     *
     * @param xtUserApp 用户管理员申请
     * @return 结果
     */
    @Override
    public int insertXtUserApp(XtUserApp xtUserApp)
    {
        xtUserApp.setUserAppid(IdUtil.getSnowflakeNextIdStr());
        return xtUserAppMapper.insertXtUserApp(xtUserApp);
    }

    /**
     * 修改用户管理员申请
     *
     * @param xtUserApp 用户管理员申请
     * @return 结果
     */
    @Override
    public int updateXtUserApp(XtUserApp xtUserApp)
    {
        return xtUserAppMapper.updateXtUserApp(xtUserApp);
    }

    /**
     * 批量删除用户管理员申请
     *
     * @param userAppids 需要删除的用户管理员申请主键
     * @return 结果
     */
    @Override
    public int deleteXtUserAppByUserAppids(String[] userAppids)
    {
        return xtUserAppMapper.deleteXtUserAppByUserAppids(userAppids);
    }

    /**
     * 删除用户管理员申请信息
     *
     * @param userAppid 用户管理员申请主键
     * @return 结果
     */
    @Override
    public int deleteXtUserAppByUserAppid(String userAppid)
    {
        return xtUserAppMapper.deleteXtUserAppByUserAppid(userAppid);
    }
}
