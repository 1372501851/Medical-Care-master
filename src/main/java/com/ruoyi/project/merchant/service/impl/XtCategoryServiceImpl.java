package com.ruoyi.project.merchant.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.project.merchant.domain.XtCategory;
import com.ruoyi.project.merchant.mapper.XtCategoryMapper;
import com.ruoyi.project.merchant.service.IXtCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-07
 */
@Service
public class XtCategoryServiceImpl implements IXtCategoryService {

    @Autowired
    private XtCategoryMapper xtCategoryMapper;

    @Override
    public XtCategory selectXtCategoryByid(String id) {
        return xtCategoryMapper.selectXtCategoryByUproductid(id);
    }

    @Override
    public List<XtCategory> selectXtCategoryList(XtCategory xtCategory) {
        return xtCategoryMapper.selectXtCategoryList(xtCategory);
    }

    @Override
    public int insertXtCategory(XtCategory xtCategory) {
        //判断品类名字是否重复，重复的话就抛出异常
        String uname = xtCategory.getUname();
        XtCategory xtCategory1 = new XtCategory();
        xtCategory1.setUname(uname);
        List<XtCategory> xtCategories = selectXtCategoryList(xtCategory1);
        if(!xtCategories.isEmpty()){
            throw new SecurityException("名字重复了，请改名");
        }

        xtCategory.setId(IdUtil.getSnowflakeNextIdStr());
        return xtCategoryMapper.insertXtCategory(xtCategory);
    }

    @Override
    public int updateXtCategory(XtCategory xtCategory) {
        return xtCategoryMapper.updateXtCategory(xtCategory);
    }

    @Override
    public int deleteXtCategoryByIds(String[] ids) {
        return xtCategoryMapper.deleteXtCategoryByIds(ids);
    }

    @Override
    public int deleteXtCategoryByid(String id) {
        return xtCategoryMapper.deleteXtCategoryByid(id);
    }
}
