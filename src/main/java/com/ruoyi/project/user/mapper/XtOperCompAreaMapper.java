package com.ruoyi.project.user.mapper;

import java.util.List;
import com.ruoyi.project.user.domain.XtOperCompArea;

/**
 * 代理商地区中间Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-14
 */
public interface XtOperCompAreaMapper 
{
    /**
     * 查询代理商地区中间
     * 
     * @param uoperCompid 代理商地区中间主键
     * @return 代理商地区中间
     */
    public XtOperCompArea selectXtOperCompAreaByUoperCompid(String uoperCompid);

    /**
     * 查询代理商地区中间列表
     * 
     * @param xtOperCompArea 代理商地区中间
     * @return 代理商地区中间集合
     */
    public List<XtOperCompArea> selectXtOperCompAreaList(XtOperCompArea xtOperCompArea);

    /**
     * 新增代理商地区中间
     * 
     * @param xtOperCompArea 代理商地区中间
     * @return 结果
     */
    public int insertXtOperCompArea(XtOperCompArea xtOperCompArea);

    /**
     * 修改代理商地区中间
     * 
     * @param xtOperCompArea 代理商地区中间
     * @return 结果
     */
    public int updateXtOperCompArea(XtOperCompArea xtOperCompArea);

    /**
     * 删除代理商地区中间
     * 
     * @param uoperCompid 代理商地区中间主键
     * @return 结果
     */
    public int deleteXtOperCompAreaByUoperCompid(String uoperCompid);

    /**
     * 批量删除代理商地区中间
     * 
     * @param uoperCompids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtOperCompAreaByUoperCompids(String[] uoperCompids);

    List<String> getAreaIdList();

    void insertXtOperCompAreaBatch(String uoperCompid, List<String> areaIdList);

}
