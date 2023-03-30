package com.ruoyi.project.common.service;

import com.ruoyi.project.common.domain.XtPaiban;
import com.ruoyi.project.common.domain.dto.PaibanAndEmployee;
import com.ruoyi.project.common.domain.vo.PaibanVo;

import java.util.List;

/**
 * 排班Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtPaibanService
{
    /**
     * 查询排班
     *
     * @param upaibanid 排班主键
     * @return 排班
     */
    public XtPaiban selectXtPaibanByUpaibanid(String upaibanid);

    /**
     * 查询排班列表
     *
     * @param xtPaiban 排班
     * @return 排班集合
     */
    public List<XtPaiban> selectXtPaibanList(XtPaiban xtPaiban);

    /**
     * 新增排班
     *
     * @param xtPaiban 排班
     * @return 结果
     */
    public int insertXtPaiban(XtPaiban xtPaiban);

    /**
     * 修改排班
     *
     * @param xtPaiban 排班
     * @return 结果
     */
    public int updateXtPaiban(XtPaiban xtPaiban);

    /**
     * 批量删除排班
     *
     * @param upaibanids 需要删除的排班主键集合
     * @return 结果
     */
    public int deleteXtPaibanByUpaibanids(String[] upaibanids);

    /**
     * 删除排班信息
     *
     * @param upaibanid 排班主键
     * @return 结果
     */
    public int deleteXtPaibanByUpaibanid(String upaibanid);

    List<PaibanVo> selectPaibanVoList(XtPaiban xtPaiban);

    int insertXtPaibanAndEmployee(PaibanAndEmployee paibanAndEmployee);

    int updatePaibanAndEmployee(PaibanAndEmployee paibanAndEmployee);

    PaibanVo selectXtPaibanAndEmployeeByUpaibanid(String upaibanid);

    int deleteXtPaibanAndEmployeeByUpaibanids(String[] upaibanids);
}
