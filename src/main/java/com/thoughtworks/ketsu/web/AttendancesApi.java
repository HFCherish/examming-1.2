package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendances.Attendance;
import com.thoughtworks.ketsu.domain.attendances.AttendanceRepo;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.ketsu.util.JsonToObjectHelper.safeBuildAttendance;

/**
 * Created by pzzheng on 5/22/17.
 */
@Path("/attendances")
public class AttendancesApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Map<String, Object> attendanceInfo,
                        @Context AttendanceRepo attendanceRepo,
                        @Context Routes routes) {

        Attendance save = attendanceRepo.save(safeBuildAttendance(attendanceInfo));
        return Response.created(routes.attendanceUrl(save.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Attendance> getAll(@Context AttendanceRepo attendanceRepo, @QueryParam("employeeId") @DefaultValue("-1") long employeeId) {
        return attendanceRepo.findAll(employeeId);
    }


    @Path("{id}")
    public AttendanceApi getOne(@Context AttendanceRepo attendanceRepo,
                                @PathParam("id") long id) {
        return attendanceRepo.findById(id)
                .map(AttendanceApi::new)
                .orElseThrow(() -> new NotFoundException());
    }
}
