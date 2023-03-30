package com.ruoyi.project.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.common.domain.XtMessage;
import com.ruoyi.project.common.service.IXtMessageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)Controller
 *
 * @author ruoyi
 * @date 2022-10-14
 */
@RestController
@RequestMapping("/web/message")
public class XtMessageController extends BaseController
{
    @Autowired
    private IXtMessageService xtMessageService;

    /**
     * 查询提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)列表
     */

    @GetMapping("/list")
    public TableDataInfo list(XtMessage xtMessage)
    {
        startPage();
        List<XtMessage> list = xtMessageService.selectXtMessageList(xtMessage);
        return getDataTable(list);
    }

    /**
     * 导出提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)列表
     */

    @Log(title = "提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtMessage xtMessage)
    {
        List<XtMessage> list = xtMessageService.selectXtMessageList(xtMessage);
        ExcelUtil<XtMessage> util = new ExcelUtil<XtMessage>(XtMessage.class);
        util.exportExcel(response, list, "提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)数据");
    }

    /**
     * 获取提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)详细信息
     */

    @GetMapping(value = "/{umessageid}")
    public AjaxResult getInfo(@PathVariable("umessageid") String umessageid)
    {
        return AjaxResult.success(xtMessageService.selectXtMessageByUmessageid(umessageid));
    }

    /**
     * 新增提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     */

    @Log(title = "提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtMessage xtMessage)
    {
        return toAjax(xtMessageService.insertXtMessage(xtMessage));
    }

    /**
     * 修改提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     */

    @Log(title = "提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtMessage xtMessage)
    {
        return toAjax(xtMessageService.updateXtMessage(xtMessage));
    }

    /**
     * 删除提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)
     */

    @Log(title = "提醒信息(患者朋友,相关医生,相关护士,相关护工,社区义工)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{umessageids}")
    public AjaxResult remove(@PathVariable String[] umessageids)
    {
        return toAjax(xtMessageService.deleteXtMessageByUmessageids(umessageids));
    }
}
