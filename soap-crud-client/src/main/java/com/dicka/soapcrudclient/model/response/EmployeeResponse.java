/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.soapcrudclient.model.response;

import com.dicka.soapcrudclient.base.CommonResponse;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author macddl18
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse extends CommonResponse{
    private Long employeeId;
    private String employeeName;
    private String employeeDepartment;
    private String employeePhoneNumber;
    private String employeeAddress;
}
