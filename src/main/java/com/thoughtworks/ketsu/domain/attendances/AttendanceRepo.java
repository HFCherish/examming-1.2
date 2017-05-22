package com.thoughtworks.ketsu.domain.attendances;

import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/22/17.
 */
public interface AttendanceRepo {
    Attendance save(Attendance attendance);

    Optional<Attendance> findById(long id);

    List<Attendance> findAll(long employeeId);

    void update(long attendanceId, Attendance attendance);

    void delete(long id);
}
