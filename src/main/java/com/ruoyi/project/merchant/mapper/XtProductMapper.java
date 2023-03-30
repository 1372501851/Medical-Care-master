package com.ruoyi.project.merchant.mapper;

import java.util.List;
import com.ruoyi.project.merchant.domain.XtProduct;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 商品Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-02
 */
@Repository
public interface XtProductMapper
{
    /**
     * 查询商品
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
    public int insertXtProduct(XtProduct xtProduct);

    /**
     * 小刘专用增加商品
     * @param xtProduct
     * @return
     */
    @Insert("INSERT INTO xt_product (uproductid,uproduct_pic,ubarcode,ubarcode_numeric_code,ucategory_name,ucategory_place,usort_name,usort_place,utrade_name,utrade_place,uproduct_specs,uproduct_dose,umanufacturer,umaterial_code,uproduct_standards,uapproval_no,umain_components,umain_efficacy,usage_method,usingle_treatment,umatters_attention,ustorage_method,ushelf_life,uexpiration_date,uinventory_quantity,udelivery_quantity,ureceipt_quantity,utransaction_price,uretail_price,umembership_price,uprice_huicheng,uwholesale_price,uagency_price,uoutsourcing_price,upurchase_price,ucommodity_losses,uremarks1,uremarks2,uremarks3,uproducttypeid,ucategoryid,ucompid,ucustomerid,uproduct_name,uproduct_form,ucrtificate_inspection,ucertificate,flag,status,create_time,update_time,utrade_originplace,uproduct_model,sales_quantity) " +
            "values(#{uproductid},#{uproductPic},#{ubarcode},#{ubarcodeNumericCode},#{ucategoryName},#{ucategoryPlace},#{usortName},#{usortPlace},#{utradeName},#{utradePlace},#{uproductSpecs},#{uproductDose},#{umanufacturer},#{umaterialCode},#{uproductStandards},#{uapprovalNo},#{umainComponents},#{umainEfficacy},#{usageMethod},#{usingleTreatment},#{umattersAttention},#{ustorageMethod},#{ushelfLife},#{uexpirationDate},#{uinventoryQuantity},#{udeliveryQuantity},#{ureceiptQuantity},#{utransactionPrice},#{uretailPrice},#{umembershipPrice},#{upriceHuicheng},#{uwholesalePrice},#{uagencyPrice},#{uoutsourcingPrice},#{upurchasePrice},#{ucommodityLosses},#{uremarks1},#{uremarks2},#{uremarks3},#{uproducttypeid},#{ucategoryid},#{ucompid},#{ucustomerid},#{uproductName},#{uproductForm},#{ucrtificateInspection},#{ucertificate},#{flag},#{status},#{createTime},#{updateTime},#{utradeOriginplace},#{uproductModel},#{salesQuantity})")
    int addXtproduct(XtProduct xtProduct);

//uproductid,uproduct_pic,ubarcode,ubarcode_numeric_code,ucategory_name,ucategory_place,usort_name,usort_place,utrade_name,utrade_place,uproduct_specs,uproduct_dose,umanufacturer,umaterial_code,uproduct_standards,uapproval_no,umain_components,umain_efficacy,usage_method,usingle_treatment,umatters_attention,ustorage_method,ushelf_life,uexpiration_date,uinventory_quantity,udelivery_quantity,ureceipt_quantity,utransaction_price,uretail_price,umembership_price,uprice_huicheng,uwholesale_price,uagency_price,uoutsourcing_price,upurchase_price,ucommodity_losses,uremarks1,uremarks2,uremarks3,uproducttypeid,ucategoryid,ucompid,ucomp_type,ucustomerid,uproduct_name,uproduct_form,ucrtificate_inspection,ucertificate,flag,status,create_time,update_time,utrade_originplace,uproduct_model,sales_quantity
//    #{uproductid},#{uproductPic},#{ubarcode},#{ubarcodeNumericCode},#{ucategoryName},#{ucategoryPlace},#{usortName},#{usortPlace},#{utradeName},#{utradePlace},#{uproductSpecs},#{uproductDose},#{umanufacturer},#{umaterialCode},#{uproductStandards},#{uapprovalNo},#{umainComponents},#{umainEfficacy},#{usageMethod},#{usingleTreatment},#{umattersAttention},#{ustorageMethod},#{ushelfLife},#{uexpirationDate},#{uinventoryQuantity},#{udeliveryQuantity},#{ureceiptQuantity},#{utransactionPrice},#{uretailPrice},#{umembershipPrice},#{upriceHuicheng},#{uwholesalePrice},#{uagencyPrice},#{uoutsourcingPrice},#{upurchasePrice},#{ucommodityLosses},#{uremarks1},#{uremarks2},#{uremarks3},#{uproducttypeid},#{ucategoryid},#{ucompid},#{ucompType},#{ucustomerid},#{uproductName},#{uproductForm},#{ucrtificateInspection},#{ucertificate},#{flag},#{status},#{createTime},#{updateTime},#{utradeOriginplace},#{uproductModel},#{SalesQuantity}

