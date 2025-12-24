package com.huike.controller;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Permission;
import com.huike.query.QueryObject;
import com.huike.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    /*
    * 6. 权限查询
   a.请求地址: /permission/list
   b.请求参数: 无
   c.功能： 分页查询权限数据
   d.返回： 跳转到permission.list.html页面  返回pageInfo、qo
    * */
    @RequestMapping("/list")
    public String list(Model model,@ModelAttribute("qo") QueryObject qo){

        PageInfo<Permission> pageInfo =  permissionService.list(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "permission/list";
    }
}
