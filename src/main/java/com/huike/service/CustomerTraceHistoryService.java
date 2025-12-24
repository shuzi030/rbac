package com.huike.service;

import com.github.pagehelper.PageInfo;
import com.huike.entity.CustomerTraceHistory;
import com.huike.query.HistoryQuery;

import java.util.List;

/**
 * 客户跟进记录业务逻辑接口
 */
public interface CustomerTraceHistoryService {
    
    /**
     * 根据客户ID查询跟进记录
     * @param customerId 客户ID
     * @return 跟进记录列表
     */
    List<CustomerTraceHistory> listByCustomerId(Long customerId);
    
    /**
     * 分页查询跟进记录
     * @param qo 查询对象
     * @return 分页结果
     */
    PageInfo<CustomerTraceHistory> list(HistoryQuery qo);
    
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
    List<CustomerTraceHistory> listRecentByCustomerId(Long customerId, Integer limit);
}