    /**
     * 修改商品
     *
     * @param xtProduct 商品
     * @return 结果
     */
    @Update("update xt_product set uproduct_pic = #{uproductPic},ubarcode = #{ubarcode},ubarcode_numeric_code = #{ubarcodeNumericCode},ucategory_name = #{ucategoryName},ucategory_place = #{ucategoryPlace},usort_name = #{usortName},usort_place = #{usortPlace},utrade_name = #{utradeName},utrade_place = #{utradePlace},uproduct_specs = #{uproductSpecs},uproduct_dose = #{uproductDose},umanufacturer = #{umanufacturer},umaterial_code = #{umaterialCode},uproduct_standards = #{uproductStandards},uapproval_no = #{uapprovalNo},umain_components = #{umainComponents},umain_efficacy = #{umainEfficacy},usage_method = #{usageMethod},usingle_treatment = #{usingleTreatment},umatters_attention = #{umattersAttention},ustorage_method = #{ustorageMethod},ushelf_life = #{ushelfLife},uexpiration_date = #{uexpirationDate},uinventory_quantity = #{uinventoryQuantity},udelivery_quantity = #{udeliveryQuantity},ureceipt_quantity = #{ureceiptQuantity},utransaction_price = #{utransactionPrice},uretail_price = #{uretailPrice},umembership_price = #{umembershipPrice},uprice_huicheng = #{upriceHuicheng},uwholesale_price = #{uwholesalePrice},uagency_price = #{uagencyPrice},uoutsourcing_price = #{uoutsourcingPrice},upurchase_price = #{upurchasePrice},ucommodity_losses = #{ucommodityLosses},uremarks1 = #{uremarks1},uremarks2 = #{uremarks2},uremarks3 = #{uremarks3},uproducttypeid = #{uproducttypeid},ucategoryid = #{ucategoryid},ucompid = #{ucompid},ucustomerid = #{ucustomerid},uproduct_name = #{uproductName},uproduct_form = #{uproductForm},ucrtificate_inspection = #{ucrtificateInspection},ucertificate = #{ucertificate},flag = #{flag},status = #{status},create_time = #{createTime},update_time = #{updateTime},utrade_originplace = #{utradeOriginplace},uproduct_model = #{uproductModel},sales_quantity = #{salesQuantity} where uproductid = #{uproductid}")
     int updateXtProduct(XtProduct xtProduct);
//uproductid = #{uproductid},uproduct_pic = #{uproductPic},ubarcode = #{ubarcode},ubarcode_numeric_code = #{ubarcodeNumericCode},ucategory_name = #{ucategoryName},ucategory_place = #{ucategoryPlace},usort_name = #{usortName},usort_place = #{usortPlace},utrade_name = #{utradeName},utrade_place = #{utradePlace},uproduct_specs = #{uproductSpecs},uproduct_dose = #{uproductDose},umanufacturer = #{umanufacturer},umaterial_code = #{umaterialCode},uproduct_standards = #{uproductStandards},uapproval_no = #{uapprovalNo},umain_components = #{umainComponents},umain_efficacy = #{umainEfficacy},usage_method = #{usageMethod},usingle_treatment = #{usingleTreatment},umatters_attention = #{umattersAttention},ustorage_method = #{ustorageMethod},ushelf_life = #{ushelfLife},uexpiration_date = #{uexpirationDate},uinventory_quantity = #{uinventoryQuantity},udelivery_quantity = #{udeliveryQuantity},ureceipt_quantity = #{ureceiptQuantity},utransaction_price = #{utransactionPrice},uretail_price = #{uretailPrice},umembership_price = #{umembershipPrice},uprice_huicheng = #{upriceHuicheng},uwholesale_price = #{uwholesalePrice},uagency_price = #{uagencyPrice},uoutsourcing_price = #{uoutsourcingPrice},upurchase_price = #{upurchasePrice},ucommodity_losses = #{ucommodityLosses},uremarks1 = #{uremarks1},uremarks2 = #{uremarks2},uremarks3 = #{uremarks3},uproducttypeid = #{uproducttypeid},ucategoryid = #{ucategoryid},ucompid = #{ucompid},ucustomerid = #{ucustomerid},uproduct_name = #{uproductName},uproduct_form = #{uproductForm},ucrtificate_inspection = #{ucrtificateInspection},ucertificate = #{ucertificate},flag = #{flag},status = #{status},create_time = #{createTime},update_time = #{updateTime},utrade_originplace = #{utradeOriginplace},uproduct_model = #{uproductModel},sales_quantity = #{salesQuantity}
//#{uproductid},#{uproductPic},#{ubarcode},#{ubarcodeNumericCode},#{ucategoryName},#{ucategoryPlace},#{usortName},#{usortPlace},#{utradeName},#{utradePlace},#{uproductSpecs},#{uproductDose},#{umanufacturer},#{umaterialCode},#{uproductStandards},#{uapprovalNo},#{umainComponents},#{umainEfficacy},#{usageMethod},#{usingleTreatment},#{umattersAttention},#{ustorageMethod},#{ushelfLife},#{uexpirationDate},#{uinventoryQuantity},#{udeliveryQuantity},#{ureceiptQuantity},#{utransactionPrice},#{uretailPrice},#{umembershipPrice},#{upriceHuicheng},#{uwholesalePrice},#{uagencyPrice},#{uoutsourcingPrice},#{upurchasePrice},#{ucommodityLosses},#{uremarks1},#{uremarks2},#{uremarks3},#{uproducttypeid},#{ucategoryid},#{ucompid},#{ucustomerid},#{uproductName},#{uproductForm},#{ucrtificateInspection},#{ucertificate},#{flag},#{status},#{createTime},#{updateTime},#{utradeOriginplace},#{uproductModel},#{salesQuantity})")

