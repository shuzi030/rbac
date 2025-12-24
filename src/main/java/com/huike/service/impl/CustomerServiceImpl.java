package com.huike.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huike.entity.Customer;
import com.huike.entity.Employee;
import com.huike.entity.SystemDictionaryItem;
import com.huike.mapper.CustomerMapper;
import com.huike.mapper.EmployeeMapper;
import com.huike.mapper.SystemDictionaryItemMapper;
import com.huike.query.CustomerQuery;
import com.huike.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户业务逻辑实现类
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Autowired
    private SystemDictionaryItemMapper dictionaryItemMapper;
    
    @Override
    public PageInfo<Customer> list(CustomerQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Customer> customers = customerMapper.list(qo.getKeyword(), qo.getStatus().intValue());
        
        // 填充关联对象
        for (Customer customer : customers) {
            fillCustomerRelations(customer);
        }
        
        return new PageInfo<>(customers);
    }
    
    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerMapper.getCustomerById(id);
        if (customer != null) {
            fillCustomerRelations(customer);
        }
        return customer;
    }
    
    @Override
    public void save(Customer customer) {
        customer.setInputTime(new Date());
        customerMapper.save(customer);
    }
    
    @Override
    public void update(Customer customer) {
        customerMapper.update(customer);
    }
    
    @Override
    public void delete(Long id) {
        customerMapper.delete(id);
    }
    
    @Override
    public void assignSeller(Long customerId, Long sellerId) {
        customerMapper.assignSeller(customerId, sellerId);
    }
    
    @Override
    public PageInfo<Customer> listCustomerPool(CustomerQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Customer> customers = customerMapper.listCustomerPool(qo.getKeyword());
        
        // 填充关联对象
        for (Customer customer : customers) {
            fillCustomerRelations(customer);
        }
        
        return new PageInfo<>(customers);
    }
    
    @Override
    public PageInfo<Customer> listBySellerId(Long sellerId, CustomerQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Customer> customers = customerMapper.listBySellerId(sellerId, qo.getStatus().intValue());
        
        // 填充关联对象
        for (Customer customer : customers) {
            fillCustomerRelations(customer);
        }
        
        return new PageInfo<>(customers);
    }
    
    @Override
    public void updateStatus(Long id, Integer status) {
        customerMapper.updateStatus(id, status);
    }
    
    @Override
    public List<Map<String, Object>> countByStatus() {
        return customerMapper.countByStatus();
    }
    
    @Override
    public List<Customer> getAll() {
        return customerMapper.getAll();
    }
    
    /**
     * 填充客户关联对象(职业、来源、销售人员、录入人员等)
     */
    private void fillCustomerRelations(Customer customer) {
        // 填充职业
        if (customer.getJobId() != null) {
            SystemDictionaryItem job = dictionaryItemMapper.getById(customer.getJobId());
            customer.setJob(job);
        }
        
        // 填充来源
        if (customer.getSourceId() != null) {
            SystemDictionaryItem source = dictionaryItemMapper.getById(customer.getSourceId());
            customer.setSource(source);
        }
        
        // 填充销售人员
        if (customer.getSellerId() != null) {
            Employee seller = employeeMapper.getEmployeeById(customer.getSellerId());
            customer.setSeller(seller);
        }
        
        // 填充录入人员
        if (customer.getInputUserId() != null) {
            Employee inputUser = employeeMapper.getEmployeeById(customer.getInputUserId());
            customer.setInputUser(inputUser);
        }
    }
}
