package com.ruoyi.project.user.service;

import java.util.List;
import com.ruoyi.project.user.domain.XtOperComp;
import com.ruoyi.project.user.domain.query.XtOperCompQuery;

/**
 * 代理商Service接口
 *
 * @author ruoyi
 * @date 2022-11-01
 */
public interface IXtOperCompService
{
    /**
     * 查询代理商
     *
     * @param uoperCompid 代理商主键
     * @return 代理商
     */
    public XtOperComp selectXtOperCompByUoperCompid(String uoperCompid);

    /**
     * 查询代理商列表
     *
     * @param xtOperComp 代理商
     * @return 代理商集合
     */
    public List<XtOperComp> selectXtOperCompList(XtOperComp xtOperComp);

    /**
     * 新增代理商
     *
     * @param xtOperComp 代理商
     * @return 结果
     */
    public int insertXtOperComp(XtOperComp xtOperComp);

    /**
     * 修改代理商
     *
     * @param xtOperComp 代理商
     * @return 结果
     */

    public int updateXtOperComp(XtOperComp xtOperComp);

    /**
     * 批量删除代理商
     *
     * @param uoperCompids 需要删除的代理商主键集合
     * @return 结果
     */
    public int deleteXtOperCompByUoperCompids(String[] uoperCompids);

    /**
     * 删除代理商信息
     *
     * @param uoperCompid 代理商主键
     * @return 结果
     */
    public int deleteXtOperCompByUoperCompid(String uoperCompid);

    List<XtOperComp> selectXtOperCompListByType(XtOperCompQuery xtOperCompQuery);

    List<XtOperComp> selectCompByType(String areaId, String userType);
}
