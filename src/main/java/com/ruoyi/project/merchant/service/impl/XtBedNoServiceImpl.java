package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.merchant.domain.XtBedNo;
import com.ruoyi.project.merchant.mapper.XtBedNoMapper;
import com.ruoyi.project.merchant.service.IXtBedNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 床位号Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtBedNoServiceImpl implements IXtBedNoService
{
    @Autowired
    private XtBedNoMapper xtBedNoMapper;

    /**
     * 查询床位号
     *
     * @param ubednoid 床位号主键
     * @return 床位号
     */
    @Override
    public XtBedNo selectXtBedNoByUbednoid(String ubednoid)
    {
        return xtBedNoMapper.selectXtBedNoByUbednoid(ubednoid);
    }

    /**
     * 查询床位号列表
     *
     * @param xtBedNo 床位号
     * @return 床位号
     */
    @Override
    public List<XtBedNo> selectXtBedNoList(XtBedNo xtBedNo)
    {
        return xtBedNoMapper.selectXtBedNoList(xtBedNo);
    }

    /**
     * 新增床位号
     *
     * @param xtBedNo 床位号
     * @return 结果
     */
    @Override
    public int insertXtBedNo(XtBedNo xtBedNo)
    {
        //判断床位编号是否重复
        int count = xtBedNoMapper.selectXtBedNoByRoomIdAndBedId(xtBedNo.getUroomid(), xtBedNo.getUbedno());
        if (count > 0){
            throw new ServiceException("该病房床位编号已存在");
        }

        xtBedNo.setUbednoid(IdUtil.getSnowflakeNextIdStr());

        return xtBedNoMapper.insertXtBedNo(xtBedNo);
    }

    /**
     * 修改床位号
     *
     * @param xtBedNo 床位号
     * @return 结果
     */
    @Override
    public int updateXtBedNo(XtBedNo xtBedNo)
    {
        return xtBedNoMapper.updateXtBedNo(xtBedNo);
    }

    /**
     * 批量删除床位号
     *
     * @param ubednoids 需要删除的床位号主键
     * @return 结果
     */
    @Override
    public int deleteXtBedNoByUbednoids(String[] ubednoids)
    {
        int count = 0;
        //删除病床,需要判断是否有人在使用(直接写sql)
        for (int i = 0; i < ubednoids.length; i++) {
            if(hasUseByBedId(ubednoids[i])){
                throw new ServiceException("该床位正在使用,不能删除");
            }
            xtBedNoMapper.deleteXtBedNoByUbednoid(ubednoids[i]);
            count ++;
        }

        return count;
    }

    private boolean hasUseByBedId(String bedId)
    {
        int result = xtBedNoMapper.hasUseByBedId(bedId);
        return result > 0;
    }

    /**
     * 删除床位号信息
     *
     * @param ubednoid 床位号主键
     * @return 结果
     */
    @Override
    public int deleteXtBedNoByUbednoid(String ubednoid)
    {
        return xtBedNoMapper.deleteXtBedNoByUbednoid(ubednoid);
    }
}
