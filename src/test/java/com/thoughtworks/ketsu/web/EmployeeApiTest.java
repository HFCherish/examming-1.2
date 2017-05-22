package com.thoughtworks.ketsu.web;

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

import static com.thoughtworks.ketsu.support.TestHelper.prepareEmployeeWithDefaultInfo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by pzzheng on 5/22/17.
 */
@RunWith(ApiTestRunner.class)
public class EmployeeApiTest extends ApiSupport {

    private Employee employee;

    public String getEmployeesUrl(long employeeId) {
        return "/employees/" + employeeId;
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        employee = prepareEmployeeWithDefaultInfo(employeeRepo);
    }

    @Inject
    EmployeeRepo employeeRepo;

    @Test
    public void should_200_when_get_one_employee() {
        Response response = get(getEmployeesUrl(employee.getId()));

        assertThat(response.getStatus(), is(200));
        Map res = response.readEntity(Map.class);
        assertThat(res, is(notNullValue()));
        assertThat(Long.valueOf(res.get("id").toString()), is(employee.getId()));
        assertThat(res.get("name"), is(employee.getName()));
        assertThat(Long.valueOf(res.get("department_id").toString()), is(employee.getDepartmentId()));
        assertThat(Long.valueOf(res.get("role_id").toString()), is(employee.getRoleId()));
        assertThat(res.get("gender"), is(employee.getGender().toString()));
        assertThat(res.get("employee_url").toString().contains(getEmployeesUrl(employee.getId())), is(true));
    }

    @Test
    public void should_404_when_get_one_employee_if_not_exists() {
        Response response = get(getEmployeesUrl(1));

        assertThat(response.getStatus(), is(404));
    }
}