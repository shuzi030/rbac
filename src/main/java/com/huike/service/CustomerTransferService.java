package com.huike.service;

import com.github.pagehelper.PageInfo;
import com.huike.entity.CustomerTransfer;
import com.huike.query.HistoryQuery;

import java.util.List;

/**
 * 客户转移记录业务逻辑接口
 */
public interface CustomerTransferService {
    
    /**
     * 根据客户ID查询转移记录
     * @param customerId 客户ID
     * @return 转移记录列表
     */
    List<CustomerTransfer> listByCustomerId(Long customerId);
    
    /**
     * 分页查询转移记录
     * @param qo 查询对象
     * @return 分页结果
     */
    PageInfo<CustomerTransfer> list(HistoryQuery qo);
    
    /**
     * 根据ID查询转移记录详情
     * @param id 记录ID
     * @return 转移记录对象
     */
    CustomerTransfer getById(Long id);
    
    /**
     * 客户转移操作
     * @param customerId 客户ID
     * @param newSellerId 新销售人员ID
     * @param reason 转移原因
     * @param operatorId 操作人ID
     */
    void transfer(Long customerId, Long newSellerId, String reason, Long operatorId);
    
    /**
     * 删除转移记录
     * @param id 记录ID
     */
    void delete(Long id);
}
