package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/22/17.
 */
@RunWith(ApiTestRunner.class)
public class EmployeesApiTest extends ApiSupport {

    public String getEmployeesUrl() {
        return "/employees";
    }

    @Test
    public void should_201_when_create_employee() {
        Response response = post(getEmployeesUrl(), employeeJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_create_employee_if_field_incomplete() {
        Map<String, Object> incompleteInput = employeeJsonForTest();
        incompleteInput.remove("name");

        Response response = post(getEmployeesUrl(), incompleteInput);

        assertThat(response.getStatus(), is(400));
    }

    @Inject
    EmployeeRepo employeeRepo;

    @Test
    public void should_200_when_get_all_employees() {
        Employee employee = prepareEmployeeWithDefaultInfo(employeeRepo);

        Response response = get(getEmployeesUrl());

        assertThat(response.getStatus(), is(200));
        List<Map> res = response.readEntity(List.class);
        assertThat(res, is(notNullValue()));
        assertThat(res.size(), is(1));
        assertThat(res.get(0).get("id"), is(employee.getId()));
    }
}