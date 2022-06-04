package com.smalaca.restapp.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("hello")
public class HelloRestController {

    @GetMapping(value = {"", "{name}"})
//    @RequestMapping(method = {RequestMethod.GET})
    public String hello(@PathVariable Optional<String> name) {
        if (name.isEmpty()) {
            return "Hello world!";
        } else {
            return "Hello " + name.get() + "!";
        }
    }
}
