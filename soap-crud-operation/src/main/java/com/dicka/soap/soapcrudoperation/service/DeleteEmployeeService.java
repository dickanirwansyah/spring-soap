package com.dicka.soap.soapcrudoperation.service;

import com.dicka.soap.soapcrudoperation.base.BaseService;
import com.dicka.soap.soapcrudoperation.base.FindByIdRequest;
import com.dicka.soap.soapcrudoperation.model.EmployeeResponse;
import com.dicka.soap.soapcrudoperation.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeleteEmployeeService implements BaseService<FindByIdRequest, EmployeeResponse> {

    EmployeeRepository employeeRepository;
    public DeleteEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse execute(FindByIdRequest request) {
        log.info("processing delete data");
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeRepository.findById(request.getId())
                .ifPresentOrElse(employee -> {
                    employee.setDeleted(1);
                    employeeRepository.save(employee);
                    employeeResponse.setEmployeeId(employee.getId());
                    employeeResponse.setEmpPhone(employee.getPhone());
                    employeeResponse.setEmpDepartment(employee.getDepartment());
                    employeeResponse.setEmpAddress(employee.getAddress());
                    employeeResponse.setEmpName(employee.getName());
                }, ()-> {
                    employeeResponse.setEmployeeId(0l);
                    employeeResponse.setEmpPhone("-");
                    employeeResponse.setEmpDepartment("-");
                    employeeResponse.setEmpAddress("-");
                    employeeResponse.setEmpName("-");
                });
        return employeeResponse;
    }
}
