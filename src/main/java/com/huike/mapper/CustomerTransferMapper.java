package com.huike.mapper;

import com.huike.entity.CustomerTransfer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户转移记录数据访问接口
 */
public interface CustomerTransferMapper {
    
    /**
     * 根据客户ID查询转移记录
     * @param customerId 客户ID
     * @return 转移记录列表
     */
    List<CustomerTransfer> listByCustomerId(@Param("customerId") Long customerId);
    
    /**
     * 分页查询转移记录
     * @param keyword 关键字
     * @return 转移记录列表
     */
    List<CustomerTransfer> list(@Param("keyword") String keyword);
    
    /**
     * 根据ID查询转移记录详情
     * @param id 记录ID
     * @return 转移记录对象
     */
    CustomerTransfer getById(Long id);
    
    /**
     * 新增转移记录
     * @param transfer 转移记录对象
     */
    void save(CustomerTransfer transfer);
    
    /**
     * 删除转移记录
     * @param id 记录ID
     */
    void delete(Long id);
}
