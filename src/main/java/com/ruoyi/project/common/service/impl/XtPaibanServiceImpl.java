package com.ruoyi.project.common.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.common.domain.XtPaiban;
import com.ruoyi.project.common.domain.XtUsertopaiban;
import com.ruoyi.project.common.domain.dto.PaibanAndEmployee;
import com.ruoyi.project.common.domain.select.Select;
import com.ruoyi.project.common.domain.vo.PaibanVo;
import com.ruoyi.project.common.mapper.XtPaibanMapper;
import com.ruoyi.project.common.service.IXtPaibanService;
import com.ruoyi.project.common.service.IXtUsertopaibanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 排班Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtPaibanServiceImpl implements IXtPaibanService
{
    @Autowired
    private XtPaibanMapper xtPaibanMapper;

    @Autowired
    private IXtUsertopaibanService xtUsertopaibanService;

    /**
     * 查询排班
     *
     * @param upaibanid 排班主键
     * @return 排班
     */
    @Override
    public XtPaiban selectXtPaibanByUpaibanid(String upaibanid)
    {
        return xtPaibanMapper.selectXtPaibanByUpaibanid(upaibanid);
    }

    /**
     * 查询排班列表
     *
     * @param xtPaiban 排班
     * @return 排班
     */
    @Override
    public List<XtPaiban> selectXtPaibanList(XtPaiban xtPaiban)
    {
        return xtPaibanMapper.selectXtPaibanList(xtPaiban);
    }

    /**
     * 新增排班
     *
     * @param xtPaiban 排班
     * @return 结果
     */
    @Override
    public int insertXtPaiban(XtPaiban xtPaiban)
    {
        xtPaiban.setUpaibanid(IdUtil.getSnowflakeNextIdStr());
        return xtPaibanMapper.insertXtPaiban(xtPaiban);
    }

    /**
     * 修改排班
     *
     * @param xtPaiban 排班
     * @return 结果
     */
    @Override
    public int updateXtPaiban(XtPaiban xtPaiban)
    {
        return xtPaibanMapper.updateXtPaiban(xtPaiban);
    }

    /**
     * 批量删除排班
     *
     * @param upaibanids 需要删除的排班主键
     * @return 结果
     */
    @Override
    public int deleteXtPaibanByUpaibanids(String[] upaibanids)
    {
        return xtPaibanMapper.deleteXtPaibanByUpaibanids(upaibanids);
    }

    /**
     * 删除排班信息
     *
     * @param upaibanid 排班主键
     * @return 结果
     */
    @Override
    public int deleteXtPaibanByUpaibanid(String upaibanid)
    {
        return xtPaibanMapper.deleteXtPaibanByUpaibanid(upaibanid);
    }

    @Override
    public List<PaibanVo> selectPaibanVoList(XtPaiban xtPaiban) {
        List<XtPaiban> xtPaibans = xtPaibanMapper.selectXtPaibanList(xtPaiban);
        List<PaibanVo> paibanVoList = new ArrayList<>();
        for (XtPaiban paiban : xtPaibans) {
            PaibanVo paibanVo = new PaibanVo();
            //每一个排班的用户查出来
            List<Select> selectList = xtUsertopaibanService.selectEmployeeListByPaibanId(paiban.getUpaibanid());
            BeanUtils.copyProperties(paiban,paibanVo);
            paibanVo.setEmployeeList(selectList);
            paibanVoList.add(paibanVo);
        }

        System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);System.out.println(paibanVoList);
        return paibanVoList;
    }

    @Override
    @Transactional
    public int insertXtPaibanAndEmployee(PaibanAndEmployee paibanAndEmployee) {
        String paibanId = IdUtil.getSnowflakeNextIdStr();
        XtPaiban xtPaiban = new XtPaiban();
        BeanUtils.copyProperties(paibanAndEmployee,xtPaiban);
        xtPaiban.setUpaibanid(paibanId);
        xtPaibanMapper.insertXtPaiban(xtPaiban);
        return xtUsertopaibanService.insertXtUsertopaibanBatch(paibanId,paibanAndEmployee.getUemployeeids());
    }

    @Override
    @Transactional
    public int updatePaibanAndEmployee(PaibanAndEmployee paibanAndEmployee) {
        XtPaiban xtPaiban = new XtPaiban();
        BeanUtils.copyProperties(paibanAndEmployee,xtPaiban);
        xtPaibanMapper.updateXtPaiban(xtPaiban);
        xtUsertopaibanService.deleteXtUsertopaibanByUpaibanid(paibanAndEmployee.getUpaibanid());
        return xtUsertopaibanService.insertXtUsertopaibanBatch(paibanAndEmployee.getUpaibanid(),paibanAndEmployee.getUemployeeids());
    }

    @Override
    public PaibanVo selectXtPaibanAndEmployeeByUpaibanid(String upaibanid) {
        XtPaiban xtPaiban = xtPaibanMapper.selectXtPaibanByUpaibanid(upaibanid);
        List<Select> selectList = xtUsertopaibanService.selectEmployeeListByPaibanId(upaibanid);
        PaibanVo paibanVo = new PaibanVo();
        BeanUtils.copyProperties(xtPaiban,paibanVo);
        paibanVo.setEmployeeList(selectList);
        List<String> employeeIds = new ArrayList<>();
        for (Select select : selectList) {
            employeeIds.add(select.getValue());
        }
        paibanVo.setUemployeeids(employeeIds);
        return paibanVo;
    }

    @Override
    @Transactional
    public int deleteXtPaibanAndEmployeeByUpaibanids(String[] upaibanids) {
        xtUsertopaibanService.deleteXtUsertopaibanByUpaibanids(upaibanids);
        return xtPaibanMapper.deleteXtPaibanByUpaibanids(upaibanids);
    }

}
