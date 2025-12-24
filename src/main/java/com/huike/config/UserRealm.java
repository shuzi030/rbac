package com.huike.config;

import com.huike.entity.Employee;
import com.huike.entity.Permission;
import com.huike.entity.Role;
import com.huike.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //2.授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //授权
        Collection<String> coll = new HashSet<>(); //set集合的好处是可以去重复
        //取出Employee对象的权限
        Employee employee =  (Employee)principalCollection.getPrimaryPrincipal();
        List<Role> roles = employee.getRoles();
        if(roles != null){
            for(Role role:roles){
                List<Permission> permissions = role.getPermissionList();
                if(permissions != null){
                    for(Permission permission: permissions){
                        coll.add(permission.getExpression());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //用shiro规定的方式重新给Employee授权
        info.addStringPermissions(coll);
        return info;
    }


    //1.认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //登录逻辑
        //1.把AuthenticationToken强转成UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //2.从UsernamePasswordToken中获得前端传入的用户名
        String username = token.getUsername();
        //3.用前端传入的用户名去数据库中查数据(调用service)，如果查不到数据，证明用户名不对，返回null
        Employee employee =  userService.getEmployeeByUserName(username);
        if(employee == null){
            return null;
        }
        //4.如能查到数据，则说明用户名正确，返回AuthenticationInfo
        return new SimpleAuthenticationInfo(employee,employee.getPassword(),"");
    }
}
