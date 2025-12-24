package com.huike.service;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Department;
import com.huike.query.QueryObject;

import java.util.List;

public interface DepartmentService {
    PageInfo<Department> list(QueryObject qo);

    void save(Department department);

    void update(Department department);

    List<Department> getAll();
}
