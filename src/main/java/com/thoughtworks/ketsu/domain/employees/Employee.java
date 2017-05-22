package com.thoughtworks.ketsu.domain.employees;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
public class Employee implements Record {
    private long departmentId;
    private long roleId;
    private Gender gender;
    private String name;
    private long id;

    private Employee() {}

    public Employee(String name, long departmentId, long roleId, Gender gender) {
        this.id = IdGenerator.next();
        this.departmentId = departmentId;
        this.roleId = roleId;
        this.gender = gender;
        this.name = name;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public long getRoleId() {
        return roleId;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap(){{
            put("id", id);
            put("name", name);
            put("department_id", departmentId);
            put("role_id", roleId);
            put("gender", gender.toString());
            put("employee_url", routes.employeeUrl(id));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
