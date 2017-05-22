package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendances.AttendanceRepo;
import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.attendanceJsonForTest;
import static com.thoughtworks.ketsu.support.TestHelper.prepareEmployeeWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/22/17.
 */
@RunWith(ApiTestRunner.class)
public class AttendancesApiTest extends ApiSupport {

    private Employee employee;

    public String getAttendancesUrl() {
        return "/attendances";
    }

    @Inject
    EmployeeRepo employeeRepo;

    @Inject
    AttendanceRepo attendanceRepo;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        employee = prepareEmployeeWithDefaultInfo(employeeRepo);
    }

    @Test
    public void should_201_when_create_attendance() {
        Response response = post(getAttendancesUrl(), attendanceJsonForTest(employee.getId()));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_create_attendance_if_field_incomplete() {
        Map<String, Object> incompleteInput = attendanceJsonForTest(employee.getId());
        incompleteInput.remove("employee_id");

        Response response = post(getAttendancesUrl(), incompleteInput);

        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_400_when_create_attendance_if_employeeid_invalid() {
        long invalid_employeeId = 1l;

        Response response = post(getAttendancesUrl(), attendanceJsonForTest(invalid_employeeId));

        assertThat(response.getStatus(), is(400));
    }
//
//    @Test
//    public void should_200_when_get_all_attendances() {
//        Employee attendance = prepareEmployeeWithDefaultInfo(attendanceRepo);
//
//        Response response = get(getAttendancesUrl());
//
//        assertThat(response.getStatus(), is(200));
//        List<Map> res = response.readEntity(List.class);
//        assertThat(res, is(notNullValue()));
//        assertThat(res.size(), is(1));
//        assertThat(res.get(0).get("id"), is(attendance.getId()));
//    }
}