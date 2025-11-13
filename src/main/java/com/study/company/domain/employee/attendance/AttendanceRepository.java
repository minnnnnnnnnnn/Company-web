package com.study.company.domain.employee.attendance;

import com.study.company.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeAndWorkDate(Employee employee, LocalDate workDate);


}
