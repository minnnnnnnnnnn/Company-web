package com.study.company.domain.Team;

import com.study.company.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    private int employeeNum = 0;

    public Team(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void increaseEmployeeNum() {
        this.employeeNum++;
    }
}
