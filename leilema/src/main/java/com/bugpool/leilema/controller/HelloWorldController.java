package com.bugpool.leilema.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${hello.name}")
    private String name;
    @Value("${hello.project}")
    private String project;

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello " + project + " ceo " + name;
    }
}
