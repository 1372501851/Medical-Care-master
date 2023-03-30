package com.ruoyi.project.merchant.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.login.Dto.AppLoginUser;
import com.ruoyi.project.login.TokenService.AppLoginTokenService;
import com.ruoyi.project.merchant.domain.XtComp;
import com.ruoyi.project.merchant.domain.XtProducttype;
import com.ruoyi.project.merchant.domain.dto.HotProduct;
import com.ruoyi.project.merchant.domain.query.MallQuery;
import com.ruoyi.project.merchant.domain.query.ProductQuery;
import com.ruoyi.project.merchant.domain.vo.MallProductVo;
import com.ruoyi.project.merchant.domain.vo.MallTypeVo;
import com.ruoyi.project.merchant.domain.vo.MallVo;
import com.ruoyi.project.merchant.mapper.*;
import com.ruoyi.project.merchant.service.IXtProducttypeService;
import com.ruoyi.project.task.upload.until.StringToURL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.merchant.domain.XtProduct;
import com.ruoyi.project.merchant.service.IXtProductService;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-02
 */
@Service
public class XtProductServiceImpl implements IXtProductService
{
    @Autowired
    private XtProductMapper xtProductMapper;


    @Autowired
    private XtProducttypeMapper xtProducttypeMapper;


    @Autowired
    private IXtProducttypeService xtProducttypeService;


    @Autowired
    private XtOrderitemMapper orderitemMapper;

    @Autowired
    private AppLoginTokenService appLoginTokenService;

    @Autowired
    private XtCompMapper xtCompMapper;

    @Autowired
    private XtCategoryMapper xtCategoryMapper;


    /**
     * 查询商品
     *
     * @param uproductid 商品主键
     * @return 商品
     */
    @Override
    public XtProduct selectXtProductByUproductid(String uproductid)
    {
        XtProduct product = xtProductMapper.selectXtProductByUproductid(uproductid);
        String path = product.getUproductPic();
        List<String> urls = StringToURL.toURL(path);
        product.setUproductPic(urls.get(0));
        return product;
    }

    /**
     * 查询商品列表
     *
     * @param xtProduct 商品
     * @return 商品
     */

    @Override
    public List<XtProduct> selectXtProductList(XtProduct xtProduct)
    {
        return xtProductMapper.selectXtProductList(xtProduct);
    }

    /**
     * 新增商品
     *
     * @param xtProduct 商品
     * @return 结果
     */
    @Override
    public int insertXtProduct(XtProduct xtProduct, HttpServletRequest request)
    {
//        判断增加商品的人是不是商家，如果不是商家就不能让他用这个功能
        AppLoginUser loginMyUser1 = appLoginTokenService.getLoginMyUser(request);
        String userid = loginMyUser1.getUser().getUserid();
        List<XtComp> xtCompList = xtCompMapper.selectCompByUserId(userid);
        if(xtCompList.isEmpty()){
            throw new ServiceException("用户不是商家，不能进行增加商品操作");
        }
//
//        //自行设置id
        xtProduct.setUproductid(IdUtil.getSnowflakeNextIdStr());
//        //设置商品的商家id，进行关联
        xtProduct.setUcompid(xtCompList.get(0).getUcompid());
//
//        //设置品类的id
        String ucategoryid = xtCategoryMapper.selectCategoryIdByCategoryName(xtProduct.getUcategoryName());
        xtProduct.setUcategoryid(ucategoryid);
//
//        //设置种类的id
        String uproducttypeid = xtProducttypeMapper.selectProductTypeidByProductName(xtProduct.getUsortName());
        xtProduct.setUproducttypeid(uproducttypeid);


//        xtProduct.setCreateTime(DateUtils.getNowDate());
        //商家Id;商户号(这个可以根据前端传,也可以通过登陆者信息后端填写)
        //设置默认状态

        xtProduct.setFlag("0");
        xtProduct.setStatus("0");
        return xtProductMapper.addXtproduct(xtProduct);
    }

    /**
     * 修改商品
     *
     * @param xtProduct 商品
     * @return 结果
     */
    @Override
    public int updateXtProduct(XtProduct xtProduct , boolean isAdministrators)
    {
        xtProduct.setUpdateTime(DateUtils.getNowDate());
        if(isAdministrators){
            return xtProductMapper.updateXtProduct(xtProduct);
        }
        else{
            return xtProductMapper.updateXtProduct2(xtProduct);}


    }

    /**
     * 批量删除商品(逻辑删除)
     *
     * @param uproductids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteXtProductByUproductids(String[] uproductids)
    {
        return xtProductMapper.deleteXtProductByUproductids(uproductids);
    }

    /**
     * 删除商品信息
     *
     * @param uproductid 商品主键
     * @return 结果
     */
    @Override
    public int deleteXtProductByUproductid(String uproductid)
    {
        return xtProductMapper.deleteXtProductByUproductid(uproductid);
    }

    @Override
    public List<XtProduct> selectXtProductByCompId(String compId) {
        List<XtProduct> xtProducts = xtProductMapper.selectXtProductByUpCompId(compId);
        return xtProducts;
    }

