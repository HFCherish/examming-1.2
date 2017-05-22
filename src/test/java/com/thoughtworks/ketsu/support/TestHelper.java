package com.thoughtworks.ketsu.support;


import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    public static Map<String, Object> employeeJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", "pzzheng");
            put("department_id", 1);
            put("role_id", 1);
            put("gender", Gender.FEMALE);
        }};
    }

    public static Employee employeeWithDefaultInfo() {
        return new Employee("pzzheng", 1, 1, Gender.FEMALE);
    }

    public static Employee prepareEmployeeWithDefaultInfo(EmployeeRepo employeeRepo) {
//        return employeeRepo.save(employeeWithDefaultInfo());
        return null;
    }
}
