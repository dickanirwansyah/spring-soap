package com.dicka.soap.soapcrudoperation.model;

import com.dicka.soap.soapcrudoperation.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest extends BaseRequest {
    private Long id;
    private String empName;
    private String empDepartment;
    private String empPhone;
    private String empAddress;
}
