package com.study.company.service.employee;

import com.study.company.domain.employee.attendance.Attendance;
import com.study.company.domain.employee.attendance.AttendanceRepository;
import com.study.company.domain.team.Team;
import com.study.company.domain.team.TeamRepository;
import com.study.company.domain.employee.Employee;
import com.study.company.domain.employee.EmployeeRepository;
import com.study.company.dto.employee.request.EmployeeCheckInRequest;
import com.study.company.dto.employee.request.EmployeeCheckOutRequest;
import com.study.company.dto.employee.request.EmployeeCreateRequest;
import com.study.company.dto.employee.request.response.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
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

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getCheckInEmployees() {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getCheckIn() != null && a.getCheckOut() == null) // 출근했지만 퇴근 안한 직원
                .map(a -> new EmployeeResponse(a.getEmployee())) // Attendance -> Employee
                .collect(Collectors.toList());
    }

    @Transactional
    public void checkOut(EmployeeCheckOutRequest request) {
        // 1. 직원 조회
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 직원이 존재하지 않습니다."));

        // 2. 오늘 출근 기록 확인
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeAndWorkDate(employee, today)
                .orElseThrow(() -> new IllegalArgumentException("오늘 출근 기록이 존재하지 않습니다."));

        // 3. 이미 퇴근 처리되었으면 예외
        if (attendance.getCheckOut() != null) {
            throw new IllegalStateException("이미 퇴근 처리가 완료되었습니다.");
        }

        // 4. 퇴근 시간과 근무 시간 계산 (분 단위)
        LocalDateTime checkOutTime = LocalDateTime.now();
        attendance.setCheckOut(checkOutTime);

        Duration duration = Duration.between(attendance.getCheckIn(), checkOutTime);
        Long workMinutes = duration.toMinutes();  // Long 타입
        attendance.setDailyWork(workMinutes);

        // 5. 총 누적 근무 시간 업데이트 (분 단위, Long)
        Long totalWorkMinutes = employee.getTotalWork() + workMinutes;
        employee.setTotalWork(totalWorkMinutes);

        // 6. 저장 처리
        attendanceRepository.save(attendance);
        employeeRepository.save(employee);
    }


}
