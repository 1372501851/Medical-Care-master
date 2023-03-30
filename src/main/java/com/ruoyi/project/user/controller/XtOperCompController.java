package com.ruoyi.project.user.controller;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.project.user.domain.XtOperComp;
import com.ruoyi.project.user.service.IXtOperCompService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 代理商Controller
 *
 * @author ruoyi
 * @date 2022-11-01
 */
@RestController
@RequestMapping("/web/agent")
@Api(tags = "代理商管理模块")
public class XtOperCompController extends BaseController
{
    @Autowired
    private IXtOperCompService xtOperCompService;

    /**
     *
     *只能查询自己所创建的部门
     *下拉框展示的部门选择,和获取list是一样的,这一部分放在前端处理
     *
     * */

    @ApiOperation(value = "查询代理商列表")
    @GetMapping("/list")
    public TableDataInfo list( XtOperComp xtOperComp)
    {
        startPage();
        List<XtOperComp> list = xtOperCompService.selectXtOperCompList(xtOperComp);
        return getDataTable(list);
    }

    /**
     *
     * 查找comp(根据地区id)
     *
     * */

    @ApiOperation("查询comp列表")
    @GetMapping("/searchByType")
    public AjaxResult searchByType(String areaId,String userType){
        List<XtOperComp> xtOperComps = xtOperCompService.selectCompByType(areaId, userType);
        return AjaxResult.success(xtOperComps);

    }




    /**
     * 新增商家
     */

    @ApiOperation(value = "新增代理商")
    @Log(title = "商家", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody XtOperComp xtOperComp)
    {
        if (xtOperComp.getUoperCustomerid() == null){
            throw new ServiceException("商户号为空");
        }
        return toAjax(xtOperCompService.insertXtOperComp(xtOperComp));
    }

    /**
     * 修改代理商
     */

    @ApiOperation(value = "修改代理商")
    @Log(title = "代理商", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody XtOperComp xtOperComp)
    {
        return toAjax(xtOperCompService.updateXtOperComp(xtOperComp));
    }

    /**
     * 删除代理商
     */

    @ApiOperation(value = "删除代理商")
    @Log(title = "代理商", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{uoperCompid}")
    public AjaxResult remove(@PathVariable String uoperCompid)
    {
        return toAjax(xtOperCompService.deleteXtOperCompByUoperCompid(uoperCompid));
    }



    /**
     * 获取代理商详细信息
     */
    @ApiOperation(value = "获取代理商详细信息")
    @GetMapping(value = "/info/{uoperCompid}")
    public AjaxResult getInfo(@PathVariable("uoperCompid") String uoperCompid)
    {
        return AjaxResult.success(xtOperCompService.selectXtOperCompByUoperCompid(uoperCompid));
    }




    /**
     * 导出代理商列表
     */
    @Log(title = "代理商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出为excel")
    public void export(HttpServletResponse response, XtOperComp xtOperComp)
    {
        List<XtOperComp> list = xtOperCompService.selectXtOperCompList(xtOperComp);
        ExcelUtil<XtOperComp> util = new ExcelUtil<XtOperComp>(XtOperComp.class);
        util.exportExcel(response, list, "代理商数据");
    }



}
