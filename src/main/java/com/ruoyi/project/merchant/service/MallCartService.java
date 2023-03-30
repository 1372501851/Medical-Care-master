package com.ruoyi.project.merchant.service;


import com.ruoyi.project.merchant.domain.MallCart;
import com.ruoyi.project.merchant.domain.vo.CartVO;

import java.util.List;

/**
 *
 */
public interface MallCartService {

    List<CartVO> list();

    void add(String productId, Integer count);

    void update(String productId, Integer count);

    void delete(String productId);

    void updateSelectedOne(String productId, Integer selected);

    void updateSelectedAll(Integer selected);
}
