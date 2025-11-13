package com.study.company.domain.employee;

import com.study.company.domain.team.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;

    private String role;
    private LocalDate joinDate;
    private LocalDate birthDate;
    private Long totalWork = 0L;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Employee(String name, Team team, String role, LocalDate joinDate, LocalDate birth_date) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
        if (team == null) {
            throw new IllegalArgumentException("소속 팀 이름이 비어있습니다.");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("직책이 비어있습니다.");
        }
        if (joinDate == null) {
            throw new IllegalArgumentException("입사일이 비어있습니다.");
        }
        if (birth_date == null) {
            throw new IllegalArgumentException("생년월일이 비어있습니다.");
        }
        this.name = name;
        this.team = team;
        this.role = role;
        this.joinDate = joinDate;
        this.birthDate = birth_date;
    }

    public Long getId() {
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

    public Team getTeam() {
        return team;
    }

    public Long getTotalWork() {
        return totalWork;
    }

    public void setTotalWork(Long totalWork) {
        this.totalWork = totalWork;
    }
}
