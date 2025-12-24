package com.huike.controller;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Department;
import com.huike.entity.Employee;
import com.huike.entity.Role;
import com.huike.query.EmployeeQuery;
import com.huike.query.QueryObject;
import com.huike.service.DepartmentService;
import com.huike.service.EmployeeService;
import com.huike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    /*
    *  1）、员工查询
       a.请求地址:  /employee/list
       b.请求参数: 无
       c.功能： 分页查询员工数据
       d.返回：跳转到employee/list.html（视图）   携带数据（departments、pageInfo、qo（currentPage、pageSize、keyword、deptId））（模型）
    * */
    @RequestMapping("/list")
    public String list(Model model,@ModelAttribute("qo") EmployeeQuery qo){
        //善良：不做坏事儿
        PageInfo<Employee> pageInfo =  employeeService.list(qo);
        //调用departmentservice查询所有部门数据
        List<Department> departments =  departmentService.getAll();

        model.addAttribute("departments",departments);
        model.addAttribute("pageInfo",pageInfo);
        return "employee/list";
    }

    //   2）、跳转到员工添加input页面
    //       a.请求地址:  /employee/input
    //       b.请求参数: 无
    //       c.功能： 跳转到员工添加impuinput页面，查询roles--所有角色、departments--所有部门
    //       d.返回：跳转到employee/input.html（视图）    携带数据（roles、departments）
    /*
    *    4）、编辑回显
       a.请求地址:  /employee/input
       b.请求参数: id
       c.功能： 跳转到员工添加impuinput页面，根据id查询当前要编辑的员工数据employee（dept的id要赋值、roles属性要赋值）、roles、departments
       d.返回：跳转到employee/input.html（视图）    携带数据（roles、departments）
    * */
    @RequestMapping("/input")
    public String input(Model model,Long id){
        if(id != null){
            //编辑回显
            //根据id查询当前要编辑的员工数据employee（dept的id要赋值、roles属性要赋值）
            Employee employee =  employeeService.getEmployeeById(id);
            model.addAttribute("employee",employee);
        }
        //调用departmentservice查询所有部门数据
        List<Department> departments =  departmentService.getAll();
        //查询所有角色
        List<Role> roles =  roleService.getAll();
        model.addAttribute("departments",departments);
        model.addAttribute("roles",roles);
        return "employee/input";
    }


    /*
    *    3）、添加员工
       a.请求地址:  /employee/saveOrUpdate
       b.请求参数:username、name、password、repassword、email、age、dept.id
                 roleIds
       c.功能：1.保存员工数据到员工表；2.添加数据到中间表
       d.返回： 重定向到/employee/list接口
       *
       *    5）、编辑员工
       a.请求地址:  /employee/saveOrUpdate
       b.请求参数：id、name、email、age、dept.id
                  roleIds
       c.功能：1.根据参数修改员工表；2.修改中间表的数据
       d.返回： 重定向到/employee/list接口
    *
    * */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee,Integer[] roleIds){
        if(employee.getId() == null){
            employeeService.save(employee,roleIds);
        }else{
            employeeService.update(employee,roleIds);
        }
        return "redirect:/employee/list";
    }
}
