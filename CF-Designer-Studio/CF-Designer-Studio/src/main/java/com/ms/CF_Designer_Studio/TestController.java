package com.ms.CF_Designer_Studio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/port")
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/read")
    public String getMethodName() {
        return "HII I am from CF-Designer-Studio MS running on port: " + serverPort;
    }
}