    /**
     * 删除商品
     *
     * @param uproductid 商品主键
     * @return 结果
     */
    public int deleteXtProductByUproductid(String uproductid);

    /**
     * 批量删除商品
     *
     * @param uproductids 需要删除的数据主键集合
     * @return 结果
     */
    public int  deleteXtProductByUproductids(String[] uproductids);

    List<XtProduct> selectXtProductByUpCompId(String compId);

    int banXtProductByUproductids(String[] uproductids);

    int releaseXtProductByUproductids(String[] uproductids);

    List<String> selectProductTypesBycompId(String ucompid);

    /**
     * 根据商品类型查找商品
     * @param productTypeId 商品类型的ID
     * @return 商品列表
     */
    @Select("select * from xt_product where #{productTypeId} = uproducttypeid and flag = '0'")
    List<XtProduct> selectXtProductListByProductType(String productTypeId);

    /**
     * 查询所有商品的品类（看看到底有哪些品类）
     * @return 商品的所有品类
     */
    @Select("select distinct ucategory_name from xt_product where flag = '0' ")
    List<String> selectAllProductCategoryName();

    /**
     * 根据商品的品类去查询商品
     * @param ProductCategoryName 商品的品类名称
     * @return 商品列表
     */
    @Select("select * from xt_product where #{ProductCategoryName} = ucategory_name and flag = '0'")
    List<XtProduct> selectListByProductCategoryName(String ProductCategoryName);

