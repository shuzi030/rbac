package com.huike.mapper;

import com.huike.entity.Employee;
import com.huike.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmployeeMapper {

    List<Employee> list(String keyword, Long deptId);

    void save(Employee employee);

    @Insert("insert into employee_role values (#{employeeId},#{roleId})")
    void add2Mid(Long employeeId, Integer roleId);

    @Select("select * from employee where id = #{曹操}")
    Employee getEmployeeById(Long id);

    @Select("SELECT * FROM role WHERE id IN(SELECT role_id FROM employee_role WHERE employee_id = #{id})")
    List<Role> getRolesByEmpId(Long id);

    @Update("update employee set name = #{name},email = #{email},age = #{age},dept_id = #{dept.id} where id = #{id}")
    void updateEmployee(Employee employee);

    @Delete("delete from employee_role where employee_id = #{employeeId}")
    void deleteMidByEmpId(Long employeeId);
    
    @Select("select * from employee")
    List<Employee> getAll();
}
