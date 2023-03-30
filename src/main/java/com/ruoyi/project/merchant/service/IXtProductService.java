package com.ruoyi.project.merchant.service;

import java.util.List;
import com.ruoyi.project.merchant.domain.XtProduct;
import com.ruoyi.project.merchant.domain.query.MallQuery;
import com.ruoyi.project.merchant.domain.query.ProductQuery;
import com.ruoyi.project.merchant.domain.vo.MallProductVo;
import com.ruoyi.project.merchant.domain.vo.MallVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品Service接口
 *
 * @author ruoyi
 * @date 2022-11-02
 */
public interface IXtProductService
{
    /**
     * 查询商品
     *
     * @param uproductid 商品主键
     * @return 商品
     */
    public XtProduct selectXtProductByUproductid(String uproductid);

    /**
     * 查询商品列表
     *
     * @param xtProduct 商品
     * @return 商品集合
     */
    public List<XtProduct> selectXtProductList(XtProduct xtProduct);

    /**
     * 新增商品
     *
     * @param xtProduct 商品
     * @return 结果
     */
    public int insertXtProduct(XtProduct xtProduct , HttpServletRequest request);

    /**
     * 修改商品
     *
     * @param xtProduct 商品
     * @return 结果
     */
    public int updateXtProduct(XtProduct xtProduct ,  boolean isAdministrators);

    /**
     * 批量删除商品
     *
     * @param uproductids 需要删除的商品主键集合
     * @return 结果
     */
    public int deleteXtProductByUproductids(String[] uproductids);

    /**
     * 删除商品信息
     *
     * @param uproductid 商品主键
     * @return 结果
     */
    public int deleteXtProductByUproductid(String uproductid);

    List<XtProduct> selectXtProductByCompId(String compId);

    int banXtProductByUproductids(String[] uproductids);

    int releaseXtProductByUproductids(String[] uproductids);

    List<MallVo> selectXtProductListVo(MallQuery mallQuery);

    List<MallProductVo> selectHotProductList(MallQuery mallQuery);

    List<MallProductVo> selectXtProductListByQuery(ProductQuery query);

    /**
     * 根据商品类型去查询商品
     * @param productTypeId 商品的类型ID
     * @return 商品列表
     */
    List<XtProduct> selectXtProductListByProductType(String productTypeId);

    /**
     * 查询所有商品的品类（看看到底有哪些品类）
     * @return 商品的所有品类
     */
    List<String> selectAllProductCategoryName();

    /**
     * 根据商品的品类去查询商品
     * @param ProductCategoryName 商品的品类名称
     * @return 商品列表
     */
    List<XtProduct> selectListByProductCategoryName(String ProductCategoryName);

    /**
     * 根据商品的品类和商品的类型去查询商品
     * @param ProductCategoryName 商品的品类名称
     * @param productTypeId 商品的类型ID
     * @return 商品列表
     */
    List<XtProduct> selectListByProductCategoryNameAndProductType(String ProductCategoryName , String productTypeId);

    /**
     * 模糊查询商品
     */
    List<XtProduct> likeSelectXtProductByProductName(String productName , String pinleiid , String leixingid);

    /**
     * 小刘专用 修改商品
     * @param xtProduct
     * @param isAdministrators
     * @return
     */
    int reworkXtProduct(XtProduct xtProduct , boolean isAdministrators);

}
