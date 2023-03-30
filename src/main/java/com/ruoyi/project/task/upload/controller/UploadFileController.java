package com.ruoyi.project.task.upload.controller;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by hujun
 * @date 2022-11-15
 */

@Api(tags = "文件上传模块")
@RestController
@RequestMapping("/upload")
public class UploadFileController {

    /**
     * 图片上传
     */
    @Log(title = "图片上传", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "图片上传接口")
    @PostMapping("/image")
    public String image(@RequestPart MultipartFile file) throws Exception
    {
        if (!file.isEmpty())
        {
            //路劲,文件,允许的类型
            String pathName = FileUploadUtils.upload(RuoYiConfig.getProfile(), file, MimeTypeUtils.IMAGE_EXTENSION);
//            AjaxResult ajax = AjaxResult.success();
//            ajax.put("data", pathName);
            return pathName;
        }
        return "";
    }

    /**
     * 多图片上传
     */
    @Log(title = "图片上传", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "多图片上传接口")
    @PostMapping("/images")
    public AjaxResult images(@RequestParam("files") MultipartFile[] files) throws Exception
    {
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isEmpty())
            {
                //路劲,文件,允许的类型
                String pathName = FileUploadUtils.upload(RuoYiConfig.getProfile(), files[i], MimeTypeUtils.IMAGE_EXTENSION);
                urlList.add(pathName);
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrlList", urlList);
            return ajax;
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

    /**
     * 视频文件上传
     */
    @Log(title = "视频上传", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "视频文件上传接口")
    @PostMapping("/video")
    public String video(@RequestPart MultipartFile file) throws Exception
    {
        if (!file.isEmpty())
        {
            //路劲,文件,允许的类型
            String pathName = FileUploadUtils.upload(RuoYiConfig.getProfile(), file, MimeTypeUtils.VIDEO_EXTENSION);
//            AjaxResult ajax = AjaxResult.success();
//            ajax.put("videoUrl", pathName);
            return pathName;
        }
        return "";
    }

    /**
     * 图片上传
     */
    @Log(title = "默认文件上次", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "默认文件上传接口")
    @PostMapping("/defaultdefaultFile")
    public String defaultFile(@RequestPart MultipartFile file) throws Exception
    {
        if (!file.isEmpty())
        {
            //路劲,文件,允许的类型
            String pathName = FileUploadUtils.upload(RuoYiConfig.getProfile(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            return pathName;
        }
        return "";
    }


}
