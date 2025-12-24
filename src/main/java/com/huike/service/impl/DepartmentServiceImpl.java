package com.huike.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huike.entity.Department;
import com.huike.mapper.DepartmentMapper;
import com.huike.query.QueryObject;
import com.huike.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public PageInfo<Department> list(QueryObject qo) {
        //分页查询部门数据
        //1.开启分页查询
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());  //  代替sql中的limit
        //2.调用mapper查询数据
        List<Department> departments =  departmentMapper.list();
        //3.封装PageInfo
        PageInfo<Department> pageInfo = new PageInfo<>(departments);
        return pageInfo;
    }

    @Override
    public void save(Department department) {
        departmentMapper.save(department);
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public List<Department> getAll() {
        //2.调用mapper查询数据
        List<Department> departments =  departmentMapper.list();
        return departments;
    }
}
