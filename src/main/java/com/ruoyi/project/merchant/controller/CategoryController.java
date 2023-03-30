package com.ruoyi.project.merchant.controller;


import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.merchant.domain.XtCategory;
import com.ruoyi.project.merchant.mapper.XtCategoryMapper;
import com.ruoyi.project.merchant.service.IXtCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author by hujun
 * @date 2023-02-07
 */
@RestController
@RequestMapping("/category")
@Api(tags = "品类模块")
public class CategoryController {

    @Autowired
    private IXtCategoryService iXtCategoryService;

    @Autowired
    private XtCategoryMapper xtCategoryMapper;


    /**
     * 获取品类详细信息
     */
    @ApiOperation(value = " 获取品类详细信息")
    @GetMapping(value = "/info/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(iXtCategoryService.selectXtCategoryByid(id));
    }

    /**
     * 新增品类
     */
    @ApiOperation(value = "新增品类")
    @Log(title = "品类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtCategory xtCategory)
    {
        if("".equals(xtCategory.getUname()) || xtCategory.getUname() == null || "".equals(xtCategory.getUplace())||xtCategory.getUplace()==null){
            AjaxResult.error("品类名字或品类位置为空，重新输入");
        }

        return AjaxResult.success(iXtCategoryService.insertXtCategory(xtCategory));
    }

    /**
     * 修改品类
     */
    @ApiOperation(value = "修改品类")
    @Log(title = "品类", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtCategory xtCategory)
    {
        return AjaxResult.success(iXtCategoryService.updateXtCategory(xtCategory));
    }

    /**
     * 删除品类
     */
    @ApiOperation(value = "删除单个品类")
    @Log(title = "品类", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return AjaxResult.success(iXtCategoryService.deleteXtCategoryByIds(ids));
    }

    @ApiOperation(value = "查询所有品类")
    @GetMapping("/list")
    public AjaxResult list(XtCategory xtCategory)
    {
        List<XtCategory> list = iXtCategoryService.selectXtCategoryList(xtCategory);

        return AjaxResult.success(list);
    }

    /**
     * 删除品类
     */
    @ApiOperation(value = "批量删除品类")
    @Log(title = "品类", businessType = BusinessType.DELETE)
    @DeleteMapping("/dels/{id}")
    public AjaxResult remove(@PathVariable String id)
    {
        return AjaxResult.success(iXtCategoryService.deleteXtCategoryByid(id));
    }

    @ApiOperation(value = "小刘专用，获取购买过该商品品类的客户数量")
    @GetMapping("/selectCategoryCusnum")
    public AjaxResult selectCategoryCusnum(String id)
    {
        return AjaxResult.success("成功",xtCategoryMapper.selectCategoryCusnum(id));
    };

}
