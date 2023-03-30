package com.ruoyi.project.find.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.find.domain.FindModel;
import com.ruoyi.project.find.domain.dto.AddModel;
import com.ruoyi.project.find.domain.query.QueryModel;
import com.ruoyi.project.find.domain.query.QueryWork;
import com.ruoyi.project.find.domain.vo.ModelVo;
import com.ruoyi.project.find.domain.vo.OrderStatus;
import com.ruoyi.project.find.domain.vo.RobOrderVo;
import com.ruoyi.project.find.service.IFindModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;

import javax.servlet.http.HttpServletResponse;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-11-29
 */
@RestController
@RequestMapping("/app/model")
@Api(tags = "app主要功能模块")
//@Slf4j
public class FindModelController extends BaseController
{
    @Autowired
    private IFindModelService findModelService;



    /**
     * 新增【请填写功能名称】
     */

    @Log(title = "新增服务订单", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增服务订单")
    @PostMapping("/add")

    public AjaxResult add(@RequestBody AddModel addModel)
    {
        findModelService.insertFindModel(addModel);
        return AjaxResult.success();
    }

    /**
     * 查询【请填写功能名称】列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "获取服务订单列表")
    public AjaxResult list(QueryModel queryModel)
    {
        List<ModelVo> list = findModelService.selectFindModelListByModel(queryModel);
        Collections.reverse(list);
        return AjaxResult.success(list);
    }



    @ApiOperation(value = "抢单")
    @GetMapping("/rob")
    public AjaxResult rob(String id)
    {
        findModelService.robOrder(id);
        return AjaxResult.success();
    }

    @ApiOperation(value = "接单")
    @GetMapping("/accept")
    public AjaxResult accept(String id)
    {
        findModelService.acceptOrder(id);
        return AjaxResult.success();
    }



    /**这个是查询自己发布的,或者自己接受的订单列表*/


    /**
     * 导出【请填写功能名称】列表
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FindModel findModel)
    {
        List<FindModel> list = findModelService.selectFindModelList(findModel);
        ExcelUtil<FindModel> util = new ExcelUtil<FindModel>(FindModel.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(findModelService.selectFindModelById(id));
    }



    /**
     * 修改【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FindModel findModel)
    {
        return toAjax(findModelService.updateFindModel(findModel));
    }

    /**
     * 删除【请填写功能名称】
     */

    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(findModelService.deleteFindModelByIds(ids));
    }





}
