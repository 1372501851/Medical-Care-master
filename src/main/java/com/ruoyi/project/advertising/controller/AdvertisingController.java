package com.ruoyi.project.advertising.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.advertising.domain.Advertising;
import com.ruoyi.project.advertising.service.AdvertisingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Advertising)表控制层
 *
 * @author makejava
 * @since 2022-11-23 09:45:36
 */
@RestController
@RequestMapping("/advertising")
@Api(tags = "广告模块")
public class AdvertisingController {
    /**
     * 服务对象
     */
    @Resource
    private AdvertisingService advertisingService;

    /**
     * 分页查询
     *
     * @param advertising 筛选条件
     * @return 查询结果
     */
    @ApiOperation(value = "获取广告列表")
    @GetMapping("/list")
    public AjaxResult queryList(Advertising advertising) {
        List<Advertising> advertisings = advertisingService.queryList(advertising);
        Advertising advertising1 = advertisings.get(0);
        return AjaxResult.success(advertising1);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Advertising> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.advertisingService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param advertising 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Advertising> add(Advertising advertising) {
        return ResponseEntity.ok(this.advertisingService.insert(advertising));
    }

    /**
     * 编辑数据
     *
     * @param advertising 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Advertising> edit(Advertising advertising) {
        return ResponseEntity.ok(this.advertisingService.update(advertising));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.advertisingService.deleteById(id));
    }



}

