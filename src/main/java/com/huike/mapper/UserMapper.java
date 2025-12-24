package com.huike.mapper;

import com.huike.entity.Employee;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from employee where username = #{username}")
    Employee findEmployeeByUserName(String username);

}
