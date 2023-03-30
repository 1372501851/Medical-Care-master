package com.ruoyi.project.user.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.user.mapper.XtOperCompAreaMapper;
import com.ruoyi.project.user.domain.XtOperCompArea;
import com.ruoyi.project.user.service.IXtOperCompAreaService;

/**
 * 代理商地区中间Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
@Service
public class XtOperCompAreaServiceImpl implements IXtOperCompAreaService 
{
    @Autowired
    private XtOperCompAreaMapper xtOperCompAreaMapper;

    /**
     * 查询代理商地区中间
     * 
     * @param uoperCompid 代理商地区中间主键
     * @return 代理商地区中间
     */
    @Override
    public XtOperCompArea selectXtOperCompAreaByUoperCompid(String uoperCompid)
    {
        return xtOperCompAreaMapper.selectXtOperCompAreaByUoperCompid(uoperCompid);
    }

    /**
     * 查询代理商地区中间列表
     * 
     * @param xtOperCompArea 代理商地区中间
     * @return 代理商地区中间
     */
    @Override
    public List<XtOperCompArea> selectXtOperCompAreaList(XtOperCompArea xtOperCompArea)
    {
        return xtOperCompAreaMapper.selectXtOperCompAreaList(xtOperCompArea);
    }

    /**
     * 新增代理商地区中间
     * 
     * @param xtOperCompArea 代理商地区中间
     * @return 结果
     */
    @Override
    public int insertXtOperCompArea(XtOperCompArea xtOperCompArea)
    {
        return xtOperCompAreaMapper.insertXtOperCompArea(xtOperCompArea);
    }

    /**
     * 修改代理商地区中间
     * 
     * @param xtOperCompArea 代理商地区中间
     * @return 结果
     */
    @Override
    public int updateXtOperCompArea(XtOperCompArea xtOperCompArea)
    {
        return xtOperCompAreaMapper.updateXtOperCompArea(xtOperCompArea);
    }

    /**
     * 批量删除代理商地区中间
     * 
     * @param uoperCompids 需要删除的代理商地区中间主键
     * @return 结果
     */
    @Override
    public int deleteXtOperCompAreaByUoperCompids(String[] uoperCompids)
    {
        return xtOperCompAreaMapper.deleteXtOperCompAreaByUoperCompids(uoperCompids);
    }

    /**
     * 删除代理商地区中间信息
     * 
     * @param uoperCompid 代理商地区中间主键
     * @return 结果
     */
    @Override
    public int deleteXtOperCompAreaByUoperCompid(String uoperCompid)
    {
        return xtOperCompAreaMapper.deleteXtOperCompAreaByUoperCompid(uoperCompid);
    }

    @Override
    public void insertXtOperCompAreaBatch(String uoperCompid, List<String> areaIdList) {
        //判断地区是否已被设置
        List<String> allAreaId = xtOperCompAreaMapper.getAreaIdList();
        for (String areaId : areaIdList) {
            if (allAreaId.contains(areaId)) {
                //该地区已被设置
                throw new ServiceException("地区已设置代理商");
            }
        }
        //添加
        xtOperCompAreaMapper.insertXtOperCompAreaBatch(uoperCompid,areaIdList);
    }
}
