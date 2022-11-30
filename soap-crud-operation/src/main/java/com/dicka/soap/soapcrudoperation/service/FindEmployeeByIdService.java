package com.dicka.soap.soapcrudoperation.service;

import com.dicka.soap.soapcrudoperation.base.BaseService;
import com.dicka.soap.soapcrudoperation.base.FindByIdRequest;
import com.dicka.soap.soapcrudoperation.model.EmployeeResponse;
import com.dicka.soap.soapcrudoperation.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FindEmployeeByIdService implements BaseService<FindByIdRequest, EmployeeResponse> {

    private EmployeeRepository employeeRepository;
    public FindEmployeeByIdService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse execute(FindByIdRequest request) {
        log.info("processing employee find by id -> {} ",request.getId());
        return employeeRepository.findById(request.getId())
                .map(employee -> EmployeeResponse.builder()
                        .employeeId(employee.getId())
                        .empAddress(employee.getAddress())
                        .empName(employee.getName())
                        .empDepartment(employee.getDepartment())
                        .empPhone(employee.getPhone())
                        .build())
                .orElse(new EmployeeResponse());
    }
}
