package com.ruoyi.project.advertising.service;

import com.ruoyi.project.advertising.domain.Advertising;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * (Advertising)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 09:45:46
 */
public interface AdvertisingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Advertising queryById(String id);

    /**
     * 分页查询
     *
     * @param advertising 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Advertising> queryByPage(Advertising advertising, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param advertising 实例对象
     * @return 实例对象
     */
    Advertising insert(Advertising advertising);

    /**
     * 修改数据
     *
     * @param advertising 实例对象
     * @return 实例对象
     */
    Advertising update(Advertising advertising);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<Advertising> queryList(Advertising advertising);
}
