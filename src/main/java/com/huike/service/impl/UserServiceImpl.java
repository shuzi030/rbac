package com.huike.service.impl;

import com.huike.entity.Employee;
import com.huike.entity.Permission;
import com.huike.entity.Role;
import com.huike.mapper.EmployeeMapper;
import com.huike.mapper.PermissionMapper;
import com.huike.mapper.RoleMapper;
import com.huike.mapper.UserMapper;
import com.huike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    //
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Employee getEmployeeByUserName(String username) {
        Employee employee = userMapper.findEmployeeByUserName(username);
        //获取用户id
        Long employeeId = employee.getId();
        //根据用户id查询所有角色，把角色赋值给用户
        List<Role> roles = employeeMapper.getRolesByEmpId(employeeId);
        //循环所有角色
        for(Role role:roles){
            //拿到角色id，根据角色id查询对应的权限，把权限赋值给角色
            Long roleId = role.getId();
            List<Permission> permissions =  permissionMapper.getPermissionsByRoleId(roleId);
            role.setPermissionList(permissions);
        }
        employee.setRoles(roles);
        return employee;
    }
}