    /**
     * 根据商品的品类和商品的类型去查询商品
     * @param ProductCategoryName 商品的品类名称
     * @param productTypeId 商品的类型ID
     * @return 商品列表
     */
    @Select("select * from xt_product where #{ProductCategoryName} = ucategory_name and #{productTypeId} = uproducttypeid and flag = '0'")
    List<XtProduct> selectListByProductCategoryNamAndProductType(String ProductCategoryName , String productTypeId);


    @Insert("INSERT INTO xt_product (uproductid,uproduct_pic,ubarcode,ubarcode_numeric_code,ucategory_name,ucategory_place,usort_name,usort_place,utrade_name,utrade_place,uproduct_specs,uproduct_dose,umanufacturer,umaterial_code,uproduct_standards,uapproval_no,umain_components,umain_efficacy,usage_method,usingle_treatment,umatters_attention,ustorage_method,ushelf_life,uexpiration_date,uinventory_quantity,udelivery_quantity,ureceipt_quantity,utransaction_price,uretail_price,umembership_price,uprice_huicheng,uwholesale_price,uagency_price,uoutsourcing_price,upurchase_price,ucommodity_losses,uremarks1,uremarks2,uremarks3,uproducttypeid,ucategoryid,ucompid,ucustomerid,uproduct_name,uproduct_form,ucrtificate_inspection,ucertificate,flag,status,create_time,update_time,utrade_originplace,uproduct_model,sales_quantity) " +
            "values(#{uproductid},#{uproductPic},#{ubarcode},#{ubarcodeNumericCode},#{ucategoryName},#{ucategoryPlace},#{usortName},#{usortPlace},#{utradeName},#{utradePlace},#{uproductSpecs},#{uproductDose},#{umanufacturer},#{umaterialCode},#{uproductStandards},#{uapprovalNo},#{umainComponents},#{umainEfficacy},#{usageMethod},#{usingleTreatment},#{umattersAttention},#{ustorageMethod},#{ushelfLife},#{uexpirationDate},#{uinventoryQuantity},#{udeliveryQuantity},#{ureceiptQuantity},#{utransactionPrice},#{uretailPrice},#{umembershipPrice},#{upriceHuicheng},#{uwholesalePrice},#{uagencyPrice},#{uoutsourcingPrice},#{upurchasePrice},#{ucommodityLosses},#{uremarks1},#{uremarks2},#{uremarks3},#{uproducttypeid},#{ucategoryid},#{ucompid},#{ucustomerid},#{uproductName},#{uproductForm},#{ucrtificateInspection},#{ucertificate},#{flag},#{status},#{createTime},#{updateTime},#{utradeOriginplace},#{uproductModel},#{salesQuantity})")
    int updateXtProduct2(XtProduct xtProduct);

    @Select("select  * from xt_product where utrade_name like concat('%',#{utradeName},'%') and ucategoryid = #{pinlei} and uproducttypeid = #{leixing}")
    List<XtProduct> likeSelectXtProductByProductName1(String utradeName , String pinlei , String leixing);

    @Select("select  * from xt_product where utrade_name like concat('%',#{utradeName},'%') ")
    List<XtProduct> likeSelectXtProductByProductName2(String utradeName );

    @Select("select  * from xt_product where utrade_name like concat('%',#{utradeName},'%') and ucategoryid = #{pinlei} ")
    List<XtProduct> likeSelectXtProductByProductNam3(String utradeName , String pinlei , String leixing);

