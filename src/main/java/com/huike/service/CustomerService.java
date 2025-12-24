package com.huike.service;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Customer;
import com.huike.query.CustomerQuery;

import java.util.List;
import java.util.Map;

/**
 * 客户业务逻辑接口
 */
public interface CustomerService {
    
    /**
     * 分页查询客户列表
     * @param qo 查询对象
     * @return 分页结果
     */
    PageInfo<Customer> list(CustomerQuery qo);
    
    /**
     * 根据ID查询客户详情
     * @param id 客户ID
     * @return 客户对象
     */
    Customer getCustomerById(Long id);
    
    /**
     * 新增客户
     * @param customer 客户对象
     */
    void save(Customer customer);
    
    /**
     * 更新客户信息
     * @param customer 客户对象
     */
    void update(Customer customer);
    
    /**
     * 删除客户
     * @param id 客户ID
     */
    void delete(Long id);
    
    /**
     * 分配客户给销售人员
     * @param customerId 客户ID
     * @param sellerId 销售人员ID
     */
    void assignSeller(Long customerId, Long sellerId);
    
    /**
     * 查询客户池(未分配销售人员的客户)
     * @param qo 查询对象
     * @return 分页结果
     */
    PageInfo<Customer> listCustomerPool(CustomerQuery qo);
    
    /**
     * 根据销售人员ID和状态查询客户
     * @param sellerId 销售人员ID
     * @param qo 查询对象
     * @return 分页结果
     */
    PageInfo<Customer> listBySellerId(Long sellerId, CustomerQuery qo);
    
    /**
     * 更新客户状态
     * @param id 客户ID
     * @param status 新状态
     */
    void updateStatus(Long id, Integer status);
    
    /**
     * 统计各状态客户数量
     * @return 统计数据
     */
    List<Map<String, Object>> countByStatus();
    
    /**
     * 查询所有客户
     * @return 客户列表
     */
    List<Customer> getAll();
}
