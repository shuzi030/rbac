package com.huike.controller;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Permission;
import com.huike.entity.Role;
import com.huike.query.QueryObject;
import com.huike.service.PermissionService;
import com.huike.service.RoleService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;
    /*
    *    1）、角色查询
       a.请求地址:  /role/list
       b.请求参数: 无
       c.功能： 分页查询角色数据
       d.返回：跳转到role/list.html（视图）    携带数据（pageInfo、qo（currentPage、pageSize））（模型）
    * */
    @RequestMapping("/list")
    public String list(Model model,@ModelAttribute("qo") QueryObject qo){
        PageInfo<Role> pageInfo =  roleService.list(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "role/list";
    }
    /*
    *    2）、添加回显
       a.请求地址:  /role/input
       b.请求参数: 无
       c.功能： 查询所有权限，跳转到role/input.html
       d.返回：所有权限，跳转到role/input.html
    * */
    @RequestMapping("/input")
    public String input(Model model){
        List<Permission> permissions =  permissionService.getAll();
        model.addAttribute("permissions",permissions);
        return "role/input";
    }
    /*
    *   3）、角色添加
       a.请求地址:  /role/saveOrUpdate
       b.请求参数: name、sn    permissionIds
       c.功能：1.添加数据到角色表；  2.添加数据到角色权限中间表
       d.返回：重定向到/role/list接口
    * */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role, Integer[] permissionIds){
        roleService.save(role,permissionIds);
        return "redirect:/role/list";
    }
}
