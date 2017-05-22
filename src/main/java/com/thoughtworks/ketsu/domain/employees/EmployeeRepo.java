package com.thoughtworks.ketsu.domain.employees;

import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/22/17.
 */
public interface EmployeeRepo {
    Employee save(Employee employee);

    Optional<Employee> findById(long id);

    List<Employee> findAll();
}
