package com.thoughtworks.ketsu.domain.attendances;

import com.thoughtworks.ketsu.domain.employees.Employee;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.util.IdGenerator;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pzzheng on 5/22/17.
 */
public class Attendance implements Record {
    private Employee employee;
    private long id;
    private String fromDate;
    private String toDate;
    private String description;
    private boolean present;

    private Attendance() {}

    public Attendance(Employee employee, String fromDate, String toDate, String description, boolean present) {
        this.id = IdGenerator.next();
        this.employee = employee;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
        this.present = present;
    }


    public long getEmployeeId() {
        return employee.getId();
    }

    public long getId() {
        return id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPresent() {
        return present;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap(){{
            put("id", id);
            put("from_date", fromDate);
            put("to_date", toDate);
            put("description", description);
            put("present", present);
            put("employee_id", employee.getId());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> res = toRefJson(routes);
        res.put("name", employee.getName());
        return res;
    }
}
