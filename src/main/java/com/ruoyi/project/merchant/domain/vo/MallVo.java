package com.ruoyi.project.merchant.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-11-22
 */
@Data
public class MallVo {
    /**商品类型*/
    private MallTypeVo mallTypeVo;

    /**该类型所有的商品*/

    private List<MallProductVo> mallProductVoList  = new ArrayList<>();

    /**子vo*/
    private List<MallVo> children = new ArrayList<>();
}
