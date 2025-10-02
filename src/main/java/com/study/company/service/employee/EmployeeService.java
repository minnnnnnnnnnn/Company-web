package com.study.company.service.employee;

import com.study.company.dto.employee.request.EmployeeCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Transactional
    public void saveEmployee(EmployeeCreateRequest request) {

    }

}
