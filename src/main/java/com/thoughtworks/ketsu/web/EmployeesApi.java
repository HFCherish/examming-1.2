package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.util.JsonToObjectHelper;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        Employee save = employeeRepo.save(JsonToObjectHelper.safeBuildEmployee(employeeInfo));

        return Response.created(routes.employeeUrl(save.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll(@Context EmployeeRepo employeeRepo) {
        return employeeRepo.findAll();
    }

    @Path("{id}")
    public EmployeeApi getOne(@PathParam("id") long id,
            @Context EmployeeRepo employeeRepo) {
        return employeeRepo.findById(id)
                .map(EmployeeApi:: new)
                .orElseThrow(() -> new NotFoundException());
    }
}
