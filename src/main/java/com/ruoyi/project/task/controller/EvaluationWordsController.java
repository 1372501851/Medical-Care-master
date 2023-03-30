package com.ruoyi.project.task.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.task.domain.XtPingjiaWords;
import com.ruoyi.project.task.service.IXtPingjiaWordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-10-21
 */
@RestController
@RequestMapping("/evaluation/words")
@Api(tags = "评价用语模块")
public class EvaluationWordsController extends BaseController {

    @Autowired
    private IXtPingjiaWordsService xtPingjiaWordsService;

    /**
     * 查询设置评价词语列表
     */

    @ApiOperation(value = "查询设置评价词语列表")
    @GetMapping("/list")
    public TableDataInfo list(XtPingjiaWords xtPingjiaWords)
    {
        startPage();
        List<XtPingjiaWords> list = xtPingjiaWordsService.selectXtPingjiaWordsList(xtPingjiaWords);
        return getDataTable(list);
    }

    /**
     * 导出设置评价词语列表
     */
//    @ApiOperation(value = "导出设置评价词语列表")
//    @Log(title = "设置评价词语", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, XtPingjiaWords xtPingjiaWords)
//    {
//        List<XtPingjiaWords> list = xtPingjiaWordsService.selectXtPingjiaWordsList(xtPingjiaWords);
//        ExcelUtil<XtPingjiaWords> util = new ExcelUtil<XtPingjiaWords>(XtPingjiaWords.class);
//        util.exportExcel(response, list, "设置评价词语数据");
//    }

    /**
     * 获取设置评价词语详细信息
     */
    @ApiOperation(value = "获取设置评价词语详细信息")
    @GetMapping(value = "/info/{upingjiaWordsid}")
    public AjaxResult getInfo(@PathVariable("upingjiaWordsid") String upingjiaWordsid)
    {
        return AjaxResult.success(xtPingjiaWordsService.selectXtPingjiaWordsByUpingjiaWordsid(upingjiaWordsid));
    }

    /**
     * 新增设置评价词语
     */
    @ApiOperation(value = "新增设置评价词语")
    @Log(title = "设置评价词语", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtPingjiaWords xtPingjiaWords)
    {
        return toAjax(xtPingjiaWordsService.insertXtPingjiaWords(xtPingjiaWords));
    }

    /**
     * 修改设置评价词语
     */
    @ApiOperation(value = "修改设置评价词语")
    @Log(title = "设置评价词语", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtPingjiaWords xtPingjiaWords)
    {
        return toAjax(xtPingjiaWordsService.updateXtPingjiaWords(xtPingjiaWords));
    }

    /**
     * 删除设置评价词语
     */
    @ApiOperation(value = "删除设置评价词语")
    @Log(title = "设置评价词语", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{upingjiaWordsids}")
    public AjaxResult remove(@PathVariable String[] upingjiaWordsids)
    {
        return toAjax(xtPingjiaWordsService.deleteXtPingjiaWordsByUpingjiaWordsids(upingjiaWordsids));
    }


}
