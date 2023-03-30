package com.ruoyi.project.merchant.service;

import com.ruoyi.project.merchant.domain.XtCategory;

import java.util.List;

public interface IXtCategoryService {
    /**
     * 查询品类
     *
     * @param id 主键
     * @return 品类
     */
    XtCategory selectXtCategoryByid(String id);

    /**
     * 查询品类列表
     *
     * @param xtCategory 品类
     * @return 品类集合
     */
    List<XtCategory> selectXtCategoryList(XtCategory xtCategory);

    /**
     * 新增品类
     *
     * @param xtCategory 品类
     * @return 结果
     */
    int insertXtCategory(XtCategory xtCategory);

    /**
     * 修改品类
     * @param xtCategory 品类
     * @return 结果
     */
    int updateXtCategory(XtCategory xtCategory );

    /**
     * 批量删除品类
     *
     * @param ids 需要删除的品类主键集合
     * @return 结果
     */
    int deleteXtCategoryByIds(String[] ids);

    /**
     * 删除商品信息
     *
     * @param id 品类主键
     * @return 结果
     */
    int deleteXtCategoryByid(String id);//
}
