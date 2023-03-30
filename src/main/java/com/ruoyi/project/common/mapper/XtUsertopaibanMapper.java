package com.ruoyi.project.common.mapper;

import com.ruoyi.project.common.domain.XtUsertopaiban;
import com.ruoyi.project.common.domain.select.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与排班Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtUsertopaibanMapper
{
    /**
     * 查询用户与排班
     *
     * @param upaibanid 用户与排班主键
     * @return 用户与排班
     */
    public XtUsertopaiban selectXtUsertopaibanByUpaibanid(String upaibanid);

    /**
     * 查询用户与排班列表
     *
     * @param xtUsertopaiban 用户与排班
     * @return 用户与排班集合
     */
    public List<XtUsertopaiban> selectXtUsertopaibanList(XtUsertopaiban xtUsertopaiban);

    /**
     * 新增用户与排班
     *
     * @param xtUsertopaiban 用户与排班
     * @return 结果
     */
    public int insertXtUsertopaiban(XtUsertopaiban xtUsertopaiban);

    /**
     * 修改用户与排班
     *
     * @param xtUsertopaiban 用户与排班
     * @return 结果
     */
    public int updateXtUsertopaiban(XtUsertopaiban xtUsertopaiban);

    /**
     * 删除用户与排班
     *
     * @param upaibanid 用户与排班主键
     * @return 结果
     */
    public int deleteXtUsertopaibanByUpaibanid(String upaibanid);

    /**
     * 批量删除用户与排班
     *
     * @param upaibanids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtUsertopaibanByUpaibanids(@Param("upaibanids") String[] upaibanids);

    List<Select> selectEmployeeListByPaibanId(String upaibanid);

    int insertXtUsertopaibanBatch(String upaibanid, List<String> uemployeeids);
}
