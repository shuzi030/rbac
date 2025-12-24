package com.huike.controller;

import com.github.pagehelper.PageInfo;
import com.huike.entity.CustomerTraceHistory;
import com.huike.entity.Employee;
import com.huike.entity.SystemDictionaryItem;
import com.huike.query.HistoryQuery;
import com.huike.service.CustomerTraceHistoryService;
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
 * 客户跟进记录控制器
 */
@Controller
@RequestMapping("/traceHistory")
public class CustomerTraceHistoryController {
    
    @Autowired
    private CustomerTraceHistoryService traceHistoryService;
    
    @Autowired
    private SystemDictionaryItemService dictionaryItemService;
    
    /**
     * 跟进历史页面
     * 请求地址: /traceHistory/toTraceHistoryPage
     * 功能: 显示客户跟进记录列表
     */
    @RequestMapping("/toTraceHistoryPage")
    public String toTraceHistoryPage(Model model, @ModelAttribute("qo") HistoryQuery qo) {
        PageInfo<CustomerTraceHistory> pageInfo = traceHistoryService.list(qo);
        
        // 查询数据字典: 跟进类型、跟进结果
        List<SystemDictionaryItem> traceTypes = dictionaryItemService.listBySn("communicationMethod");
        List<SystemDictionaryItem> traceResults = dictionaryItemService.listBySn("wantedLevel");
        
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("traceTypes", traceTypes);
        model.addAttribute("traceResults", traceResults);
        
        return "history/tracehistorypage";
    }
    
    /**
     * 根据客户ID查询跟进记录
     * 请求地址: /traceHistory/listByCustomerId
     * 功能: Ajax获取指定客户的跟进记录
     */
    @RequestMapping("/listByCustomerId")
    @ResponseBody
    public List<CustomerTraceHistory> listByCustomerId(Long customerId) {
        return traceHistoryService.listByCustomerId(customerId);
    }
    
    /**
     * 跳转到跟进记录表单页面
     * 请求地址: /traceHistory/input
     * 功能: 显示跟进记录表单,如果有id则回显数据
     */
    @RequestMapping("/input")
    public String input(Model model, Long id, Long customerId) {
        if (id != null) {
            // 编辑回显
            CustomerTraceHistory history = traceHistoryService.getById(id);
            model.addAttribute("history", history);
        }
        
        if (customerId != null) {
            model.addAttribute("customerId", customerId);
        }
        
        // 查询数据字典
        List<SystemDictionaryItem> traceTypes = dictionaryItemService.listBySn("communicationMethod");
        List<SystemDictionaryItem> traceResults = dictionaryItemService.listBySn("wantedLevel");
        List<SystemDictionaryItem> types = dictionaryItemService.listBySn("tracePurpose");
        
        model.addAttribute("traceTypes", traceTypes);
        model.addAttribute("traceResults", traceResults);
        model.addAttribute("types", types);
        
        return "history/traceinput";
    }
    
    /**
     * 保存或更新跟进记录
     * 请求地址: /traceHistory/saveOrUpdate
     * 功能: 新增或修改跟进记录
     */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(CustomerTraceHistory history) {
        // 获取当前登录用户作为录入人员
        Subject subject = SecurityUtils.getSubject();
        Employee currentUser = (Employee) subject.getPrincipal();
        
        if (history.getId() == null) {
            // 新增
            history.setInputUserId(currentUser.getId());
            traceHistoryService.save(history);
        } else {
            // 更新
            traceHistoryService.update(history);
        }
        
        return "redirect:/traceHistory/toTraceHistoryPage";
    }
    
    /**
     * 删除跟进记录
     * 请求地址: /traceHistory/delete
     * 功能: 删除指定跟进记录
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            traceHistoryService.delete(id);
            result.put("success", true);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "删除失败: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * 查询最近的跟进记录
     * 请求地址: /traceHistory/listRecent
     * 功能: Ajax获取客户最近的跟进记录
     */
    @RequestMapping("/listRecent")
    @ResponseBody
    public List<CustomerTraceHistory> listRecent(Long customerId, Integer limit) {
        if (limit == null) {
            limit = 5; // 默认查询最近5条
        }
        return traceHistoryService.listRecentByCustomerId(customerId, limit);
    }
}
