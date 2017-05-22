package com.thoughtworks.ketsu.domain.employees;

import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.employeeWithDefaultInfo;
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
    public void should_create_and_can_get_that_one_later() {
        Employee save = employeeRepo.save(employeeWithDefaultInfo());
        Optional<Employee> byId = employeeRepo.findById(save.getId());

        assertThat(byId.isPresent(), is(true));
        assertThat(byId.get().getId(), is(save.getId()));
    }
}