    @Override
    public int banXtProductByUproductids(String[] uproductids) {
        return xtProductMapper.banXtProductByUproductids(uproductids);
    }

    @Override
    public int releaseXtProductByUproductids(String[] uproductids) {

        return xtProductMapper.releaseXtProductByUproductids(uproductids);
    }

    @Override
    public List<MallVo> selectXtProductListVo(MallQuery mallQuery) {
        List<MallVo> mallVoList = new ArrayList<>();

        //找到该商家所有的商品类型
        String ucompid = mallQuery.getUcompid();
        //该商家的所有商品的类型
        List<String> productTypes = xtProductMapper.selectProductTypesBycompId(ucompid);
        List<XtProducttype> xtProducttypeList = new ArrayList<>();
        for (int i = 0; i < productTypes.size(); i++) {
            XtProducttype xtProducttype = xtProducttypeMapper.selectXtProducttypeByUproducttypeid(productTypes.get(i));
            xtProducttypeList.add(xtProducttype);
        }

        //转成vo
        List<MallTypeVo> mallTypeVoList = new ArrayList<>();
        for (int i = 0; i < xtProducttypeList.size(); i++) {
            MallTypeVo mallTypeVo = new MallTypeVo();
            BeanUtils.copyProperties(xtProducttypeList.get(i),mallTypeVo);
            //将类型加入mallVo
            MallVo mallVo = new MallVo();
            mallVo.setMallTypeVo(mallTypeVo);
            mallVoList.add(mallVo);
            mallTypeVoList.add(mallTypeVo);
        }



        List<MallVo> mallVos = buildTypeTree(mallVoList,mallQuery);

        //将list的属性赋予mallTypeVo

        //构建商品类型树


        //搜索对应type的商品,然后也将属性赋予vo

        //构建商品列表vo树


        return mallVos;


    }

    @Override
    public List<MallProductVo> selectHotProductList(MallQuery mallQuery) {
        List<MallProductVo> mallProductVoList = new ArrayList<>();
        //统计该商家销售数量最多的10件商品(根据订单明细表来计算)
        List<HotProduct> hotProducts = orderitemMapper.selectXtOrderitemListByCompId(mallQuery.getUcompid());
        for (int i = 0; i < hotProducts.size(); i++) {
            MallProductVo mallProductVo = new MallProductVo();
            //查询商品列表
            String productId = hotProducts.get(i).getProductId();
            XtProduct product = xtProductMapper.selectXtProductByUproductid(productId);
//            String uproductPic = product.getUproductPic();
//            List<String> strings = StringToURL.toURL(uproductPic);
//            product.setUproductPic(strings.get(0));
//            try {
                BeanUtils.copyProperties(product, mallProductVo);
                mallProductVo.setSalesQuantity(hotProducts.get(i).getCount());
                mallProductVoList.add(mallProductVo);
//            } catch (Exception e){
//                throw new RuntimeException("有点问题，先等等！");
//            }

        }
        return mallProductVoList;

    }

    @Override
    public List<MallProductVo> selectXtProductListByQuery(ProductQuery query) {
        //还需要增加排序逻辑
        List<MallProductVo> mallProductVoList = new ArrayList<>();
        XtProduct product = new XtProduct();
        BeanUtils.copyProperties(query,product);
        List<XtProduct> xtProducts = xtProductMapper.selectXtProductList(product);
        for (int i = 0; i < xtProducts.size(); i++) {
            MallProductVo mallProductVo = new MallProductVo();
            String uproductPic = xtProducts.get(i).getUproductPic();
//            List<String> strings = StringToURL.toURL(uproductPic);
//            xtProducts.get(i).setUproductPic(strings.get(0));
            BeanUtils.copyProperties(xtProducts.get(i),mallProductVo);
            mallProductVoList.add(mallProductVo);
        }
        return mallProductVoList;
    }




    public List<MallVo> buildTypeTree(List<MallVo> types,MallQuery query)
    {
        List<MallVo> mallVos = buildProductTypeTree(types,query);
        return mallVos;
    }

