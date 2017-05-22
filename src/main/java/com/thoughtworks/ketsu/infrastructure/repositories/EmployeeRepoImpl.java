package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.EmployeeMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/22/17.
 */
public class EmployeeRepoImpl implements EmployeeRepo {
    @Inject
    EmployeeMapper employeeMapper;

    @Override
    public Employee save(Employee employee) {
        employeeMapper.save(employee);
        return employeeMapper.findById(employee.getId());
    }

    @Override
    public Optional<Employee> findById(long id) {
        return Optional.ofNullable(employeeMapper.findById(id));
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    public void update(long employeeId, Employee employee) {
        employeeMapper.update(employeeId, employee);
    }

    @Override
    public void delete(long id) {
        employeeMapper.delete(id);
    }
}
