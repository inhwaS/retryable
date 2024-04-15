package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final ExternalServiceClient serviceClient;

    public HelloController(ExternalServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @GetMapping("/")
    public String index() {
        return "index";  // Name of the Thymeleaf template without .html extension
    }

    @PostMapping("/invokeService")
    @ResponseBody
    public String invokeService() {
        try {
            serviceClient.invokeExternalService();
            return "Service Invoked Successfully";
        } catch (RuntimeException e) {
            return "Service Invocation Failed: " + e.getMessage();
        }
    }
}
