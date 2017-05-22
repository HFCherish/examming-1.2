package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendances.Attendance;
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

import static com.thoughtworks.ketsu.support.TestHelper.prepareAttendanceWithDefaultInfo;
import static com.thoughtworks.ketsu.support.TestHelper.prepareEmployeeWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/22/17.
 */
@RunWith(ApiTestRunner.class)
public class AttendanceApiTest extends ApiSupport {

    private Attendance attendance;
    private Employee employee;

    public String getAttendancesUrl(long attendanceId) {
        return "/attendances/" + attendanceId;
    }

    @Inject
    AttendanceRepo attendanceRepo;

    @Inject
    EmployeeRepo employeeRepo;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        employee = prepareEmployeeWithDefaultInfo(employeeRepo);
        attendance = prepareAttendanceWithDefaultInfo(attendanceRepo, employee);
    }

    @Test
    public void should_200_when_get_one_attendance() {
        Response response = get(getAttendancesUrl(attendance.getId()));

        assertThat(response.getStatus(), is(200));
        Map res = response.readEntity(Map.class);
        assertThat(res, is(notNullValue()));
        assertThat(Long.valueOf(res.get("id").toString()), is(attendance.getId()));
        assertThat(Long.valueOf(res.get("employee_id").toString()), is(attendance.getEmployeeId()));
        assertThat(res.get("name"), is(employee.getName()));
        assertThat(res.get("from_date"), is(attendance.getFromDate()));
        assertThat(res.get("to_date"), is(attendance.getToDate()));
        assertThat(res.get("description"), is(attendance.getDescription()));
        assertThat(res.get("present"), is(attendance.isPresent()));
    }

    @Test
    public void should_404_when_get_one_attendance_if_not_exists() {
        Response response = get(getAttendancesUrl(1));

        assertThat(response.getStatus(), is(404));
    }

//    @Test
//    public void should_204_when_update_one_attendance() {
//        Map<String, Object> updateInfo = attendanceJsonForTest();
//        updateInfo.replace("department_id", 2);
//
//        Response response = put(getAttendancesUrl(attendance.getId()), updateInfo);
//
//        assertThat(response.getStatus(), is(204));
//    }

//    @Test
//    public void should_204_when_delete_one_attendance() {
//
//        Response response = delete(getAttendancesUrl(attendance.getId()));
//
//        assertThat(response.getStatus(), is(204));
//    }
}