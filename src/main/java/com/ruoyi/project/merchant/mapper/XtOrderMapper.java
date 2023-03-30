package com.ruoyi.project.merchant.mapper;

import com.ruoyi.project.merchant.domain.XtOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-13
 */

@Repository
public interface XtOrderMapper
{
    /**
     * 查询订单
     *
     * @param uorderid 订单主键
     * @return 订单
     */
    public XtOrder selectXtOrderByUorderid(String uorderid);

    /**
     * 查询订单列表
     *
     * @param xtOrder 订单
     * @return 订单集合
     */
    public List<XtOrder> selectXtOrderList(XtOrder xtOrder);

    /**
     * 新增订单
     *
     * @param xtOrder 订单
     * @return 结果
     */
    public int insertXtOrder(XtOrder xtOrder);

    /**
     * 修改订单
     *
     * @param xtOrder 订单
     * @return 结果
     */
    public int updateXtOrder(XtOrder xtOrder);

    /**
     * 删除订单
     *
     * @param uorderid 订单主键
     * @return 结果
     */
    public int deleteXtOrderByUorderid(String uorderid);

    /**
     * 批量删除订单
     *
     * @param uorderids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXtOrderByUorderids(String[] uorderids);

    List<XtOrder> selectOrderListByStatusAndName(@Param("userid") String userid,@Param("status") String status,@Param("name") String name);

    XtOrder selectXtOrderByUorderNo(String outTradeNo);


    int updateOrderStatusAndPaymentStatusByOrderNo(String orderNo, String Orderstaus,String payStauts);

    int updateOrderStatusByOrderNo(String orderNo, String code);

//    @Select("SELECT * FROM xt_order WHERE ucompid = #{ucompid} AND ustatus = '1' AND product_id = #{productId}")
//    List<XtOrder> selectXtOrderByProductId(String ucompid,String productId);


}
