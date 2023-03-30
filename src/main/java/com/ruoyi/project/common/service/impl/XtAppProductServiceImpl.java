package com.ruoyi.project.common.service.impl;

import com.ruoyi.project.common.domain.XtAppProduct;
import com.ruoyi.project.common.mapper.XtAppProductMapper;
import com.ruoyi.project.common.service.IXtAppProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请产品列(也可以是报销项目)Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Service
public class XtAppProductServiceImpl implements IXtAppProductService
{
    @Autowired
    private XtAppProductMapper xtAppProductMapper;

    /**
     * 查询申请产品列(也可以是报销项目)
     *
     * @param uappProductid 申请产品列(也可以是报销项目)主键
     * @return 申请产品列(也可以是报销项目)
     */
    @Override
    public XtAppProduct selectXtAppProductByUappProductid(String uappProductid)
    {
        return xtAppProductMapper.selectXtAppProductByUappProductid(uappProductid);
    }

    /**
     * 查询申请产品列(也可以是报销项目)列表
     *
     * @param xtAppProduct 申请产品列(也可以是报销项目)
     * @return 申请产品列(也可以是报销项目)
     */
    @Override
    public List<XtAppProduct> selectXtAppProductList(XtAppProduct xtAppProduct)
    {
        return xtAppProductMapper.selectXtAppProductList(xtAppProduct);
    }

    /**
     * 新增申请产品列(也可以是报销项目)
     *
     * @param xtAppProduct 申请产品列(也可以是报销项目)
     * @return 结果
     */
    @Override
    public int insertXtAppProduct(XtAppProduct xtAppProduct)
    {
        return xtAppProductMapper.insertXtAppProduct(xtAppProduct);
    }

    /**
     * 修改申请产品列(也可以是报销项目)
     *
     * @param xtAppProduct 申请产品列(也可以是报销项目)
     * @return 结果
     */
    @Override
    public int updateXtAppProduct(XtAppProduct xtAppProduct)
    {
        return xtAppProductMapper.updateXtAppProduct(xtAppProduct);
    }

    /**
     * 批量删除申请产品列(也可以是报销项目)
     *
     * @param uappProductids 需要删除的申请产品列(也可以是报销项目)主键
     * @return 结果
     */
    @Override
    public int deleteXtAppProductByUappProductids(String[] uappProductids)
    {
        return xtAppProductMapper.deleteXtAppProductByUappProductids(uappProductids);
    }

    /**
     * 删除申请产品列(也可以是报销项目)信息
     *
     * @param uappProductid 申请产品列(也可以是报销项目)主键
     * @return 结果
     */
    @Override
    public int deleteXtAppProductByUappProductid(String uappProductid)
    {
        return xtAppProductMapper.deleteXtAppProductByUappProductid(uappProductid);
    }
}
