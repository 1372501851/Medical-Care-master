package com.ruoyi.project.global.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.global.domain.XtPosition;
import com.ruoyi.project.global.domain.select.PositionSelect;
import com.ruoyi.project.global.mapper.XtPositionMapper;
import com.ruoyi.project.global.service.IXtPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 职位 提供选择（传职位名称给用户信息）Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtPositionServiceImpl implements IXtPositionService
{
    @Autowired
    private XtPositionMapper xtPositionMapper;

    /**
     * 查询职位 提供选择（传职位名称给用户信息）
     *
     * @param upositionname 职位 提供选择（传职位名称给用户信息）主键
     * @return 职位 提供选择（传职位名称给用户信息）
     */
    @Override
    public XtPosition selectXtPositionByUpositionname(String upositionname)
    {
        return xtPositionMapper.selectXtPositionByUpositionname(upositionname);
    }

    /**
     * 查询职位 提供选择（传职位名称给用户信息）列表
     *
     * @param xtPosition 职位 提供选择（传职位名称给用户信息）
     * @return 职位 提供选择（传职位名称给用户信息）
     */
    @Override
    public List<XtPosition> selectXtPositionList(XtPosition xtPosition)
    {
        return xtPositionMapper.selectXtPositionList(xtPosition);
    }

    /**
     * 新增职位 提供选择（传职位名称给用户信息）
     *
     * @param xtPosition 职位 提供选择（传职位名称给用户信息）
     * @return 结果
     */
    @Override
    public int insertXtPosition(XtPosition xtPosition)
    {
        //职位不能重复
        xtPosition.setUpositionid(IdUtil.getSnowflakeNextIdStr());
        return xtPositionMapper.insertXtPosition(xtPosition);
    }

    /**
     * 修改职位 提供选择（传职位名称给用户信息）
     *
     * @param xtPosition 职位 提供选择（传职位名称给用户信息）
     * @return 结果
     */
    @Override
    public int updateXtPosition(XtPosition xtPosition)
    {
        return xtPositionMapper.updateXtPosition(xtPosition);
    }

    /**
     * 批量删除职位 提供选择（传职位名称给用户信息）
     *
     * @param upositionnames 需要删除的职位 提供选择（传职位名称给用户信息）主键
     * @return 结果
     */
    @Override
    public int deleteXtPositionByUpositionIds(String[] postIds)
    {
        return xtPositionMapper.deleteXtPositionByUpositionIds(postIds);
    }

    /**
     * 删除职位 提供选择（传职位名称给用户信息）信息
     *
     * @param upositionname 职位 提供选择（传职位名称给用户信息）主键
     * @return 结果
     */
    @Override
    public int deleteXtPositionByUpositionname(String upositionname)
    {
        return xtPositionMapper.deleteXtPositionByUpositionname(upositionname);
    }

    @Override
    public XtPosition selectXtPositionByUpositionId(String upositionid) {


        XtPosition xtPosition = xtPositionMapper.selectXtPositionByUpositionId(upositionid);
        return xtPosition;
    }

    @Override
    public List<PositionSelect> getSelectList() {
        List<XtPosition> xtPositions = xtPositionMapper.selectXtPositionList(new XtPosition());
        List<PositionSelect> positionSelects = new ArrayList<>();
        for (XtPosition xtPosition : xtPositions) {
            PositionSelect positionSelect = new PositionSelect();
            positionSelect.setValue(xtPosition.getUpositionid());
            positionSelect.setLabel(xtPosition.getUpositionname());
            positionSelects.add(positionSelect);
        }
        return positionSelects;
    }
}
