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
import com.ruoyi.project.common.domain.XtChat;
import com.ruoyi.project.common.service.IXtChatService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 聊天记录Controller
 *
 * @author ruoyi
 * @date 2022-10-14
 */
@RestController
@RequestMapping("/web/chat")
public class XtChatController extends BaseController
{
    @Autowired
    private IXtChatService xtChatService;

    /**
     * 查询聊天记录列表
     */

    @GetMapping("/list")
    public TableDataInfo list(XtChat xtChat)
    {
        startPage();
        List<XtChat> list = xtChatService.selectXtChatList(xtChat);
        return getDataTable(list);
    }

    /**
     * 导出聊天记录列表
     */

    @Log(title = "聊天记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, XtChat xtChat)
    {
        List<XtChat> list = xtChatService.selectXtChatList(xtChat);
        ExcelUtil<XtChat> util = new ExcelUtil<XtChat>(XtChat.class);
        util.exportExcel(response, list, "聊天记录数据");
    }

    /**
     * 获取聊天记录详细信息
     */

    @GetMapping(value = "/{uchatid}")
    public AjaxResult getInfo(@PathVariable("uchatid") String uchatid)
    {
        return AjaxResult.success(xtChatService.selectXtChatByUchatid(uchatid));
    }

    /**
     * 新增聊天记录
     */

    @Log(title = "聊天记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XtChat xtChat)
    {
        return toAjax(xtChatService.insertXtChat(xtChat));
    }

    /**
     * 修改聊天记录
     */

    @Log(title = "聊天记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XtChat xtChat)
    {
        return toAjax(xtChatService.updateXtChat(xtChat));
    }

    /**
     * 删除聊天记录
     */

    @Log(title = "聊天记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uchatids}")
    public AjaxResult remove(@PathVariable String[] uchatids)
    {
        return toAjax(xtChatService.deleteXtChatByUchatids(uchatids));
    }
}
