package com.dicka.soap.soapcrudoperation.model;

import com.dicka.soap.soapcrudoperation.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse extends BaseResponse {
    private Long employeeId;
    private String empName;
    private String empPhone;
    private String empAddress;
    private String empDepartment;
}
