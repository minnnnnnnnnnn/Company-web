package com.study.company.dto.employee.request;

public class EmployeeCheckOutRequest {

    private Long employeeId;

    public EmployeeCheckOutRequest(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
