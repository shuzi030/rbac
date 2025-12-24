package com.huike.mapper;

import com.huike.entity.CustomerTraceHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户跟进记录数据访问接口
 */
public interface CustomerTraceHistoryMapper {
    
    /**
     * 根据客户ID查询跟进记录
     * @param customerId 客户ID
     * @return 跟进记录列表
     */
    List<CustomerTraceHistory> listByCustomerId(@Param("customerId") Long customerId);
    
    /**
     * 分页查询跟进记录
     * @param keyword 关键字
     * @return 跟进记录列表
     */
    List<CustomerTraceHistory> list(@Param("keyword") String keyword);
    
    /**
     * 根据ID查询跟进记录详情
     * @param id 记录ID
     * @return 跟进记录对象
     */
    CustomerTraceHistory getById(Long id);
    
    /**
     * 新增跟进记录
     * @param history 跟进记录对象
     */
    void save(CustomerTraceHistory history);
    
    /**
     * 更新跟进记录
     * @param history 跟进记录对象
     */
    void update(CustomerTraceHistory history);
    
    /**
     * 删除跟进记录
     * @param id 记录ID
     */
    void delete(Long id);
    
    /**
     * 查询最近的跟进记录
     * @param customerId 客户ID
     * @param limit 限制条数
     * @return 跟进记录列表
     */
    List<CustomerTraceHistory> listRecentByCustomerId(@Param("customerId") Long customerId, @Param("limit") Integer limit);
}
