package com.thoughtworks.ketsu.infrastructure.repositories;


import com.thoughtworks.ketsu.domain.attendances.Attendance;
import com.thoughtworks.ketsu.domain.attendances.AttendanceRepo;
import com.thoughtworks.ketsu.domain.employees.EmployeeRepo;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.AttendanceMapper;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Optional;

/**
 * Created by pzzheng on 5/22/17.
 */
public class AttendanceRepoImpl implements AttendanceRepo {
    @Inject
    AttendanceMapper attendanceMapper;

    @Inject
    EmployeeRepo employeeRepo;

    @Override
    public Attendance save(Attendance attendance) {
        checkAttendanceEmployee(attendance);
        attendanceMapper.save(attendance);
        return attendanceMapper.findById(attendance.getId());
    }

    private void checkAttendanceEmployee(Attendance attendance) {
        if (!employeeRepo.findById(attendance.getEmployeeId()).isPresent())
            throw new BadRequestException("employeeid not exists");
    }

    @Override
    public Optional<Attendance> findById(long id) {
        return Optional.ofNullable(attendanceMapper.findById(id));
    }

    @Override
    public List<Attendance> findAll() {
        return attendanceMapper.findAll();
    }

    @Override
    public void update(long attendanceId, Attendance attendance) {
        attendanceMapper.update(attendanceId, attendance);
    }

    @Override
    public void delete(long id) {
        attendanceMapper.delete(id);
    }
}