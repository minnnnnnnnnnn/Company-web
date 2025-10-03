package com.study.company.service.employee;

import com.study.company.domain.employee.Employee;
import com.study.company.domain.employee.EmployeeRepository;
import com.study.company.dto.employee.request.EmployeeCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
