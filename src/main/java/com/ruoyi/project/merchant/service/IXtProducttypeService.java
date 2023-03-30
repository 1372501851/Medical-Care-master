package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtProducttype;
import com.ruoyi.project.merchant.domain.query.MallQuery;
import com.ruoyi.project.merchant.domain.tree.ProductTypeTreeSelect;
import com.ruoyi.project.merchant.domain.vo.ProductTypeVo;

import java.util.List;

/**
 * 产品类型（用在医疗护理系统，药店系统，其他系统）Service接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */
public interface IXtProducttypeService
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
     *
     * @return 结果
     */
    public int updateXtProducttype(String name, String space , String id);

    /**
     * 批量删除产品类型（用在医疗护理系统，药店系统，其他系统）
     *
     * @param uproducttypeids 需要删除的产品类型（用在医疗护理系统，药店系统，其他系统）主键集合
     * @return 结果
     */
    public int deleteXtProducttypeByUproducttypeids(String[] uproducttypeids);

    /**
     * 删除产品类型（用在医疗护理系统，药店系统，其他系统）信息
     *
     * @param uproducttypeid 产品类型（用在医疗护理系统，药店系统，其他系统）主键
     * @return 结果
     */
    public int deleteXtProducttypeByUproducttypeid(String uproducttypeid);

    List<ProductTypeTreeSelect> selectTypeTreeSelect(XtProducttype xtProducttype);

    List<ProductTypeVo> buildTypeTree(XtProducttype xtProducttype);

    List<ProductTypeTreeSelect> buildTypeTreeBycompId(MallQuery mallQuery);
}
