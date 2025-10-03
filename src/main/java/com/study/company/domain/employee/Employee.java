package com.study.company.domain.employee;

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
    private long team_id;

    public Employee(String name, String role, LocalDate joinDate, LocalDate birth_date) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
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
        this.role = role;
        this.joinDate = joinDate;
        this.birthDate = birth_date;
    }


}
