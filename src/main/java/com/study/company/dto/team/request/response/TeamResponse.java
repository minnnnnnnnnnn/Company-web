package com.study.company.dto.team.request.response;

import com.study.company.domain.Team.Team;

public class TeamResponse {

    private long id;
    private String name;
    private long managerId;
    private Integer employeeNum;

    public TeamResponse(long id, String name, long managerId, Integer employeeNum) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.employeeNum = employeeNum;
    }

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.managerId = (team.getManagerId() != null) ? team.getManagerId() : 0L;
        this.employeeNum = team.getEmployeeNum();
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getManagerId() {
        return managerId;
    }

    public Integer getEmployeeNum() {
        return employeeNum;
    }
}
