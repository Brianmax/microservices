package org.example.ms2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/basic")
public class BasicController {

    @GetMapping("/aprobado")
    public String isAprobado(@RequestParam float nota) {
        if (nota >= 13) {
            return "YES";
        }
        return "NO";
    }
}
