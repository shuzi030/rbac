package com.huike.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huike.entity.Permission;
import com.huike.mapper.PermissionMapper;
import com.huike.query.QueryObject;
import com.huike.service.PermissionService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getAll() {
        List<Permission> permissions =  permissionMapper.getAll();
        return permissions;
    }

    @Override
    public PageInfo<Permission> list(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> permissions =  permissionMapper.getAll();
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        return pageInfo;
    }
}
