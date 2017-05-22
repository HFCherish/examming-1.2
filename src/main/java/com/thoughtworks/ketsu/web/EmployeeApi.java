package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.web.validators.Validators.*;

/**
 * Created by pzzheng on 5/22/17.
 */
public class EmployeeApi {

    private Employee employee;

    public EmployeeApi(Employee employee) {
        this.employee = employee;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getOne() {
        return employee;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String, Object> employeeInfo,
                           @Context EmployeeRepo employeeRepo) {

        validate(employeeInfo, all(
                fieldNotEmpty("name"),
                fieldNotEmpty("department_id"),
                fieldNotEmpty("role_id"),
                fieldNotEmpty("gender"),
                fieldIsEnum(Gender.class, "gender")
        ));

        employeeRepo.update(employee.getId(), employeeInfo);
        return Response.noContent().build();
    }

    @PUT
    public Response delete() {
        return null;
    }
}
