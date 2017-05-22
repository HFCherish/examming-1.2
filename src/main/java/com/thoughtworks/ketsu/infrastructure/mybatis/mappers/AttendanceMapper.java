package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.attendances.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pzzheng on 5/22/17.
 */
public interface AttendanceMapper {

    Attendance findById(@Param("id") long id);

    void save(@Param("attendance") Attendance attendance);

    List<Attendance> findAll(@Param("employeeId") long employeeId);

    void update(@Param("id") long attendanceId, @Param("attendance") Attendance attendance);

    void delete(@Param("id") long id);
}
