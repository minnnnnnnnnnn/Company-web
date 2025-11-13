package com.study.company.controller.employee;

import com.study.company.domain.employee.Employee;
import com.study.company.dto.employee.request.EmployeeCheckInRequest;
import com.study.company.dto.employee.request.EmployeeCheckOutRequest;
import com.study.company.dto.employee.request.EmployeeCreateRequest;
import com.study.company.dto.employee.request.response.EmployeeResponse;
import com.study.company.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/employee")
    public List<EmployeeResponse> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("/employee/checkIn")
    public void checkIn(@RequestBody EmployeeCheckInRequest request) {
        employeeService.checkIn(request);
    }
    @GetMapping("/employee/checkIn")
    public List<EmployeeResponse> getCheckInEmployees() {
        return employeeService.getCheckInEmployees();
    }

    @PostMapping("/employee/checkOut")
    public void checkOut(@RequestBody EmployeeCheckOutRequest request) {
        employeeService.checkOut(request);
    }

}
