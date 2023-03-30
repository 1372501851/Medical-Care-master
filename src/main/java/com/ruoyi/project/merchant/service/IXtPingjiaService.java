package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtPingjia;
import com.ruoyi.project.merchant.domain.dto.EvaluationDto;
import com.ruoyi.project.merchant.domain.vo.EvaluationVo;

import java.util.List;

/**
 * 任务评价Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtPingjiaService
{
    /**
     * 查询任务评价
     *
     * @param upingjiaid 任务评价主键
     * @return 任务评价
     */
    public EvaluationVo selectXtPingjiaByUpingjiaid(String upingjiaid);

    /**
     * 查询任务评价列表
     *
     * @param xtPingjia 任务评价
     * @return 任务评价集合
     */
    public List<XtPingjia> selectXtPingjiaList(XtPingjia xtPingjia);

    /**
     * 新增任务评价
     *
     * @param evaluationDto@return 结果
     */
    public int insertXtPingjia(EvaluationDto evaluationDto);

    /**
     * 修改任务评价
     *
     * @param xtPingjia 任务评价
     * @return 结果
     */
    public int updateXtPingjia(XtPingjia xtPingjia);

    /**
     * 批量删除任务评价
     *
     * @param upingjiaids 需要删除的任务评价主键集合
     * @return 结果
     */
    public int deleteXtPingjiaByUpingjiaids(String[] upingjiaids);

    /**
     * 删除任务评价信息
     *
     * @param upingjiaid 任务评价主键
     * @return 结果
     */
    public int deleteXtPingjiaByUpingjiaid(String upingjiaid);
}
