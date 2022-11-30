package com.dicka.soap.soapcrudoperation.websocket.model;

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
public class Request implements Serializable{
    private String name;
}
