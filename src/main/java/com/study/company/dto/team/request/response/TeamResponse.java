package com.study.company.dto.team.request.response;

import com.study.company.domain.team.Team;

public class TeamResponse {

    private long id;
    private String name;
    private long managerId;
    private String managerName;
    private Integer employeeNum;

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.managerId = (team.getManager() != null) ? team.getManager().getId() : 0L;
        this.managerName = (team.getManager() != null) ? team.getManager().getName() : "";
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

    public String getManagerName() {
        return managerName;
    }
}
