package com.huike.mapper;

import com.huike.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {
    @Select("select * from permission")
    List<Permission> getAll();

    @Select("SELECT * FROM permission WHERE id IN(SELECT permission_id FROM role_permission WHERE role_id = #{roleId})")
    List<Permission> getPermissionsByRoleId(Long roleId);
}
