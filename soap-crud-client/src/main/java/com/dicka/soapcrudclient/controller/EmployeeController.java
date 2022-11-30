/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.soapcrudclient.controller;

import com.dicka.soapcrudclient.model.request.EmployeeRequest;
import com.dicka.soapcrudclient.model.response.EmployeeResponse;
import com.dicka.soapcrudclient.service.AddClientEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author macddl18
 */

@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {
    
    private AddClientEmployeeService addClientEmployeeService;
    public EmployeeController(AddClientEmployeeService addClientEmployeeService){
        this.addClientEmployeeService = addClientEmployeeService;
    }
    
    @PostMapping(value = "/create")
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest employeeRequest){
        return ResponseEntity.ok(this.addClientEmployeeService.execute(employeeRequest));
    }
}
