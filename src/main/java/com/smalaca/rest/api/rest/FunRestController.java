package com.smalaca.rest.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class FunRestController {
    @GetMapping
    public String isFun() {
        return "REST is fun!";
    }
}
