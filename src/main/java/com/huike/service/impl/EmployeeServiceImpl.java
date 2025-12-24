package com.huike.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huike.entity.Department;
import com.huike.entity.Employee;
import com.huike.entity.Role;
import com.huike.mapper.DepartmentMapper;
import com.huike.mapper.EmployeeMapper;
import com.huike.query.EmployeeQuery;
import com.huike.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public PageInfo<Employee> list(EmployeeQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Employee> employees =  employeeMapper.list(qo.getKeyword(),qo.getDeptId());
        for(Employee employee:employees){
            //1.遍历集合，取出每个员工对应的部门id
            Long deptId = employee.getDeptId();
            //2.根据部门id查询部门对象，把部门对象赋值给员工对象
            Department department =  departmentMapper.getDepartmentByDeptId(deptId);
            employee.setDept(department);
        }
        PageInfo<Employee> pageInfo = new PageInfo<>(employees);

        return pageInfo;
    }

    //一个业务设计多张表的增删改，需要用事务

    @Override
    public void save(Employee employee, Integer[] roleIds) {
        //功能：1.保存员工数据到员工表；
        employeeMapper.save(employee);

        // 2.添加数据到员工角色中间表
        //  a.获得新增员工的主键
        Long employeeId = employee.getId();
        add2Mid(employeeId,roleIds);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        //根据id查询当前要编辑的员工数据employee（dept的id要赋值、roles属性要赋值）
        Employee employee = employeeMapper.getEmployeeById(id);
        //roles属性要赋值
        //根据id去查询员工对应的角色集合
        List<Role> roles =  employeeMapper.getRolesByEmpId(id);
        //把查询到的角色集合赋值给员工
        employee.setRoles(roles);
        return employee;
    }

    @Override
    public void update(Employee employee, Integer[] roleIds) {
        // 1.根据参数修改员工表；
        employeeMapper.updateEmployee(employee);
        // 2.修改(先删除原先数据，再添加新数据)中间表的数据
        // a. 根据员工id删除中间表数据
        Long employeeId = employee.getId();
        employeeMapper.deleteMidByEmpId(employeeId);
        // b. 添加数据到中间表
        add2Mid(employeeId,roleIds);
    }

    //添加数据到中间表
    public void add2Mid(Long employeeId,Integer[] roleIds){
        if(roleIds != null){
            //  b.把roleIds循环得到每个角色id
            for(Integer roleId :roleIds){
                //  c. 把新增员工的主键和角色id依次加入中间表
                employeeMapper.add2Mid(employeeId,roleId);
            }
        }
    }
}
