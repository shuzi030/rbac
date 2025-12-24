package com.huike.service;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Employee;
import com.huike.query.EmployeeQuery;

public interface EmployeeService {
    PageInfo<Employee> list(EmployeeQuery qo);

    void save(Employee employee, Integer[] roleIds);

    Employee getEmployeeById(Long id);

    void update(Employee employee, Integer[] roleIds);
}
