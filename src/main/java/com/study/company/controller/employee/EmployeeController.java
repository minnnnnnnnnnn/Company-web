package com.study.company.controller.employee;

import com.study.company.dto.employee.request.EmployeeCreateRequest;
import com.study.company.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody EmployeeCreateRequest request) {
        employeeService.saveEmployee(request);
    }
}
