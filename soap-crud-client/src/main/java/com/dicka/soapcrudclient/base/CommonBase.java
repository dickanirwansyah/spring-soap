/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dicka.soapcrudclient.base;

/**
 *
 * @author macddl18
 */
public interface CommonBase <V extends CommonRequest, T extends CommonResponse>{
    
    T execute(V request);
}
