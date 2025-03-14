package com.ms.CF_DD;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class CfDdController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/say")
    public String getMethodName() {
        return "HII I am from CF-DD MS running on port: " + serverPort;
    }
}