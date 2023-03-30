package com.ruoyi.project.area.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.area.domain.PtArea;
import com.ruoyi.project.area.domain.tree.AreaTreeSelect;
import com.ruoyi.project.area.domain.vo.PtAreaVo;
import com.ruoyi.project.area.mapper.PtAreaMapper;
import com.ruoyi.project.area.service.IPtAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 地区Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-11
 */
@Service
public class PtAreaServiceImpl implements IPtAreaService
{
    @Autowired
    private PtAreaMapper ptAreaMapper;

    /**
     * 查询地区
     * 
     * @param uareaid 地区主键
     * @return 地区
     */
    @Override
    public PtArea selectPtAreaByUareaid(String uareaid)
    {
        return ptAreaMapper.selectPtAreaByUareaid(uareaid);
    }

    /**
     * 查询地区列表
     * 
     * @param ptArea 地区
     * @return 地区
     */
    @Override
    public List<PtArea> selectPtAreaList(PtArea ptArea)
    {
        return ptAreaMapper.selectPtAreaList(ptArea);
    }

    /**
     * 新增地区
     * 
     * @param ptArea 地区
     * @return 结果
     */
    @Override
    public int insertPtArea(PtArea ptArea)
    {
        String uparentid = ptArea.getUparentid();
        PtArea parentArea = ptAreaMapper.selectPtAreaByUareaid(uparentid);
        ptArea.setUlevel(parentArea.getUlevel() + 1);
        ptArea.setUareaid(IdUtil.getSnowflakeNextIdStr());
        return ptAreaMapper.insertPtArea(ptArea);
    }

    /**
     * 修改地区
     * 
     * @param ptArea 地区
     * @return 结果
     */
    @Override
    public int updatePtArea(PtArea ptArea)
    {
        return ptAreaMapper.updatePtArea(ptArea);
    }

    /**
     * 批量删除地区
     * 
     * @param uareaids 需要删除的地区主键
     * @return 结果
     */
    @Override
    public int deletePtAreaByUareaids(String[] uareaids)
    {
        return ptAreaMapper.deletePtAreaByUareaids(uareaids);
    }

    /**
     * 删除地区信息
     * 
     * @param uareaid 地区主键
     * @return 结果
     */
    @Override
    public int deletePtAreaByUareaid(String uareaid)
    {
        return ptAreaMapper.deletePtAreaByUareaid(uareaid);
    }

    @Override
    public List<PtAreaVo> getPtAreaTree(PtArea ptArea) {
        List<PtArea> ptAreaList = ptAreaMapper.selectPtAreaList(ptArea);
        startPage();
        return buildAreaTree(ptAreaList);
    }

    @Override
    public List<AreaTreeSelect> getAreaSelectTree(PtArea ptArea) {
        List<PtArea> ptAreaList = ptAreaMapper.selectPtAreaList(ptArea);
        List<PtArea> areaList = ptAreaList.stream().filter(area ->
            area.getUlevel() == 1 || area.getUlevel() == 2 || area.getUlevel() == 3
        ).collect(Collectors.toList());
        return buildAreaTreeSelect(areaList);
    }

    private List<PtAreaVo> buildAreaTree(List<PtArea> ptAreaList) {
        return buildTree(ptAreaList);
    }

    public List<AreaTreeSelect> buildAreaTreeSelect(List<PtArea> ptAreaList) {
        List<PtAreaVo> ptAreaVos = buildTree(ptAreaList);
        return ptAreaVos.stream().map(AreaTreeSelect::new).collect(Collectors.toList());
    }

    //构建地区树的核心代码
    public List<PtAreaVo> buildTree(List<PtArea> ptAreaList) {
        List<PtArea> oneList = new ArrayList<>();
        List<PtArea> twoList = new ArrayList<>();
        List<PtArea> threeList = new ArrayList<>();
        for (PtArea ptArea : ptAreaList) {
            if (ptArea.getUlevel() == 1) {
                oneList.add(ptArea);
            } else if (ptArea.getUlevel() == 2) {
                twoList.add(ptArea);
            } else if (ptArea.getUlevel() == 3) {
                threeList.add(ptArea);
            }
        }

        List<PtAreaVo> returnList = new ArrayList<>();
        for (PtArea ptArea : oneList) {
            PtAreaVo ptAreaVo = new PtAreaVo();
            BeanUtils.copyProperties(ptArea,ptAreaVo);
            ptAreaVo.setChildren(getChildren(ptAreaVo,twoList,threeList));
            returnList.add(ptAreaVo);
        }
        return returnList;
    }

    private List<PtAreaVo> getChildren(PtAreaVo ptAreaVo,List<PtArea> twoList,List<PtArea> threeList){
        return twoList.stream().filter(ptArea ->
                ptArea.getUparentid().equals(ptAreaVo.getUareaid())
        ).map(ptArea -> {
            PtAreaVo ptAreaVo1 = new PtAreaVo();
            BeanUtils.copyProperties(ptArea,ptAreaVo1);
            ptAreaVo1.setChildren(getChildren(ptAreaVo1,threeList));
            return ptAreaVo1;
        }).collect(Collectors.toList());
    }

    private List<PtAreaVo> getChildren(PtAreaVo ptAreaVo,List<PtArea> threeList){
        return threeList.stream().filter(ptArea ->
                ptArea.getUparentid().equals(ptAreaVo.getUareaid())
        ).map(ptArea -> {
            PtAreaVo ptAreaVo1 = new PtAreaVo();
            BeanUtils.copyProperties(ptArea,ptAreaVo1);
            return ptAreaVo1;
        }).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<PtAreaVo> ptAreaVoList,List<PtAreaVo> ptAreaVos) {
        for (PtAreaVo tChild : ptAreaVos) {
            //判断子节点是否还有孩子
            List<PtAreaVo> AreaVos = hasChild(ptAreaVoList, tChild);
            if (AreaVos != null) {
                tChild.setChildren(AreaVos);
                for (PtAreaVo areaVo : ptAreaVos) {
                    ptAreaVoList.remove(areaVo);
                }
                recursionFn(ptAreaVoList,AreaVos);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<PtAreaVo> getChildList(List<PtAreaVo> ptAreaVoList, PtAreaVo ptAreaVo) {
        //list:全部;t:其中一个
        List<PtAreaVo> tlist = new ArrayList<>();
        for (PtAreaVo n : ptAreaVoList) {
            //判断是否是顶级节点的条件(这个判断条件需要该,每次判断自己是否是顶级节点)
            //将每个子部门去和要判断的部门比较判断,如果属于子部门就加入
            if (StringUtils.isNotNull(n.getUparentid()) && n.getUparentid().equals(ptAreaVo.getUareaid())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private List<PtAreaVo> hasChild(List<PtAreaVo> ptAreaVoList, PtAreaVo ptAreaVo) {
        List<PtAreaVo> childList = getChildList(ptAreaVoList, ptAreaVo);
        if (childList.size() > 0) {
            return childList;
        }else {
            return null;
        }
    }

}
