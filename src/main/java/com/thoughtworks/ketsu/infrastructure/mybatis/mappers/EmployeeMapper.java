package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.employees.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pzzheng on 5/22/17.
 */
public interface EmployeeMapper {

    Employee findById(@Param("id") long id);

    void save(@Param("employee") Employee employee);

    List<Employee> findAll();

    void update(@Param("id") long employeeId, @Param("employee") Employee employee);
}
