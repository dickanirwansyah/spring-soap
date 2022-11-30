package com.dicka.soap.soapcrudoperation.service;

import com.dicka.soap.soapcrudoperation.base.BaseService;
import com.dicka.soap.soapcrudoperation.entity.Employee;
import com.dicka.soap.soapcrudoperation.model.EmployeeRequest;
import com.dicka.soap.soapcrudoperation.model.EmployeeResponse;
import com.dicka.soap.soapcrudoperation.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateEmployeeService implements BaseService<EmployeeRequest, EmployeeResponse> {

    private EmployeeRepository employeeRepository;
    public UpdateEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public EmployeeResponse execute(EmployeeRequest request) {
        log.info("processing update employee -> ",request.toString());
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeRepository.findById(request.getId())
                .ifPresentOrElse(employee -> {
                    employee.setAddress(request.getEmpAddress());
                    employee.setName(request.getEmpName());
                    employee.setAddress(request.getEmpAddress());
                    employee.setPhone(request.getEmpPhone());
                    employee.setDepartment(request.getEmpDepartment());
                    Employee responseEmployee = employeeRepository.save(employee);

                    employeeResponse.setEmployeeId(responseEmployee.getId());
                    employeeResponse.setEmpAddress(responseEmployee.getAddress());
                    employeeResponse.setEmpName(responseEmployee.getName());
                    employeeResponse.setEmpDepartment(responseEmployee.getDepartment());
                    employeeResponse.setEmpPhone(responseEmployee.getPhone());
                    log.info("success to update data employee");
                }, ()-> {
                    log.info("failed to update data employee because id not found");
                    employeeResponse.setEmployeeId(0l);
                });
        return employeeResponse;
    }
}
