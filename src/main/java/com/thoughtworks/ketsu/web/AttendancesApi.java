package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendances.Attendance;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
@Path("/attendances")
public class AttendancesApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Map<String, Object> attendanceInfo,
                        @Context Routes routes) {
        return Response.created(URI.create("")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Attendance> getAll() {
        return new ArrayList<>();
    }


    @Path("{attendanceId}")
    public AttendanceApi getOne() {
        return new AttendanceApi();
    }
}
