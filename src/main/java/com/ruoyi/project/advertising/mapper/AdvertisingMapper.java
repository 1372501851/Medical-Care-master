package com.ruoyi.project.advertising.mapper;

import com.ruoyi.project.advertising.domain.Advertising;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Advertising)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 09:45:36
 */
public interface AdvertisingMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Advertising queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param advertising 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Advertising> queryList(Advertising advertising);

    /**
     * 统计总行数
     *
     * @param advertising 查询条件
     * @return 总行数
     */
    long count(Advertising advertising);

    /**
     * 新增数据
     *
     * @param advertising 实例对象
     * @return 影响行数
     */
    int insert(Advertising advertising);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Advertising> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Advertising> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Advertising> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Advertising> entities);

    /**
     * 修改数据
     *
     * @param advertising 实例对象
     * @return 影响行数
     */
    int update(Advertising advertising);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

