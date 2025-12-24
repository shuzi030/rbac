package com.huike.mapper;

import com.huike.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DepartmentMapper {

    @Select("select * from department order by id desc")
    List<Department> list();

    @Insert("insert into department(name,sn) values(#{name},#{sn})")
    void save(Department department);

    @Update("UPDATE department SET NAME = #{name},sn = #{sn} WHERE id = #{id}")
    void update(Department department);

    @Select("select * from department where id = #{坤坤}")
    Department getDepartmentByDeptId(Long deptId);
}
