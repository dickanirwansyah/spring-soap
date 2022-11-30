package com.dicka.soap.soapcrudoperation.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse extends BaseResponse{
    private boolean valid;
}
