package com.huike.service;

import com.github.pagehelper.PageInfo;
import com.huike.entity.Role;
import com.huike.query.QueryObject;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    PageInfo<Role> list(QueryObject qo);

    void save(Role role, Integer[] permissionIds);
}
