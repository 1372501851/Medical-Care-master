package com.ruoyi.project.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.project.common.domain.XtPaymentRecord;
import com.ruoyi.project.common.service.IXtPaymentRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 支付记录Controller
 * 
 * @author ruoyi
 * @date 2023-01-28
 */
@RestController
@RequestMapping("/system/record")
public class XtPaymentRecordController extends BaseController
{
    @Autowired
    private IXtPaymentRecordService xtPaymentRecordService;

    /**
     * 查询支付记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(XtPaymentRecord xtPaymentRecord)
    {
        startPage();
        List<XtPaymentRecord> list = xtPaymentRecordService.selectXtPaymentRecordList(xtPaymentRecord);
        return getDataTable(list);
    }

    /**
     * 导出支付记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "支付记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtPaymentRecord xtPaymentRecord)
    {
        List<XtPaymentRecord> list = xtPaymentRecordService.selectXtPaymentRecordList(xtPaymentRecord);
        ExcelUtil<XtPaymentRecord> util = new ExcelUtil<XtPaymentRecord>(XtPaymentRecord.class);
        util.exportExcel(response, list, "支付记录数据");
    }

    /**
     * 获取支付记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{paymentRecordId}")
    public AjaxResult getInfo(@PathVariable("paymentRecordId") String paymentRecordId)
    {
        return AjaxResult.success(xtPaymentRecordService.selectXtPaymentRecordByPaymentRecordId(paymentRecordId));
    }

    /**
     * 新增支付记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "支付记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtPaymentRecord xtPaymentRecord)
    {
        return toAjax(xtPaymentRecordService.insertXtPaymentRecord(xtPaymentRecord));
    }

    /**
     * 修改支付记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "支付记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtPaymentRecord xtPaymentRecord)
    {
        return toAjax(xtPaymentRecordService.updateXtPaymentRecord(xtPaymentRecord));
    }

    /**
     * 删除支付记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "支付记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{paymentRecordIds}")
    public AjaxResult remove(@PathVariable String[] paymentRecordIds)
    {
        return toAjax(xtPaymentRecordService.deleteXtPaymentRecordByPaymentRecordIds(paymentRecordIds));
    }
}
