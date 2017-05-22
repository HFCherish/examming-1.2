package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendances.Attendance;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
public class AttendanceApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Attendance getOne() {
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String, Object> attendanceInfo) {
        return null;
    }

    @PUT
    public Response delete() {
        return null;
    }

}
