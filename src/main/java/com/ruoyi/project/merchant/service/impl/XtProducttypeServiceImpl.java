package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.merchant.domain.XtProducttype;
import com.ruoyi.project.merchant.domain.query.MallQuery;
import com.ruoyi.project.merchant.domain.tree.ProductTypeTreeSelect;
import com.ruoyi.project.merchant.domain.vo.ProductTypeVo;
import com.ruoyi.project.merchant.mapper.XtProductMapper;
import com.ruoyi.project.merchant.mapper.XtProducttypeMapper;
import com.ruoyi.project.merchant.service.IXtProducttypeService;
import com.ruoyi.project.user.domain.tree.DeptTreeSelect;
import com.ruoyi.project.user.domain.vo.DeptVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品类型（用在医疗护理系统，药店系统，其他系统）Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtProducttypeServiceImpl implements IXtProducttypeService
{
    @Autowired
    private XtProducttypeMapper xtProducttypeMapper;

    @Autowired
    private XtProductMapper xtProductMapper;

    /**
     * 查询产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param uproducttypeid 产品类型（用在医疗护理系统，药店系统，其他系统）主键
     * @return 产品类型（用在医疗护理系统，药店系统，其他系统）
     */
    @Override
    public XtProducttype selectXtProducttypeByUproducttypeid(String uproducttypeid)
    {
        return xtProducttypeMapper.selectXtProducttypeByUproducttypeid(uproducttypeid);
    }

    /**
     * 查询产品类型（用在医疗护理系统，药店系统，其他系统）列表
     *
     * @param xtProducttype 产品类型（用在医疗护理系统，药店系统，其他系统）
     * @return 产品类型（用在医疗护理系统，药店系统，其他系统）
     */
    @Override
    public List<XtProducttype> selectXtProducttypeList(XtProducttype xtProducttype)
    {
        //构建类型树
        return xtProducttypeMapper.selectXtProducttypeList(xtProducttype);
    }

    /**
     * 新增产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param xtProducttype 产品类型（用在医疗护理系统，药店系统，其他系统）
     * @return 结果
     */
    @Override
    public int insertXtProducttype(XtProducttype xtProducttype)
    {
        String uproducttypename = xtProducttype.getUproducttypename();
        XtProducttype xtProducttype1 = new XtProducttype();
        xtProducttype1.setUproducttypename(uproducttypename);
        List<ProductTypeTreeSelect> productTypeTreeSelects = selectTypeTreeSelect(xtProducttype1);
        if(!productTypeTreeSelects.isEmpty()){
            throw new ServiceException("名字重复");
        }


        xtProducttype.setUproducttypeid(IdUtil.getSnowflakeNextIdStr());
        return xtProducttypeMapper.insertXtProducttype(xtProducttype);
    }

    /**
     * 修改产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param
     * @return 结果
     */
    @Override
    public int updateXtProducttype(String name , String space , String id)
    {
        return xtProducttypeMapper.updateXtProducttype(name,space,id);
    }

    /**
     * 批量删除产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param uproducttypeids 需要删除的产品类型（用在医疗护理系统，药店系统，其他系统）主键
     * @return 结果
     */
    @Override
    public int deleteXtProducttypeByUproducttypeids(String[] uproducttypeids)
    {
        return xtProducttypeMapper.deleteXtProducttypeByUproducttypeids(uproducttypeids);
    }

    /**
     * 删除产品类型（用在医疗护理系统，药店系统，其他系统）信息
     *
     * @param uproducttypeid 产品类型（用在医疗护理系统，药店系统，其他系统）主键
     * @return 结果
     */
    @Override
    public int deleteXtProducttypeByUproducttypeid(String uproducttypeid)
    {
        return xtProducttypeMapper.deleteXtProducttypeByUproducttypeid(uproducttypeid);
    }

    @Override
    public List<ProductTypeTreeSelect> selectTypeTreeSelect(XtProducttype xtProducttype) {

        //构建商品类型选择树
        List<XtProducttype> xtProducttypes = xtProducttypeMapper.selectXtProducttypeList(xtProducttype);
        List<ProductTypeVo> productTypeVoList = new ArrayList<>();

        for (int i = 0; i < xtProducttypes.size(); i++) {
            ProductTypeVo productTypeVo = new ProductTypeVo();
            BeanUtils.copyProperties(xtProducttypes.get(i),productTypeVo);
            productTypeVoList.add(productTypeVo);
        }



        return buildProductTypeTreeSelect(productTypeVoList);

    }

    @Override
    public List<ProductTypeVo> buildTypeTree(XtProducttype xtProducttype) {
        List<XtProducttype> xtProducttypes = xtProducttypeMapper.selectXtProducttypeList(xtProducttype);
        List<ProductTypeVo> productTypeVoList = new ArrayList<>();

        for (int i = 0; i < xtProducttypes.size(); i++) {
            ProductTypeVo productTypeVo = new ProductTypeVo();
            BeanUtils.copyProperties(xtProducttypes.get(i),productTypeVo);
            productTypeVoList.add(productTypeVo);
        }

        return buildTypeTree(productTypeVoList);

    }

    @Override
    public List<ProductTypeTreeSelect> buildTypeTreeBycompId(MallQuery mallQuery) {

        String ucompid = mallQuery.getUcompid();
        //该商家的所有商品的类型
        List<String> productTypes = xtProductMapper.selectProductTypesBycompId(ucompid);
        List<XtProducttype> xtProducttypes = new ArrayList<>();
        for (int i = 0; i < productTypes.size(); i++) {
            XtProducttype xtProducttype = xtProducttypeMapper.selectXtProducttypeByUproducttypeid(productTypes.get(i));
            xtProducttypes.add(xtProducttype);
        }

        List<ProductTypeVo> productTypeVoList = new ArrayList<>();

        for (int i = 0; i < xtProducttypes.size(); i++) {
            ProductTypeVo productTypeVo = new ProductTypeVo();
            BeanUtils.copyProperties(xtProducttypes.get(i),productTypeVo);
            productTypeVoList.add(productTypeVo);
        }

        return buildProductTypeTreeSelect(productTypeVoList);
//        return buildTypeTree(productTypeVoList);
    }

    public List<ProductTypeVo> buildTypeTree(List<ProductTypeVo> types)
    {
        List<ProductTypeVo> productTypeVoList = buildProductTypeTree(types);
        return productTypeVoList;
    }
    public List<ProductTypeTreeSelect> buildProductTypeTreeSelect(List<ProductTypeVo> types)
    {
        List<ProductTypeVo> productTypeVoList = buildProductTypeTree(types);
        return productTypeVoList.stream().map(ProductTypeTreeSelect::new).collect(Collectors.toList());
    }

    public List<ProductTypeVo> buildProductTypeTree(List<ProductTypeVo> types)
    {
        List<ProductTypeVo> returnList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        for (int i = 0; i < types.size(); i++) {
            String typeId = types.get(i).getUproducttypeid();
            tempList.add(typeId);
        }
        for (ProductTypeVo type : types)
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


    private void recursionFn(List<ProductTypeVo> list, ProductTypeVo t)
    {
        // 得到子节点列表(可以)
        List<ProductTypeVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ProductTypeVo tChild : childList)
        {
            //判断子节点是否还有孩子
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }
    private List<ProductTypeVo> getChildList(List<ProductTypeVo> list, ProductTypeVo t)
    {

        //list:全部;t:其中一个
        List<ProductTypeVo> tlist = new ArrayList<>();
        Iterator<ProductTypeVo> it = list.iterator();
        while (it.hasNext())
        {
            ProductTypeVo n = (ProductTypeVo) it.next();
            //判断是否是顶级节点的条件(这个判断条件需要该,每次判断自己是否是顶级节点)
            //将每个子部门去和要判断的部门比较判断,如果属于子部门就加入
            if (StringUtils.isNotNull(n.getUparentid()) && n.getUparentid().equals(t.getUproducttypeid()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ProductTypeVo> list, ProductTypeVo t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }


}
