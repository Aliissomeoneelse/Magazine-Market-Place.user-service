package com.company.userservice.controller;

import com.company.userservice.dto.TestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "test")
public class TestController {

    private final Environment environment;

    @Value(value = "${server.port}")
    private String serverPort;

    @GetMapping(value = "/get")
    public TestResponse getValue(){
        log.info(String.format("User-service port :: %%s",serverPort));
        return new TestResponse(environment.getProperty("server.port",Integer.class));
    }

}