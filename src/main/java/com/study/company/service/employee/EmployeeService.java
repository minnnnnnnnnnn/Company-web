package com.study.company.service.employee;

import com.study.company.domain.employee.attendance.Attendance;
import com.study.company.domain.employee.attendance.AttendanceRepository;
import com.study.company.domain.team.Team;
import com.study.company.domain.team.TeamRepository;
import com.study.company.domain.employee.Employee;
import com.study.company.domain.employee.EmployeeRepository;
import com.study.company.dto.employee.request.EmployeeCheckInRequest;
import com.study.company.dto.employee.request.EmployeeCreateRequest;
import com.study.company.dto.employee.request.response.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final AttendanceRepository attendanceRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional
    public void saveEmployee(EmployeeCreateRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(IllegalArgumentException::new);
        Employee employee = new Employee(
                request.getName(),
                team,
                request.getRole(),
                request.getJoinDate(),
                request.getBirthDate()
        );
        employeeRepository.save(employee);

        team.increaseEmployeeNum();

        if ("manager".equalsIgnoreCase(request.getRole())) {
            team.setManager(employee);
        }
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void checkIn(EmployeeCheckInRequest request) {
        // 1. 직원 조회
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 출근 날짜, 시간 저장
        LocalDate workDate = LocalDate.now();
        LocalDateTime checkIn = LocalDateTime.now();

        // 3. 출근 처리
        attendanceRepository.save(new Attendance(employee, workDate, checkIn));
    }


}
