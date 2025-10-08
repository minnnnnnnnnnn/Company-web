package com.study.company.service.employee;

import com.study.company.domain.Team.Team;
import com.study.company.domain.Team.TeamRepository;
import com.study.company.domain.employee.Employee;
import com.study.company.domain.employee.EmployeeRepository;
import com.study.company.dto.employee.request.EmployeeCreateRequest;
import com.study.company.dto.employee.request.response.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
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

}
