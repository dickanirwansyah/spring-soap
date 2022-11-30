/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.soapcrudclient.service;

import com.dicka.soapcrudclient.base.CommonBase;
import com.dicka.soapcrudclient.base.CommonRequest;
import com.dicka.soapcrudclient.base.CommonResponse;
import com.dicka.soapcrudclient.generatedwsdl.AddEmployeeRequest;
import com.dicka.soapcrudclient.generatedwsdl.AddEmployeeResponse;
import com.dicka.soapcrudclient.generatedwsdl.EmployeeInfo;
import com.dicka.soapcrudclient.model.request.EmployeeRequest;
import com.dicka.soapcrudclient.model.response.EmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 *
 * @author macddl18
 */

@Slf4j
@Service
public class AddClientEmployeeService implements CommonBase<EmployeeRequest, EmployeeResponse>{
    
    private static final String URI_SOAP = "http://localhost:8081/soap/";
    private Jaxb2Marshaller marshaller;
    private WebServiceTemplate webServiceTemplate;
    public AddClientEmployeeService(Jaxb2Marshaller marshaller){
        this.marshaller = marshaller;
    }

    @Override
    public EmployeeResponse execute(EmployeeRequest request) {
        AddEmployeeResponse responseEmployeeFromSoap = employeeResponse(request);
        return EmployeeResponse.
                builder()
                .employeeAddress(responseEmployeeFromSoap.getEmployeeInfo().getAddress())
                .employeeDepartment(responseEmployeeFromSoap.getEmployeeInfo().getDepartment())
                .employeeId(responseEmployeeFromSoap.getEmployeeInfo().getEmployeeId())
                .employeeName(responseEmployeeFromSoap.getEmployeeInfo().getName())
                .employeePhoneNumber(responseEmployeeFromSoap.getEmployeeInfo().getPhone())
                .build();
    }
    
    
    private AddEmployeeResponse employeeResponse(EmployeeRequest request){
        webServiceTemplate = new WebServiceTemplate(marshaller);
        AddEmployeeRequest addEmployeeRequest = new AddEmployeeRequest();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setEmployeeId(0);
        employeeInfo.setAddress(request.getEmployeeAddress());
        employeeInfo.setDepartment(request.getEmployeeDepartment());
        employeeInfo.setName(request.getEmployeeName());
        employeeInfo.setPhone(request.getEmployeePhoneNumber());
        addEmployeeRequest.setEmployeeInfo(employeeInfo);
        
        log.info("request to marshaller -> {} ",addEmployeeRequest.toString());
        
        AddEmployeeResponse responseEmployeeFromSoap = (AddEmployeeResponse) webServiceTemplate
                .marshalSendAndReceive(URI_SOAP, addEmployeeRequest);
        log.info("response marshaller -> {} ",responseEmployeeFromSoap.toString());
        return responseEmployeeFromSoap;
    }
}
