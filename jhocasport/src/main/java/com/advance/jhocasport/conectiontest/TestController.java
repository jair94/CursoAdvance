package com.advance.jhocasport.conectiontest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/ping")
    public String ping() {
        return "Conexión exitosa a jhocasport 🚀";
    }
}