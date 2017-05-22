package com.thoughtworks.ketsu.support;


import com.thoughtworks.ketsu.domain.attendances.Attendance;
import com.thoughtworks.ketsu.domain.attendances.AttendanceRepo;
import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;
import org.joda.time.DateTime;

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
        return employeeRepo.save(employeeWithDefaultInfo());
    }

    public static Map<String, Object> attendanceJsonForTest(long employeeId) {
        return new HashMap<String, Object>() {{
            put("employee_id", employeeId);
            put("from_date", new DateTime().toString());
            put("to_date", new DateTime().toString());
            put("description", "....");
            put("present", false);
        }};
    }

    public static Attendance attendanceWithDefaultInfo(long employeeId) {
        return new Attendance(employeeId, new DateTime().toString(), new DateTime().toString(), "...", false);
    }

    public static Attendance prepareAttendanceWithDefaultInfo(AttendanceRepo attendanceRepo, long employeeId) {
        return attendanceRepo.save(attendanceWithDefaultInfo(employeeId));
    }
}
