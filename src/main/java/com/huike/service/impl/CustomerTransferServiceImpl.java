package com.huike.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huike.entity.Customer;
import com.huike.entity.CustomerTransfer;
import com.huike.entity.Employee;
import com.huike.mapper.CustomerMapper;
import com.huike.mapper.CustomerTransferMapper;
import com.huike.mapper.EmployeeMapper;
import com.huike.query.HistoryQuery;
import com.huike.service.CustomerTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 客户转移记录业务逻辑实现类
 */
@Service
@Transactional
public class CustomerTransferServiceImpl implements CustomerTransferService {
    
    @Autowired
    private CustomerTransferMapper transferMapper;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Override
    public List<CustomerTransfer> listByCustomerId(Long customerId) {
        List<CustomerTransfer> transfers = transferMapper.listByCustomerId(customerId);
        for (CustomerTransfer transfer : transfers) {
            fillTransferRelations(transfer);
        }
        return transfers;
    }
    
    @Override
    public PageInfo<CustomerTransfer> list(HistoryQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<CustomerTransfer> transfers = transferMapper.list(qo.getKeyword());
        
        for (CustomerTransfer transfer : transfers) {
            fillTransferRelations(transfer);
        }
        
        return new PageInfo<>(transfers);
    }
    
    @Override
    public CustomerTransfer getById(Long id) {
        CustomerTransfer transfer = transferMapper.getById(id);
        if (transfer != null) {
            fillTransferRelations(transfer);
        }
        return transfer;
    }
    
    @Override
    public void transfer(Long customerId, Long newSellerId, String reason, Long operatorId) {
        // 1. 查询客户当前的销售人员
        Customer customer = customerMapper.getCustomerById(customerId);
        Long oldSellerId = customer.getSellerId();
        
        // 2. 创建转移记录
        CustomerTransfer transfer = new CustomerTransfer();
        transfer.setCustomerId(customerId);
        transfer.setOperatorId(operatorId);
        transfer.setOperateTime(new Date());
        transfer.setOldSellerId(oldSellerId);
        transfer.setNewSellerId(newSellerId);
        transfer.setReason(reason);
        
        transferMapper.save(transfer);
        
        // 3. 更新客户的销售人员
        customerMapper.assignSeller(customerId, newSellerId);
    }
    
    @Override
    public void delete(Long id) {
        transferMapper.delete(id);
    }
    
    /**
     * 填充转移记录关联对象
     */
    private void fillTransferRelations(CustomerTransfer transfer) {
        // 填充客户信息
        if (transfer.getCustomerId() != null) {
            Customer customer = customerMapper.getCustomerById(transfer.getCustomerId());
            transfer.setCustomer(customer);
        }
        
        // 填充操作人员
        if (transfer.getOperatorId() != null) {
            Employee operator = employeeMapper.getEmployeeById(transfer.getOperatorId());
            transfer.setOperator(operator);
        }
        
        // 填充原销售人员
        if (transfer.getOldSellerId() != null) {
            Employee oldSeller = employeeMapper.getEmployeeById(transfer.getOldSellerId());
            transfer.setOldSeller(oldSeller);
        }
        
        // 填充新销售人员
        if (transfer.getNewSellerId() != null) {
            Employee newSeller = employeeMapper.getEmployeeById(transfer.getNewSellerId());
            transfer.setNewSeller(newSeller);
        }
    }
}
