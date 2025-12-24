package com.huike.service;

import com.huike.entity.Employee;

public interface UserService {

    Employee getEmployeeByUserName(String username);
}
