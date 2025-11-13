package com.study.company.dto.employee.request.response;

import com.study.company.domain.employee.Employee;

import java.time.LocalDate;

public class EmployeeResponse {

    private long id;
    private String name;
    private String teamName;
    private String role;
    private LocalDate joinDate;
    private LocalDate birthDate;
    private long team_id;
    private Long totalWork = 0L;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.teamName = employee.getTeam() != null ? employee.getTeam().getName() : "";
        this.role = employee.getRole();
        this.joinDate = employee.getJoinDate();
        this.birthDate = employee.getBirthDate();
        this.team_id = employee.getTeam().getId();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
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
