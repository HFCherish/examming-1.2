package com.thoughtworks.ketsu.util;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.Gender;

import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

/**
 * Created by pzzheng on 5/22/17.
 */
public class JsonToObjectHelper {

    public static Employee safeBuildEmployee(Map<String, Object> employeeInfo) {
        validate(employeeInfo, all(
                fieldNotEmpty("name"),
                fieldNotEmpty("department_id"),
                fieldNotEmpty("role_id"),
                fieldNotEmpty("gender"),
                fieldIsEnum(Gender.class, "gender")
        ));

        return new Employee(employeeInfo.get("name").toString(),
                Long.valueOf(employeeInfo.get("department_id").toString()),
                Long.valueOf(employeeInfo.get("role_id").toString()),
                Gender.valueOf(employeeInfo.get("gender").toString()));
    }
}
