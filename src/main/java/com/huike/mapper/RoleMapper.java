package com.huike.mapper;

import com.huike.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    @Select("select * from role")
    List<Role> getAll();

    void save(Role role);

    @Insert("insert into role_permission values(#{roleId},#{permissionIds})")
    void add2Mid(Long roleId, Integer permissionIds);


}
