package com.study.company.dto.employee.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeCheckInRequest {

    private Long employeeId;

    public EmployeeCheckInRequest(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
