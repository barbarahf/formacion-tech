package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hola mundo desde API tech U";
    }

    @RequestMapping("/hello")

    public String hello(@RequestParam(value = "name", defaultValue = "Tech U") String name) {
        return String.format("Hola %s", name);
    }
}

