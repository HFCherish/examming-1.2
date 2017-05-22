package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.attendances.Attendance;
import com.thoughtworks.ketsu.domain.attendances.AttendanceRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.ketsu.util.JsonToObjectHelper.safeBuildAttendance;

/**
 * Created by pzzheng on 5/22/17.
 */
public class AttendanceApi {

    private Attendance attendance;

    public AttendanceApi(Attendance attendance) {
        this.attendance = attendance;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Attendance getOne() {
        return attendance;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String, Object> attendanceInfo,
                           @Context AttendanceRepo attendanceRepo) {

        attendanceRepo.update(attendance.getId(), safeBuildAttendance(attendanceInfo));
        return Response.noContent().build();
    }

    @DELETE
    public Response delete(@Context AttendanceRepo attendanceRepo) {
        attendanceRepo.delete(attendance.getId());
        return Response.noContent().build();
    }

}
