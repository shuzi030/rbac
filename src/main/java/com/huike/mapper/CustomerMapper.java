package com.huike.mapper;

import com.huike.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户数据访问接口
 */
public interface CustomerMapper {
    
    /**
     * 分页查询客户列表
     * @param keyword 关键字(姓名/电话)
     * @param status 客户状态
     * @return 客户列表
     */
    List<Customer> list(@Param("keyword") String keyword, @Param("status") Integer status);
    
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
    void assignSeller(@Param("customerId") Long customerId, @Param("sellerId") Long sellerId);
    
    /**
     * 查询客户池(未分配销售人员的客户)
     * @param keyword 关键字
     * @return 客户列表
     */
    List<Customer> listCustomerPool(@Param("keyword") String keyword);
    
    /**
     * 根据销售人员ID查询客户
     * @param sellerId 销售人员ID
     * @param status 客户状态
     * @return 客户列表
     */
    List<Customer> listBySellerId(@Param("sellerId") Long sellerId, @Param("status") Integer status);
    
    /**
     * 更新客户状态
     * @param id 客户ID
     * @param status 新状态
     */
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 统计各状态客户数量
     * @return 统计数据
     */
    List<java.util.Map<String, Object>> countByStatus();
    
    /**
     * 查询所有客户
     * @return 客户列表
     */
    List<Customer> getAll();
}
