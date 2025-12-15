package com.example.demo.controller;

import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class BasicController {

    @GetMapping("/hola")
    public String holaEndpoint() {
        return "Hola desde Spring Boot";
    }

    @GetMapping("/adios")
    public String adiosEndpoint() {
        return "Adios desde Spring Boot";
    }
}