    public List<MallVo> buildProductTypeTree(List<MallVo> types,MallQuery query)
    {
        List<MallVo> returnList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        //记录所有的typeId
        for (int i = 0; i < types.size(); i++) {
            String typeId = types.get(i).getMallTypeVo().getUproducttypeid();
            tempList.add(typeId);
        }

        for (MallVo type : types)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(type.getMallTypeVo().getUparentid()))
            {
                recursionFn(types, type,query);
                returnList.add(type);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = types;
        }
        return returnList;
    }

    private void recursionFn(List<MallVo> list, MallVo t,MallQuery query)
    {

        //将该类型对应的商品记录
        XtProduct product = new XtProduct();
        product.setUcompid(query.getUcompid());
        product.setUproducttypeid(t.getMallTypeVo().getUproducttypeid());
        List<XtProduct> xtProducts = xtProductMapper.selectXtProductList(product);
        List<MallProductVo> mallProductVoList = new ArrayList<>();

        for (int i = 0; i < xtProducts.size(); i++) {
            String uproductPic = xtProducts.get(i).getUproductPic();
            List<String> strings = StringToURL.toURL(uproductPic);
            xtProducts.get(i).setUproductPic(strings.get(0));

            MallProductVo mallProductVo = new MallProductVo();
            BeanUtils.copyProperties(xtProducts.get(i),mallProductVo);
            mallProductVoList.add(mallProductVo);
        }
        t.setMallProductVoList(mallProductVoList);

        // 得到子节点列表(可以)
        List<MallVo> childList = getChildList(list, t);

        t.setChildren(childList);
        for (MallVo tChild : childList)
        {
            //判断子节点是否还有孩子

            //没有子节点,将不会记录该类型的商品列表
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild,query);
            }else{
                //将该类型对应的商品记录
                XtProduct product1 = new XtProduct();
                product1.setUcompid(query.getUcompid());
                product1.setUproducttypeid(tChild.getMallTypeVo().getUproducttypeid());
                List<XtProduct> xtProducts1 = xtProductMapper.selectXtProductList(product1);
                List<MallProductVo> mallProductVoList1 = new ArrayList<>();

                for (int i = 0; i < xtProducts1.size(); i++) {
                    String uproductPic = xtProducts1.get(i).getUproductPic();
                    List<String> strings = StringToURL.toURL(uproductPic);
                    xtProducts1.get(i).setUproductPic(strings.get(0));

                    MallProductVo mallProductVo = new MallProductVo();
                    BeanUtils.copyProperties(xtProducts1.get(i),mallProductVo);
                    mallProductVoList1.add(mallProductVo);
                }
                tChild.setMallProductVoList(mallProductVoList1);
            }
        }
    }

    private List<MallVo> getChildList(List<MallVo> list, MallVo t)
    {

        //list:全部;t:其中一个
        List<MallVo> tlist = new ArrayList<>();
        Iterator<MallVo> it = list.iterator();
        while (it.hasNext())
        {
            MallVo n = (MallVo) it.next();
            //判断是否是顶级节点的条件(这个判断条件需要该,每次判断自己是否是顶级节点)
            //将每个子部门去和要判断的部门比较判断,如果属于子部门就加入
            if (StringUtils.isNotNull(n.getMallTypeVo().getUparentid()) && n.getMallTypeVo().getUparentid().equals(t.getMallTypeVo().getUproducttypeid()))
            {
                tlist.add(n);
            }
        }


        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<MallVo> list, MallVo t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    @Override
    public List<XtProduct> selectXtProductListByProductType(String productTypeId) {
        return xtProductMapper.selectXtProductListByProductType(productTypeId);
    }

    @Override
    public List<String> selectAllProductCategoryName() {
        return xtProductMapper.selectAllProductCategoryName();
    }

    @Override
    public List<XtProduct> selectListByProductCategoryName(String ProductCategoryName) {
        return xtProductMapper.selectListByProductCategoryName(ProductCategoryName);
    }

    @Override
    public List<XtProduct> selectListByProductCategoryNameAndProductType(String ProductCategoryName, String productTypeId) {
        return xtProductMapper.selectListByProductCategoryNamAndProductType(ProductCategoryName,productTypeId);
    }

    @Override
    public List<XtProduct> likeSelectXtProductByProductName(String productName , String pinlei , String leixing) {
        if (pinlei != "" && leixing != "") {
            return xtProductMapper.likeSelectXtProductByProductName1(productName, pinlei, leixing);
        }
        if (pinlei != "" && leixing == "") {
            return xtProductMapper.likeSelectXtProductByProductName4(productName, pinlei, leixing);
        }
        if (pinlei == "" && leixing != "") {
            return xtProductMapper.likeSelectXtProductByProductNam3(productName, pinlei, leixing);
        }
        return xtProductMapper.likeSelectXtProductByProductName2(productName);
    }
    @Override
    public int reworkXtProduct(XtProduct xtProduct, boolean isAdministrators) {
        xtProductMapper.deleteXtProductByUproductid(xtProduct.getUproductid());
        int i = xtProductMapper.addXtproduct(xtProduct);

        return i;
    }


//    private List<String> hasChildTypeList(String compId ,String productTypeId){
//        List<String> typeList = new ArrayList<>();
//
//        //该商家的所有商品的类型
//        List<String> productTypes = xtProductMapper.selectProductTypesBycompId(compId);
//        List<XtProducttype> xtProducttypeList = new ArrayList<>();
//        for (int i = 0; i < productTypes.size(); i++) {
//            XtProducttype xtProducttype = xtProducttypeMapper.selectXtProducttypeByUproducttypeid(productTypes.get(i));
//            xtProducttypeList.add(xtProducttype);
//        }
//
//        //递归判断该类型是否有孩子
//        for (int i = 0; i < xtProducttypeList.size(); i++) {
//            if(xtProducttypeList.get(i).getUparentid().equals(productTypeId)){
//
//            }
//        }
//
//
//    }

}
