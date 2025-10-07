package com.study.company.service.employee;

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

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void saveEmployee(EmployeeCreateRequest request) {
        employeeRepository.save(new Employee(request.getName(), request.getRole(), request.getJoinDate(), request.getBirthDate()));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

}
