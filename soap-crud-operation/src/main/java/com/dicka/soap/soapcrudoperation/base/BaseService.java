package com.dicka.soap.soapcrudoperation.base;

public interface BaseService<V extends BaseRequest, T extends BaseResponse>{

    T execute(V request);

}
