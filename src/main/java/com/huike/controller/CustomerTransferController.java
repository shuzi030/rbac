package com.huike.controller;

import com.github.pagehelper.PageInfo;
import com.huike.entity.CustomerTransfer;
import com.huike.entity.Employee;
import com.huike.query.HistoryQuery;
import com.huike.service.CustomerTransferService;
import com.huike.service.EmployeeService;
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
 * 客户转移记录控制器
 */
@Controller
@RequestMapping("/transfer")
public class CustomerTransferController {
    
    @Autowired
    private CustomerTransferService transferService;
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * 转移历史页面
     * 请求地址: /transfer/toTransferPage
     * 功能: 显示客户转移记录列表
     */
    @RequestMapping("/toTransferPage")
    public String toTransferPage(Model model, @ModelAttribute("qo") HistoryQuery qo) {
        PageInfo<CustomerTransfer> pageInfo = transferService.list(qo);
        
        model.addAttribute("pageInfo", pageInfo);
        
        return "history/transferpage";
    }
    
    /**
     * 根据客户ID查询转移记录
     * 请求地址: /transfer/listByCustomerId
     * 功能: Ajax获取指定客户的转移记录
     */
    @RequestMapping("/listByCustomerId")
    @ResponseBody
    public List<CustomerTransfer> listByCustomerId(Long customerId) {
        return transferService.listByCustomerId(customerId);
    }
    
    /**
     * 跳转到客户转移页面
     * 请求地址: /transfer/input
     * 功能: 显示客户转移表单
     */
    @RequestMapping("/input")
    public String input(Model model, Long customerId) {
        if (customerId != null) {
            model.addAttribute("customerId", customerId);
        }
        
        // 查询所有员工作为可选的销售人员
        // List<Employee> employees = employeeService.getAll();
        // model.addAttribute("employees", employees);
        
        return "history/transferinput";
    }
    
    /**
     * 执行客户转移
     * 请求地址: /transfer/doTransfer
     * 功能: 将客户从一个销售人员转移到另一个销售人员
     * 参数: customerId-客户ID, newSellerId-新销售人员ID, reason-转移原因
     */
    @RequestMapping("/doTransfer")
    @ResponseBody
    public Map<String, Object> doTransfer(Long customerId, Long newSellerId, String reason) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 获取当前登录用户作为操作人员
            Subject subject = SecurityUtils.getSubject();
            Employee currentUser = (Employee) subject.getPrincipal();
            
            transferService.transfer(customerId, newSellerId, reason, currentUser.getId());
            
            result.put("success", true);
            result.put("msg", "转移成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "转移失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 批量转移客户
     * 请求地址: /transfer/batchTransfer
     * 功能: 批量将多个客户转移给指定销售人员
     * 参数: customerIds-客户ID数组, newSellerId-新销售人员ID, reason-转移原因
     */
    @RequestMapping("/batchTransfer")
    @ResponseBody
    public Map<String, Object> batchTransfer(Long[] customerIds, Long newSellerId, String reason) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 获取当前登录用户作为操作人员
            Subject subject = SecurityUtils.getSubject();
            Employee currentUser = (Employee) subject.getPrincipal();
            
            for (Long customerId : customerIds) {
                transferService.transfer(customerId, newSellerId, reason, currentUser.getId());
            }
            
            result.put("success", true);
            result.put("msg", "批量转移成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "批量转移失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 删除转移记录
     * 请求地址: /transfer/delete
     * 功能: 删除指定转移记录
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            transferService.delete(id);
            result.put("success", true);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "删除失败: " + e.getMessage());
        }
        return result;
    }
}
