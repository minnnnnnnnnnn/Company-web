package com.study.company.dto.employee.request;

import java.time.LocalDate;

public class EmployeeCreateRequest {

    private String name;
    private long teamId;
    private String role;
    private LocalDate joinDate;
    private LocalDate birthDate;

    public EmployeeCreateRequest(String name, long teamId, String role, LocalDate joinDate, LocalDate birthDate) {
        this.name = name;
        this.teamId = teamId;
        this.role = role;
        this.joinDate = joinDate;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getRole() {
        return role;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
