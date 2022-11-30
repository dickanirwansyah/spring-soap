package com.dicka.soap.soapcrudoperation.endpoint;

import com.dicka.soap.soapcrudoperation.base.FindByIdRequest;
import com.dicka.soap.soapcrudoperation.model.EmployeeRequest;
import com.dicka.soap.soapcrudoperation.model.EmployeeResponse;
import com.dicka.soap.soapcrudoperation.service.AddEmployeeService;
import com.dicka.soap.soapcrudoperation.service.DeleteEmployeeService;
import com.dicka.soap.soapcrudoperation.service.FindEmployeeByIdService;
import com.dicka.soap.soapcrudoperation.service.UpdateEmployeeService;
import com.dicka.springboot.soap.interfaces.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@Endpoint
public class EmployeeEndpoint {

    public static final String targetNameSpace = "http://interfaces.soap.springboot.dicka.com";

    AddEmployeeService addEmployeeService;
    FindEmployeeByIdService findEmployeeByIdService;
    UpdateEmployeeService updateEmployeeService;
    DeleteEmployeeService deleteEmployeeService;
    public EmployeeEndpoint(AddEmployeeService addEmployeeService,
                            FindEmployeeByIdService findEmployeeByIdService,
                            UpdateEmployeeService updateEmployeeService,
                            DeleteEmployeeService deleteEmployeeService){
        this.addEmployeeService = addEmployeeService;
        this.findEmployeeByIdService = findEmployeeByIdService;
        this.updateEmployeeService = updateEmployeeService;
        this.deleteEmployeeService = deleteEmployeeService;
    }

    @PayloadRoot(namespace = targetNameSpace, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse createEmployee(@RequestPayload AddEmployeeRequest request){
        AddEmployeeResponse response = new AddEmployeeResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        ServiceStatus serviceStatus = new ServiceStatus();

        if (request.getEmployeeInfo().getEmployeeId()==0){
            log.info("processing create data..");
            if (this.addEmployeeService.execute(EmployeeRequest.builder()
                    .empName(request.getEmployeeInfo().getName())
                    .empAddress(request.getEmployeeInfo().getAddress())
                    .empDepartment(request.getEmployeeInfo().getDepartment())
                    .empPhone(request.getEmployeeInfo().getPhone())
                    .build()).isValid()){

                employeeInfo.setEmployeeId(0l);
                employeeInfo.setName(request.getEmployeeInfo().getName());
                employeeInfo.setAddress(request.getEmployeeInfo().getAddress());
                employeeInfo.setDepartment(request.getEmployeeInfo().getDepartment());
                employeeInfo.setAddress(request.getEmployeeInfo().getAddress());
                employeeInfo.setPhone(request.getEmployeeInfo().getPhone());
                serviceStatus.setStatus("success");
                serviceStatus.setMessage("Content employee is created");
            }else{
                employeeInfo.setEmployeeId(0l);
                employeeInfo.setPhone("-");
                employeeInfo.setName("-");
                employeeInfo.setAddress("-");
                employeeInfo.setDepartment("-");
                serviceStatus.setStatus("failed");
                serviceStatus.setMessage("Content employee is failed created");
            }

        }else{
            log.info("processing update data..");
            EmployeeResponse employeeResponse = this.updateEmployeeService.execute(EmployeeRequest.builder()
                    .id(request.getEmployeeInfo().getEmployeeId())
                    .empName(request.getEmployeeInfo().getName())
                    .empPhone(request.getEmployeeInfo().getPhone())
                    .empDepartment(request.getEmployeeInfo().getDepartment())
                    .empAddress(request.getEmployeeInfo().getAddress())
                    .build());

            employeeInfo.setPhone(employeeResponse.getEmpPhone());
            employeeInfo.setName(employeeResponse.getEmpName());
            employeeInfo.setEmployeeId(employeeResponse.getEmployeeId());
            employeeInfo.setDepartment(employeeResponse.getEmpDepartment());
            employeeInfo.setAddress(employeeResponse.getEmpAddress());
        }
        response.setEmployeeInfo(employeeInfo);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = targetNameSpace, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployeeById(@RequestPayload GetEmployeeByIdRequest request){
        GetEmployeeResponse employeeResponse = new GetEmployeeResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        EmployeeResponse responseDataEmployee = this.findEmployeeByIdService.execute(FindByIdRequest.builder()
                        .id(request.getEmployeeId())
                .build());
        if (ObjectUtils.isNotEmpty(responseDataEmployee.getEmployeeId())){
            employeeInfo.setEmployeeId(responseDataEmployee.getEmployeeId());
            employeeInfo.setDepartment(responseDataEmployee.getEmpDepartment());
            employeeInfo.setName(responseDataEmployee.getEmpName());
            employeeInfo.setAddress(responseDataEmployee.getEmpAddress());
            employeeInfo.setPhone(responseDataEmployee.getEmpPhone());
        }else{
            employeeInfo.setEmployeeId(0l);
            employeeInfo.setDepartment("-");
            employeeInfo.setName("-");
            employeeInfo.setAddress("-");
            employeeInfo.setPhone("-");
        }
        employeeResponse.setEmployeeInfo(employeeInfo);
        return employeeResponse;
    }

    @PayloadRoot(namespace = targetNameSpace, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployeeById(@RequestPayload DeleteEmployeeRequest request){
        DeleteEmployeeResponse employeeResponse = new DeleteEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        if (this.deleteEmployeeService.execute(FindByIdRequest.builder()
                .id(request.getEmployeeId())
                .build()).getEmployeeId() != 0){
            serviceStatus.setMessage("Success delete data employee");
            serviceStatus.setStatus("success");
        }else{
            serviceStatus.setMessage("failed delete data employee");
            serviceStatus.setStatus("failed");
        }
        employeeResponse.setServiceStatus(serviceStatus);
        return employeeResponse;
    }
}
