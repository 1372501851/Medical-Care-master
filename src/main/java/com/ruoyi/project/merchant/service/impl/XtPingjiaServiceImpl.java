package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.merchant.domain.XtPingjia;
import com.ruoyi.project.merchant.domain.dto.EvaluationDto;
import com.ruoyi.project.merchant.domain.vo.EvaluationVo;
import com.ruoyi.project.merchant.mapper.XtPingjiaMapper;
import com.ruoyi.project.merchant.service.IXtPingjiaService;
import com.ruoyi.project.task.upload.until.StringToURL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务评价Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtPingjiaServiceImpl implements IXtPingjiaService
{
    @Autowired
    private XtPingjiaMapper xtPingjiaMapper;

    /**
     * 查询任务评价
     *
     * @param upingjiaid 任务评价主键
     * @return 任务评价
     */
    @Override
    public EvaluationVo selectXtPingjiaByUpingjiaid(String upingjiaid)
    {
        EvaluationVo evaluationVo = new EvaluationVo();
        XtPingjia xtPingjia = xtPingjiaMapper.selectXtPingjiaByUpingjiaid(upingjiaid);
        BeanUtils.copyProperties(xtPingjia,evaluationVo);
        List<String> urls = StringToURL.toURL(xtPingjia.getUpic());
        evaluationVo.setUpic(urls);
        return evaluationVo;
    }

    /**
     * 查询任务评价列表
     *
     * @param xtPingjia 任务评价
     * @return 任务评价
     */
    @Override
    public List<XtPingjia> selectXtPingjiaList(XtPingjia xtPingjia)
    {
        return xtPingjiaMapper.selectXtPingjiaList(xtPingjia);
    }

    /**
     * 新增任务评价
     *
     * @param evaluationDto@return 结果
     */
    @Override
    public int insertXtPingjia(EvaluationDto evaluationDto)
    {
        XtPingjia xtPingjia = new XtPingjia();
        BeanUtils.copyProperties(evaluationDto,xtPingjia);
        List<String> upics = evaluationDto.getUpic();
        String collect = upics.stream().collect(Collectors.joining(","));
        xtPingjia.setUpic(collect);
        xtPingjia.setUpingjiaid(IdUtil.getSnowflakeNextIdStr());
        return xtPingjiaMapper.insertXtPingjia(xtPingjia);
    }

    /**
     * 修改任务评价
     *
     * @param xtPingjia 任务评价
     * @return 结果
     */
    @Override
    public int updateXtPingjia(XtPingjia xtPingjia)
    {
        return xtPingjiaMapper.updateXtPingjia(xtPingjia);
    }

    /**
     * 批量删除任务评价
     *
     * @param upingjiaids 需要删除的任务评价主键
     * @return 结果
     */
    @Override
    public int deleteXtPingjiaByUpingjiaids(String[] upingjiaids)
    {
        return xtPingjiaMapper.deleteXtPingjiaByUpingjiaids(upingjiaids);
    }

    /**
     * 删除任务评价信息
     *
     * @param upingjiaid 任务评价主键
     * @return 结果
     */
    @Override
    public int deleteXtPingjiaByUpingjiaid(String upingjiaid)
    {
        return xtPingjiaMapper.deleteXtPingjiaByUpingjiaid(upingjiaid);
    }
}
