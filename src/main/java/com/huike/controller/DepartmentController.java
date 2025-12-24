package com.huike.controller;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Department;
import com.huike.query.QueryObject;
import com.huike.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    /*
    *    1）、部门查询
    *  a.请求地址 :  /department/list
       b.请求参数:   qo
       c.功能： 分页查询所有部门信息
       d.返回： 跳转到department/list.html（视图）    携带数据（pageInfo、qo）（模型）
    * */
    // 当有一个对象既需要接收参数，有需要返回数据，那么可以使用@ModelAttribute注解
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo){
        //pageInfo:MyBatis的分页插件的一个类，pageInfo中包含了所有分页相关的信息
        PageInfo<Department> pageInfo =  departmentService.list(qo);

        model.addAttribute("pageInfo",pageInfo);
        //thymleaf实现后端跳转html页面  。thymleaf默认的视图解析器前缀---templates/    后缀是----.html
        //  templates/department/list.html
        return "department/list"; //跳转到department/list.html（视图）
    }

    /*
    *    2）、部门添加
       a.请求地址 :  /department/saveOrUpdate
       b.请求参数:  name、sn
       c.功能：接收数据，添加到数据库
       d.返回： 跳转到department/list.html   分页查询部门并传回前端
       *
       *    3）、部门编辑
       a.请求地址 :  /department/saveOrUpdate
       b.请求参数: id、name、sn
       c.功能： 根据参数修改数据
       d.返回： 重定向到department/list接口
    * */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department department){
        if (department.getId() == null) {
            departmentService.save(department); //添加
        }else{
            departmentService.update(department); //修改
        }
        return "redirect:/department/list";
    }
}
