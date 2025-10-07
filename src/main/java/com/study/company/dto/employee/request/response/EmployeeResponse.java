package com.study.company.dto.employee.request.response;

import com.study.company.domain.employee.Employee;

import java.time.LocalDate;

public class EmployeeResponse {

    private long id;
    private String name;
    private String role;
    private LocalDate joinDate;
    private LocalDate birthDate;
    private long team_id;

    public EmployeeResponse(long id, String name, String role, LocalDate joinDate, LocalDate birthDate, long team_id) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
        this.birthDate = birthDate;
        this.team_id = team_id;
    }

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.role = employee.getRole();
        this.joinDate = employee.getJoinDate();
        this.birthDate = employee.getBirthDate();
        this.team_id = employee.getTeam_id();;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public long getTeam_id() {
        return team_id;
    }
}
