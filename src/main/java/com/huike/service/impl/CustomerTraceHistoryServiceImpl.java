package com.huike.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huike.entity.Customer;
import com.huike.entity.CustomerTraceHistory;
import com.huike.entity.Employee;
import com.huike.entity.SystemDictionaryItem;
import com.huike.mapper.CustomerMapper;
import com.huike.mapper.CustomerTraceHistoryMapper;
import com.huike.mapper.EmployeeMapper;
import com.huike.mapper.SystemDictionaryItemMapper;
import com.huike.query.HistoryQuery;
import com.huike.service.CustomerTraceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 客户跟进记录业务逻辑实现类
 */
@Service
@Transactional
public class CustomerTraceHistoryServiceImpl implements CustomerTraceHistoryService {
    
    @Autowired
    private CustomerTraceHistoryMapper traceHistoryMapper;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Autowired
    private SystemDictionaryItemMapper dictionaryItemMapper;
    
    @Override
    public List<CustomerTraceHistory> listByCustomerId(Long customerId) {
        List<CustomerTraceHistory> histories = traceHistoryMapper.listByCustomerId(customerId);
        for (CustomerTraceHistory history : histories) {
            fillHistoryRelations(history);
        }
        return histories;
    }
    
    @Override
    public PageInfo<CustomerTraceHistory> list(HistoryQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<CustomerTraceHistory> histories = traceHistoryMapper.list(qo.getKeyword());
        
        for (CustomerTraceHistory history : histories) {
            fillHistoryRelations(history);
        }
        
        return new PageInfo<>(histories);
    }
    
    @Override
    public CustomerTraceHistory getById(Long id) {
        CustomerTraceHistory history = traceHistoryMapper.getById(id);
        if (history != null) {
            fillHistoryRelations(history);
        }
        return history;
    }
    
    @Override
    public void save(CustomerTraceHistory history) {
        history.setInputTime(new Date());
        traceHistoryMapper.save(history);
    }
    
    @Override
    public void update(CustomerTraceHistory history) {
        traceHistoryMapper.update(history);
    }
    
    @Override
    public void delete(Long id) {
        traceHistoryMapper.delete(id);
    }
    
    @Override
    public List<CustomerTraceHistory> listRecentByCustomerId(Long customerId, Integer limit) {
        List<CustomerTraceHistory> histories = traceHistoryMapper.listRecentByCustomerId(customerId, limit);
        for (CustomerTraceHistory history : histories) {
            fillHistoryRelations(history);
        }
        return histories;
    }
    
    /**
     * 填充跟进记录关联对象
     */
    private void fillHistoryRelations(CustomerTraceHistory history) {
        // 填充客户信息
        if (history.getCustomerId() != null) {
            Customer customer = customerMapper.getCustomerById(history.getCustomerId());
            history.setCustomer(customer);
        }
        
        // 填充录入人员
        if (history.getInputUserId() != null) {
            Employee inputUser = employeeMapper.getEmployeeById(history.getInputUserId());
            history.setInputUser(inputUser);
        }
        
        // 填充跟进类型
        if (history.getTraceTypeId() != null) {
            SystemDictionaryItem traceType = dictionaryItemMapper.getById(history.getTraceTypeId());
            history.setTraceTypes(traceType);
        }
        
        // 填充跟进结果
        if (history.getTraceResult() != null) {
            SystemDictionaryItem traceResult = dictionaryItemMapper.getById(Long.valueOf(history.getTraceResult()));
            history.setTraceResults(traceResult);
        }
        
        // 填充类型
        if (history.getType() != null) {
            SystemDictionaryItem type = dictionaryItemMapper.getById(Long.valueOf(history.getType()));
            history.setTypes(type);
        }
    }
}
