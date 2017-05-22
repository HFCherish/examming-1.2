package com.thoughtworks.ketsu.domain.attendances;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.attendanceJsonForTest;
import static com.thoughtworks.ketsu.support.TestHelper.attendanceWithDefaultInfo;
import static com.thoughtworks.ketsu.support.TestHelper.prepareEmployeeWithDefaultInfo;
import static com.thoughtworks.ketsu.util.JsonToObjectHelper.safeBuildAttendance;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/22/17.
 */
@RunWith(DatabaseTestRunner.class)
public class AttendanceRepoTest {

    @Inject
    AttendanceRepo attendanceRepo;

    @Inject
    EmployeeRepo employeeRepo;
    private Employee employee;

    @Before
    public void setUp() {
        employee = prepareEmployeeWithDefaultInfo(employeeRepo);
    }

    @Test
    public void should_crud_success() {
//        create and get
        Attendance save = attendanceRepo.save(attendanceWithDefaultInfo(employee));
        Optional<Attendance> byId = attendanceRepo.findById(save.getId());

        assertThat(byId.isPresent(), is(true));
        assertThat(byId.get().getId(), is(save.getId()));


//        update
//        assertThat(save.getDescription(), is(not("change")));
//        employeeRepo.findById(employee.getId());
//        Map<String, Object> updateInfo = attendanceJsonForTest(employee.getId());
//        updateInfo.replace("description", "change");
//        updateInfo.put("id", save.getId());
//        attendanceRepo.update(save.getId(), safeBuildAttendance(updateInfo));
//
//        // prevent using cache when get
//        attendanceRepo.save(attendanceWithDefaultInfo(employee));
//        assertThat(attendanceRepo.findById(save.getId()).get().getDescription(), is("change"));

//        delete
        attendanceRepo.delete(save.getId());
        // prevent using cache when get
        attendanceRepo.save(attendanceWithDefaultInfo(employee));
        assertThat(attendanceRepo.findById(save.getId()).isPresent(), is((false)));
    }

}