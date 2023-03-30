package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtProducttype;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品类型（用在医疗护理系统，药店系统，其他系统）Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
@Repository
public interface XtProducttypeMapper
{
    /**
     * 查询产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param uproducttypeid 产品类型（用在医疗护理系统，药店系统，其他系统）主键
     * @return 产品类型（用在医疗护理系统，药店系统，其他系统）
     */
    public XtProducttype selectXtProducttypeByUproducttypeid(String uproducttypeid);

    /**
     * 查询产品类型（用在医疗护理系统，药店系统，其他系统）列表
     *
     * @param xtProducttype 产品类型（用在医疗护理系统，药店系统，其他系统）
     * @return 产品类型（用在医疗护理系统，药店系统，其他系统）集合
     */
    public List<XtProducttype> selectXtProducttypeList(XtProducttype xtProducttype);

    /**
     * 新增产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param xtProducttype 产品类型（用在医疗护理系统，药店系统，其他系统）
     * @return 结果
     */
    public int insertXtProducttype(XtProducttype xtProducttype);

    /**
     * 修改产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param
     * @return 结果
     */
    @Update("UPDATE xt_producttype SET uproducttypename = #{name} , uproduct_type_space = #{space} where uproducttypeid = #{id}")
    public int updateXtProducttype(String name , String space , String id);

    /**
     * 删除产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param uproducttypeid 产品类型（用在医疗护理系统，药店系统，其他系统）主键
     * @return 结果
     */
    public int deleteXtProducttypeByUproducttypeid(String uproducttypeid);

    /**
     * 批量删除产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param uproducttypeids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtProducttypeByUproducttypeids(String[] uproducttypeids);

    @Select("SELECT uproducttypeid FROM xt_producttype WHERE uproducttypename = #{name}")
    String selectProductTypeidByProductName(String name);

    @Select("SELECT * FROM xt_producttype")
    List<XtProducttype> selectAllXtproductType();

    @Select("SELECT cusnum FROM xt_producttype WHERE uproducttypeid = #{id}")
    Integer selectProductTypeCusnum(String id);
}
