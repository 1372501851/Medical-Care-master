package com.ruoyi.project.advertising.service.impl;

import com.ruoyi.project.advertising.domain.Advertising;
import com.ruoyi.project.advertising.mapper.AdvertisingMapper;
import com.ruoyi.project.advertising.service.AdvertisingService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Advertising)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 09:45:47
 */
@Service("advertisingService")
public class AdvertisingServiceImpl implements AdvertisingService {
    @Resource
    private AdvertisingMapper advertisingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Advertising queryById(String id) {
        return this.advertisingDao.queryById(id);
    }

    @Override
    public Page<Advertising> queryByPage(Advertising advertising, PageRequest pageRequest) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param advertising 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<Advertising> queryByPage(Advertising advertising, PageRequest pageRequest) {
//        long total = this.advertisingDao.count(advertising);
//        return new PageImpl<>(this.advertisingDao.queryAllByLimit(advertising, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param advertising 实例对象
     * @return 实例对象
     */
    @Override
    public Advertising insert(Advertising advertising) {
        this.advertisingDao.insert(advertising);
        return advertising;
    }

    /**
     * 修改数据
     *
     * @param advertising 实例对象
     * @return 实例对象
     */
    @Override
    public Advertising update(Advertising advertising) {
        this.advertisingDao.update(advertising);
        return this.queryById(advertising.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.advertisingDao.deleteById(id) > 0;
    }

    @Override
    public List<Advertising> queryList(Advertising advertising) {
        List<Advertising> advertisings = advertisingDao.queryList(advertising);
        return advertisings;
    }
}
