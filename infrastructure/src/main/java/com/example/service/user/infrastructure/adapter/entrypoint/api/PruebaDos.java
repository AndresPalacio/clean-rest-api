package com.example.service.user.infrastructure.adapter.entrypoint.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PruebaDos {


    @GetMapping(value = "pruebados")
    public String prueba(){
        return "prueba";
    }
}
