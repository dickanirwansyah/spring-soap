package com.dicka.soap.soapcrudoperation.service;

import com.dicka.soap.soapcrudoperation.base.BaseService;
import com.dicka.soap.soapcrudoperation.base.ValidationResponse;
import com.dicka.soap.soapcrudoperation.entity.Employee;
import com.dicka.soap.soapcrudoperation.model.EmployeeRequest;
import com.dicka.soap.soapcrudoperation.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddEmployeeService implements BaseService<EmployeeRequest, ValidationResponse> {

    private EmployeeRepository employeeRepository;
    public AddEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ValidationResponse execute(EmployeeRequest request) {
        log.info("processing create new employee..");
        Employee employee = Employee.builder()
                .name(request.getEmpName())
                .phone(request.getEmpPhone())
                .address(request.getEmpAddress())
                .department(request.getEmpDepartment())
                .build();
        this.employeeRepository.save(employee);
        return ValidationResponse.builder()
                .valid(true)
                .build();
    }
}
