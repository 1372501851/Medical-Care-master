package com.ruoyi.project.user.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.user.domain.query.XtOperCompQuery;
import com.ruoyi.project.user.domain.XtOperUser;
import com.ruoyi.project.user.domain.query.XtOperCompQueryList;
import com.ruoyi.project.user.service.IXtOperCompAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.user.mapper.XtOperCompMapper;
import com.ruoyi.project.user.domain.XtOperComp;
import com.ruoyi.project.user.service.IXtOperCompService;

/**
 * 代理商Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-01
 */
@Service
public class XtOperCompServiceImpl implements IXtOperCompService
{
    @Autowired
    private XtOperCompMapper xtOperCompMapper;

    @Autowired
    private IXtOperCompAreaService iXtOperCompAreaService;

    /**
     * 查询代理商
     *
     * @param uoperCompid 代理商主键
     * @return 代理商
     */
    @Override
    public XtOperComp selectXtOperCompByUoperCompid(String uoperCompid)
    {
        return xtOperCompMapper.selectXtOperCompByUoperCompid(uoperCompid);
    }

    /**
     * 查询代理商列表
     *
     * @param xtOperComp 代理商
     * @return 代理商
     */
    @Override
    public List<XtOperComp> selectXtOperCompList(XtOperComp xtOperComp)
    {
        String query = "";
        XtOperUser user = SecurityUtils.getWebLoginUser().getUser();
        if (xtOperComp.getAncestors()!= null && xtOperComp.getUoperCompid() != null){
            query = xtOperComp.getAncestors() + "," + xtOperComp.getUoperCompid();
        }else {

            //查找当前公司所创建的部门:当前公司(部门)的Id+祖级路劲+模糊查询
            XtOperComp xtOperComp1 = xtOperCompMapper.selectXtOperCompByUoperCompid(user.getUoperCompid());
            String ancestors = xtOperComp1.getAncestors();
            query = ancestors + "," + user.getUoperCompid();
            xtOperComp.setCreaterId(user.getUoperUserid());
        }


        XtOperCompQueryList xtOperCompQueryList = new XtOperCompQueryList();
        BeanUtils.copyProperties(xtOperComp,xtOperCompQueryList);
        xtOperCompQueryList.setQuery(query);

        return xtOperCompMapper.selectXtOperCompListByQueryList(xtOperCompQueryList);
    }

    /**
     * 新增代理商
     *
     * @param xtOperComp 代理商
     * @return 结果
     */
    @Override
    public int insertXtOperComp(XtOperComp xtOperComp)
    {
//        XtOperUser user = SecurityUtils.getWebLoginUser().getUser();

        //需要判断改商户号是否已经注册过
        XtOperComp xtOperComp2 = xtOperCompMapper.selectXtOperCompByUCustomerid(xtOperComp.getUoperCustomerid());
        if (xtOperComp2 != null){
            throw new ServiceException("该商户号已被注册!");
        }

        //商家id
        String uoperCompid = IdUtil.getSnowflakeNextIdStr();
        //添加代理商地区
        List<String> areaIdList = xtOperComp.getAreaIdList();
        iXtOperCompAreaService.insertXtOperCompAreaBatch(uoperCompid,areaIdList);

        xtOperComp.setUoperCompid(uoperCompid);

        //插入他的父级id(如果没有选择的话)
        //设置祖级列表(查询当前登陆者的祖级路劲+当前登陆者id,或者查询父级id的祖籍路径+父级id)
//        if (xtOperComp.getUparentid() == null){
//            //查询当前登陆者的商家id
//            String uoperCompid = user.getUoperCompid();
//            xtOperComp.setUparentid(uoperCompid);
//            XtOperComp xtOperComp1 = xtOperCompMapper.selectXtOperCompByUoperCompid(uoperCompid);
//            String ancestors = xtOperComp1.getAncestors();
//            xtOperComp.setAncestors(ancestors+","+uoperCompid);
//        }else {
//            XtOperComp xtOperComp1 = xtOperCompMapper.selectXtOperCompByUoperCompid(xtOperComp.getUparentid());
//            String ancestors = xtOperComp1.getAncestors();
//            xtOperComp.setAncestors(ancestors+","+ xtOperComp.getUparentid());
//        }
//
//        xtOperComp.setCreaterId(user.getUoperUserid());
//        xtOperComp.setCreateBy(user.getUname());


        return xtOperCompMapper.insertXtOperComp(xtOperComp);
    }

    /**
     * 修改代理商
     *
     * @param xtOperComp 代理商
     * @return 结果
     */
    @Override
    public int updateXtOperComp(XtOperComp xtOperComp)
    {
        return xtOperCompMapper.updateXtOperComp(xtOperComp);
    }

    /**
     * 批量删除代理商
     *
     * @param uoperCompids 需要删除的代理商主键
     * @return 结果
     */
    @Override
    public int deleteXtOperCompByUoperCompids(String[] uoperCompids)
    {
        return xtOperCompMapper.deleteXtOperCompByUoperCompids(uoperCompids);
    }

    /**
     * 删除代理商信息
     *
     * @param uoperCompid 代理商主键
     * @return 结果
     */
    @Override
    public int deleteXtOperCompByUoperCompid(String uoperCompid)
    {
        return xtOperCompMapper.deleteXtOperCompByUoperCompid(uoperCompid);
    }

    @Override
    public List<XtOperComp> selectXtOperCompListByType(XtOperCompQuery xtOperCompQuery) {

        //连表查询or查询两次(先查这个role的所有userId,然后根据userId查询用户)

        //连表查询
        List<XtOperComp> xtOperComps = xtOperCompMapper.selectXtOperCompListByType(xtOperCompQuery);
        return xtOperComps;

    }

    @Override
    public List<XtOperComp> selectCompByType(String areaId, String userType) {
        List<XtOperComp> xtOperComps = xtOperCompMapper.selectXtOperCompListByUserType(areaId, userType);
        return xtOperComps;
    }
}
