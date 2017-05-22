package com.thoughtworks.ketsu.domain.employees;

import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.employeeJsonForTest;
import static com.thoughtworks.ketsu.support.TestHelper.employeeWithDefaultInfo;
import static com.thoughtworks.ketsu.util.JsonToObjectHelper.safeBuildEmployee;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/22/17.
 */
@RunWith(DatabaseTestRunner.class)
public class EmployeeRepoTest {

    @Inject
    EmployeeRepo employeeRepo;

    @Test
    public void should_crud_success() {
//        create and get
        Employee save = employeeRepo.save(employeeWithDefaultInfo());
        Optional<Employee> byId = employeeRepo.findById(save.getId());

        assertThat(byId.isPresent(), is(true));
        assertThat(byId.get().getId(), is(save.getId()));


//        update
        assertThat(save.getDepartmentId(), is(1l));
        Map<String, Object> updateInfo = employeeJsonForTest();
        updateInfo.replace("department_id", 2l);
        employeeRepo.update(save.getId(), safeBuildEmployee(updateInfo));

        // prevent using cache when get
        employeeRepo.save(employeeWithDefaultInfo());
        assertThat(employeeRepo.findById(save.getId()).get().getDepartmentId(), is(2l));
    }

}