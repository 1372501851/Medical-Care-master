package com.ruoyi.project.user.mapper;

import java.util.List;
import com.ruoyi.project.user.domain.XtOperComp;
import com.ruoyi.project.user.domain.query.XtOperCompQuery;
import com.ruoyi.project.user.domain.query.XtOperCompQueryList;
import org.springframework.stereotype.Repository;

/**
 * 代理商Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-01
 */
@Repository
public interface XtOperCompMapper
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

    List<XtOperComp> selectXtOperCompListByType(XtOperCompQuery xtOperCompQuery);

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
     * 删除代理商
     *
     * @param uoperCompid 代理商主键
     * @return 结果
     */
    public int deleteXtOperCompByUoperCompid(String uoperCompid);

    /**
     * 批量删除代理商
     *
     * @param uoperCompids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtOperCompByUoperCompids(String[] uoperCompids);

    XtOperComp selectXtOperCompByUCustomerid(Long uoperCustomerid);



    List<XtOperComp> selectXtOperCompListByQueryList(XtOperCompQueryList xtOperCompQueryList);

    List<XtOperComp> selectXtOperCompListByUserType(String areaId, String userType);
}
