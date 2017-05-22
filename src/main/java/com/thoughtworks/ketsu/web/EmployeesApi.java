package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendances.Attendance;
import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.domain.employees.Gender;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
@Path("/employees")
public class EmployeesApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Map<String, Object> employeeInfo,
                        @Context EmployeeRepo employeeRepo,
                        @Context Routes routes) {

        Employee save = employeeRepo.save(new Employee(employeeInfo.get("name").toString(),
                Long.valueOf(employeeInfo.get("department_id").toString()),
                Long.valueOf(employeeInfo.get("role_id").toString()),
                Gender.valueOf(employeeInfo.get("gender").toString())));

        return Response.created(routes.employeeUrl(save.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Attendance> getAll() {
        return new ArrayList<>();
    }

    @Path("{employeeId}")
    public EmployeeApi getOne() {
        return new EmployeeApi();
    }
}
