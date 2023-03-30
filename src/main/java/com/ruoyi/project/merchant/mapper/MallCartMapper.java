package com.ruoyi.project.merchant.mapper;


import com.ruoyi.project.merchant.domain.MallCart;
import com.ruoyi.project.merchant.domain.vo.CartVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.yunsw.zouwangyun.pojo.MallCart
 */
@Repository
public interface MallCartMapper  {

    MallCart selectCartByUserIdAndProductId(String userId, String productId);

    int insertSelective(MallCart cart);

    int updateByPrimaryKeySelective(MallCart cartNew);

//    @Select("SELECT * FROM mall_cart WHERE user_id = #{userId}")
    List<MallCart> selectList(String userId);

    int deleteByPrimaryKey(String id);

    int updateSelectedOne(String userId, String productId, Integer selected);

    int updateSelectedAll(String userId, Integer selected);
}




