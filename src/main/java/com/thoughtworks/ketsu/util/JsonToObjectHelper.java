package com.thoughtworks.ketsu.util;

import com.thoughtworks.ketsu.domain.attendances.Attendance;
import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.Map;
import java.util.Optional;

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

    @Inject
    static EmployeeRepo employeeRepo;

    public static Attendance safeBuildAttendance(Map<String, Object> attendanceInfo) {
        validate(attendanceInfo, all(
                fieldNotEmpty("employee_id"),
                fieldNotEmpty("from_date"),
                fieldNotEmpty("to_date"),
                fieldNotEmpty("description"),
                fieldNotEmpty("present")
        ));

        Optional<Employee> employee = employeeRepo.findById(Long.valueOf(attendanceInfo.get("employee_id").toString()));

        if (!employee.isPresent())
            throw new BadRequestException("employeeid not exists");

        return new Attendance(employee.get(),
                attendanceInfo.get("from_date").toString(),
                attendanceInfo.get("to_date").toString(),
                attendanceInfo.get("description").toString(),
                Boolean.valueOf(attendanceInfo.get("present").toString()));
    }
}