    @Select("select  * from xt_product where utrade_name like concat('%',#{utradeName},'%')  and uproducttypeid = #{leixing}")
    List<XtProduct> likeSelectXtProductByProductName4(String utradeName , String pinlei , String leixing);


    @Select("select  * from xt_product")
    List<XtProduct> selectAllProduct();

    @Update("update xt_product set uproduct_pic = #{uproductPic},ubarcode = #{ubarcode},ubarcode_numeric_code = #{ubarcodeNumericCode},ucategory_name = #{ucategoryName},ucategory_place = #{ucategoryPlace},usort_name = #{usortName},usort_place = #{usortPlace},utrade_name = #{utradeName},utrade_place = #{utradePlace},uproduct_specs = #{uproductSpecs},uproduct_dose = #{uproductDose},umanufacturer = #{umanufacturer},umaterial_code = #{umaterialCode},uproduct_standards = #{uproductStandards},uapproval_no = #{uapprovalNo},umain_components = #{umainComponents},umain_efficacy = #{umainEfficacy},usage_method = #{usageMethod},usingle_treatment = #{usingleTreatment},umatters_attention = #{umattersAttention},ustorage_method = #{ustorageMethod},ushelf_life = #{ushelfLife},uexpiration_date = #{uexpirationDate},uinventory_quantity = #{uinventoryQuantity},udelivery_quantity = #{udeliveryQuantity},ureceipt_quantity = #{ureceiptQuantity},utransaction_price = #{utransactionPrice},uretail_price = #{uretailPrice},umembership_price = #{umembershipPrice},uprice_huicheng = #{upriceHuicheng},uwholesale_price = #{uwholesalePrice},uagency_price = #{uagencyPrice},uoutsourcing_price = #{uoutsourcingPrice},upurchase_price = #{upurchasePrice},ucommodity_losses = #{ucommodityLosses},uremarks1 = #{uremarks1},uremarks2 = #{uremarks2},uremarks3 = #{uremarks3},uproducttypeid = #{uproducttypeid},ucategoryid = #{ucategoryid},ucompid = #{ucompid},ucustomerid = #{ucustomerid},uproduct_name = #{uproductName},uproduct_form = #{uproductForm},ucrtificate_inspection = #{ucrtificateInspection},ucertificate = #{ucertificate},flag = #{flag},status = #{status},create_time = #{createTime},update_time = #{updateTime},utrade_originplace = #{utradeOriginplace},uproduct_model = #{uproductModel},sales_quantity = #{salesQuantity} where uproductid = #{uproductid}")
    int reworkXtProduct(XtProduct xtProduct);


    List<XtProduct> selectProductByPlAndZl(String sortName , String categoryName);

    @Select("SELECT  * FROM  xt_product WHERE uproductid = #{id}")
    XtProduct selectProductInfoId(String id);

    @Select("SELECT  * FROM  xt_product WHERE ucompid = #{compid}")
    List<XtProduct> selectProductBycomp(String compid);

    @Select("SELECT uoutsourcing_price FROM xt_product WHERE uproductid = #{id}")
    Integer selectUoutsourcingPriceById(String id);

    @Select("SELECT  * FROM  xt_product ORDER BY  ${collation}  DESC ")
    List<XtProduct> selectProductSort(String collation);

    @Update("update xt_product set dailysales = 0")
    int emptyDailysales();

    @Update("update xt_product set weeklysales = 0")
    int emptyWeeklysales();

    @Update("update xt_product set monthsales = 0")
    int emptyMonthsales();

    @Update("update xt_product set quartersales = 0")
    int emptyQuartersales();

    @Update("update xt_product set annualsales = 0")
    int emptyAnnualsales();
//    SELECT ${seat} * uretail_price As lll FROM xt_product ORDER BY lll DESC
    @Select("SELECT ${seat} * uretail_price FROM xt_product")
    List<Integer> selectIncome(String seat);

    @Select(" SELECT * FROM xt_product WHERE uproductid = #{id} ")
    XtProduct selectAll(String id);
}

