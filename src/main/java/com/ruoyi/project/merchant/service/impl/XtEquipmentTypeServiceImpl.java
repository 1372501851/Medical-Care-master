package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.merchant.domain.XtEquipmentType;
import com.ruoyi.project.merchant.domain.tree.EquipmentTypeTreeSelect;
import com.ruoyi.project.merchant.domain.vo.EquipmentAppTypeVo;
import com.ruoyi.project.merchant.domain.vo.EquipmentTypeVo;
import com.ruoyi.project.merchant.mapper.XtEquipmentTypeMapper;
import com.ruoyi.project.merchant.service.IXtEquipmentTypeService;
import com.ruoyi.project.task.upload.until.StringToURL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 设备类型Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtEquipmentTypeServiceImpl implements IXtEquipmentTypeService
{
    @Autowired
    private XtEquipmentTypeMapper xtEquipmentTypeMapper;

    /**
     * 查询设备类型
     *
     * @param uequipmentTypeid 设备类型主键
     * @return 设备类型
     */
    @Override
    public XtEquipmentType selectXtEquipmentTypeByUequipmentTypeid(String uequipmentTypeid)
    {
        return xtEquipmentTypeMapper.selectXtEquipmentTypeByUequipmentTypeid(uequipmentTypeid);
    }

    /**
     * 查询设备类型列表
     *
     * @param xtEquipmentType 设备类型
     * @return 设备类型
     */
    @Override
    public List<XtEquipmentType> selectXtEquipmentTypeList(XtEquipmentType xtEquipmentType)
    {
        return xtEquipmentTypeMapper.selectXtEquipmentTypeList(xtEquipmentType);
    }

    /**
     * 新增设备类型
     *
     * @param xtEquipmentType 设备类型
     * @return 结果
     */
    @Override
    public int insertXtEquipmentType(XtEquipmentType xtEquipmentType)
    {
        //设备名称不能重复
        xtEquipmentType.setUequipmentTypeid(IdUtil.getSnowflakeNextIdStr());
        return xtEquipmentTypeMapper.insertXtEquipmentType(xtEquipmentType);
    }

    /**
     * 修改设备类型
     *
     * @param xtEquipmentType 设备类型
     * @return 结果
     */
    @Override
    public int updateXtEquipmentType(XtEquipmentType xtEquipmentType)
    {
        //设备名称不能重复
        return xtEquipmentTypeMapper.updateXtEquipmentType(xtEquipmentType);
    }

    /**
     * 批量删除设备类型
     *
     * @param uequipmentTypeids 需要删除的设备类型主键
     * @return 结果
     */
    @Override
    public int deleteXtEquipmentTypeByUequipmentTypeids(String[] uequipmentTypeids)
    {
        //类型下还有子类型不能删除
        return xtEquipmentTypeMapper.deleteXtEquipmentTypeByUequipmentTypeids(uequipmentTypeids);
    }

    /**
     * 删除设备类型信息
     *
     * @param uequipmentTypeid 设备类型主键
     * @return 结果
     */
    @Override
    public int deleteXtEquipmentTypeByUequipmentTypeid(String uequipmentTypeid)
    {
        return xtEquipmentTypeMapper.deleteXtEquipmentTypeByUequipmentTypeid(uequipmentTypeid);
    }

    @Override
    public List<XtEquipmentType> selectTypeTreeSelect(XtEquipmentType xtEquipmentType) {

        //构建类型选择树
        List<XtEquipmentType> xtEquipmentTypes = xtEquipmentTypeMapper.selectXtEquipmentTypeList(xtEquipmentType);
        List<EquipmentTypeVo> equipmentTypeVoList = new ArrayList<>();

        for (int i = 0; i < xtEquipmentTypes.size(); i++) {
            EquipmentTypeVo equipmentTypeVo = new EquipmentTypeVo();
            BeanUtils.copyProperties(xtEquipmentTypes.get(i),equipmentTypeVo);
            equipmentTypeVoList.add(equipmentTypeVo);
        }

        return xtEquipmentTypes;
    }

    @Override
    public List<EquipmentTypeVo> buildTypeTree(XtEquipmentType xtEquipmentType) {

        List<XtEquipmentType> xtEquipmentTypes = xtEquipmentTypeMapper.selectXtEquipmentTypeList(xtEquipmentType);
        List<EquipmentTypeVo> equipmentTypeVoList = new ArrayList<>();

        for (int i = 0; i < xtEquipmentTypes.size(); i++) {
            EquipmentTypeVo equipmentTypeVo = new EquipmentTypeVo();
            BeanUtils.copyProperties(xtEquipmentTypes.get(i),equipmentTypeVo);
            equipmentTypeVoList.add(equipmentTypeVo);
        }

        return buildEquipmentTypeTree(equipmentTypeVoList);

    }

    @Override
    public List<EquipmentAppTypeVo> selectAppEquipmentTypes() {


        List<XtEquipmentType> xtEquipmentTypes = xtEquipmentTypeMapper.selectXtEquipmentTypeListAll();

        List<EquipmentAppTypeVo> equipmentAppTypeVos = new ArrayList<>();

        for (int i = 0; i < xtEquipmentTypes.size(); i++) {
            EquipmentAppTypeVo equipmentAppTypeVo = new EquipmentAppTypeVo();
            BeanUtils.copyProperties(xtEquipmentTypes.get(i),equipmentAppTypeVo);
//            List<String> url = StringToURL.toURL(xtEquipmentTypes.get(i).getPic());
//            equipmentAppTypeVo.setPic(url.get(0));
            equipmentAppTypeVos.add(equipmentAppTypeVo);
        }
        return equipmentAppTypeVos;


    }

    public List<EquipmentTypeVo> buildEquipmentTypeTree(List<EquipmentTypeVo> types)
    {
        List<EquipmentTypeVo> typeVoList = buildTypeTree(types);
        return typeVoList;
    }

    public List<EquipmentTypeTreeSelect> buildTypeTreeSelect(List<EquipmentTypeVo> types)
    {
        List<EquipmentTypeVo> typeVoList = buildTypeTree(types);
        return typeVoList.stream().map(EquipmentTypeTreeSelect::new).collect(Collectors.toList());
    }

    public List<EquipmentTypeVo> buildTypeTree(List<EquipmentTypeVo> types)
    {
        List<EquipmentTypeVo> returnList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        for (int i = 0; i < types.size(); i++) {
            String typeId = types.get(i).getUequipmentTypeid();
            tempList.add(typeId);
        }
        for (EquipmentTypeVo type : types)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(type.getUparentid()))
            {
                recursionFn(types, type);
                returnList.add(type);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = types;
        }
        return returnList;
    }
    private void recursionFn(List<EquipmentTypeVo> list, EquipmentTypeVo t)
    {
        // 得到子节点列表(可以)
        List<EquipmentTypeVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (EquipmentTypeVo tChild : childList)
        {
            //判断子节点是否还有孩子
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }


    private List<EquipmentTypeVo> getChildList(List<EquipmentTypeVo> list, EquipmentTypeVo t)
    {

        //list:全部;t:其中一个
        List<EquipmentTypeVo> tlist = new ArrayList<>();
        Iterator<EquipmentTypeVo> it = list.iterator();
        while (it.hasNext())
        {
            EquipmentTypeVo n = (EquipmentTypeVo) it.next();
            //判断是否是顶级节点的条件(这个判断条件需要该,每次判断自己是否是顶级节点)
            //将每个子部门去和要判断的部门比较判断,如果属于子部门就加入
            if (StringUtils.isNotNull(n.getUparentid()) && n.getUparentid().equals(t.getUequipmentTypeid()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<EquipmentTypeVo> list, EquipmentTypeVo t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}
