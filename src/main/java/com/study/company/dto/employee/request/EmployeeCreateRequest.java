package com.study.company.dto.employee.request;

public class EmployeeCreateRequest {

    private String name;

    public EmployeeCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
