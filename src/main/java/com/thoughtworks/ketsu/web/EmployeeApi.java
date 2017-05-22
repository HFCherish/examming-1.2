package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.util.JsonToObjectHelper.safeBuildEmployee;

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
        employeeRepo.update(employee.getId(), safeBuildEmployee(employeeInfo));
        return Response.noContent().build();
    }

    @DELETE
    public Response delete(@Context EmployeeRepo employeeRepo) {
        employeeRepo.delete(employee.getId());
        return Response.noContent().build();
    }
}
