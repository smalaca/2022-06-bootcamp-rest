package com.smalaca.restapp.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloRestController {

    @GetMapping("/{name}")
//    @RequestMapping(method = {RequestMethod.GET})
    public String hello(@PathVariable String name) {
        return "Hello " + name + "!";
    }
}
