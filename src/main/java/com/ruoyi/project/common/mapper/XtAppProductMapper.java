package com.ruoyi.project.common.mapper;

import com.ruoyi.project.common.domain.XtAppProduct;

import java.util.List;

/**
 * 申请产品列(也可以是报销项目)Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface XtAppProductMapper
{
    /**
     * 查询申请产品列(也可以是报销项目)
     *
     * @param uappProductid 申请产品列(也可以是报销项目)主键
     * @return 申请产品列(也可以是报销项目)
     */
    public XtAppProduct selectXtAppProductByUappProductid(String uappProductid);

    /**
     * 查询申请产品列(也可以是报销项目)列表
     *
     * @param xtAppProduct 申请产品列(也可以是报销项目)
     * @return 申请产品列(也可以是报销项目)集合
     */
    public List<XtAppProduct> selectXtAppProductList(XtAppProduct xtAppProduct);

    /**
     * 新增申请产品列(也可以是报销项目)
     *
     * @param xtAppProduct 申请产品列(也可以是报销项目)
     * @return 结果
     */
    public int insertXtAppProduct(XtAppProduct xtAppProduct);

    /**
     * 修改申请产品列(也可以是报销项目)
     *
     * @param xtAppProduct 申请产品列(也可以是报销项目)
     * @return 结果
     */
    public int updateXtAppProduct(XtAppProduct xtAppProduct);

    /**
     * 删除申请产品列(也可以是报销项目)
     *
     * @param uappProductid 申请产品列(也可以是报销项目)主键
     * @return 结果
     */
    public int deleteXtAppProductByUappProductid(String uappProductid);

    /**
     * 批量删除申请产品列(也可以是报销项目)
     *
     * @param uappProductids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtAppProductByUappProductids(String[] uappProductids);
}
