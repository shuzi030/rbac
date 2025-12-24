package com.huike.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huike.entity.Role;
import com.huike.mapper.RoleMapper;
import com.huike.query.QueryObject;
import com.huike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getAll() {
        //
        List<Role> roles =  roleMapper.getAll();
        return roles;
    }

    @Override
    public PageInfo<Role> list(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> roles = roleMapper.getAll();
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        return pageInfo;
    }

    @Override
    public void save(Role role, Integer[] permissionIds) {
        //1.添加数据到角色表；
         roleMapper.save(role);
        // 2.添加数据到角色权限中间表
        //a.先获取到新增的角色id
        Long roleId = role.getId();
        //b.循环permissionIds，得到每个权限id
        if(permissionIds != null){
            for(Integer permissionId:permissionIds){
                //c. 分别把角色id和权限id加到中间表
                roleMapper.add2Mid(roleId,permissionId);
            }
        }
    }
}
