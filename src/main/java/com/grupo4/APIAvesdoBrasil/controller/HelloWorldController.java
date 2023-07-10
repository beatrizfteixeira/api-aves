package com.grupo4.APIAvesdoBrasil.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
    // http://localhost:8080/helloworld
    @GetMapping("helloworld")
    public String sayHelloWorld() {
        return "hellooo";
    }
}
