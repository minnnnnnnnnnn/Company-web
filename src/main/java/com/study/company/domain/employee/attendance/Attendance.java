package com.study.company.domain.employee.attendance;

import com.study.company.domain.employee.Employee;
import jakarta.persistence.*;

import java.time.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate workDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Long dailyWork;

    public Attendance(Employee employee, LocalDate workDate, LocalDateTime checkIn) {
        this.employee = employee;
        this.workDate = workDate;
        this.checkIn = checkIn;
    }

    public Attendance() {
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public void setDailyWork(Long dailyWork) {
        this.dailyWork = dailyWork;
    }

    public Employee getEmployee() {
        return employee;
    }
}
