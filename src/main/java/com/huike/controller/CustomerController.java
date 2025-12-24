package com.huike.controller;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Customer;
import com.huike.entity.Employee;
import com.huike.entity.SystemDictionaryItem;
import com.huike.query.CustomerQuery;
import com.huike.service.CustomerService;
import com.huike.service.EmployeeService;
import com.huike.service.SystemDictionaryItemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户管理控制器
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private SystemDictionaryItemService dictionaryItemService;
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * 客户列表页面
     * 请求地址: /customer/list
     * 功能: 分页查询所有客户
     */
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") CustomerQuery qo) {
        PageInfo<Customer> pageInfo = customerService.list(qo);
        
        // 查询数据字典: 职业、来源、跟进类型
        List<SystemDictionaryItem> jobs = dictionaryItemService.listBySn("job");
        List<SystemDictionaryItem> sources = dictionaryItemService.listBySn("source");
        List<SystemDictionaryItem> traceTypes = dictionaryItemService.listBySn("communicationMethod");
        
        // 查询所有员工作为销售人员选项
        List<Employee> sellers = employeeService.getAll();
        
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageInfoJob", jobs);
        model.addAttribute("pageInfoSource", sources);
        model.addAttribute("pageInfoTraceType", traceTypes);
        model.addAttribute("sellers", sellers);
        
        return "customer/list";
    }
    
    /**
     * 客户池页面
     * 请求地址: /customer/customerPool
     * 功能: 显示未分配销售人员的客户
     */
    @RequestMapping("/customerPool")
    public String customerPool(Model model, @ModelAttribute("qo") CustomerQuery qo) {
        PageInfo<Customer> pageInfo = customerService.listCustomerPool(qo);
        
        model.addAttribute("pageInfo", pageInfo);
        
        return "customer/customerpoollist";
    }
    
    /**
     * 潜在客户列表
     * 请求地址: /customer/potentialCustomer
     * 功能: 显示状态为潜在客户(status=0)的客户
     */
    @RequestMapping("/potentialCustomer")
    public String potentialCustomer(Model model, @ModelAttribute("qo") CustomerQuery qo) {
        qo.setStatus(0L); // 潜在客户状态
        PageInfo<Customer> pageInfo = customerService.list(qo);
        
        model.addAttribute("pageInfo", pageInfo);
        
        return "customer/potentialcustomerlist";
    }
    
    /**
     * 正式客户列表
     * 请求地址: /customer/formalCustomer
     * 功能: 显示状态为正式客户(status=1)的客户
     */
    @RequestMapping("/formalCustomer")
    public String formalCustomer(Model model, @ModelAttribute("qo") CustomerQuery qo) {
        qo.setStatus(1L); // 正式客户状态
        PageInfo<Customer> pageInfo = customerService.list(qo);
        
        model.addAttribute("pageInfo", pageInfo);
        
        return "customer/formallist";
    }
    
    /**
     * 流失客户列表
     * 请求地址: /customer/loseCustomer
     * 功能: 显示状态为流失客户(status=2)的客户
     */
    @RequestMapping("/loseCustomer")
    public String loseCustomer(Model model, @ModelAttribute("qo") CustomerQuery qo) {
        qo.setStatus(2L); // 流失客户状态
        PageInfo<Customer> pageInfo = customerService.list(qo);
        
        model.addAttribute("pageInfo", pageInfo);
        
        return "customer/losecustomerlist";
    }
    
    /**
     * 失败客户列表
     * 请求地址: /customer/failCustomer
     * 功能: 显示状态为失败客户(status=3)的客户
     */
    @RequestMapping("/failCustomer")
    public String failCustomer(Model model, @ModelAttribute("qo") CustomerQuery qo) {
        qo.setStatus(3L); // 失败客户状态
        PageInfo<Customer> pageInfo = customerService.list(qo);
        
        model.addAttribute("pageInfo", pageInfo);
        
        return "customer/failcustomerlist";
    }
    
    /**
     * 跳转到客户添加/编辑页面
     * 请求地址: /customer/input
     * 功能: 显示客户表单页面,如果有id则回显数据
     */
    @RequestMapping("/input")
    public String input(Model model, Long id) {
        if (id != null) {
            // 编辑回显
            Customer customer = customerService.getCustomerById(id);
            model.addAttribute("customer", customer);
        }
        
        // 查询数据字典
        List<SystemDictionaryItem> jobs = dictionaryItemService.listBySn("job");
        List<SystemDictionaryItem> sources = dictionaryItemService.listBySn("source");
        
        model.addAttribute("jobs", jobs);
        model.addAttribute("sources", sources);
        
        return "customer/input";
    }
    
    /**
     * 保存或更新客户
     * 请求地址: /customer/saveOrUpdate
     * 功能: 新增或修改客户信息
     */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Customer customer) {
        // 获取当前登录用户作为录入人员
        Subject subject = SecurityUtils.getSubject();
        Employee currentUser = (Employee) subject.getPrincipal();
        
        if (customer.getId() == null) {
            // 新增
            customer.setInputUserId(currentUser.getId());
            customer.setStatus(0); // 默认为潜在客户
            customerService.save(customer);
        } else {
            // 更新
            customerService.update(customer);
        }
        
        return "redirect:/customer/list";
    }
    
    /**
     * 删除客户
     * 请求地址: /customer/delete
     * 功能: 删除指定客户
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            customerService.delete(id);
            result.put("success", true);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "删除失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 分配客户给销售人员
     * 请求地址: /customer/assign
     * 功能: 从客户池分配客户给销售人员
     */
    @RequestMapping("/assign")
    @ResponseBody
    public Map<String, Object> assignSeller(Long customerId, Long sellerId) {
        Map<String, Object> result = new HashMap<>();
        try {
            customerService.assignSeller(customerId, sellerId);
            result.put("success", true);
            result.put("msg", "分配成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "分配失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 更新客户状态
     * 请求地址: /customer/updateStatus
     * 功能: 修改客户状态(潜在、正式、流失、失败)
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(Long id, Integer status) {
        Map<String, Object> result = new HashMap<>();
        try {
            customerService.updateStatus(id, status);
            result.put("success", true);
            result.put("msg", "状态更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "状态更新失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 客户数据统计页面
     * 请求地址: /customer/echarts
     * 功能: 显示客户数据可视化图表
     */
    @RequestMapping("/echarts")
    public String echarts(Model model) {
        // 统计各状态客户数量
        List<Map<String, Object>> statusCount = customerService.countByStatus();
        model.addAttribute("statusCount", statusCount);
        
        return "customer/echarts";
    }
}
