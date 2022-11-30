package com.dicka.soap.soapcrudoperation.websocket.controller;

import com.dicka.soap.soapcrudoperation.websocket.model.Greeting;
import com.dicka.soap.soapcrudoperation.websocket.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WebSocketController {
    
    @MessageMapping(value = "/news")
    @SendTo("/topic/news")
    public Greeting greeting(Request request) throws Exception{
        log.info("call greeting..");
        Thread.sleep(1000);
        return new Greeting("Hello "+request.getName()+" !");
    }
}
